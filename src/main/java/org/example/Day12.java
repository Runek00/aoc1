package org.example;

import java.util.ArrayList;
import java.util.List;

public class Day12 {

    static Long aoc12(String input) {
        char[][] heightMap = getHeightMap(input);
        int[] start = getStart(heightMap);
        int[] end = getEnd(heightMap);
        boolean[][] visited = new boolean[heightMap.length][heightMap[0].length];
        long[][] bestRes = new long[heightMap.length][heightMap[0].length];

        return dfs(heightMap, start, end, visited, bestRes, 'a', 0L)-1;
    }

    static Long aoc12a(String input) {
        char[][] heightMap = getHeightMap(input);
        int[] end = getEnd(heightMap);
        List<int[]> as = getAs(heightMap);
        boolean[][] visited = new boolean[heightMap.length][heightMap[0].length];
        long[][] bestRes = new long[heightMap.length][heightMap[0].length];

        long output = Long.MAX_VALUE;
        for (int[] start : as) {
            output = Math.min(output, dfs(heightMap, start, end, visited, bestRes, 'a', 0L)-1);
        }
        return output;
    }

    private static char[][] getHeightMap(String input) {
        String[] splitInput = input.split("\n");
        int x = splitInput.length;
        int y = splitInput[0].length();
        char[][] output = new char[x][y];
        for(int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                output[i][j] = splitInput[i].charAt(j);
            }
        }
        return output;
    }

    private static int[] getStart(char[][] heightMap) {
        return findChar(heightMap, 'S');
    }

    private static int[] getEnd(char[][] heightMap) {
        return findChar(heightMap, 'E');
    }

    private static int[] findChar(char[][] heightMap, char c) {
        for (int i = 0; i < heightMap.length; i++) {
            for(int j = 0; j < heightMap[0].length; j++) {
                if (heightMap[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
    private static List<int[]> getAs(char[][] heightMap) {
        List<int[]> out = new ArrayList<>();
        for (int i = 0; i < heightMap.length; i++) {
            for(int j = 0; j < heightMap[0].length; j++) {
                if (heightMap[i][j] == 'a' || heightMap[i][j] == 'S') {
                    out.add(new int[]{i, j});
                }
            }
        }
        return out;
    }

    private static long dfs(char[][] heightMap, int[] pos, int[] end, boolean[][] visited, long[][] bestRes, char prevHeight, long step) {
        if (prevHeight == 'S') {
            prevHeight = 'a';
        }
        if (pos[0] < 0 || pos[0] >= heightMap.length || pos[1] < 0 || pos[1] >= heightMap[0].length) {
            return Long.MAX_VALUE;
        }
        if (visited[pos[0]][pos[1]]) {
            return Long.MAX_VALUE;
        }
        if (bestRes[pos[0]][pos[1]] > 0 && step >= bestRes[pos[0]][pos[1]]) {
            return Long.MAX_VALUE;
        }
        char curHeight = heightMap[pos[0]][pos[1]];
        if (curHeight == 'E') {
            curHeight = 'z';
        }
        if (prevHeight + 1 < curHeight) {
            return Long.MAX_VALUE;
        }
        if (pos[0] == end[0] && pos[1] == end[1]) {
            return 1L;
        }
        visited[pos[0]][pos[1]] = true;
        bestRes[pos[0]][pos[1]] = step;
        long r1 = dfs(heightMap, new int[]{pos[0] - 1, pos[1]}, end, visited, bestRes, curHeight, step + 1);
        long r2 = dfs(heightMap, new int[]{pos[0] + 1, pos[1]}, end, visited, bestRes, curHeight, step + 1);
        long r3 = dfs(heightMap, new int[]{pos[0], pos[1] - 1}, end, visited, bestRes, curHeight, step + 1);
        long r4 = dfs(heightMap, new int[]{pos[0], pos[1] + 1}, end, visited, bestRes, curHeight, step + 1);
        visited[pos[0]][pos[1]] = false;
        long result = Math.min(Math.min(Math.min(r1, r2), r3), r4);
        if (result < Long.MAX_VALUE) {
            result++;
        }
        return result;
    }


    static String day12Input = """
            abaaaaacccccccccccccccccccccccccccccccccccccccaaaaaaaccccaaaaaaaaaaaaaaaaacccccaaaaaacccccccccccccccccccccccaaaaaaaaccccccccccccccccccccccccccccccccaaaaaa
            abaaaaaacccaaaacccccccccccccccccccccccaccccccccaaaaaaaaccaaaaaaaaaaaaaaaaccccccaaaaaacccccccccccccccccccccccccaaaaccccccccccccccccccccccccccccccccccaaaaaa
            abaaaaaacccaaaacccccccccccccccccaaaaaaaacccccccaaaaaaaaacaaaaaaaaaaaaacccccccccaaaaacccccccccccccccccccccccccaaaaacccccccccccccccccccaaaccccccccccccaaaaaa
            abaaacaccccaaaaccccccccccccccccccaaaaaacccccccccaaaaaaaccccaaaaaaaaaaacccccccccaaaaacccccccccccccccccccccccccaacaaaccccccccccccccccccaaacccccccccccccccaaa
            abaaacccccccaaacccccccccccaacccccaaaaaaccccccccaaaaaaccccccaacaaaaaaaacccccccccccccccccccccccaaccccccccccccccacccaaaaacccccccccaaccccaaacccccccccccccccaaa
            abccccccccccccccccccccccccaaaaccaaaaaaaacccccccaaaaaaaccccccccaaaaaaaaaccccccccccaacccccccccaaaccccccccccccccccccacaaacccccccccaaaaccaaacccccccccccccccaac
            abccccccccccccccccccccccaaaaaacaaaaaaaaaaccccccaaccaaaaacccccaaaaccaaaaccccccccccaaacaacccccaaacaaacccaaccccccccaaaaaaaacccccccaaaaakkkkkkcccccccccccccccc
            abccccccccccccccccccccccaaaaaccaaaaaaaaaacccccccccccaaaaaaccccacccaaaaaccccccccccaaaaaaccaaaaaaaaaaaaaaaccccccccaaaaaaaaccccccccaaajkkkkkkkaccccccaacccccc
            abcccccccccccccccccccccccaaaaacacacaaaccccccccccccccaaaaaaccccccccaaaacccccccccaaaaaaacccaaaaaaaaaaaaaaaaaccccccccaaaaaccccccccccjjjkkkkkkkkccaaaaaacccccc
            abcccccccccccccccccccccccaacaacccccaaacccaccccccccccaaaaaaccccccccaaaacccccccccaaaaaaacccccaaaaaacaaaaaaaacccccccaaaaacccccccjjjjjjjooopppkkkcaaaaaaaccccc
            abcccccccccccccccccaacaacccccccccccaaaaaaacccccccccccaaaaacccccccccccccccccccccccaaaaaaccccaaaaaaccaaaaaaacccccccaaaaaacciijjjjjjjjoooopppkkkcaaaaaaaacccc
            abccccccccccaaaccccaaaaacccccccccccccaaaaacccccccccccaaaaccccccccccccccccccccccccaacaaaccccaaaaaaacaaaaacccccccccaccaaaciiiijjjjjjoooopppppkllcaaaaaaacccc
            abccaaccccccaaaaacaaaaacccccccccccccaaaaaacccccccccccccccccccccccccccccccccccccccaacccccccaaaacaaaaaaaaacccaaccccaaaaaciiiiinoooooooouuuupplllaaaaaacccccc
            abcaaacccccaaaaaacaaaaaacccccccccccaaaaaaaaccccccccaacaccccccccccccccccccccccccccccccccccccaccccccccccaaccaaaccccaaaaaciiinnnooooooouuuuuppplllaaacacccccc
            abaaaaaacccaaaaaacccaaaacccccccccccaaaaaaaaccccccccaaaaccccccccccccccccccccccccccccccccccccccccccccccccaaaaacaacaaaaaaiiinnnnntttoouuuuuupppllllcccccccccc
            abaaaaaaccccaaaaacccaaccccccccccacccccaaccccccccccaaaaaccccccccccccccccccccccccccccccccccccccccccccccccaaaaaaaacaaaaaaiiinnnnttttuuuuxxuuupppllllccccccccc
            abaaaaacccccaacaaccccccccccccccaaaccccaacccccaacccaaaaaacccccccccccccccccccccccccccccccccccccccccccccccccaaaaaccaaaaaaiiinnnttttxxuuxxyyuuppppllllcccccccc
            abaaaacccccccccccccccccccccaaacaaaccccccaaacaaaaccacaaaacccccccccccccccccccccccccccccccccccaacccccccccccaaaaaccccaaaccciinnntttxxxxxxxyyvvvqqqqqlllccccccc
            abaaaaaccccccccccccccccccccaaaaaaaaaacccaaaaaaacccccaaccccccccccccccccccccccccccccccccccccaaacccccccccccaacaaaccccccccciiinntttxxxxxxxyyvvvvvqqqqljjcccccc
            abccaaaccaccccccccaaacccccccaaaaaaaaaccccaaaaaacccccccccccccccccccccccccccccccaacccccccaaaaacaaccccccccccccaacccccccccchhinnnttxxxxxxyyyyyvvvvqqqjjjcccccc
            SbccccaaaacccccccaaaaaacccccccaaaaaccccccaaaaaaaaccccccccccccccccccccaaccccccaaaaccccccaaaaaaaacccccccccccccccccccccccchhhnnntttxxxxEzyyyyyvvvqqqjjjcccccc
            abccccaaaacccccccaaaaaaccccccaaaaaacccccaaaaaaaaaacccccccccccccccccccaaccccccaaaaccccccccaaaaacccccccccccccccccccccccccchhhnntttxxxyyyyyyyvvvvqqqjjjcccccc
            abcccaaaaaaccccccaaaaaacccccaaaaaaaccccaaaaaaaaaacccccccccccccccccaaaaaaaacccaaaacccccccaaaaaccccccccccccccccccccccccccchhmmmttxxxyyyyyyvvvvvqqqjjjdcccccc
            abcccaaaaaacccccccaaaaacccccaaacaaacaaaaaaaaaaccccccccccccaaacccccaaaaaaaaccccccccccccccaacaaacccccccaacaaacccccccccccchhhmmmtswwwyyyyyyvvvqqqqjjjjdddcccc
            abcccccaacccccccccaacaacccccccccccacaaaaaccaaaccccccccccaaaaacccccccaaaacccccccccccccccccccaaccccccccaaaaaacccccccccccchhhmmssswwwwwwyyywvrqqqjjjjdddccccc
            abcccccccccccccccccccccccccccccccccaaaaaccccaaccccccccacaaaaaacccccaaaaacccccccccccccccccccccccccccccaaaaaacccccccccccchhhmmssswwwwwwywywwrrqjjjjddddccccc
            abcccccccccccccccccccccccccccccccccaaaaaccccccccaaacaaacaaaaaacccccaaaaaaccccccccccccccccccccccccccccaaaaaaaccccccccccchhmmmsssswwsswwwwwwrrkkjjddddcccccc
            abccccccccccccccccccccccccccccccccccaaaaacccccccaaaaaaacaaaaaccccccaaccaacccccccccccaaccccccccccccccaaaaaaaacaacaaccccchhhmmmsssssssswwwwrrrkkjddddaaccccc
            abcccccccccccccccccccccccccaaaaaccccaacccccccccccaaaaaacaaaaacccccccccccccaacccccccaaaaaacccccccccccaaaaaaaacaaaaaccccchhgmmmmssssssrrwwwrrrkkddddaaaccccc
            abcccccccccccccccccccccccccaaaaacccccccccccccccccaaaaaaaacccccccccccccccaaaaaaccccccaaaaaccccaaccccccccaaacccaaaaaaccccgggmmmmmmllllrrrrrrrkkkeedaaaaccccc
            abcccccccccccaaccccccccccccaaaaaacccccccccccccccaaaaaaaaacccccccccccccccaaaaaaccccaaaaaaacccaaaacccccccaaccccaaaaaaccccggggmmmmllllllrrrrrkkkkeedaaaaacccc
            abcccccccccccaaacaacaaaccccaaaaaaccccccccccccccaaaaaaaaaacccccccccccccccaaaaaaccccaaaaaaaaccaaaacccccccccccccaaaaaccccccgggggglllllllllrrkkkkeeeaaaaaacccc
            abcccccccccccaaaaaacaaaacccaaaaaaccccccccccccccaaacaaaaaaccccccccccccccccaaaaaccccaaaaaaaaccaaaacccccccccccaaccaaaccccccgggggggggffflllkkkkkkeeeaaaaaacccc
            abaccccccccaaaaaaaccaaaacccccaaacccccccccccccccccccaaaaaacaccccccccaaccccaaaacccccccaaacacccccccccccccccaaaaaccccccccccccccgggggffffflllkkkkeeeccaaacccccc
            abaccccccccaaaaaaaccaaacccccccccccccccccaaaccccccccaaacaaaaaccccccaaacccccccccccccaaaacccccccccccccccccccaaaaaccccccccccccccccccaffffffkkkeeeeeccaaccccccc
            abaaaccccccccaaaaaaccccccccccccccccccccaaaaaacccccccaaaaaaaacaaaacaaacccccccccaaaaaacccccccccccccccccccccaaaaaccccccccccccccccccccaffffffeeeeecccccccccccc
            abaacccccccccaacaaaccccccccccccccccccccaaaaaaccccccccaaaaaccaaaaaaaaacccccccccaaaaaaaaccccccccccaaccccccaaaaacccccccccccccccccccccaaaffffeeeecccccccccccaa
            abaacccccccccaaccccccccccccccccaaccccccaaaaacaaccaacccaaaaacaaaaaaaaacccccccccaaaaaaaaccccccaaacaacccccccccaacccccccccccccccccccccaaaccceaecccccccccccccaa
            abaacccccccccccccccccccccccccccaaaaaacccaaaaaaaaaaaccaaacaaccaaaaaaaaaaaaacccccaaaaaaacccccccaaaaaccccccccccccccccccccccccccccccccaaacccccccccccccccaaacaa
            abcccccccccccccccccccccccccccccaaaaaccccaacaacaaaaacccaaccccccaaaaaaaaaaaacccccaaaaacccccccccaaaaaaaccccccccccccccccccccccccccccccaaacccccccccccccccaaaaaa
            abcccccccccccccccccccccccccccaaaaaaaccccccccaaaaaaaaccccccccccaaaaaaaaaaccccccaaaaaaccccccccaaaaaaaaccccccccccccccccccccccccccccccccccccccccccccccccaaaaaa
            """;
}
