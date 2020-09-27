package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestUTF8 {

	@Test
	void testOneByte() {
		Codepoint codepoint = new Codepoint("0015");
		this.assertEqualsIgnoreCase("15", codepoint.toUTF8());
	}
	
	@Test
	void testTwoBytes() {
		Codepoint codepoint = new Codepoint("01A0");
		this.assertEqualsIgnoreCase("C6A0", codepoint.toUTF8());
	}
	
	@Test
	void testThreeBytes() {
		Codepoint codepoint = new Codepoint("4CE3");
		this.assertEqualsIgnoreCase("E4B3A3", codepoint.toUTF8());
	}
	
	@Test
	void testFourBytes() {
		Codepoint codepoint = new Codepoint("1AB341");
		this.assertEqualsIgnoreCase("F6AB8D81", codepoint.toUTF8());
	}
	
	private boolean assertEqualsIgnoreCase(String target, String actual) {
		return target.equalsIgnoreCase(actual);
	}

}
