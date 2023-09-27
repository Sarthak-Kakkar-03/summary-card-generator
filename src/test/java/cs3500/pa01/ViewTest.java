package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.model.FlashCard;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ViewTest {
  private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private View view;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outContent));
    view = new View();
  }


  @Test
  void createWelcomeScreen() {
    view.createWelcomeScreen();
    String expected =
        "Greetings!\nYou have activated a study session!\nPlease input the path for collection of"
            + " Flashcards you want to practice today!\n(Remember to enter the full path including "
            + "the filename ending with .sr)\n";
    assertEquals(expected, outContent.toString());
  }

  @Test
  void numFlashCardView() {
    view.numFlashCardView(5);
    String expected =
        "your chosen file seems to have 5 flashcards!\nhow many would you like to practice "
            + "today?\n\n";
    assertEquals(expected, outContent.toString());
  }

  @Test
  void showQuestion() {
    FlashCard card = new FlashCard("What is AI?", "Artificial Intelligence");
    view.showQuestion(card);
    String expected = "Question: What is AI?\n";
    assertEquals(expected, outContent.toString());
  }

  @Test
  void showAnswer() {
    FlashCard card = new FlashCard("What is AI?", "Artificial Intelligence");
    view.showAnswer(card);
    String expected = "Artificial Intelligence\n";
    assertEquals(expected, outContent.toString());
  }

  @Test
  void instructionsView() {
    view.instructionsView();
    String expected =
        "Remember:\n press [1] to mark a question as easy\n press [2] to mark a question "
            + "as hard\n press [3] to show answer\npress [4] to exit the study session\n\n";
    assertEquals(expected, outContent.toString());
  }

  @Test
  void endScreen() {
    view.endScreen(10, 3, 7, 5, 15);
    String expected = "Good job!\n"
        + "You completed 10 questions!!\n"
        + "3 cards were declared hard.\n"
        + "7 cards were declared easy.\n"
        + "You have total 5 hard cards now\n"
        + "You have total 15 easy cards now\n";

    assertEquals(expected, outContent.toString());
  }

  @Test
  void moreNumCardsTest() {
    view.moreCardsView(10);
    String expected = "You don't have that many cards!! Let's just practice all 10 cards you "
        + "have\n";
    assertEquals(expected, outContent.toString());
  }
}
