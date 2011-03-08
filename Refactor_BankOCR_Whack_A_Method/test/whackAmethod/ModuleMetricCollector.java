package whackAmethod;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cyvis.core.MetricExtractor;

public class ModuleMetricCollector {
  private MetricExtractor metricExtractor;
  private MethodMetricCollector methodLister;
  private RecursiveClassFileFinder classFileFinder;
  
  private List<File> classFiles;
  private List<Object[]> metricsForAllClasses;
  private List<Object[]> classesTimesMethodsAsList;
  private String startingDirectory;
  
  public ModuleMetricCollector(String startingDirectory) throws Exception {
    this.startingDirectory = startingDirectory;
    metricExtractor = new MetricExtractor();
    collectClasses();
    collectMethods();
  }

  private void collectClasses() throws Exception {
    metricsForAllClasses = new ArrayList<Object[]>(); 
    discoverListOfClassFiles();
    mapClassMetricsTo2DArray();
  }

  private void collectMethods() throws Exception {
    methodLister = new MethodMetricCollector(classFiles);
    classesTimesMethodsAsList = methodLister.collectAllClassMetricsAndMethodMetrics();
  }

  private void mapClassMetricsTo2DArray() throws Exception {
    for (File classFile : classFiles) {
      getAllMetricsForThisClass(classFile);
    }
  }

  private void getAllMetricsForThisClass(File classFile) throws FileNotFoundException {
    BufferedInputStream inputStreamForClassFile = new InputStreamBuilder().getInputStreamForClass(classFile);
    metricsForAllClasses.add(new Object[] {metricExtractor.getClassMetric(inputStreamForClassFile)});
  }

  private void discoverListOfClassFiles() throws Exception {
    classFileFinder = new RecursiveClassFileFinder(startingDirectory);
    classFiles = classFileFinder.getClassFiles();
  }

  public List<Object[]> getClassMetricsAsList() {
    return metricsForAllClasses;
  }
  
  public List<Object[]> getAllMethodMetricsAsList() {
    return classesTimesMethodsAsList;
  }
}
