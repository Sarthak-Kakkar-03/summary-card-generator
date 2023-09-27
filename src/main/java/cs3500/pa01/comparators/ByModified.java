package cs3500.pa01.comparators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;


/**
 * Comparator to compare by the time last modified
 */
public class ByModified implements Comparator<Path> {
  /**
   * @param file1 the first object to be compared.
   * @param file2 the second object to be compared.
   * @return A negative integer, Zero, or positive integer depending on
   *     the fact if the first argument was last modified before, at the same
   *     time, or after the second argument.
   */
  public int compare(Path file1, Path file2) {
    BasicFileAttributes attrs1;
    BasicFileAttributes attrs2;
    try {
      attrs1 = Files.readAttributes(file1, BasicFileAttributes.class);
    } catch (IOException e) {

      throw new RuntimeException("failed");
    }
    try {
      attrs2 = Files.readAttributes(file2, BasicFileAttributes.class);
    } catch (IOException e) {

      throw new RuntimeException("failed");

    }
    return attrs1.lastModifiedTime().compareTo(attrs2.lastModifiedTime());

  }
}
