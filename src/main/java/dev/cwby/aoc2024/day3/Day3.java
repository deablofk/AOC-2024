package dev.cwby.aoc2024.day3;

import dev.cwby.aoc2024.ResourceUtils;

public class Day3 {
  private static final String CONTENT = ResourceUtils.read("day3.txt");

  public static void main(String[] args) {
    System.out.println("mulIntructionResult: " + runPart1());
  }

  ////  fuck regex
  //  public static String runPart1() {
  //    Pattern pattern = Pattern.compile("mul\\((-?\\d+),(-?\\d+)\\)");
  //    Matcher matcher = pattern.matcher(content);
  //
  //    int totalAmount = 0;
  //    while (matcher.find()) {
  //      String[] values = matcher.group().replace("mul(", "").replace(")", "").split(",");
  //      int x = Integer.parseInt(values[0].trim());
  //      int y = Integer.parseInt(values[1].trim());
  //      totalAmount += (x * y);
  //    }
  //
  //    return String.valueOf(totalAmount);
  //  }

  // yeahhh baby fuck regex
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
}
