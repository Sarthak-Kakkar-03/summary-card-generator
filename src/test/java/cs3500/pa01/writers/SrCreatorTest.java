package cs3500.pa01.writers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.Utils;
import cs3500.pa01.filetraverse.FileFinder;
import cs3500.pa01.formatters.FormatterInterface;
import cs3500.pa01.formatters.SrFormat;
import cs3500.pa01.readers.NoteExtractor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SrCreatorTest {

  FileFinder fileFinder;
  Utils utils;
  NoteExtractor extractorObject;
  FormatterInterface srFormat;
  Random random;
  SrCreator writer;

  @BeforeEach
  void init() {
    fileFinder = new FileFinder("src/test/MdFilesSetSR");
    utils = new Utils();
    try {
      Files.walkFileTree(fileFinder.targetInputDirectory, fileFinder);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // System.out.println(sortList);
    extractorObject = new NoteExtractor(fileFinder.getDirectoryFiles());
    srFormat = new SrFormat(extractorObject.unFormatted);
    random = new Random();
    writer = new SrCreator("src/test/Output/testResult.md", srFormat.getFinal());

  }

  @Test
  void writeFile() throws IOException {
    writer.writeFile();
    assertTrue(Files.exists(Path.of("src/test/Output/testResult.sr")));
  }
}