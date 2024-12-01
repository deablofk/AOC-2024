package dev.cwby.aoc2024.day1;

import dev.cwby.aoc2024.ResourceUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day1 {

  private static final List<String> lines = ResourceUtils.readLines("day1.txt");

  public static void main(String[] args) {
    System.out.println("totalDistance: " + runPart1());
    System.out.println("totalScoreSimilarity: " + runPart2());
  }

  public static String runPart1() {
    int[] list1 = new int[lines.size()];
    int[] list2 = new int[lines.size()];

    for (int i = 0; i < lines.size(); i++) {
      String[] vars = lines.get(i).split("\\s+");
      list1[i] = Integer.parseInt(vars[0]);
      list2[i] = Integer.parseInt(vars[1]);
    }

    Arrays.sort(list1);
    Arrays.sort(list2);

    int totalDistance = 0;
    for (int i = 0; i < list1.length; i++) {
      totalDistance += Math.abs(list1[i] - list2[i]);
    }

    return String.valueOf(totalDistance);
  }

  public static String runPart2() {
    var lefts = new int[lines.size()];
    var rightMap = new HashMap<Integer, Integer>();

    for (int i = 0; i < lines.size(); i++) {
      String[] vars = lines.get(i).split("\\s+");
      rightMap.merge(Integer.parseInt(vars[1]), 1, Integer::sum);
      lefts[i] = Integer.parseInt(vars[0]);
    }

    int totalScore =
        Arrays.stream(lefts).map(x -> x * rightMap.getOrDefault(x, 0)).reduce(0, Integer::sum);

    return String.valueOf(totalScore);
  }
}
