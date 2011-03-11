package whackAmethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ModuleMetricCollector {
  private ClassMetricCollector classMetricCollector;
  private MethodMetricCollector methodLister;
  
  private List<File> classFiles;
  private List<Object[]> metricsForAllClasses;
  private List<Object[]> classesTimesMethodsAsList;
  private String startingDirectory;
  
  public ModuleMetricCollector(String startingDirectory) throws Exception {
    this.startingDirectory = startingDirectory;
    this.classMetricCollector = new ClassMetricCollector();
    metricsForAllClasses = new ArrayList<Object[]>(); 
    
    collectClassFiles();
    collectClassMetrics();
    collectMethods();
  }

  private void collectClassFiles() throws Exception {
    classFiles = new RecursiveClassFileFinder(startingDirectory).getClassFiles();
  }

  private void collectMethods() throws Exception {
    methodLister = new MethodMetricCollector(classFiles);
    classesTimesMethodsAsList = methodLister.collectAllClassMetricsAndMethodMetrics();
  }

  private void collectClassMetrics() throws Exception {
    for (File classFile : classFiles) {
      metricsForAllClasses.add(new Object[] { classMetricCollector.addMethodMetricsForClass(classFile) });
    }
  }

  public List<Object[]> getClassMetricsAsList() {
    return metricsForAllClasses;
  }
  
  public List<Object[]> getAllMethodMetricsAsList() {
    return classesTimesMethodsAsList;
  }
}
