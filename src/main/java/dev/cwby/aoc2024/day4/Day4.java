package dev.cwby.aoc2024.day4;

import dev.cwby.aoc2024.ResourceUtils;

import java.util.List;

public class Day4 {
  private static final List<String> LINES = ResourceUtils.readLines("day4.txt");

  private static final char[][] grid =
      LINES.stream().map(String::toCharArray).toArray(char[][]::new);

  public static void main(String[] args) {
    System.out.println("XMAS ocurrences in all directions: " + runPart1());
    System.out.println("MAS (X) patterns in the grid: " + runPart2());
  }

  public static int runPart1() {
    String word = "XMAS";

    // grid directions
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    int total = 0;
    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {
        for (int[] direction : directions) {
          int directionX = direction[0];
          int directionY = direction[1];
          boolean found = true;

          // XMAS (4 CHARACTERS)
          for (int i = 0; i < word.length(); i++) {
            int nextX = row + i * directionX;
            int nextY = col + i * directionY;

            boolean isValid =
                nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length;

            if (!isValid || grid[nextX][nextY] != word.charAt(i)) {
              found = false;
              break;
            }
          }

          if (found) {
            total++;
          }
        }
      }
    }

    return total;
  }

  // oh i am lazyyyeee
  // refactor (maybe someday it will happen, or no)
  public static int runPart2() {
    int total = 0;

    int rows = grid.length;
    int cols = grid[0].length;

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        if (row + 2 < rows
            && col + 2 < cols
            && grid[row][col] == 'M'
            && grid[row + 1][col + 1] == 'A'
            && grid[row + 2][col + 2] == 'S'
            && grid[row + 2][col] == 'M'
            && grid[row][col + 2] == 'S') {
          total++;
        }
        if (row + 2 < rows
            && col + 2 < cols
            && grid[row][col] == 'M'
            && grid[row + 1][col + 1] == 'A'
            && grid[row + 2][col + 2] == 'S'
            && grid[row + 2][col] == 'S'
            && grid[row][col + 2] == 'M') {
          total++;
        }
        if (row + 2 < rows
            && col + 2 < cols
            && grid[row][col] == 'S'
            && grid[row + 1][col + 1] == 'A'
            && grid[row + 2][col + 2] == 'M'
            && grid[row + 2][col] == 'M'
            && grid[row][col + 2] == 'S') {
          total++;
        }
        if (row + 2 < rows
            && col + 2 < cols
            && grid[row][col] == 'S'
            && grid[row + 1][col + 1] == 'A'
            && grid[row + 2][col + 2] == 'M'
            && grid[row + 2][col] == 'S'
            && grid[row][col + 2] == 'M') {
          total++;
        }
      }
    }
    return total;
  }
}