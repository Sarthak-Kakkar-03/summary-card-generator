package cs3500.pa01.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetFlashBoxTest {
  SetFlashBox testObj;
  ArrayList<FlashCard> boxOfCards;


  @BeforeEach
  private void init() {
    testObj = new SetFlashBox("src/test/Output/DriverTestResult.sr");
    boxOfCards = new ArrayList<FlashCard>();
    boxOfCards.add(new FlashCard("question in", "brackets"));
    boxOfCards.add(new FlashCard("question without bullet? - ", "yes"));
    boxOfCards.add(new FlashCard("question is this - ", "answer is that"));
    boxOfCards.add(new FlashCard("main", " - that"));
    boxOfCards.add(new FlashCard("unique1", " unique2"));
    boxOfCards.add(new FlashCard("unique3", "unique4"));

  }

  @Test
  void getCopyOfBoxOfCards() {
    assertEquals(testObj.getCopyOfBoxOfCards().size(), 6);

  }
}