package whackAmethod;

import java.util.ArrayList;
import java.util.List;

import cyvis.metric.ClassMetric;
import cyvis.metric.MethodMetric;

public class CleanTestBase {
  public static final int MAX_METHODS_PER_CLASS = 9;
  public static final int MIN_METHODS_PER_CLASS = 3;
  public static final int MAX_METHOD_COMPLEXITY = 4;
  public static final int MAX_BYTECODE_STATEMENTS = 42; //Thank you Douglas Adams!
  
  protected ClassMetric classMetric;
  protected MethodMetric methodMetric;
  protected ArrayList<MethodMetric> methodMetrics;
  protected static ModuleMetricCollector classLister;
  
  protected static List<Object[]> getAllClassMetrics() {
    return classLister.getClassMetricsAsList();
  }
}
