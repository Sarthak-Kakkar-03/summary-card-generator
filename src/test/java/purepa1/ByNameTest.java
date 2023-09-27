package purepa1;

//import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.Utils;
import cs3500.pa01.comparators.ByName;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ByNameTest {
  ByName testObject;
  Path testFile1;
  Path testFile2;
  Path testFile3;
  Path testFile4;
  Path testFile5;
  Path testFile6;
  Path testFile7;
  Path testFile8;
  Utils utils;

  @BeforeEach
  void initialize() {
    testObject = new ByName();
    testFile1 = Path.of("src/test/MdFilesSet/file1.md");
    testFile2 = Path.of("src/test/MdFilesSet/file2.md");
    testFile3 = Path.of("src/test/MdFilesSet/file3.md");
    testFile5 = Path.of("src/test/MdFilesSet/file5.md");
    testFile4 = Path.of("src/test/MdFilesSet/file4.pdf");
    testFile6 = Path.of("src/test/MdFilesSet/Afile6.md");
    testFile7 = Path.of("src/test/MdFilesSet/Zfile7.md");
    testFile8 = Path.of("src/test/MdFilesSet/Internal/anotherTest.md");
    utils = new Utils();
  }

  @Test
  void testCompare() {
    assertEquals(-20, testObject.compare(testFile1, testFile7));
    assertEquals(-5, testObject.compare(testFile6, testFile2));
    assertEquals(0, testObject.compare(testFile4, testFile5));

  }
}