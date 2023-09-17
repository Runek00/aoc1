package org.example;

import java.util.Arrays;
import java.util.List;

public class Day13 {

    static int aoc13(String input) {
        int out = 0;
        List<String[]> pairs = getPairs(input);
        for (int i = 0; i < pairs.size(); i++) {
            String[] pair = pairs.get(i);
            if (correctOrder(pair[0], pair[1]) == -1) {
                out += i + 1;
            }
        }
        return out;
    }

    private static List<String[]> getPairs(String input) {
        return Arrays.stream(input.split("\n\n"))
                .map(s -> s.split("\n"))
                .toList();
    }

    static int correctOrder(String left, String right) {
        if (!left.startsWith("[") && !right.startsWith("[")) {
            return compareNumbers(left, right);
        } else if (left.startsWith("[") && !right.startsWith("[")) {
            return correctOrder(left, "[" + right + "]");
        } else if (!left.startsWith("[") && right.startsWith("[")) {
            return correctOrder("[" + left + "]", right);
        } else {
            return compareLists(left, right);
        }
    }

    private static int compareLists(String left, String right) {

        left = left.substring(1, left.length() - 1);
        right = right.substring(1, right.length() - 1);
        if (left.isEmpty() && right.isEmpty()) {
            return 0;
        } else if (left.isEmpty()) {
            return -1;
        } else if (right.isEmpty()) {
            return 1;
        }
        int result = 0;
        while (result == 0) {
            String[] lArr = getSplit(left);
            String[] rArr = getSplit(right);
            result = correctOrder(lArr[0], rArr[0]);
            if (result != 0) {
                continue;
            }
            if (lArr.length == 1 && rArr.length == 1) {
                return result;
            }
            if (lArr.length > rArr.length) {
                return 1;
            } else if (lArr.length < rArr.length) {
                return -1;
            }
            left = lArr[1];
            right = rArr[1];
        }
        return result;
    }

    private static String[] getSplit(String str) {
        if (str.startsWith("[")) {
            int cnt = 1;
            int idx = 1;
            while (cnt > 0) {
                if(str.charAt(idx) == '[') {
                    cnt++;
                } else if (str.charAt(idx) == ']') {
                    cnt--;
                }
                idx++;
            }
            if (idx >= str.length()-1) {
                return new String[]{str};
            } else {
                return new String[]{str.substring(0, idx), str.substring(idx+1)};
            }
        } else {
            return str.split(",", 2);
        }
    }

    private static int compareNumbers(String left, String right) {
        if (left.equals(right)) {
            return 0;
        }
        if (Integer.parseInt(left) < Integer.parseInt(right)) {
            return -1;
        } else {
            return 1;
        }
    }


    static String day13Input = """
            [1,1,3,1,1]
            [1,1,5,1,1]
                        
            [[1],[2,3,4]]
            [[1],4]
                        
            [9]
            [[8,7,6]]
                        
            [[4,4],4,4]
            [[4,4],4,4,4]
                        
            [7,7,7,7]
            [7,7,7]
                        
            []
            [3]
                        
            [[[]]]
            [[]]
                        
            [1,[2,[3,[4,[5,6,7]]]],8,9]
            [1,[2,[3,[4,[5,6,0]]]],8,9]""";
}
