package org.example;

import java.util.HashMap;

import static org.example.Input.day2Input;

public class Day2 {

    static void aoc2() {
        HashMap<Character, Integer> symbolPoints = new HashMap<>();
        symbolPoints.put('X', 1);
        symbolPoints.put('Y', 2);
        symbolPoints.put('Z', 3);

        Integer output = 0;
        for (String s : day2Input.split("""

                """)) {
            output += symbolPoints.get(s.charAt(2));
            if (s.equals("A Y") || s.equals("B Z") || s.equals("C X")) {
                output += 6;
            } else if (s.equals("A X") || s.equals("B Y") || s.equals("C Z")) {
                output += 3;
            }
        }
        System.out.println(output);
    }

    static void aoc2a() {
        HashMap<Character, Integer> symbolPoints = new HashMap<>();
        symbolPoints.put('X', 0);
        symbolPoints.put('Y', 3);
        symbolPoints.put('Z', 6);

        Integer output = 0;
        for (String s : day2Input.split("""

                """)) {
            output += symbolPoints.get(s.charAt(2));
            if (s.equals("B X") || s.equals("A Y") || s.equals("C Z")) {
                output += 1;
            } else if (s.equals("C X") || s.equals("B Y") || s.equals("A Z")) {
                output += 2;
            } else {
                output += 3;
            }
        }
        System.out.println(output);
    }
}
