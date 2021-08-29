package task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

public class SubTask3
{
    static <T> Collection<T> removeDuplicates(Collection<T> collection)
    {
        return new TreeSet<>(collection);
    }
    public static void main(String[] args) {
        Integer[] inArr = new Integer[]{1, 2, 1, 4, 3, 6, 2};
        Collection<Integer> input = Arrays.asList(inArr);
        System.out.println(removeDuplicates(input));
    }
}
