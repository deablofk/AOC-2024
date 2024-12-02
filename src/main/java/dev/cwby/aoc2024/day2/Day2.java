package dev.cwby.aoc2024.day2;

import dev.cwby.aoc2024.ResourceUtils;

import java.util.List;

public class Day2 {

  private static final List<String> lines = ResourceUtils.readLines("day2.txt");

  public static void main(final String[] args) {
    System.out.println("safeReports: " + runPart1());
    System.out.println("safeReports(Dampener):" + runPart2());
  }

  // maybe using IntStream is better for this method
  public static boolean isSafeReport(final String[] numbers) {
    boolean isIncreasing = true;
    boolean isDecreasing = true;

    for (int i = 1; i < numbers.length; i++) {
      final int diff = Integer.parseInt(numbers[i]) - Integer.parseInt(numbers[i - 1]);
      final int unsignedDiff = Math.abs(diff);

      if (unsignedDiff < 1 || unsignedDiff > 3) return false;

      if (diff < 0) {
        isIncreasing = false;
      } else if (diff > 0) {
        isDecreasing = false;
      }
    }

    return isIncreasing || isDecreasing;
  }

  public static boolean isSafeWithDampener(final String[] numbers) {
    if (isSafeReport(numbers)) return true;

    for (int i = 0; i < numbers.length; i++) {
      final String[] modifiedNumbers = new String[numbers.length - 1];
      System.arraycopy(numbers, 0, modifiedNumbers, 0, i);
      System.arraycopy(numbers, i + 1, modifiedNumbers, i, numbers.length - i - 1);

      if (isSafeReport(modifiedNumbers)) return true;
    }

    return false;
  }

  public static long runPart2() {
    return lines.stream().map(x -> x.split(" ")).filter(Day2::isSafeWithDampener).count();
  }

  public static long runPart1() {
    return lines.stream().map(x -> x.split(" ")).filter(Day2::isSafeReport).count();
  }
}
