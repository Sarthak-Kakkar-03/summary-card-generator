package cs3500.pa01.comparators;

import java.nio.file.Path;
import java.util.Comparator;

/**
 * Comparator to compare by the lexographical order
 */
public class ByName implements Comparator<Path> {


  /**
   * Compares its two arguments for order.  Returns a negative integer,
   * zero, or a positive integer as the first argument is less than, equal
   * to, or greater than the second.
   *
   * @param file1 the first object to be compared.
   * @param file2 the second object to be compared.
   * @return a negative integer, zero, or a positive integer as the
   *     first argument is lexicographically earlier, at the same position
   *     or after the second argument
   * @throws NullPointerException if an argument is null and this
   *                              comparator does not permit null arguments
   * @throws ClassCastException   if the arguments' types prevent them from
   *                              being compared by this comparator.
   */
  @Override
  public int compare(Path file1, Path file2) {
    String name1 = file1.getFileName().toString().toUpperCase();
    String name2 = file2.getFileName().toString().toUpperCase();
    return name1.substring(0, 1).compareTo(name2.substring(0, 1));
  }
}
