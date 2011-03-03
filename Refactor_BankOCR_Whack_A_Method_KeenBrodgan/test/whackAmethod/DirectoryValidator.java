package whackAmethod;

import java.io.File;
import java.io.FileNotFoundException;

public class DirectoryValidator {
  public void validateIsValidDirectory(File directory) throws FileNotFoundException {
    validateIsDirectory(directory);
    validateIsNotNull(directory);
    validateExists(directory);
    validateIsReadable(directory);
  }

  private void validateIsReadable(File directory) {
    if (!directory.canRead()) {
      throw new IllegalArgumentException("Directory cannot be read: "
          + directory);
    }
  }

  private void validateIsDirectory(File directory) {
    if (!directory.isDirectory()) {
      throw new IllegalArgumentException("Is not a directory: " + directory);
    }
  }

  private void validateExists(File directory) throws FileNotFoundException {
    if (!directory.exists()) {
      throw new FileNotFoundException("Directory does not exist: " + directory);
    }
  }

  private void validateIsNotNull(File directory) {
    if (directory == null) {
      throw new IllegalArgumentException("Directory should not be null.");
    }
  }

}
