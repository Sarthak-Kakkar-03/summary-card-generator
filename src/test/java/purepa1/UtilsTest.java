package purepa1;

//import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.Utils;
import cs3500.pa01.filetraverse.FileFinder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilsTest {
  FileFinder testObject;
  Path testFile1;
  Path testFile2;
  Path testFile3;
  Utils utils;
  Instant instant1;
  Instant instantM1;
  FileTime fileTime1;
  FileTime fileTimeM1;
  Instant instant2;
  Instant instantM2;
  FileTime fileTime2;
  FileTime fileTimeM2;
  Instant instant3;
  Instant instantM3;
  FileTime fileTime3;
  FileTime fileTimeM3;
  ArrayList<Path> argList;


  @BeforeEach
  void initialize() throws IOException {
    instant1 = Instant.parse("2023-05-05T10:37:30.00Z");
    instantM1 = Instant.parse("2023-06-05T10:37:30.00Z");
    fileTime1 = FileTime.from(instant1);
    fileTimeM1 = FileTime.from(instantM1);
    instant2 = Instant.parse("2023-05-05T10:38:30.00Z");
    instantM2 = Instant.parse("2023-07-05T10:38:30.00Z");
    fileTime2 = FileTime.from(instant2);
    fileTimeM2 = FileTime.from(instantM2);
    instant3 = Instant.parse("2023-05-05T10:39:30.00Z");
    instantM3 = Instant.parse("2023-08-05T10:39:30.00Z");
    fileTime3 = FileTime.from(instant3);
    fileTimeM3 = FileTime.from(instantM3);
    Path testDir = Files.createTempDirectory("TestDirectory");
    Path testInDir = Files.createDirectory(testDir.resolve("inDir"));
    testFile1 = Files.createFile(testDir.resolve("atest1.md"));
    testFile2 = Files.createFile(testDir.resolve("btest2.md"));
    testFile3 = Files.createFile(testInDir.resolve("ctest3.md"));
    Files.setAttribute(testFile1, "creationTime", fileTime1);
    Files.setAttribute(testFile1, "lastModifiedTime", fileTimeM1);
    Files.setAttribute(testFile2, "creationTime", fileTime2);
    Files.setAttribute(testFile2, "lastModifiedTime", fileTimeM2);
    Files.setAttribute(testFile3, "creationTime", fileTime3);
    Files.setAttribute(testFile3, "lastModifiedTime", fileTimeM3);
    testObject = new FileFinder(testDir.toString());
    utils = new Utils();
    argList = new ArrayList<>();
    argList.add(testFile1);
    argList.add(testFile2);
    argList.add(testFile3);

  }


  @Test
  void sortByToken() throws IOException {
    Files.walkFileTree(testObject.targetInputDirectory, testObject);
    assertEquals(Arrays.asList(testFile1, testFile2, testFile3),
        utils.sortByToken("filename", argList));
    //  System.out.print(argList);
    assertEquals(Arrays.asList(testFile1, testFile2, testFile3),
        utils.sortByToken("created", argList));
    // System.out.print(argList);
    assertEquals(Arrays.asList(testFile1, testFile2,
        testFile3), utils.sortByToken("modified", argList));
    Files.delete(testFile1);
    Files.delete(testFile2);
    Files.delete(testFile3);

  }
}