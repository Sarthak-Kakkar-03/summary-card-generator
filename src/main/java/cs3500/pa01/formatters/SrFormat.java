package cs3500.pa01.formatters;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Used to format as per the SR files we need
 */
public class SrFormat implements FormatterInterface {
  private final ArrayList<StringBuilder> unformattedList = new ArrayList<>();
  public ArrayList<String> finalSr;
  private PrintFormat printFormat;

  /**
   * Formats the extracted data
   *
   * @param unformattedList Is the data that wasn't formatted as per need question-answer clauses
   */
  public SrFormat(ArrayList<StringBuilder> unformattedList) {
    printFormat = new PrintFormat(unformattedList);
    ArrayList<StringBuilder> processedList = returnBuilder(printFormat.finalPrint);
    this.unformattedList.addAll(processedList);
    this.finalSr = format();
  }

  /**
   * Filters out all the lines without a question parameter clause
   *
   * @param list the list it will remove all non-questions from
   */
  private static void filter(ArrayList<StringBuilder> list) {
    list.removeIf(stringBuilder -> !stringBuilder.toString().contains(":::"));
  }

  /**
   * removes all punctuation
   *
   * @param sentence is a string builder for the punctuation ot be removed form
   * @return an arrayList of string after removing punctuation
   */
  private static String remPunctuation(StringBuilder sentence) {
    SrFormat.remHash(sentence);
    SrFormat.remDash(sentence);
    return sentence.toString()
        .replace("[[", "").replace("]]", "");
  }

  /**
   * Take a string builder and remove the #'s
   */
  private static void remHash(StringBuilder sentence) {
    int sum = 0;
    for (int i = 0; i < sentence.length(); i++) {
      if (sentence.charAt(i) == '#') {
        sum++;
      } else {
        break;
      }
    }
    sentence.delete(0, sum);
  }

  /**
   * removes the bullet dash
   */
  private static void remDash(StringBuilder sentence) {
    if (sentence.toString().startsWith("- ")) {
      sentence.delete(0, 2);
    }
  }
  
  /**
   * Return final format for arrayList of string builders into appropriate strings
   * For sr files they need to have all
   *
   * @return return arraylist of printable strings
   */
  @Override
  public ArrayList<String> format() {
    SrFormat.filter(unformattedList);
    ArrayList<String> result = new ArrayList<>();
    for (StringBuilder builder : unformattedList) {
      result.add(SrFormat.remPunctuation(builder));
    }
    return result;
  }

  @Override
  public ArrayList<String> getFinal() {
    return this.finalSr;
  }

  /**
   * Return this entire array list of string as a list of new string builders
   *
   * @param arg the list to be returned
   * @return list of string builders
   */
  private ArrayList<StringBuilder> returnBuilder(ArrayList<String> arg) {
    ArrayList<StringBuilder> result = new ArrayList<>();
    for (String string : arg) {
      result.add(new StringBuilder(string));
    }
    return result;
  }


}