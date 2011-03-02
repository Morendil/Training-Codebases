package convert;

import java.util.HashMap;
import java.util.Map;

public class StickDigitInitializer 
{
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

	  Map<String, String> stickDigitsMappedToNumerals = new HashMap<String, String>();
	
	public Map<String, String> initStickDigits()
	{
		initStickDigitsMap0to3();
		initStickDigitsMap4to5();
	    initStickDigitsMap6to9();
		
		return stickDigitsMappedToNumerals;
	}
	
	private void initStickDigitsMap6to9() {
		stickDigitsMappedToNumerals.put(SIX, "6");
	    stickDigitsMappedToNumerals.put(SEVEN, "7");
	    stickDigitsMappedToNumerals.put(EIGHT, "8");
	    stickDigitsMappedToNumerals.put(NINE, "9");
	}

	private void initStickDigitsMap0to3() {
		stickDigitsMappedToNumerals.put(ZERO, "0");
	    stickDigitsMappedToNumerals.put(ONE, "1");
	    stickDigitsMappedToNumerals.put(TWO, "2");
	    stickDigitsMappedToNumerals.put(THREE, "3");
	}
	    private void initStickDigitsMap4to5() {
	    stickDigitsMappedToNumerals.put(FOUR, "4");
	    stickDigitsMappedToNumerals.put(FIVE, "5");
	}
	
}
