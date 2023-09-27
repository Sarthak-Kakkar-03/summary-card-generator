package cs3500.pa01;

import cs3500.pa01.model.FlashCard;

/**
 * Controls the display to the viewer
 */
public class View {

  /**
   * View for the welcome screen
   */
  public void createWelcomeScreen() {
    System.out.println("Greetings!");
    System.out.println("You have activated a study session!");
    System.out.println("Please input the path for collection of Flashcards you want to "
        + "practice today!");
    System.out.println(
        "(Remember to enter the full path including the filename ending with .sr)");
  }

  /**
   * Shows the number of flashcards
   *
   * @param numCards total numcards
   */
  public void numFlashCardView(int numCards) {
    System.out.println("your chosen file seems to have " + numCards + " flashcards!");
    System.out.println("how many would you like to practice today?\n");
  }


  /**
   * Screen for the questions
   *
   * @param card the card to be shown
   */
  public void showQuestion(FlashCard card) {
    System.out.println("Question: " + card.getQuestion());
  }

  /**
   * View the answer
   *
   * @param card card for which the answer is to be shown
   */
  public void showAnswer(FlashCard card) {
    System.out.println(card.getAnswer());
  }

  /**
   * Show the instructions page
   */
  public void instructionsView() {
    System.out.println("Remember:\n press [1] to mark a question as easy\n "
        + "press [2] to mark a question as hard\n press [3] to show answer\n"
        + "press [4] to exit the study session\n");
  }

  /**
   * Shows the ending screen
   *
   * @param num number of process flashcards
   */
  public void endScreen(int num, int hardAdd, int easyAdd, int totalHard, int totalEasy) {
    System.out.println("Good job!");
    System.out.println("You completed " + num + " questions!!");
    System.out.println(hardAdd + " cards were declared hard.");
    System.out.println(easyAdd + " cards were declared easy.");
    System.out.println("You have total " + totalHard + " hard cards now");
    System.out.println("You have total " + totalEasy + " easy cards now");



  }

  /**
   * Shows a message if user wants more cards than the number of total cards in source file
   *
   * @param num total cards
   */
  public void moreCardsView(int num) {
    System.out.println("You don't have that many cards!! Let's just practice all "
        + num + " cards you have");
  }

}
