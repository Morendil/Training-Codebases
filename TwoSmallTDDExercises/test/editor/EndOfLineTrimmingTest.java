package editor;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



public class EndOfLineTrimmingTest {
  private EndOfLineTrimmer eolTrimmer;

  @Before
  public void setup() {
    eolTrimmer = new EndOfLineTrimmer();
  }
  
  /*
   * When editing text files, like source code, one often happens to add 
   * spurious spaces and tabs at the end of each line. 
   * Instead of pressing the "End" key at each 
   * line and removing those invisible characters manually, a simple algorithm to remove them 
   * automatically at the press of a button might be useful.

      Test-drive the simplest code you can (in the src folder) that, 
      given an input string, produces a right-trimmed output string.
      
      Here are some tests that you could write (remember Beck's Test List technique):

      "   "        -> "   "
      "abc   "     -> "abc"
      "def\t "     -> "def"
      "  cde"      -> "  cde" (does not remove beginning whitespace)
      "ab\n cd \n" -> "ab\n cd\n" (removes whitespace for each line)
      "ab\r\ncd\n" -> "ab\r\ncd\n" (handles both Windows and Unix line endings)
      
      Are these all the meaningful tests cases, if each type of character can be intermixed
      with others in any combination? 
   * 
   */
  
  @Test
  public void givenOnlyLeadingSpaces_WillIgnoreThem() throws Exception {
    assertEquals("   abc", eolTrimmer.trim("   abc"));
  }
  
  @Test
  public void givenSpacesFollowedByTabs_CanRemoveThemAll() throws Exception {
    assertEquals("abc", eolTrimmer.trim("abc     \t\t\t"));
  }
  
  @Test
  public void givenTabsFollowedBySpaces_CanRemoveThemAll() throws Exception {
    assertEquals("abc", eolTrimmer.trim("abc\t\t\t     "));
  }
  
  @Test
  public void given_OnlyTrailingSpaces_CanRemoveThem() throws Exception {
    assertEquals("abc", eolTrimmer.trim("abc   "));
  }
  
  @Test
  public void givenEmpty_CanReturnEmpty() throws Exception {
    assertEquals("", eolTrimmer.trim(""));
  }
  

  
}
