package org.example;

import java.util.*;
import java.util.concurrent.Callable;

public class Day11 {

    static Long aoc11(String input) {
        Monke[] monkeMap = parseMonke(input);
        for (long i = 0; i < 20; i++) {
            for (Monke m : monkeMap) {
                try {
                    m.turn(monkeMap, false);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        List<Long> maxVals = Arrays.stream(monkeMap)
                .map(m -> m.inspection)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .toList();
        return maxVals.get(0) * maxVals.get(1);
    }

    static Long aoc11a(String input) {
        Monke[] monkeMap = parseMonke(input);
        Long alltests = Arrays.stream(monkeMap).map(m -> (long)m.testValue).reduce(1L, (tv1, tv2) -> tv1*tv2);
        Arrays.stream(monkeMap).forEach(m -> m.allTests = alltests);
        for (long i = 0; i < 10000; i++) {
            for (Monke m : monkeMap) {
                try {
                    m.turn(monkeMap, true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        List<Long> maxVals = Arrays.stream(monkeMap)
                .map(m -> m.inspection)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .toList();
        return maxVals.get(0) * maxVals.get(1);
    }

    static String day11Input = """
            Monkey 0:
              Starting items: 96, 60, 68, 91, 83, 57, 85
              Operation: new = old * 2
              Test: divisible by 17
                If true: throw to monkey 2
                If false: throw to monkey 5
                        
            Monkey 1:
              Starting items: 75, 78, 68, 81, 73, 99
              Operation: new = old + 3
              Test: divisible by 13
                If true: throw to monkey 7
                If false: throw to monkey 4
                        
            Monkey 2:
              Starting items: 69, 86, 67, 55, 96, 69, 94, 85
              Operation: new = old + 6
              Test: divisible by 19
                If true: throw to monkey 6
                If false: throw to monkey 5
                        
            Monkey 3:
              Starting items: 88, 75, 74, 98, 80
              Operation: new = old + 5
              Test: divisible by 7
                If true: throw to monkey 7
                If false: throw to monkey 1
                        
            Monkey 4:
              Starting items: 82
              Operation: new = old + 8
              Test: divisible by 11
                If true: throw to monkey 0
                If false: throw to monkey 2
                        
            Monkey 5:
              Starting items: 72, 92, 92
              Operation: new = old * 5
              Test: divisible by 3
                If true: throw to monkey 6
                If false: throw to monkey 3
                        
            Monkey 6:
              Starting items: 74, 61
              Operation: new = old * old
              Test: divisible by 2
                If true: throw to monkey 3
                If false: throw to monkey 1
                        
            Monkey 7:
              Starting items: 76, 86, 83, 55
              Operation: new = old + 4
              Test: divisible by 5
                If true: throw to monkey 4
                If false: throw to monkey 0
            """;

    static Monke[] parseMonke(String input) {
        String[] inputParts = input.split("\n\n");
        Monke[] monkeMap = new Monke[inputParts.length];
        for (int i = 0; i < inputParts.length; i++) {
            monkeMap[i] = new Monke(inputParts[i]);
        }
        return monkeMap;
    }

    static class Monke {

        long inspection = 0;

        Queue<Long> items = new ArrayDeque<>();

        InputCall<Long> operation;

        int testValue;

        int targetTrue;

        int targetFalse;

        Long allTests;

        Monke(String input) {
            String[] rows = input.split("\n");
            Arrays.stream(rows[1].trim().split("Starting items: ")[1].split(", "))
                    .map(Long::parseLong)
                    .forEach(item -> items.add(item));

            operation = getOperation(rows[2].split(" = ")[1]);
            testValue = Integer.parseInt(rows[3].split(" by ")[1]);
            targetTrue = Integer.parseInt(rows[4].split("monkey ")[1]);
            targetFalse = Integer.parseInt(rows[5].split("monkey ")[1]);
        }

        void addItem(long item) {
            this.items.add(item);
        }

        void turn(Monke[] monkeMap, boolean strongMonke) throws Exception {
            while (!items.isEmpty()) {
                Long item = items.poll();
                operation.item = item;
                item = operation.call();
                inspection++;
                if (!strongMonke){
                    item /= 3;
                }
                if (allTests != null) {
                    item = item%allTests;
                }
                if (item % testValue == 0) {
                    monkeMap[targetTrue].addItem(item);
                } else {
                    monkeMap[targetFalse].addItem(item);
                }
            }
        }
    }

    abstract static class InputCall<T> implements Callable<T> {
        long item;
    }

    private static InputCall<Long> getOperation(String oper) {
        if (oper.equals("old * old")) {
            return new InputCall<>() {
                @Override
                public Long call() {
                    return item * item;
                }
            };
        } else if (oper.equals("old + old")) {
            return new InputCall<>() {
                @Override
                public Long call() {
                    return 2 * item;
                }
            };
        } else if (oper.contains("*")) {
            long param = Long.parseLong(oper.split(" \\* ")[1]);
            return new InputCall<>() {
                @Override
                public Long call() {
                    return item * param;
                }
            };
        } else if (oper.contains("+")) {
            long param = Long.parseLong(oper.split(" \\+ ")[1]);
            return new InputCall<>() {
                @Override
                public Long call() {
                    return item + param;
                }
            };
        }
        return null;
    }
}
