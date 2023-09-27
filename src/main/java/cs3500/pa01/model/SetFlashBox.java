package cs3500.pa01.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Initializes the box of flashcards for the model
 */
public class SetFlashBox {
  private Path sourceFile;
  private final ArrayList<String> cardsAsString;
  private final ArrayList<FlashCard> boxOfCards;

  SetFlashBox(String filePath) {
    sourceFile = initSourcePath(filePath);
    cardsAsString = readCards();
    boxOfCards = shuffleSort(FlashCard.newFlashcard(cardsAsString));
  }

  /**
   * Read the cards from the source file
   *
   * @return ArrayList of string to be turned into flashcards
   */
  private ArrayList<String> readCards() {
    ArrayList<String> result = new ArrayList<>();
    File file = this.sourceFile.toFile();
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        result.add(scanner.nextLine());
      }
    } catch (FileNotFoundException exc) {
      System.out.println("The file path is invalid, please input correct path");
      Scanner pathCorrect = new Scanner(System.in);
      String updatedPath = pathCorrect.nextLine();
      sourceFile = Paths.get(updatedPath);
      return readCards();
    }
    return result;
  }

  /**
   * Shuffles and sorts flashcards such as the hard ones come before easy ones
   *
   * @param arg is the flashcards to be shuffle-sorted
   * @return the shuffle-sorted result
   */
  private ArrayList<FlashCard> shuffleSort(ArrayList<FlashCard> arg) {
    Collections.shuffle(arg);
    ArrayList<FlashCard> hardList = new ArrayList<>();
    ArrayList<FlashCard> easyList = new ArrayList<>();
    for (FlashCard card : arg) {
      if (card.difficulty.equals("hard")) {
        hardList.add(card);
      } else {
        easyList.add(card);
      }
    }
    Collections.shuffle(hardList);
    Collections.shuffle(easyList);
    ArrayList<FlashCard> result = new ArrayList<>();
    result.addAll(hardList);
    result.addAll(easyList);
    return result;
  }

  /**
   * Copy method for box of cards
   *
   * @return new arrayList of FlashCard
   */
  public ArrayList<FlashCard> getCopyOfBoxOfCards() {
    return new ArrayList<>(this.boxOfCards);
  }

  /**
   * Checks if the path to be read is appropriate
   *
   * @param filePath the path to be initialized for this class
   * @return the string as Path
   */
  private Path initSourcePath(String filePath) {
    Path path = Paths.get(filePath);
    if (Files.exists(path) && filePath.endsWith(".sr")) {
      return path;
    } else {
      throw new RuntimeException("invalid path was provided");
    }
  }


}
