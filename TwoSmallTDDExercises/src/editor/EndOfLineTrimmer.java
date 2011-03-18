package editor;

public class EndOfLineTrimmer {
  private static final String EMPTY = "";
  private static final String SPACE = " ";
  private static final String TAB = "\t";

  public String trim(String input) {
    StringBuffer buffer = new StringBuffer(input);

    removeAllTrailing(SPACE, buffer);
    removeAllTrailing(TAB, buffer);
    
    return buffer.toString();
  }

  private void removeAllTrailing(String toRemove, StringBuffer buffer) {
    buffer.reverse();
    buffer.replace(0, lastNonTrimmableCharIndex(toRemove, buffer) + 1, EMPTY);
    buffer.reverse();
  }

  private int lastNonTrimmableCharIndex(String toRemove, StringBuffer buffer) {
    
    for (int i = 0; i < buffer.length(); i++) {
      if (buffer.charAt(i)) == toRemove) 
    }

    return buffer.lastIndexOf(toRemove);
  }

}
