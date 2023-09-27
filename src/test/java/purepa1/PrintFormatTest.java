package purepa1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.Utils;
import cs3500.pa01.filetraverse.FileFinder;
import cs3500.pa01.formatters.PrintFormat;
import cs3500.pa01.readers.NoteExtractor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrintFormatTest {
  FileFinder fileFinder;
  Utils utils;
  NoteExtractor extractorObject;
  PrintFormat printFormat;
  Random random;
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
    printFormat = new PrintFormat(extractorObject.unFormatted);
    random = new Random();

  }


  @Test
  void format() {
    String randomSentence = printFormat.finalPrint
        .get(random.nextInt(printFormat.finalPrint.size()));
    assertTrue(randomSentence.startsWith("#") || randomSentence.startsWith("-")
        || randomSentence.equals(" "));
    ArrayList<String> result =
        printFormat.removeBracket(new StringBuilder("question: \n :::\n answer"));
    //System.out.println(result);
    // System.out.println(extractorObject.unFormatted);
    //System.out.println(printFormat.finalPrint);
    // System.out.println(printFormat.unformattedList);
    // assertEquals(25, printFormat.finalPrint.size());
    //System.out.println(extractorObject.unFormatted);
    //System.out.println(printFormat.finalPrint);

  }


}