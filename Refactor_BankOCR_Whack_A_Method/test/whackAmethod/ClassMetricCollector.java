package whackAmethod;

import java.io.BufferedInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cyvis.core.MetricExtractor;
import cyvis.metric.ClassMetric;
import cyvis.metric.MethodMetric;

public class ClassMetricCollector {
  private MetricExtractor metricExtractor;
  private List<Object[]> methodMetricsForClass;
  private List<MethodMetric> methodMetrics;
  private ClassMetric classMetric;
  
  public ClassMetricCollector() {
    metricExtractor = new MetricExtractor();
    methodMetricsForClass = new ArrayList<Object[]>();
  }
  
  public ClassMetric addMethodMetricsForClass(File file) throws Exception {
    BufferedInputStream inputStreamForClassFile = new InputStreamBuilder().getInputStreamForClass(file);
    classMetric = metricExtractor.getClassMetric(inputStreamForClassFile);
    methodMetrics = getMethodMetricsForClass(classMetric);
    methodMetricsForClass = addMethodMetricsForClass(classMetric);
    
    return classMetric;
  }
  
  private List<MethodMetric> getMethodMetricsForClass(ClassMetric classMetric) throws Exception {
    return classMetric.methods;
  }

  private List<Object[]> addMethodMetricsForClass(ClassMetric classMetric) {
    for (int j = 0; j < methodMetrics.size(); j++) {
      addThisClassMethodPair(classMetric, j);
    }
    return methodMetricsForClass;
  }

  private void addThisClassMethodPair(ClassMetric thisClassMetric, int j) {
    Object[] thisClassMethodMetricPair;
    MethodMetric thisMethodMetric = methodMetrics.get(j);
    thisClassMethodMetricPair = new Object[] { thisClassMetric, thisMethodMetric };
    methodMetricsForClass.add(thisClassMethodMetricPair);
  }
  
  public List<Object[]> getMethodMetricsForClass() {
    return methodMetricsForClass;
    
  }

}
