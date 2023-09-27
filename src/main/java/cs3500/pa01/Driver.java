package cs3500.pa01;

import cs3500.pa01.filetraverse.FileFinder;
import cs3500.pa01.formatters.PrintFormat;
import cs3500.pa01.formatters.SrFormat;
import cs3500.pa01.readers.NoteExtractor;
import cs3500.pa01.writers.NewNote;
import cs3500.pa01.writers.SrCreator;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   * @throws IOException can throw one
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 3) {
      createFiles(args);
    } else if (args.length == 0) {
      studySession();
    } else {
      System.err.println("Invalid number of arguments provided");
    }

  }

  private static void createFiles(String[] args) throws IOException {
    FileFinder fileFinder = new FileFinder(args[0]);
    Utils utils = new Utils();
    Files.walkFileTree(fileFinder.targetInputDirectory, fileFinder);
    //System.out.print(fileFinder.getDirectoryFiles());
    ArrayList<Path> postSortList = utils.sortByToken(args[1], fileFinder.getDirectoryFiles());
    NoteExtractor extractor = new NoteExtractor(postSortList);
    //System.out.println(extractor.unFormatted.toString());
    PrintFormat format = new PrintFormat(extractor.unFormatted);
    //System.out.println(format.finalPrint);
    SrFormat formatSr = new SrFormat(extractor.unFormatted);
    //System.out.println(formatSr.finalSr);
    NewNote result = new NewNote(args[2], format.finalPrint);
    SrCreator srCreator = new SrCreator(args[2], formatSr.finalSr);
    //System.out.println(format.finalPrint);
    result.writeFile();
    srCreator.writeFile();
    //System.out.println(result.toWrite);

  }

  private static void studySession() throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    Controller controller = new Controller(inputStreamReader);
    controller.initSession();
    controller.numFlashCardSelection();
    controller.study();
  }
}