package com.pillar.roman;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RomanNumeralConvertorTests {

	RomanNumber convertor;
	
	@Test
	public void should_return_1_when_passed_I() {
		assertEquals(1, new RomanNumber("I").intValue());
	}

	@Test
	public void should_return_5_when_passed_V() {
		assertEquals(5, new RomanNumber("V").intValue());
	}

	@Test
	public void should_return_3_when_passed_III() {
		assertEquals(3, new RomanNumber("III").intValue());
	}

	@Test
	public void should_return_6_when_passed_VI() {
		assertEquals(6, new RomanNumber("VI").intValue());
	}

	@Test
	public void should_return_4_when_passed_IV() {
		assertEquals(4, new RomanNumber("IV").intValue());
	}

	@Test
	public void should_return_1900_when_passed_MCM() {
		assertEquals(1900, new RomanNumber("MCM").intValue());
	}
}
