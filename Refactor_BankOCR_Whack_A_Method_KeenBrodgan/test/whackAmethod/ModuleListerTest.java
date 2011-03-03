package whackAmethod;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ModuleListerTest {
  private ModuleLister moduleLister;
  private List<File> fileNames;
  private static final String WHACK_A_METHOD_PACKAGE_PATH = "whackAMethod/";  
  
  @Before
  public void setup() throws Exception {
    moduleLister = new ModuleLister(WHACK_A_METHOD_PACKAGE_PATH);
    moduleLister.getClassMetricsAsList();
    fileNames = moduleLister.getClassFiles();
  }

  @Test
  public void canListAllClassFiles_InPackage() throws Exception {
    assertEquals(10, fileNames.size());
  }
}
