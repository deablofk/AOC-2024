package dev.cwby.aoc2024.day7;

import dev.cwby.aoc2024.IDay;
import dev.cwby.aoc2024.ResourceUtils;

import java.util.Arrays;
import java.util.List;

public class Day7 implements IDay {

  private final List<String> lines = ResourceUtils.readLines("day7.txt");

  public static void main(String[] args) {
    var day7 = new Day7();
    System.out.println("totalCalibrationResult: " + day7.runPart1());
    day7.runPart2();
  }

  @Override
  public long runPart1() {
    long totalCalibration = 0;
    for (String line : lines) {
      String[] operations = line.split(": ");
      long expectedResult = Long.parseLong(operations[0]);
      long[] numbers = Arrays.stream(operations[1].split(" ")).mapToLong(Long::parseLong).toArray();

      int operatorCount = numbers.length - 1;
      int combinations = 1 << operatorCount;
      for (int mask = 0; mask < combinations; mask++) {
        long tempResult = numbers[0];
        for (int i = 0; i < operatorCount; i++) {
          if ((mask & (1 << i)) == 0) {
            tempResult += numbers[i + 1];
          } else {
            tempResult *= numbers[i + 1];
          }
        }

        if (tempResult == expectedResult) {
          totalCalibration += expectedResult;
          break;
        }
      }
    }

    return totalCalibration;
  }

  @Override
  public long runPart2() {
    return 0;
  }
}
