package dev.cwby.aoc2024;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceUtils {

  public static Stream<String> readLinesAsStream(String resourceFile) {
    InputStream is =
        Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceFile);
    if (is == null) {
      throw new IllegalArgumentException("Resource not found: " + resourceFile);
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    return reader.lines();
  }

  public static List<String> readLines(String resourceFile) {
    return readLinesAsStream(resourceFile).toList();
  }

  public static String read(String resourceFile) {
    return readLinesAsStream(resourceFile).collect(Collectors.joining("\n"));
  }
}
