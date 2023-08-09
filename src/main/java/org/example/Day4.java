package org.example;

import java.util.Arrays;

import static org.example.Input.day4Input;

public class Day4 {

    static void aoc4() {
        String[] pairs = day4Input.split("""
                                
                """);

        Long output = Arrays.stream(pairs)
                .filter(pair -> {
                    String left = pair.split(",")[0];
                    String right = pair.split(",")[1];
                    int leftStart = Integer.parseInt(left.split("-")[0]);
                    int leftEnd = Integer.parseInt(left.split("-")[1]);
                    int rightStart = Integer.parseInt(right.split("-")[0]);
                    int rightEnd = Integer.parseInt(right.split("-")[1]);
                    return (leftStart >= rightStart && leftEnd <= rightEnd) ||
                            (leftStart <= rightStart && leftEnd >= rightEnd);
                })
                .count();
        System.out.println(output);
    }

    static void aoc4a() {
        String[] pairs = day4Input.split("""
                                
                """);

        Long output = Arrays.stream(pairs)
                .filter(pair -> {
                    String left = pair.split(",")[0];
                    String right = pair.split(",")[1];
                    int leftStart = Integer.parseInt(left.split("-")[0]);
                    int leftEnd = Integer.parseInt(left.split("-")[1]);
                    int rightStart = Integer.parseInt(right.split("-")[0]);
                    int rightEnd = Integer.parseInt(right.split("-")[1]);
                    return (leftStart >= rightStart && leftStart <= rightEnd) ||
                            (leftEnd >= rightStart && leftEnd <= rightEnd) ||
                            (rightStart >= leftStart && rightStart <= leftEnd) ||
                            (rightEnd >= leftStart && rightEnd <= leftEnd);
                })
                .count();
        System.out.println(output);
    }
}
