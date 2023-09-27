package cs3500.pa01.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlashBoxTest {
  FlashBox testObj;
  FlashCard testCard;

  @BeforeEach
  void init() {
    testObj = new FlashBox("src/test/Output/DriverTestResult.sr");
    testCard = new FlashCard("hi", "hello");
  }

  @Test
  void giveFlashCard() {
    FlashCard given = testObj.giveFlashCard();
    assertNotNull(given);
  }

  @Test
  void assignDifficulty() {
    int resultNum = testObj.getResultList().size();
    testObj.assignDifficulty(testCard, 1);
    assertEquals(resultNum + 1, testObj.getResultList().size());
    testObj.assignDifficulty(testCard, 2);
    assertEquals(resultNum + 2, testObj.getResultList().size());
    testObj.assignDifficulty(testCard, 3);
    assertEquals(resultNum + 3, testObj.getResultList().size());
    testObj.assignDifficulty(testCard, 4);
    assertEquals(resultNum + 4, testObj.getResultList().size());

  }

  @Test
  void numOfTotalCards() {
    assertEquals(testObj.numOfTotalCards(), 6);
  }

  @Test
  void exitGame() throws IOException {
    testObj.exitGame();
    assertTrue(Files.exists(Paths.get("src/test/Output/DriverTestResult.sr")));
  }
}