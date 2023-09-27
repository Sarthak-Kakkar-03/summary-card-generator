package purepa1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.Utils;
import cs3500.pa01.filetraverse.FileFinder;
import cs3500.pa01.formatters.PrintFormat;
import cs3500.pa01.readers.NoteExtractor;
import cs3500.pa01.writers.NewNote;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NewNoteTest {
  FileFinder fileFinder;
  Utils utils;
  NoteExtractor extractorObject;
  PrintFormat printFormat;
  Random random;
  NewNote resultObject;

  @BeforeEach
  void init() throws IOException {
    fileFinder = new FileFinder("src/test/MdFilesSet");
    utils = new Utils();
    ArrayList<Path> sortList;
    try {
      Files.walkFileTree(fileFinder.targetInputDirectory, fileFinder);
      sortList = utils.sortByToken("filename", fileFinder.getDirectoryFiles());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // System.out.println(sortList);
    extractorObject = new NoteExtractor(sortList);
    printFormat = new PrintFormat(extractorObject.unFormatted);
    random = new Random();
    resultObject = new NewNote("src/test/Output/testResult.md",
        printFormat.finalPrint);
    resultObject.writeFile();

  }


  @Test
  void writeFile() {


    assertTrue(Files.exists(Paths.get("src/test/Output/testResult.md")));
  }
}