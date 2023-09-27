package cs3500.pa01.writers;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class to overwrite the sr save files after study session
 */

public class OverWriter implements WriterInterface {
  private Path path;
  private ArrayList<String> write;

  public OverWriter(String path, ArrayList<String> write) {
    this.path = Paths.get(path);
    this.write = write;
  }

  /**
   * Overwrites the file as the path of this class
   *
   * @throws IOException if file wasn't found
   */
  @Override
  public void writeFile() throws IOException {
    FileWriter writer;
    try {
      writer = new FileWriter(this.path.toFile());
      for (String sentence : this.write) {
        writer.write(sentence);
      }
    } catch (IOException e) {
      System.err.println("This exception throws" + e);
      throw e;
    }
    writer.close();

  }
}
