package org.example;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class Day10 {

    static Long aoc10(String input) {
        int cycle = 1;
        long X = 1L;
        Iterator<Long> cs = commandStream(input);
        int commandCounter = 0;
        Long n = 0L;
        long output = 0L;
        while (cycle < 221) {
            commandCounter--;
            cycle++;
            if (commandCounter <= 0) {
                if (n != null) {
                    X += n;
                    n = null;
                }
                if (cs.hasNext()) {
                    n = cs.next();
                    commandCounter += n == null ? 1 : 2;
                }
            }
            if ((cycle - 20) % 40 == 0) {
                output += cycle * X;
            }
        }
        return output;
    }

    static String aoc10a(String input) {
        long X = 1L;
        Iterator<Long> cs = commandStream(input);
        int commandCounter = 0;
        Long n = 0L;
        StringBuilder out = new StringBuilder();
        for (int cycle = 0; cycle < 241; cycle++) {
            commandCounter--;
            if (cycle%40 == X || cycle%40 == X-1 || cycle%40 == X+1) {
                out.append("#");
            } else {
                out.append(".");
            }
            if (commandCounter <= 0) {
                if (n != null) {
                    X += n;
                    n = null;
                }
                if (cs.hasNext()) {
                    n = cs.next();
                    commandCounter += n == null ? 1 : 2;
                }
            }
            if ((cycle+1)%40 == 0) {
                out.append("\n");
            }
        }
        return out.toString();
    }

    private static Iterator<Long> commandStream(String input) {
        return Arrays.stream(input.split("\n"))
                .map(s -> Objects.equals(s, "noop") ? null : Long.parseLong(s.split(" ")[1]))
                .iterator();
    }

    static String day10Input = """
            noop
            addx 5
            noop
            noop
            noop
            addx 1
            addx 2
            addx 5
            addx 2
            addx 5
            noop
            noop
            noop
            noop
            noop
            addx -12
            addx 18
            addx -1
            noop
            addx 3
            addx 5
            addx -5
            addx 7
            noop
            addx -36
            addx 18
            addx -16
            noop
            noop
            noop
            addx 5
            addx 2
            addx 5
            addx 2
            addx 13
            addx -6
            addx -4
            addx 5
            addx 2
            addx 4
            addx -3
            addx 2
            noop
            addx 3
            addx 2
            addx 5
            addx -40
            addx 25
            addx -22
            addx 25
            addx -21
            addx 5
            addx 3
            noop
            addx 2
            addx 19
            addx -10
            addx -4
            noop
            addx -4
            addx 7
            noop
            addx 3
            addx 2
            addx 5
            addx 2
            addx -26
            addx 27
            addx -36
            noop
            noop
            noop
            noop
            addx 4
            addx 6
            noop
            addx 12
            addx -11
            addx 2
            noop
            noop
            noop
            addx 5
            addx 5
            addx 2
            noop
            noop
            addx 1
            addx 2
            addx 5
            addx 2
            addx 1
            noop
            noop
            addx -38
            noop
            addx 9
            addx -4
            noop
            noop
            addx 7
            addx 10
            addx -9
            addx 2
            noop
            addx -9
            addx 14
            addx 5
            addx 2
            addx -24
            addx 25
            addx 2
            addx 5
            addx 2
            addx -30
            addx 31
            addx -38
            addx 7
            noop
            noop
            noop
            addx 1
            addx 21
            addx -16
            addx 8
            addx -4
            addx 2
            addx 3
            noop
            noop
            addx 5
            addx -2
            addx 5
            addx 3
            addx -1
            addx -1
            addx 4
            addx 5
            addx -38
            noop
            """;

}
