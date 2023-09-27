package cs3500.pa01.writers;

import cs3500.pa01.model.FlashCard;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Used to write sr files when creating new ones
 */
public class SrCreator implements WriterInterface {
  Path output;
  ArrayList<String> toWrite;
  FileWriter writer;

  /**
   * Creates a SR file while processing the strings into relevant flash format
   *
   * @param outputLocation Same as the md file location but with a new sr fle
   * @param toWrite The strings using ::: format as Flashcard format
   */
  public SrCreator(String outputLocation, ArrayList<String> toWrite) {
    this.output = formatPath(outputLocation);
    this.toWrite = this.convertToFlashString(toWrite);

  }

  /**
   * Formats md path to relevant .sr path
   *
   * @param toFormat Given sister md file path
   * @return new sr file path
   */
  private Path formatPath(String toFormat) {
    if (toFormat.endsWith(".md")) {
      String newPath = toFormat.substring(0, toFormat.length() - 2).concat("sr");
      return Paths.get(newPath);
    } else {
      throw new RuntimeException("The provided path is invalid");
    }
  }

  /**
   * Converts the extracted source of string to flash strings to be written
   *
   * @param init Supposed to be string using the ::: format for flashcards
   * @return Strings that use the Question, Answer, Difficulty format
   */
  private ArrayList<String> convertToFlashString(ArrayList<String> init) {
    ArrayList<String> result = new ArrayList<>();
    for (String string : init) {
      String flashString = FlashCard.newFlashcard(string).toString();
      result.add(flashString);
    }
    return result;
  }


  /**
   * Writes new sr file as per this class object
   */
  @Override
  public void writeFile() throws IOException {
    try {
      writer = new FileWriter(this.output.toFile());
      for (String sentence : this.toWrite) {
        writer.write(sentence);
      }
    } catch (IOException e) {
      System.err.println("This exception throws" + e);
      throw e;
    }
    writer.close();

  }
}
