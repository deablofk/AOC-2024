package dev.cwby.aoc2024.day5;

import dev.cwby.aoc2024.ResourceUtils;

import java.util.*;

public class Day5 {
  private static final String CONTENT = ResourceUtils.read("day5.txt");

  public static void main(String[] args) {
    System.out.println("middle page number(correctly-ordered): " + runPart1());
  }

  private static int runPart1() {
    String[] sections = CONTENT.split("\n\n");
    String[] rules = sections[0].split("\n");
    String[] updates = sections[1].split("\n");

    Map<Integer, List<Integer>> graph = new HashMap<>();

    for (String rule : rules) {
      String[] parts = rule.split("\\|");
      int from = Integer.parseInt(parts[0]);
      int to = Integer.parseInt(parts[1]);
      graph.putIfAbsent(from, new ArrayList<>());
      graph.get(from).add(to);
    }

    int sumMPages = 0;

    for (String update : updates) {
      List<Integer> pages = new ArrayList<>();
      for (String part : update.split(",")) {
        pages.add(Integer.parseInt(part));
      }

      if (isCorrectOrder(pages, graph)) {
        sumMPages += pages.get(pages.size() / 2);
      }
    }

    return sumMPages;
  }

  private static boolean isCorrectOrder(List<Integer> pages, Map<Integer, List<Integer>> graph) {
    Map<Integer, Integer> pageIndex = new HashMap<>();
    for (int i = 0; i < pages.size(); i++) {
      pageIndex.put(pages.get(i), i);
    }

    for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
      for (int to : entry.getValue()) {
        if (pageIndex.containsKey(entry.getKey())
            && pageIndex.containsKey(to)
            && pageIndex.get(entry.getKey()) >= pageIndex.get(to)) {
          return false;
        }
      }
    }

    return true;
  }
}
