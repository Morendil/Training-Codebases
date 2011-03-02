package whackAmethod;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class RecursiveClassFileFinderTest {
  
  @Test
  public void canPrintAllClassFilesInEntireDirectoryTree() throws Exception {
    RecursiveClassFileFinder finder = new RecursiveClassFileFinder("whackAMethod");
    assertEquals(10, finder.getNumberOfClassFiles());
  }

}
