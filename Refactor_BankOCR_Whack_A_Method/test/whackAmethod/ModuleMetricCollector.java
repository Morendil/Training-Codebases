package whackAmethod;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import cyvis.core.MetricExtractor;

public class ModuleMetricCollector {
  private MetricExtractor metricExtractor;
  private MethodMetricCollector methodLister;
  private RecursiveClassFileFinder classFileFinder;
  
  private List<File> classFiles;
  private Object metricsForClass;
  private Object[][] metricsForAllClasses;
  private List<Object[]> classesTimesMethodsAsList;
  private String startingDirectory;
  
  public ModuleMetricCollector(String startingDirectory) throws Exception {
    this.startingDirectory = startingDirectory;
    collectClasses();
    collectMethods();
  }

  private void collectClasses() throws Exception {
    metricsForAllClasses = discoverListOfClassFiles();
    mapClassMetricsTo2DArray();
  }

  private void collectMethods() throws Exception {
    methodLister = new MethodMetricCollector(classFiles);
    classesTimesMethodsAsList = methodLister.collectAllClassMetricsAndMethodMetrics();
  }

  private void mapClassMetricsTo2DArray() throws Exception {
    metricExtractor = new MetricExtractor();
    for (int i = 0; i < classFiles.size(); i++) {
      getAllMetricsForThisClass(i);
    }
  }

  private void getAllMetricsForThisClass(int i) throws FileNotFoundException {
    BufferedInputStream inputStreamForClassFile = new InputStreamBuilder().getInputStreamForClass(classFiles.get(i));
    metricsForClass = metricExtractor.getClassMetric(inputStreamForClassFile);
    metricsForAllClasses[i] = new Object[] {metricsForClass};
  }

  private Object[][] discoverListOfClassFiles() throws Exception {
    classFileFinder = new RecursiveClassFileFinder(startingDirectory);
    classFiles = classFileFinder.getClassFiles();
    return new Object[classFiles.size()][1];
  }

  public List<Object[]> getClassMetricsAsList() {
    return Arrays.asList(metricsForAllClasses);
  }
  
  public List<Object[]> getAllMethodMetricsAsList() {
    return classesTimesMethodsAsList;
  }
  
  public List<File> getClassFiles() {
    return classFiles;
  }


}
