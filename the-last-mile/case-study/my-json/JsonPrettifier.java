package org.shoupu.JsonPrettifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JsonPrettifier {

    /**
     *Thorough tokenization on string json, the end result is a tree structure of token  
     * A collection of name/value pairs. In various languages, this is realized as an object, record, struct, dictionary, hash table, keyed list, or associative array.
     * An ordered list of values. In most languages, this is realized as an array, vector, list, or sequence.
     * @param json
     * @return
     */
    static Node tokenize(String json){
        if(json == null) return null;
        json = json.trim();
        char c = json.charAt(0);
        json = json.substring(1);
        switch(c){
        case '{':
            return tokenizeObj(json);
        case '[':
            return tokenizeArr(json);
        case '"':
            return tokenizeStr(json);
        case ',':

        default:
        }
        Node root = null;
        return root;
    }

    private static Node tokenizeStr(String json) {
        int idx = json.indexOf('"');
        Node n = new Node();
        n.data = json.substring(0, idx);
        n.type = Node.EntityType.STRING;
        n.leftOver = json.substring(idx);
        return n;
    }

    private static void tokenizeArr(String json, List<Node> children) {

    }
    private static Node tokenizeArr(String json) {
        // TODO Auto-generated method stub
        return null;
    }

    private static void tokenizeObj(String json, Map<String, Node> children) {

    }

    private static Node tokenizeObj(String json) {
        Node obj = new Node();
        obj.type = Node.EntityType.OBJECT;
        Map<String, Node> children = new HashMap<String, Node>();
        while(!json.isEmpty() && json.charAt(0) != '}'){
            Node node = tokenize(json);
            json = obj.leftOver;
        }
        return null;
    }

    //Format the token-tree
    static String formatter(Node node){
        return null;
    }

    static String prettifier(String json){
        Node root = tokenize(json);
        return formatter(root);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String json = "{\"id\":\"id-123\",\"woe_id\":[123,345,435], \"attribute\":{\"title\":\"a\",\"desc\":\"b\" } }";
        System.out.println(json);
        System.out.println(prettifier(json));
    }

}

class Node {
    enum EntityType{
        OBJECT,
        ARRAY,
        STRING,
        ;
    }
    EntityType type;

    //depending on the type, children's type may be one of the following
    // Map<String, Node>
    //List<Node>
    //String as a key
    //Object as a value
    Object data;
    String leftOver;
}
