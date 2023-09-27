package purepa1;

//import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.comparators.ByModified;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ByModifiedTest {

  ByModified testObject;
  Path testFile1;
  Path testFile2;
  Path testFile3;
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




  @BeforeEach
  void initialize() throws IOException {
    instant1 = Instant.parse("2023-05-05T10:37:30.00Z");
    instantM1 = Instant.parse("2023-06-05T10:37:30.00Z");
    fileTime1  = FileTime.from(instant1);
    fileTimeM1  = FileTime.from(instantM1);
    instant2 = Instant.parse("2023-04-05T10:38:30.00Z");
    instantM2 = Instant.parse("2023-07-05T10:38:30.00Z");
    fileTime2  = FileTime.from(instant2);
    fileTimeM2  = FileTime.from(instantM2);
    instant3 = Instant.parse("2023-07-05T10:39:30.00Z");
    instantM3 = Instant.parse("2023-08-05T10:39:30.00Z");
    fileTime3  = FileTime.from(instant3);
    fileTimeM3  = FileTime.from(instantM3);
    Path testDir = Files.createTempDirectory("TestDirectory");
    Path testInDir = Files.createDirectory(testDir.resolve("inDir"));
    testFile1 = Files.createFile(testDir.resolve("atest1.md"));
    testFile3 = Files.createFile(testInDir.resolve("ctest3.md"));
    Files.setAttribute(testFile1, "creationTime", fileTime1);
    Files.setAttribute(testFile1, "lastModifiedTime", fileTimeM1);
    testFile2 = Files.createFile(testDir.resolve("btest2.md"));
    Files.setAttribute(testFile2, "creationTime", fileTime2);
    Files.setAttribute(testFile2, "lastModifiedTime", fileTimeM2);
    Files.setAttribute(testFile3, "creationTime", fileTime3);
    Files.setAttribute(testFile3, "lastModifiedTime", fileTimeM3);
    testObject = new ByModified();

  }

  @Test
  void testCompare() {
    assertEquals(1, testObject.compare(testFile3, testFile2));
    assertEquals(-1, testObject.compare(testFile1, testFile3));
  }



  @Test
  void testErrors() {
    assertThrows(RuntimeException.class, () -> testObject.compare(Path.of("givenFile1"),
        Path.of("givenFile2")));
    assertThrows(RuntimeException.class, () -> testObject.compare(testFile1,
        Path.of("givenFile2")));
  }
}