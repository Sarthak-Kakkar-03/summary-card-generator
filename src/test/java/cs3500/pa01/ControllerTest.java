package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.model.FlashCard;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerTest {
  private Appendable appendable;
  private Controller controller;
  private InputStreamReader inputStreamReader;
  private InputStream inputStream;
  private String fileContent;
  private static final String VALUE = "src/test/Output/singleFile.sr\n1\n1";
  FlashCard expectedCard = new FlashCard("Let's see if i", "work", "hard");



  @BeforeEach
  void setUp() throws IOException {
    fileContent = "Question: Let's see if i\nAnswer:  work\nDifficulty: hard\n<separator>\n";
    inputStream = new ByteArrayInputStream(VALUE.getBytes(StandardCharsets.UTF_8));
    inputStreamReader = new InputStreamReader(inputStream);
    controller = new Controller(inputStreamReader);
    this.appendable = new StringBuilder();
    controller.initSession();
    controller.numFlashCardSelection();
  }

  @Test
  void initSession() {
    assertEquals(controller.getTotalAvailableCards(), 1);
  }

  @Test
  void numFlashCardSelection() {
    assertEquals(controller.getTotalCards(), 1);
  }

  @Test
  void studyExit() throws IOException {
    controller.study();
    controller.setExit();
    assertTrue(controller.getExit());
  }


  @Test
  void difficultyNum() {
    FlashCard easyCard = new FlashCard("Question", "Answer", "easy");
    controller.difficultyNum(easyCard, 2);
    FlashCard hardCard = new FlashCard("Question", "Answer", "hard");
    assertEquals(controller.getEasyNum(), 0);
    assertEquals(controller.getHardNum(), 1);
    controller.difficultyNum(hardCard, 1);
    assertEquals(controller.getEasyNum(), 1);
    assertEquals(controller.getHardNum(), 0);
  }
}