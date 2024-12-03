package dev.cwby.aoc2024.day3;

import dev.cwby.aoc2024.ResourceUtils;

public class Day3 {
  private static final String CONTENT = ResourceUtils.read("day3.txt");

  public static void main(String[] args) {
    System.out.println("mulIntructionResult: " + runPart1());
    System.out.println("multIntructionResultDoAndDontDo: " + runPart2());
  }

  public static int runPart1() {
    int result = 0;
    int indexPos = 0;
    while (indexPos < CONTENT.length()) {
      if (indexPos + 4 <= CONTENT.length() && CONTENT.startsWith("mul(", indexPos)) {
        indexPos += 4;
        int start = indexPos;

        while (indexPos < CONTENT.length()
            && CONTENT.charAt(indexPos) != ','
            && Character.isDigit(CONTENT.charAt(indexPos))) {
          indexPos++;
        }
        if (indexPos == start || indexPos >= CONTENT.length() || CONTENT.charAt(indexPos) != ',') {
          indexPos = start;
          continue;
        }
        int x = Integer.parseInt(CONTENT.substring(start, indexPos).trim());
        indexPos++;

        start = indexPos;

        while (indexPos < CONTENT.length()
            && CONTENT.charAt(indexPos) != ')'
            && Character.isDigit(CONTENT.charAt(indexPos))) {
          indexPos++;
        }
        if (indexPos == start || indexPos >= CONTENT.length() || CONTENT.charAt(indexPos) != ')') {
          indexPos = start;
          continue;
        }

        int y = Integer.parseInt(CONTENT.substring(start, indexPos).trim());
        result += x * y;

        indexPos++;
      } else {
        indexPos++;
      }
    }

    return result;
  }

  // look at this porra i'm gonna shut myself out
  public static int runPart2() {
    int result = 0;
    int indexPos = 0;
    boolean mulEnabled = true;

    while (indexPos < CONTENT.length()) {
      if (indexPos + 4 <= CONTENT.length() && CONTENT.startsWith("mul(", indexPos)) {
        if (mulEnabled) {
          indexPos += 4;
          int start = indexPos;

          while (indexPos < CONTENT.length()
              && CONTENT.charAt(indexPos) != ','
              && Character.isDigit(CONTENT.charAt(indexPos))) {
            indexPos++;
          }
          if (indexPos == start
              || indexPos >= CONTENT.length()
              || CONTENT.charAt(indexPos) != ',') {
            indexPos = start;
            continue;
          }
          int x = Integer.parseInt(CONTENT.substring(start, indexPos).trim());
          indexPos++;

          start = indexPos;

          while (indexPos < CONTENT.length()
              && CONTENT.charAt(indexPos) != ')'
              && Character.isDigit(CONTENT.charAt(indexPos))) {
            indexPos++;
          }
          if (indexPos == start
              || indexPos >= CONTENT.length()
              || CONTENT.charAt(indexPos) != ')') {
            indexPos = start;
            continue;
          }
          int y = Integer.parseInt(CONTENT.substring(start, indexPos).trim());
          result += x * y;

          indexPos++;
        } else {
          indexPos += 4;
        }
      } else if (CONTENT.startsWith("do()", indexPos)) {
        mulEnabled = true;
        indexPos += 4;
      } else if (CONTENT.startsWith("don't()", indexPos)) {
        mulEnabled = false;
        indexPos += 7;
      } else {
        indexPos++;
      }
    }

    return result;
  }
}
