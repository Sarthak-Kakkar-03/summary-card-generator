package cs3500.pa01.readers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to extract the files as string-builders
 */
public class NoteExtractor {
  private final ArrayList<Path> sortedPaths;
  /**
   * All the data in all files as a string builder
   */
  public ArrayList<StringBuilder> unFormatted;
  File file;


  public NoteExtractor(ArrayList<Path> sortedPaths) {
    this.sortedPaths = sortedPaths;
    this.unFormatted = readText();
  }

  /**
   * @return a new ArrayList of string builder that reads all the lines in previously sorted
   *     list of paths. Gets initialized to unformatted since the unnecessary data hasn't
   *     been removed yet.
   */
  private ArrayList<StringBuilder> readText() {
    ArrayList<String> resultString = new ArrayList<>();
    ArrayList<StringBuilder> result = new ArrayList<>();
    for (Path path : this.sortedPaths) {
      file = path.toFile();
      try {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
          resultString.add(scanner.nextLine());
        }
      } catch (IOException e) {
        System.err.println("Exception Ocurred: " + e);
        throw new RuntimeException("an IOException occured");
      }
    }

    for (String line : resultString) {
      result.add(new StringBuilder(line));
    }
    return result;
  }

}
