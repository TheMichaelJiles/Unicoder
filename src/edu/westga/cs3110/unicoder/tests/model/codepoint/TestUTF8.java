package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

/**
 * Unit tests for UTF8 encoding
 * @author Michael Jiles
 *
 */
class TestUTF8 {
	
	@Test
	void testZero() {
		Codepoint codepoint = new Codepoint("0");
		assertTrue(this.assertEqualsIgnoreCase("00", codepoint.toUTF8()));
	}

	@Test
	void testOneByte() {
		Codepoint codepoint = new Codepoint("0015");
		assertTrue(this.assertEqualsIgnoreCase("15", codepoint.toUTF8()));
	}
	
	@Test
	void testU007f() {
		Codepoint codepoint = new Codepoint("007F");
		assertTrue(this.assertEqualsIgnoreCase("7F", codepoint.toUTF8()));
	}
	
	@Test
	void testU0080() {
		Codepoint codepoint = new Codepoint("0080");
		assertTrue(this.assertEqualsIgnoreCase("C280", codepoint.toUTF8()));
	}
	
	@Test
	void testTwoBytes() {
		Codepoint codepoint = new Codepoint("01A0");
		assertTrue(this.assertEqualsIgnoreCase("C6A0", codepoint.toUTF8()));
	}
	
	@Test
	void testU07FF() {
		Codepoint codepoint = new Codepoint("07FF");
		assertTrue(this.assertEqualsIgnoreCase("DFBF", codepoint.toUTF8()));
	}
	
	@Test
	void testU0800() {
		Codepoint codepoint = new Codepoint("0800");
		assertTrue(this.assertEqualsIgnoreCase("E0A080", codepoint.toUTF8()));
	}
	
	@Test
	void testThreeBytes() {
		Codepoint codepoint = new Codepoint("4CE3");
		assertTrue(this.assertEqualsIgnoreCase("E4B3A3", codepoint.toUTF8()));
	}
	
	@Test
	void testUFFFF() {
		Codepoint codepoint = new Codepoint("FFFF");
		assertTrue(this.assertEqualsIgnoreCase("EFBFBF", codepoint.toUTF8()));
	}
	
	@Test
	void testU10000() {
		Codepoint codepoint = new Codepoint("10000");
		assertTrue(this.assertEqualsIgnoreCase("F0908080", codepoint.toUTF8()));
	}
	
	@Test
	void testFourBytes() {
		Codepoint codepoint = new Codepoint("1AB341");
		assertTrue(this.assertEqualsIgnoreCase("F6AB8D81", codepoint.toUTF8()));
	}
	
	@Test
	void testU10FFFF() {
		Codepoint codepoint = new Codepoint("10FFFF");
		assertTrue(this.assertEqualsIgnoreCase("F48FBFBF", codepoint.toUTF8()));
	}
	
	private boolean assertEqualsIgnoreCase(String target, String actual) {
		return target.equalsIgnoreCase(actual);
	}

}
