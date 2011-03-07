package com.pillar.roman;

import java.util.LinkedHashMap;
import java.util.Map;


public class RomanNumber {

	Map<String, Integer> romanToArabic = new LinkedHashMap<String, Integer>();
	private String romanString;

	public RomanNumber(String romanString) {
		this.romanString = romanString;
		initMap();
	}
	
	public int intValue() {
		int runningTotal = 0;
		String muteableString = new String(romanString);
		return recursiveConvert(runningTotal, muteableString);
	}

	private void initMap() {
		romanToArabic.put("M", 1000);
		romanToArabic.put("CM", 900);
		romanToArabic.put("D", 500);
		romanToArabic.put("CD", 400);
		romanToArabic.put("C", 100);
		romanToArabic.put("XC", 90);
		romanToArabic.put("L", 50);
		romanToArabic.put("XL", 40);
		romanToArabic.put("X", 10);
		romanToArabic.put("IX", 9);
		romanToArabic.put("V", 5);
		romanToArabic.put("IV", 4);
		romanToArabic.put("I", 1);
	}

	private int recursiveConvert(int total, String stringToParse) {
		for (String key : romanToArabic.keySet()) {
			if (stringToParse.startsWith(key)) {
				int newTotal = total + romanToArabic.get(key);
				String newString = stringToParse.replaceFirst(key, "");
				return recursiveConvert(newTotal, newString);
			}
		}
		return total;
	}
}
