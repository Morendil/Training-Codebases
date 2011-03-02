package whackAmethod;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import cyvis.metric.ClassMetric;


@RunWith(Parameterized.class)
public class CleanClassTest extends CleanTestBase {
  @Parameters
   public static Collection<Object[]> getClassesToTest() throws Exception {
     classLister = new ModuleLister("");
     return getAllClassMetrics();
   }
  
  public CleanClassTest(ClassMetric classMetric) throws Exception  {
    this.classMetric = classMetric;
    this.methodMetrics = classMetric.methods; 
  }
  
  @Test
  public void numberOfMethodsOnClass_Is_GreaterThanMin() throws Exception {
    assertTrue("Expected number of methods on class " + classMetric.className + " to be above minimum of " 
        + MIN_METHODS_PER_CLASS + " but was " + methodMetrics.size(), methodMetrics.size() >= MIN_METHODS_PER_CLASS);
  }
  
  @Test
  public void numberOfMethodsOnClass_Is_LessThanMaximum() throws Exception {
    assertTrue("Expected number of methods on class " + classMetric.className + " to be below maximum of " 
        + MAX_METHODS_PER_CLASS + " but was " + methodMetrics.size(), methodMetrics.size() < MAX_METHODS_PER_CLASS);
  }
  
  
}
