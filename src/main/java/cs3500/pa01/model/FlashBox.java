package cs3500.pa01.model;

import cs3500.pa01.writers.OverWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Flashbox model that is edits and moves the cards around when in as session
 */
public class FlashBox {

  private final SetFlashBox initialBox;
  private final String path;
  private final ArrayList<FlashCard> questionLine;
  private final ArrayList<FlashCard> resultList;


  /**
   * Constructor for flashbox
   *
   * @param path path of sr file to be read as flashbox
   */
  public FlashBox(String path) {
    this.path = path;
    initialBox = new SetFlashBox(path);
    questionLine = initialBox.getCopyOfBoxOfCards();
    resultList = new ArrayList<>();
  }

  /**
   * Takes a flashcard out of the question line
   *
   * @return gives a flashcard that was removed from the question line
   */
  public FlashCard giveFlashCard() {
    FlashCard result;
    result = questionLine.remove(0);
    return result;
  }

  /**
   * moves the card to appropriate difficulty list as per user input
   */
  public void assignDifficulty(FlashCard card, int input) {
    if (input == 1) {
      card.setDifficulty("easy");
      resultList.add(card);
    } else if (input == 2 || input == 3) {
      card.setDifficulty("hard");
      resultList.add(card);
    } else if (input == 4) {
      resultList.add(card);
    }
  }

  /**
   * Tells the max number of total questions
   *
   * @return int
   */
  public int numOfTotalCards() {
    return questionLine.size();
  }

  /**
   * Overwrites the file when a game session has ended
   *
   * @throws IOException in-case the file wasn't found
   */
  public void exitGame() throws IOException {
    OverWriter overWriter = new OverWriter(this.path, prepToWrite());
    overWriter.writeFile();
  }

  /**
   * Converts the flashcards that were processed back into flash-strings to overwrite source file
   *
   * @return array list of string to be written
   */
  private ArrayList<String> prepToWrite() {
    ArrayList<String> result = new ArrayList<>();
    ArrayList<FlashCard> combined = new ArrayList<>(resultList);
    combined.addAll(questionLine);
    for (FlashCard card : combined) {
      result.add(card.toString());
    }
    return result;
  }

  /**
   * Getter method for the result list
   *
   * @return the final result list
   */
  public ArrayList<FlashCard> getResultList() {
    return resultList;
  }

  /**
   * number of hard difficulty cards in question line
   *
   * @return int
   */
  public int getHardNum() {
    int result = 0;
    for (FlashCard card : questionLine) {
      if (card.difficulty.equals("hard")) {
        result++;
      }
    }
    return result;
  }

  /**
   * Number of easy difficulty card in question line
   *
   * @return int
   */
  public int getEasyNum() {
    int result = 0;
    for (FlashCard card : questionLine) {
      if (card.difficulty.equals("easy")) {
        result++;
      }
    }
    return result;
  }
}
