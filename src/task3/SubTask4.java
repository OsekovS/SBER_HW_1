package task3;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SubTask4 {
    protected static <V, K> Map<V, K> changFunc(Map<K, V> input)
    {
        Map<V, K> result = new HashMap<V, K>();
        for (K key : input.keySet()) {
            V resKey = input.get(key);
            result.put(resKey, key);
        }
        return result;
    }
    public static void main(String[] args)
    {
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put("A", 1);
        input.put("B", 2);
        input.put("C", 3);
        Map<Integer, String> output = changFunc(input);

    }
}
