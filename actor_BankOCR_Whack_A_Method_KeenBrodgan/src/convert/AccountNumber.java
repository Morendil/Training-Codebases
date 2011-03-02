package convert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountNumber {
  public String textVersion = "";
  public boolean isValid;
  final static int WIDTH_OF_OCR_NUMERAL = 3;
  final static int NUMBER_OF_DIGITS = 9;
private static final int WIDTH_OF_NUMERAL_TIMES_NUM_DIGITS = WIDTH_OF_OCR_NUMERAL * NUMBER_OF_DIGITS;

  Map<String, String> stickDigitsMappedToNumerals = new HashMap<String, String>();

  public final static String ZERO = " _ | ||_|";
  public final static String ONE = "     |  |";
  public final static String TWO = " _  _||_ ";
  public final static String THREE = " _  _| _|";
  public final static String FOUR = "   |_|  |";
  public final static String FIVE = " _ |_  _|";
  public final static String SIX = " _ |_ |_|";
  public final static String SEVEN = " _   |  |";
  public final static String EIGHT = " _ |_||_|";
  public final static String NINE = " _ |_| _|";
private int startOfFirstLine;
private int startOfSecondLine;
private int startOfThirdLine;

  public AccountNumber(String ocrAccountNumber) {
	  
    initStickDigitsMap();
    this.textVersion = createAccountNumberFromOcr(ocrAccountNumber);
    
  }

private void initStickDigitsMap() {
	stickDigitsMappedToNumerals = new StickDigitInitializer().initStickDigits();
}


  private String createAccountNumberFromOcr(String ocrToInterpret) {
    ArrayList<String> accountNumberAsOcrDigits1 = getAccountNumberAsOcrDigits1(ocrToInterpret);
    
    ArrayList<String> accountNumberAsOcrDigits = accountNumberAsOcrDigits1;

    String accountNumber = formulateAccountNumber(accountNumberAsOcrDigits);
    
    computeChecksum(accountNumber);
    return accountNumber;
  }

private ArrayList<String> getAccountNumberAsOcrDigits1(String ocrToInterpret) {
	ArrayList<String> accountNumberAsOcrDigits1 = new ArrayList<String>();
    for (int digit1 = 0; digit1 < NUMBER_OF_DIGITS; digit1++) {
    
      addDigitToAccountNumber(ocrToInterpret, accountNumberAsOcrDigits1, digit1);
    }
	return accountNumberAsOcrDigits1;
}

private void addDigitToAccountNumber(String ocrToInterpret, ArrayList<String> accountNumberAsOcrDigits1, int digit1) 
{
	  String nextDigit = getNextDigit(ocrToInterpret, digit1);
      
      accountNumberAsOcrDigits1.add(nextDigit);
}

private String getNextDigit(String ocrToInterpret, int digit1) {
	initLineStarts(digit1);
	
      String firstLineOfOcrDigit = getLineOfOcrDigit(ocrToInterpret, startOfFirstLine);
      
      String secondLineOfOcrDigit = getLineOfOcrDigit(ocrToInterpret, startOfSecondLine);
      
      String thirdLineOfOcrDigit = getLineOfOcrDigit(ocrToInterpret, startOfThirdLine);
    
      String nextDigit = firstLineOfOcrDigit + secondLineOfOcrDigit + thirdLineOfOcrDigit;
	return nextDigit;
}

private void initLineStarts(int digit1) {
	startOfFirstLine = (digit1 * WIDTH_OF_OCR_NUMERAL);
	startOfSecondLine = startOfFirstLine + WIDTH_OF_NUMERAL_TIMES_NUM_DIGITS;
	startOfThirdLine = startOfSecondLine + WIDTH_OF_NUMERAL_TIMES_NUM_DIGITS;
}

private String formulateAccountNumber(
		ArrayList<String> accountNumberAsOcrDigits ) {
	String accountNumber = "";
	final int ACCOUNT_NUMBER_LENGTH = accountNumberAsOcrDigits.size();

    for (int digit = 0; digit < ACCOUNT_NUMBER_LENGTH; digit++) {
      accountNumber = accountNumber
          + (stickDigitsMappedToNumerals.get(accountNumberAsOcrDigits.get(digit)));
    }
	return accountNumber;
}

private void computeChecksum(String accountNumber) {
	int checkSumCalculation = 0;
    
    for (int digit = 0; digit < NUMBER_OF_DIGITS; digit++) {
    
      checkSumCalculation = checkSumCalculation + ((NUMBER_OF_DIGITS - digit) * getDigit(accountNumber, digit));
    }

    isValid = ((checkSumCalculation % 11) == 0);
}

private int getDigit(String accountNumber, int digit) {
	int currentDigit;
	String thisCharacter = accountNumber.substring(digit, digit + 1);
      currentDigit = Integer.parseInt(thisCharacter);
	return currentDigit;
}

private String getLineOfOcrDigit(String ocrToInterpret, int startOfFirstLine) {
	return ocrToInterpret.substring(startOfFirstLine, (startOfFirstLine + WIDTH_OF_OCR_NUMERAL));
}
}
