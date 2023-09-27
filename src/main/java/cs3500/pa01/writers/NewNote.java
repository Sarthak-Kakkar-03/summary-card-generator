package cs3500.pa01.writers;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class to create a new note using the data from md files
 */

public class NewNote implements WriterInterface {
  Path outputPath;
  ArrayList<String> toWrite;
  FileWriter writer;

  /**
   * public constructor to take a new note
   *
   * @param outputLocation the location where a file is produced
   * @param toWrite the data to be written in said file
   */

  public NewNote(String outputLocation, ArrayList<String> toWrite) {
    this.outputPath = Paths.get(outputLocation);
    this.toWrite = toWrite;

  }

  /**
   * Writes a new file using the output path and ArrayString in toWrite
   *
   * @throws IOException if file path is inaccessible
   */
  @Override
  public void writeFile() throws IOException {
    try  {
      writer = new FileWriter(this.outputPath.toFile());
      for (String sentence : this.toWrite) {
        if (!sentence.contains(":::")) {
          writer.write(sentence + "\n");
        }
      }
    } catch (IOException e) {
      System.err.println("This exception throws" + e);
      throw e;
    }
    writer.close();

  }



}
