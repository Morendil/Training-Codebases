package whackAmethod;

import java.io.BufferedInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cyvis.core.MetricExtractor;
import cyvis.metric.ClassMetric;
import cyvis.metric.MethodMetric;

public class MethodLister {
  private MetricExtractor metricExtractor;

  private List<Object[]> methodMetricsForClass;
  private List<Object[]> classesTimesMethodsAsList;
  private List<File> classFiles;

  private List<MethodMetric> methodMetrics;
  private ClassMetric classMetric;

  public MethodLister(List<File> classFiles) {
    this.classFiles = classFiles;
    initializeCollections();
  }

  private void initializeCollections() {
    metricExtractor = new MetricExtractor();
    classesTimesMethodsAsList = new ArrayList<Object[]>();
    methodMetricsForClass = new ArrayList<Object[]>();
  }

  public List<Object[]> collectAllClassMetricsAndMethodMetrics() throws Exception {
    addAllMethodMetricsForAllClasses();
    return addAllClassMethodPairs();
  }

  private void addAllMethodMetricsForAllClasses() throws Exception {
    for (int i = 0; i < classFiles.size(); i++) {
      addMethodMetricsForClass(classFiles.get(i));
    }
  }

  private void addMethodMetricsForClass(File file) throws Exception {
    BufferedInputStream inputStreamForClassFile = new InputStreamBuilder().getInputStreamForClass(file);
    classMetric = metricExtractor.getClassMetric(inputStreamForClassFile);
    methodMetrics = getMethodMetricsForClass(classMetric);
    methodMetricsForClass = addMethodMetricsForClass(classMetric);
  }
  private List<MethodMetric> getMethodMetricsForClass(ClassMetric classMetric) throws Exception {
    return classMetric.methods;
  }

  private List<Object[]> addAllClassMethodPairs() {
    for (Object[] classMetricMethodMetricPair : methodMetricsForClass) {
      classesTimesMethodsAsList.add(classMetricMethodMetricPair);
    }
    
    return classesTimesMethodsAsList;
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

}
