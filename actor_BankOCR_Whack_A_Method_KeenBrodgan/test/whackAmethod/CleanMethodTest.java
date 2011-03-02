package whackAmethod;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import cyvis.metric.ClassMetric;
import cyvis.metric.MethodMetric;

@RunWith(Parameterized.class)
public class CleanMethodTest extends CleanTestBase {
  
  @Parameters
  public static Collection<Object[]> getMethodsToTest() throws Exception {
    classLister = new ModuleLister("");
    return classLister.getAllMethodMetricsAsList();
  }

  private String className;
  private String methodName;
  private int methodByteCodeLength;
  private int methodComplexity;

  public CleanMethodTest(ClassMetric classMetric, MethodMetric methodMetric) throws Exception {
    this.classMetric = classMetric;
    this.className = classMetric.className;
    this.methodName = methodMetric.name;
    this.methodByteCodeLength = methodMetric.byteCodeLength;    
    this.methodComplexity = methodMetric.cyclomaticComplexity;
  }

  @Test
  public void methodIsSmallEnough() {
        assertTrue("Expected method " + className + "." + methodName 
        + "() to have fewer than " + MAX_BYTECODE_STATEMENTS + " bytecode statements but received " 
        + methodByteCodeLength, 
        methodByteCodeLength < MAX_BYTECODE_STATEMENTS);
  }

  @Test
  public void methodHasLowEnoughCyclomaticComplexity() {
        assertTrue("Expected method " + className + "."
        + methodName + "() to have cyclomatic complexity lower than " + MAX_METHOD_COMPLEXITY
        + " but received " + methodComplexity, 
        methodComplexity < MAX_METHOD_COMPLEXITY);
  }

}
