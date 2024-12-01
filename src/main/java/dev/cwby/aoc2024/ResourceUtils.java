package dev.cwby.aoc2024;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ResourceUtils {

  public static List<String> readLines(String resourceFile) {
    InputStream is =
        Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceFile);
    if (is == null) {
      throw new IllegalArgumentException("Resource not found: " + resourceFile);
    }
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    return reader.lines().toList();
  }
}
