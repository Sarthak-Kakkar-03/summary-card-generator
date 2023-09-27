//import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa01.Controller;
import cs3500.pa01.Driver;
import cs3500.pa01.formatters.SrFormat;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class DriverTest {

  @Test
  public void mainTest() throws IOException {

    Driver.main(new String[] {"src/test/MdFilesSet2", "created",
        "src/test/Output/DriverTestResult.md"});


    boolean condition1 = Files.exists(Paths.get("src/test/Output/DriverTestResult.md"));
    boolean condition2 = Files.exists(Paths.get("src/test/Output/DriverTestResult.sr"));
    assert (condition1 && condition2);

  }

  /*
  @Test
  void studySessionTest() throws IOException {
    // Simulate user input
    String input = "src/test/Output/DriverTestResult.sr\n2\n1\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    Driver.main(new String[] {});

    System.setIn(System.in);
  }

   */
}