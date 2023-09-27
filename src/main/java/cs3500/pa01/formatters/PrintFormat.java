package cs3500.pa01.formatters;

import cs3500.pa01.Utils;
import java.util.ArrayList;

/**
 * Class to format the String builder as per study guide requirements
 */
public class PrintFormat {

  /**
   * A new arraylist to be initialized as a copy string builded of all data in all files
   */
  public ArrayList<StringBuilder> unformattedList = new ArrayList<>();

  /**
   * final result after to format as string, to be written using the writer
   */
  public ArrayList<String> finalPrint;
  Utils utils;

  /**
   * Changes to format into relevant md file printable format
   *
   * @param unformattedList The previous list to formatted
   */
  public PrintFormat(ArrayList<StringBuilder> unformattedList) {
    // System.out.println(unformattedList);
    this.unformattedList.addAll(unformattedList);
    this.preformat(this.unformattedList);
    utils = new Utils();
    this.finalPrint = format();
  }

  /**
   * Picks the all data between [[]] in string builders and returns as Strings.
   * ArrayList for more than [[]] in the same sentence
   *
   * @param sentence A string Builder sentence
   * @return returns the string enclosed with "[[]]" and appends to "- "
   */
  public ArrayList<String> removeBracket(StringBuilder sentence) {
    ArrayList<String> result = new ArrayList<>();
    String temp = sentence.toString();
    StringBuilder fragment = new StringBuilder();
    int start;
    int finish;
    while (temp.contains("[[") && temp.contains("]]")) {
      start = temp.indexOf("[[");
      finish = temp.indexOf("]]");
      fragment.append(temp, start + 2, finish);
      result.add("- " + fragment.toString());
      temp = temp.substring(finish + 2);
      fragment = new StringBuilder();

    }
    return result;

  }

  /**
   * preformats the builders that have brackets spanning in multiple args
   *
   * @param arg used for the unformatted list
   */
  private void preformat(ArrayList<StringBuilder> arg) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < arg.size(); i++) {
      if (this.openCheck(arg.get(i))) {
        int j = i + 1;
        StringBuilder temp = new StringBuilder();
        while (j < arg.size()) {
          if (!arg.get(j).toString().contains("]]")) {
            temp.append(arg.get(j));
          } else {
            temp.append(arg.get(j));
            break;
          }
          //arg.remove(j);
          j++;
        }
        arg.get(i).append(temp);
        if (j == arg.size()) {
          break;
        }
        arg.set(i, arg.get(i).append(result));
        result = new StringBuilder();
      }
    }
    // System.out.println(arg);
  }


  /**
   * Checks if the string builder has an open bracket
   *
   * @param sentence the given builder
   * @return true if it does
   */
  private boolean openCheck(StringBuilder sentence) {
    String str = sentence.toString();
    int openSum = 0;
    int closedSum = 0;
    int index = str.indexOf("[[");
    while (index != -1) {
      openSum++;
      index = str.indexOf("[[", index + 2);
    }
    index = str.indexOf("]]");
    while (index != -1) {
      closedSum++;
      index = str.indexOf("]]", index + 2);
    }

    return openSum > closedSum;
  }


  /**
   * Formats as per md file
   *
   * @return The strings between [[]] or following #'s
   */
  public ArrayList<String> format() {
    ArrayList<String> result = new ArrayList<>();
    //  this.preformat(this.unformattedList);
    for (StringBuilder sentence : this.unformattedList) {
      if (sentence.toString().startsWith("#")) {
        result.add(" ");
        result.add(sentence.toString());
      } else {
        result.addAll(this.removeBracket(sentence));
      }
    }
    result.remove(0);
    return result;
  }


}