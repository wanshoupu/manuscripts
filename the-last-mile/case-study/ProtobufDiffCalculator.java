package com.apple.sam.geo.jobs.utils.protobuf;

import com.google.common.collect.Maps;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Utility to determine the amount of similarity between factual and counterfactual protobufs.
 * <p>
 * Utility which determines how much two protobufs are similar to each other with tunable weights
 * given to each field compared.
 */
public class ProtobufDiffCalculator<T extends Message> {
    private static final double EXPONENT_VALUE = 2.0;
    private final Descriptors.Descriptor descriptor;

    //Map from fieldNumber to weight
    private final Map<Integer, Double> fieldNumToWeightMap = Maps.newHashMap();

    //Map from fieldNumber to calculator
    private final Map<Integer, ProtobufDiffCalculator> fieldNumToCalculatorMap
            = Maps.newHashMap();

    private DiffScoreStrategy strategy = DiffScoreStrategy.FIRST_ONLY;

    /**
     * Create a new diff calculator for the given protobuf type.
     *
     * @param descriptor Description of which protobuf messages are to be compared by this calculator.
     */
    public ProtobufDiffCalculator(Descriptors.Descriptor descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Find the difference between two protobufs by comparing the values on each field.
     * <p>
     * Find a weighted difference score that describes how different two protobufs are. This weighted
     * score is determined by comparing the two protobufs field by field and providing a weighted sum of
     * those differences.
     *
     * @param factual        The first protobuf to be compared.
     * @param counterfactual The second protobuf to be compared.
     * @return Weighted sum of the difference scores computed for each field on factual and
     * counterfactual. This is configurable via setStrategy.
     */
    public double getDiffScore(T factual, T counterfactual) {
        final Map<Integer, Double> fieldScore = getDiffScoreByField(factual, counterfactual);
        return getWeightedScore(fieldScore);
    }

    /**
     * Find the weighted difference score for a collection of protobufs.
     * <p>
     * Compare the protobufs from two collections pairwise such that the first protobuf in factual is
     * compared with the first protobuf in counterfactual and so on. Then, report the difference between
     * these two collections by summarizing the differences as a weighted sum.
     *
     * @param factual        The first collection against which protobufs in counterfactual should be compared.
     * @param counterfactual The second collection from which protobufs should be compared against
     *                       factual.
     * @return Weighted sum of the difference scores computed for each pair of protobufs in factual
     * versus counterfactual. This is configurable via setStrategy.
     */
    @SuppressWarnings("squid:MethodCyclomaticComplexity")
    public double getDiffScore(List<T> factual, List<T> counterfactual) {
        switch (strategy) {
            case EVEN_AVG:
                return getExponentiallyWeightedScore(factual, counterfactual, 1.0);
            case EXPONENTIAL_DEC:
                return getExponentiallyWeightedScore(factual, counterfactual, 1 / EXPONENT_VALUE);
            case EXPONENTIAL_INC:
                return getExponentiallyWeightedScore(factual, counterfactual, EXPONENT_VALUE);
            default: // by default everything is treated as FIRST_ONLY
                if (factual.isEmpty() || counterfactual.isEmpty()) {
                    return factual.size() == counterfactual.size() ? 0 : 1;
                }
                return getDiffScore(factual.get(0), counterfactual.get(0));
        }
    }

    /**
     * Indicate how the weighted sum describing the difference between protobufs should be calculated.
     *
     * @param strategy The strategy describing how the weigthed sum between protobufs should be
     *                 calculated in this calculator. This will overwrite any previously set strategies in this
     *                 calculator. Examples of strategies include exponential decay and exponential increase.
     */
    public void setStrategy(DiffScoreStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Describe how the difference score should be computed for a protobuf field.
     * <p>
     * Provide a strategy indicating how the difference score should be calculated for one of the fields
     * in the protobufs processed by this calculator.
     *
     * @param fieldNumber The number of the field as indicated in the underlying protobuf.
     * @param calculator  The strategy by which values of this field should be compared.
     * @param weight      The weight of this field in producing the overall difference score between
     *                    protobufs.
     */
    public void addCalculator(int fieldNumber, ProtobufDiffCalculator calculator, double weight) {
        final Descriptors.FieldDescriptor fieldDescriptor = descriptor.findFieldByNumber(fieldNumber);
        final Descriptors.Descriptor messageType = fieldDescriptor.getMessageType();
        if (!messageType.equals(calculator.getDescriptor())) {
            throw new IllegalArgumentException(
                    String.format("Failed adding calculator %s for field %s - type not match",
                            calculator, messageType.getFullName()));
        }
        fieldNumToCalculatorMap.put(fieldNumber, calculator);
        fieldNumToWeightMap.put(fieldNumber, weight);
    }

    /**
     * Summarize the collection of per-field differences scores in a single weighted sum.
     *
     * @param fieldScore Mapping from the id / field number from the protobuf to the differences
     *                   calculated on that field.
     * @return Weighted sum across all differences reported in fieldScore.
     */
    @SuppressWarnings("squid:S1244")
    protected double getWeightedScore(Map<Integer, Double> fieldScore) {
        final double sum = sum(fieldNumToWeightMap.values());
        if (sum == 0.0)
            return 0.0;

        double score = 0;
        for (int key : fieldNumToWeightMap.keySet()) {
            score += fieldScore.get(key) * fieldNumToWeightMap.get(key);
        }
        return score / sum;
    }

    private double sum(Collection<Double> values) {
        double result = 0;
        for (double d : values) {
            result += d;
        }
        return result;
    }

    /**
     * Calculate the per-field difference score between two protobufs.
     * <p>
     * Calculate by how much two protobufs are different by comparing their values field by field and
     * reporting the results based on the calculation method provided in addCalculator.
     *
     * @param factual        The first protobuf to be compared.
     * @param counterfactual The second protobuf to be compared.
     * @return Mapping from protobuf field number to the difference score describing how the values
     * were different between the two provided protobufs.
     */
    protected Map<Integer, Double> getDiffScoreByField(T factual, T counterfactual) {
        Map<Integer, Double> result = Maps.newHashMap();
        for (Descriptors.FieldDescriptor f : descriptor.getFields()) {
            final ProtobufDiffCalculator calculator
                    = fieldNumToCalculatorMap.get(f.getNumber());
            if (calculator != null) {
                double partial;
                final Object factualField = factual.getField(f);
                final Object counterfactualField = counterfactual.getField(f);
                if (f.isRepeated()) {
                    partial = calculator.getDiffScore((List) factualField, (List) counterfactualField);
                } else {
                    // Assuming all other fields are of protobuf type 'Message'
                    partial = calculator.getDiffScore((Message) factualField, (Message) counterfactualField);
                }
                result.put(f.getNumber(), partial);
            }
        }
        return result;
    }

    private double getExponentiallyWeightedScore(List<T> factual, List<T> counterfactual, double exponentValue) {
        double score = 0;
        double weightTotal = 0;
        int minSize = Math.min(factual.size(), counterfactual.size());
        int maxSize = Math.max(factual.size(), counterfactual.size());
        for (int i = 0; i < maxSize; ++i) {
            final double weight = Math.pow(exponentValue, i);
            score += weight *
                            /* the missing elements are treated as max diff score, which is 1.0*/
                    (i < minSize ? getDiffScore(factual.get(i), counterfactual.get(i)) : 1.0);
            weightTotal += weight;
        }
        return score / weightTotal; // average
    }

    public Descriptors.Descriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public String toString() {
        return descriptor.getFullName() + "DiffCalculator";
    }

    public enum DiffScoreStrategy {
        /**
         * only first element count
         */
        FIRST_ONLY,
        /**
         * all elements count equally (missing elements are padded with diff score '1.0')
         */
        EVEN_AVG,
        /**
         * weights decrease by 1/EXPONENT_VALUE (0th element weigh 1, 1st element weigh 1/2, etc.)
         */
        EXPONENTIAL_DEC,
        /**
         * weights increase by EXPONENT_VALUE (0th element weigh 1, 1st element weigh 2, etc.)
         */
        EXPONENTIAL_INC,
    }

}
