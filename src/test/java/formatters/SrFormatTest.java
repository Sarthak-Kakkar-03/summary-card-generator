package formatters;

import static org.junit.jupiter.api.Assertions.assertFalse;

import cs3500.pa01.Utils;
import cs3500.pa01.filetraverse.FileFinder;
import cs3500.pa01.formatters.FormatterInterface;
import cs3500.pa01.formatters.SrFormat;
import cs3500.pa01.readers.NoteExtractor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SrFormatTest {

  FileFinder fileFinder;
  Utils utils;
  NoteExtractor extractorObject;
  FormatterInterface srFormat;
  Random random;
  ArrayList<Path> sortList;


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

  }

  @Test
  void formatTest() {
    if (srFormat.getFinal().size() != 0) {
      String tester = srFormat.getFinal().get(random.nextInt(srFormat.getFinal().size()));

      assertFalse(tester.startsWith("#"));
      assertFalse(tester.startsWith("- "));
      assertFalse(tester.contains("[[") && tester.contains("]]"));
    }
  }
}