package purepa1;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.Utils;
import cs3500.pa01.filetraverse.FileFinder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileFinderTest {
  FileFinder testObject;
  Path testFile1;
  Path testFile2;
  Path testFile3;
  Utils utils;

  @BeforeEach
  void initialize() throws IOException {
    Path testDir = Files.createTempDirectory("TestDirectory");
    Path testInDir = Files.createDirectory(testDir.resolve("inDir"));
    testFile1 = Files.createFile(testDir.resolve("atest1.md"));
    testFile2 = Files.createFile(testDir.resolve("btest2.md"));
    testFile3 = Files.createFile(testInDir.resolve("ctest3.md"));
    testObject = new FileFinder(testDir.toString());
    utils = new Utils();
  }


  @Test
  void testFileWalk() throws IOException {
    Files.walkFileTree(testObject.targetInputDirectory, testObject);
    assertEquals(3, testObject.getDirectoryFiles().size());

    assertEquals(Arrays.asList(testFile1, testFile2, testFile3),
        utils.sortByToken("filename", testObject.getDirectoryFiles()));
    //System.out.println(testObject.getDirectoryFiles());


  }

  @Test
  void testErrors() {
    assertThrows(IllegalStateException.class, () -> testObject.getDirectoryFiles());
  }
}