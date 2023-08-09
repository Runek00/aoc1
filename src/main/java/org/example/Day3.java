package org.example;

import java.util.Arrays;
import java.util.HashSet;

import static org.example.Input.day3Input;

public class Day3 {

    static void aoc3() {
        String[] rucksacks = day3Input.split("""

                """);
        int output = Arrays.stream(rucksacks)
                .map(Day3::getWrongItem)
                .map(Day3::priority)
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println(output);
    }

    static void aoc3a() {
        String[] rucksacks = day3Input.split("""

                """);
        int output = -1;
        HashSet<Character> badgeFinder = new HashSet<>();
        for (int i = 0; i < rucksacks.length; i++) {
            if (i % 3 == 0) {
                output += priority(badgeFinder.stream().findAny().orElse('a'));
                badgeFinder.clear();
                for (char c : rucksacks[i].toCharArray()) {
                    badgeFinder.add(c);
                }
            } else {
                HashSet<Character> repeated = new HashSet<>();
                for (char c : rucksacks[i].toCharArray()) {
                    if (badgeFinder.contains(c)) {
                        repeated.add(c);
                    }
                }
                badgeFinder = repeated;
            }


        }
        output += priority(badgeFinder.stream().findAny().orElse('a'));
        System.out.println(output);

    }

    static char getWrongItem(String rucksack) {
        HashSet<Character> charSet = new HashSet<>();
        for (int i = 0; i < rucksack.length(); i++) {
            char c = rucksack.charAt(i);
            if (i < rucksack.length() / 2) {
                charSet.add(c);
            } else {
                if (charSet.contains(c)) {
                    return c;
                }
            }
        }
        return 'a';
    }

    static int priority(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 1;
        } else {
            return c - 'A' + 27;
        }
    }
}
