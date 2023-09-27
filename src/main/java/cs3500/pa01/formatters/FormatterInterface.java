package cs3500.pa01.formatters;

import java.util.ArrayList;

/**
 * Formatter interface for classes formatting the to be printed StringBuilders
 */
public interface FormatterInterface {

  /**
   * Return final format for arrayList of string builders into appropriate strings
   *
   * @return return arraylist of printable strings
   */
  ArrayList<String> format();

  /**
   * Get the post formatter result
   *
   * @return to be printed array list of strinf
   */
  ArrayList<String> getFinal();
}
