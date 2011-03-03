package whackAmethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MethodMetricCollector {
  private ClassMetricCollector classMetricCollector;

  private List<Object[]> methodMetricsForClass;
  private List<Object[]> classesTimesMethodsAsList;
  private List<File> classFiles;

  public MethodMetricCollector(List<File> classFiles) {
    this.classFiles = classFiles;
    classMetricCollector = new ClassMetricCollector();
    classesTimesMethodsAsList = new ArrayList<Object[]>();
    methodMetricsForClass = new ArrayList<Object[]>();
  }

  public List<Object[]> collectAllClassMetricsAndMethodMetrics() throws Exception {
    addAllMethodMetricsForAllClasses();
    return addAllClassMethodPairs();
  }
  
  private void addAllMethodMetricsForAllClasses() throws Exception {
    for (int i = 0; i < classFiles.size(); i++) {
      classMetricCollector.addMethodMetricsForClass(classFiles.get(i));
    }
    methodMetricsForClass = classMetricCollector.getMethodMetricsForClass();
  }
  
  private List<Object[]> addAllClassMethodPairs() {
    for (Object[] classMetricMethodMetricPair : methodMetricsForClass) {
      classesTimesMethodsAsList.add(classMetricMethodMetricPair);
    }
    
    return classesTimesMethodsAsList;
  }
  
}
