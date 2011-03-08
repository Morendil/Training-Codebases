package whackAmethod;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ModuleMetricCollectorTest {
  private ModuleMetricCollector moduleLister;
  private static final String WHACK_A_METHOD_PACKAGE_PATH = "whackAMethod/";  
  
  @Before
  public void setup() throws Exception {
    moduleLister = new ModuleMetricCollector(WHACK_A_METHOD_PACKAGE_PATH);
    moduleLister.getClassMetricsAsList();
  }

  @Test
  public void canListAllClassFiles_InPackage() throws Exception {
    assertEquals(11, moduleLister.getClassMetricsAsList().size());
  }
}
