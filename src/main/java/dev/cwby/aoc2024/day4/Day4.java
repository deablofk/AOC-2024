package dev.cwby.aoc2024.day4;

import dev.cwby.aoc2024.ResourceUtils;

import java.util.List;

public class Day4 {
  private static final List<String> LINES = ResourceUtils.readLines("day4.txt");

  public static void main(String[] args) {
    System.out.println("XMAS ocurrences in all directions: " + runPart1());
  }

  public static int runPart1() {

    // populate the grid
    char[][] grid = new char[LINES.size()][];
    for (int i = 0; i < LINES.size(); i++) {
      String line = LINES.get(i);
      grid[i] = line.toCharArray();
    }

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

            boolean isValid = nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length;

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
}
