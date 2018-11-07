    ...
    List<Animal> collection = ...
    collection.add(constructAnimal(sound));
    ...
}

private Animal constructAnimal(sound) {
    if (sound == null)
        return new SilentAnimal();
    if (soundsLikeTrumpet(sound))
        return new Elephant();
    if (soundsLikeNeigh(sound))
        return new Horse();
    amplifier = new Amplifier();
    if (soundsLikeHiss(amplifier.apply(sound)))
        return new Snake();
    if (soundsLikeBuzz(amplifier.apply(sound)))
        return new Bee();
    return new UnknownAnimal();
}
