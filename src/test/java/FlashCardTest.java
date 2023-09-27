import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.model.FlashCard;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlashCardTest {
  FlashCard flashCard1;
  FlashCard flashCard2;
  FlashCard flashCard3;

  @BeforeEach
  void init() {
    flashCard1 = new FlashCard("hey", "hi");
    flashCard2 = new FlashCard("hey", "hi", "easy");
    flashCard3 = new FlashCard("anything", "whatever", "hard");
  }

  @Test
  void getQuestion() {
    assertEquals("hey", flashCard1.getQuestion());
  }

  @Test
  void getAnswer() {
    assertEquals("hi", flashCard1.getAnswer());
  }

  @Test
  void getDifficulty() {
    assertEquals("hard", flashCard1.getDifficulty());
    assertEquals("easy", flashCard2.getDifficulty());

  }

  @Test
  void testToString() {
    String result = "Question: hey\n" + "Answer: hi\n" + "Difficulty: hard\n" + "<separator>\n";
    assertEquals(result, flashCard1.toString());
  }

  @Test
  void newFlashcard() {
    assertEquals(flashCard1, FlashCard.newFlashcard("hey:::hi"));
  }

  @Test
  void testNewFlashcard() {
    ArrayList<String> arg = new ArrayList<>(
        Arrays.asList("Question: hey", "Answer: hi", "Difficulty: hard", "<separator>",
            "Question: hey", "Answer: hi", "Difficulty: easy", "<separator>")
    );
    ArrayList<FlashCard> tester = FlashCard.newFlashcard(arg);
    ArrayList<FlashCard> expected = new ArrayList<>(Arrays.asList(flashCard1, flashCard2));
    assertEquals(expected, tester);
  }

  @Test
  void setDifficultyTest() {
    flashCard1.setDifficulty("easy");
    assertEquals("easy", flashCard1.getDifficulty());
    flashCard2.setDifficulty("hard");
    assertEquals("hard", flashCard2.getDifficulty());
  }

  @Test
  void hashCodeTest() {
    int hashCode3 = flashCard3.hashCode();
    assertNotEquals(hashCode3, flashCard2.hashCode());
  }

  @Test
  void equalTest() {
    assertEquals(flashCard1, flashCard2);
    assertNotEquals(flashCard1, flashCard3);
  }

}