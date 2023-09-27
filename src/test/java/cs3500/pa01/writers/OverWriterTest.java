package cs3500.pa01.writers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OverWriterTest {
  OverWriter writer;
  ArrayList<String> sample;
  String path;

  @BeforeEach
  void setUp() {
    sample = new ArrayList<>();
    sample.add("Question1");
    sample.add("answer1");
    sample.add("difficutly1");
    path = "src/test/Output/OverWrite.sr";
    writer = new OverWriter(path, sample);
  }

  @Test
  void writeFile() throws IOException {
    writer.writeFile();
    assertTrue(Files.exists(Paths.get(path)));
  }
}