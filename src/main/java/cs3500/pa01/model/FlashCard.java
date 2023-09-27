package cs3500.pa01.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The cards that are going to be presented to the students
 */
public class FlashCard {
  String question;
  String answer;
  String difficulty;

  /**
   * Creating a flashcard where we decide the difficulty
   *
   * @param question   Question string
   * @param answer     Answer string
   * @param difficulty can be hard or easy
   */
  public FlashCard(String question, String answer, String difficulty) {
    this.question = question;
    this.answer = answer;
    this.difficulty = difficulty;
  }

  /**
   * Defualts difficulty to hard
   *
   * @param question Question string
   * @param answer   Answer string
   */
  public FlashCard(String question, String answer) {
    this.question = question;
    this.answer = answer;
    this.difficulty = "hard";
  }

  /**
   * Getter for question
   *
   * @return returns the question string
   */
  public String getQuestion() {
    return question;
  }

  /**
   * getter for answer
   *
   * @return the answer string
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Getter for difficulty
   *
   * @return difficulty as string
   */
  public String getDifficulty() {
    return this.difficulty.toString();
  }

  /**
   * Creates a string representation of this flash card
   *
   * @return a string as flashcard
   */
  public String toString() {
    return
        "Question: " + this.question + "\n" + "Answer: " + this.answer + "\n"
            + "Difficulty: " + this.getDifficulty() + "\n" + "<separator>\n";

  }

  /**
   * Create flash card from a normal string using :::
   *
   * @return a flash card
   */
  public static FlashCard newFlashcard(String line) {
    String[] partition = line.split(":::");
    return new FlashCard(partition[0], partition[1]);
  }

  /**
   * Creates an arrayList of flashcards from properly formatted list of strings
   *
   * @param list formatted list of flash-strings
   * @return an arraylist of flashcards
   */
  public static ArrayList<FlashCard> newFlashcard(ArrayList<String> list) {
    if (FlashCard.validFlashArray(list)) {
      ArrayList<FlashCard> result = new ArrayList<>();
      Iterator<String> iterator = list.iterator();
      String question = "";
      String answer = "";
      String difficulty = "";
      while (iterator.hasNext()) {
        String next = iterator.next();
        if (next.startsWith("Question: ")) {
          question = next.substring("Question: ".length());
        }
        if (next.startsWith("Answer: ")) {
          answer = next.substring("Answer: ".length());
        }
        if (next.startsWith("Difficulty: ")) {
          difficulty = next.substring("Difficulty: ".length());
        }
        if (next.equals("<separator>")) {
          result.add(new FlashCard(question, answer, difficulty));
          question = "";
          answer = "";
          difficulty = "";
        }
      }
      return result;
    } else {
      throw new IllegalStateException("provided input has been corrupted");
    }
  }

  /**
   * Checks if the array given is a valid save file array returned
   *
   * @param list to be checked
   * @return boolean if the list is valid to be checked
   */
  private static boolean validFlashArray(ArrayList<String> list) {
    Iterator<String> iterator = list.iterator();
    boolean valid = true;
    while (iterator.hasNext()) {
      if (iterator.next().startsWith("Question ") && !iterator.next().startsWith("Answer")) {
        valid = false;
      }
    }
    return valid;
  }

  /**
   * Used to set the difficulty to hard OR easy
   *
   * @param difficulty the string "hard" or "easy"
   */
  public void setDifficulty(String difficulty) {
    if (difficulty.equals("hard")) {
      this.difficulty = "hard";
    } else if (difficulty.equals("easy")) {
      this.difficulty = "easy";
    } else {
      throw new IllegalArgumentException(difficulty + "isn't a valid difficulty type!");
    }
  }


  /**
   * Equals method for flashcards
   *
   * @param obj is another flashcard
   * @return if these are the same flashcards
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof FlashCard) {
      return this.question.equals(((FlashCard) obj).getQuestion())
          && this.answer.equals(((FlashCard) obj).getAnswer());
    } else {
      return false;
    }
  }

  /**
   * Overrides the hashcode method to main contract for the equals method
   *
   * @return Int as the hashcode
   */
  @Override
  public int hashCode() {
    int result = 7;
    result = result + 37 * question.length();
    result = result + 37 * answer.length();
    result = result + 37 * difficulty.length();
    return result;

  }


}
