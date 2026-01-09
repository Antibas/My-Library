package com.antibas.games.foureqten;

import com.antibas.math.combinations.Combination;
import com.antibas.math.Math2;
import com.antibas.math.combinations.Permutation;
import com.antibas.math.combinations.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FourEqTen {

    public final static int N =4;
    public final static List<Character> operations = List.of('+', '-', '*', '/');

    public static Set<String> getSolutions(List<Integer> numbers, int goal) {
        Permutation<Integer> numberPerms = new Permutation<>(numbers, N);
        Product<Character> operationPerms = new Product<>(operations, N-1);
        Set<String> solutions = new HashSet<>();
        String sol;
        for(List<Integer> nums: numberPerms)
            for (List<Character> ops: operationPerms){
                sol = String.valueOf(nums.getFirst()) +
                        ops.getFirst() +
                        nums.get(1) +
                        ops.get(1) +
                        nums.get(2) +
                        ops.get(2) +
                        nums.get(3);
                if (Math2.eval(sol) == goal) {
                    solutions.add(sol);
                }
            }
        return solutions;
    }

    public static void main(String... args){
        System.out.println(getSolutions(List.of(2,2,7,7), 10));
    }
}
