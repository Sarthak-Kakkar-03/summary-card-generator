package cs3500.pa01;

import cs3500.pa01.model.FlashBox;
import cs3500.pa01.model.FlashCard;
import java.io.IOException;
import java.util.Scanner;

/**
 * Takes user input and controls the model and the view
 */
public class Controller {
  private final View view = new View();
  private FlashBox model;
  private int totalCards;
  private int processedCards = 0;
  private boolean exit = false;
  private final Scanner scanner;
  private int hardNum;
  private int easyNum;
  private int hardAdded = 0;
  private int easyAdd = 0;

  /**
   * Initialize the controller
    */
  public Controller(Readable scanner) {
    this.scanner = new Scanner(scanner);
  }

  /**
   * Start the study session
   */
  public void initSession() {
    view.createWelcomeScreen();
    String str = scanner.nextLine();
    model = new FlashBox(str);
    easyNum = model.getEasyNum();
    hardNum = model.getHardNum();
  }


  /**
   * Lets the user select how many flashcards they want to practice
   * a number greater than available flashcards will only allow available flashcards
   * to be practiced
   */
  public void numFlashCardSelection() {
    int numCards = model.numOfTotalCards();
    view.numFlashCardView(numCards);
    int wantedNum = scanner.nextInt();
    if (wantedNum > numCards) {
      view.moreCardsView(numCards);
      totalCards = numCards;
    } else {
      totalCards = wantedNum;
    }
  }

  /**
   * Controls the study session where flashcards move and get assigned.
   * Calls relevant methods where it's time to end session
   *
   * @throws IOException if the session couldn't be closed
   */
  public void study() throws IOException {
    if (totalCards != 0) {
      view.instructionsView();
      while (this.totalCards > this.processedCards && !exit) {
        FlashCard current = model.giveFlashCard();
        view.showQuestion(current);
        int input = scanner.nextInt();
        if (input == 1) {
          difficultyNum(current, input);
          view.showAnswer(current);
          model.assignDifficulty(current, input);
          easyAdd++;
          this.processedCards++;
        } else if (input == 3 || input == 2) {
          difficultyNum(current, 2);
          view.showAnswer(current);
          model.assignDifficulty(current, input);
          hardAdded++;
          this.processedCards++;
        } else if (input == 4) {
          model.assignDifficulty(current, input);
          exit = true;
        } else {
          view.instructionsView();
        }
      }
      // once we exit the while loop, we check if the session should be ended
      if (this.totalCards == this.processedCards || exit) {
        model.exitGame();
        view.endScreen(this.processedCards, hardAdded, easyAdd, hardNum, easyNum);
        scanner.close();
      }
    }
  }


  /**
   * Moves the total number of flashcards depening on the initial state
   *
   * @param flashCard current card
   * @param changeState the state to be changed to shown by the integer
   */
  void difficultyNum(FlashCard flashCard, int changeState) {
    if (flashCard.getDifficulty().equals("easy") && changeState == 2) {
      easyNum--;
      hardNum++;
    } else if (flashCard.getDifficulty().equals("hard") && changeState == 1) {
      hardNum--;
      easyNum++;
    }
  }

  /**
   * Getter for available cards in the file
   *
   * @return int value of total cards
   */
  public int getTotalAvailableCards() {
    return model.numOfTotalCards();
  }


  /**
   * Getter for total cards in this class
   *
   * @return int value of total cards
   */
  public int getTotalCards() {
    return this.totalCards;
  }

  /**
   * returns total easyNum
   *
   * @return integer value
   */
  public int getEasyNum() {
    return this.easyNum;
  }

  /**
   * returns total hardNum
   *
   * @return integer value
   */
  public int getHardNum() {
    return this.hardNum;
  }

  /**
   * Changes the value of exit to true to test exit
   */
  public void setExit() {
    this.exit = true;
  }

  /**
   * Gets the exit status
   *
   * @return boolean
   */
  public boolean getExit() {
    return this.exit;
  }
}
