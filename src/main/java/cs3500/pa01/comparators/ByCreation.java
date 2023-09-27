package cs3500.pa01.comparators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

/**
 * Comparator for to compare by the time files were created
 */
public class ByCreation implements Comparator<Path> {

  /**
   * @param file1 the first object to be compared.
   * @param file2 the second object to be compared.
   * @return a negative integer, zero, or a positive integer as the
   *     first argument created earlier than, same time as or, after
   *     the second argument
   * @throws NullPointerException if an argument is null and this
   *                              comparator does not permit null arguments
   * @throws ClassCastException   if the arguments' types prevent them from
   *                              being compared by this comparator.
   */

  @Override
  public int compare(Path file1, Path file2) {
    BasicFileAttributes attrs1;
    BasicFileAttributes attrs2;
    try {
      attrs1 = Files.readAttributes(file1, BasicFileAttributes.class);
    } catch (IOException e) {

      throw new RuntimeException("ran into an IOException");
    }
    try {
      attrs2 = Files.readAttributes(file2, BasicFileAttributes.class);
    } catch (IOException e) {

      throw new RuntimeException("ran into an IOException");

    }
    return attrs1.creationTime().compareTo(attrs2.creationTime());

  }
}


