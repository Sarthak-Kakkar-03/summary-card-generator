package purepa1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.Utils;
import cs3500.pa01.filetraverse.FileFinder;
import cs3500.pa01.readers.NoteExtractor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NoteExtractorTest {
  FileFinder fileFinder;
  Utils utils;
  NoteExtractor extractorObject;
  ArrayList<Path> sortList;

  @BeforeEach
  void init() {
    fileFinder = new FileFinder("src/test/MdFilesSet");
    utils = new Utils();
    try {
      Files.walkFileTree(fileFinder.targetInputDirectory, fileFinder);
      sortList = utils.sortByToken("filename", fileFinder.getDirectoryFiles());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // System.out.println(sortList);
    extractorObject = new NoteExtractor(sortList);

  }

  @Test
  void testInitialization() {
    //System.out.print(extractorObject.unFormatted);
    assertEquals(44, extractorObject.unFormatted.size());
  }

}