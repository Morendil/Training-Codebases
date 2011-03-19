package whackAmethod;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecursiveClassFileFinder {
  private static final String CLASS_FILE_SUFFIX = ".class";
  private static final String STARTING_DIRECTORY_NAME = "bin";
  private List<File> allClassFiles;
  private DirectoryValidator directoryValidator;

  public RecursiveClassFileFinder(String directoryExtension) throws Exception {
    directoryValidator = new DirectoryValidator();
    allClassFiles = getListOfAllClassFilesInDirectoryStartingWith(new File(STARTING_DIRECTORY_NAME + "/" + directoryExtension));
  }

  private List<File> getListOfAllClassFilesInDirectoryStartingWith(File startingDirectory) throws Exception {
    directoryValidator.validateIsValidDirectory(startingDirectory);
    
    List<File> result = getAllClassFilesAndDirsInDirectory(startingDirectory);
    Collections.sort(result);
    return result;
  }
  
  private List<File> addAllDirectoriesBelow(List<File> listOfFilesAndDirsSoFar, File file) throws Exception {
    if (isDirectory(file)) {
      List<File> deeperList = getAllClassFilesAndDirsInDirectory(file);
      listOfFilesAndDirsSoFar.addAll(deeperList);
    }
    return listOfFilesAndDirsSoFar;
  }

  private List<File> getAllClassFilesAndDirsInDirectory(File currentDirectory) throws Exception {
    List<File> listOfFilesAndDirsSoFar = new ArrayList<File>();
    List<File> contentsOfCurrentDirectory = Arrays.asList(currentDirectory.listFiles());
    
    for (File file : contentsOfCurrentDirectory) {
      listOfFilesAndDirsSoFar = addClassFile(listOfFilesAndDirsSoFar, file);
      listOfFilesAndDirsSoFar = addAllDirectoriesBelow(listOfFilesAndDirsSoFar, file);
    }
    
    return listOfFilesAndDirsSoFar;
  }

  private List<File> addClassFile(List<File> listOfFilesAndDirsSoFar, File file) {
    if (file.getName().contains(CLASS_FILE_SUFFIX))  listOfFilesAndDirsSoFar.add(file);
    return listOfFilesAndDirsSoFar;
  }

  private boolean isDirectory(File file) {
    return !file.isFile();
  }

  public int getNumberOfClassFiles() {
    return allClassFiles.size();
  }

  public List<File> getClassFiles() {
    return allClassFiles;
  }
}
