package whackAmethod;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InputStreamBuilder {
  public BufferedInputStream getInputStreamForClass(File file)
      throws FileNotFoundException {
    return new BufferedInputStream(new FileInputStream(file));
  }

}
