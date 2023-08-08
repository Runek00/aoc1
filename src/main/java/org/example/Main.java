package org.example;

import java.util.Arrays;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import static org.example.Input.input;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        aoc1();
        aoc2();
    }

    private static void aoc2() {
        class Elf implements Comparable<Elf>{
            final Long callories;
            final int number;

            Elf(Long callories, int number) {
                this.callories = callories;
                this.number = number;
            }
            @Override
            public int compareTo(Elf other) {
                return -this.callories.compareTo(other.callories);
            }

            @Override
            public String toString() {
                return "Elf No.: " + number + " here! I'm carrying " + callories + " callories.";
            }
        }

        PriorityQueue<Elf> pq = new PriorityQueue<>();

        for (int i = 0; i < getElfCalList().length; i++) {
            pq.add(new Elf(getElfCalList()[i], i));
        }
        long out = 0L;
        for (int i = 0; i < 3; i++) {
            Elf elf = pq.poll();
            System.out.println(elf);
            out += elf.callories;
        }
        System.out.println(out);

    }

    private static void aoc1() {
        long[] elfCalList = getElfCalList();
        int max = 0;
        for (int i = 0; i < elfCalList.length; i++) {
            if (elfCalList[i] > elfCalList[max]) {
                max = i;
            }
        }
        System.out.println(elfCalList[max]);
    }

    private static long[] getElfCalList() {
        String[] elfLoad = input.split("""


""");
        long[] elfCalList = Arrays.stream(elfLoad)
                .map(elf -> {
                    String[] snacks = elf.split("""

""");
                    return Arrays.stream(snacks).map(Long::parseLong).reduce(Long::sum);
                })
                .mapToLong(aLong -> aLong.orElse(0L))
                .toArray();
        return elfCalList;
    }
}