package cs3500.pa01;

import cs3500.pa01.comparators.ByCreation;
import cs3500.pa01.comparators.ByModified;
import cs3500.pa01.comparators.ByName;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Utilities class to implement methods across classes
 */
public class Utils {

  /**
   * @param token Input for the basis to sort the list by
   * @param list  The list that has to be sorted
   *              Takes the predefined input and sorts the given list according
   *              to that input. It's a void method.
   *              Only takes "filename", "created", and "modified" as tokens,
   * @return A sorted arrayList of paths
   */
  public ArrayList<Path> sortByToken(String token, ArrayList<Path> list) {
    ArrayList<Path> result = new ArrayList<>();
    result.addAll(list);
    if (token.equals("filename")) {
      Collections.sort(result, new ByName());
    }
    if (token.equals("created")) {
      Collections.sort(result, new ByCreation());
    }

    if (token.equals("modified")) {
      Collections.sort(result, new ByModified());
    }

    return result;
  }



}
