package convert;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;


public class AccountNumberEndToEndTest {
	Map<String, String> sampleOCRInputString = new HashMap<String, String>();

	@Before
	public void setUp() {
		initializeInputData();
	}
	
  @Test
  public void checkSumShows_InValidAccountNumberIs_InValid() {
    String numberToCheck = "999982865";
    AccountNumber accountNumber = new AccountNumber(sampleOCRInputString.get(numberToCheck));
    assertFalse("Expected " + numberToCheck + " to be invalid.", accountNumber.isValid);
  } 
	
  @Test
  public void checkSumShows_ValidAccountNumber_IsValid() {
    String numberToCheck = "123456789";
    AccountNumber accountNumber = new AccountNumber(sampleOCRInputString.get(numberToCheck));
    assertTrue("Expected " + numberToCheck + " to be valid.", accountNumber.isValid);
  } 

  private void initializeInputData() {
    sampleOCRInputString.put("999982865", 
        " _  _  _  _  _  _  _  _  _ " +
        "|_||_||_||_||_| _||_||_ |_ " +
        " _| _| _| _||_||_ |_||_| _|" +
        "                           ");
    
    sampleOCRInputString.put("000000051", 
				" _  _  _  _  _  _  _  _    " +
				"| || || || || || || ||_   |" +
				"|_||_||_||_||_||_||_| _|  |" +
				"                           ");
		
		sampleOCRInputString.put("123456789", 
				"    _  _     _  _  _  _  _ " +
				"  | _| _||_||_ |_   ||_||_|" + 
				"  ||_  _|  | _||_|  ||_| _|" +
				"                           ");		
		
		sampleOCRInputString.put("000000000", 
				" _  _  _  _  _  _  _  _  _ " +
				"| || || || || || || || || |" +
				"|_||_||_||_||_||_||_||_||_|" +
				"                           ");
  }
	
}
