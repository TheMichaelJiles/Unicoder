package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestUTF16 {
	
	@Test
	void testZero() {
		Codepoint codepoint = new Codepoint("0");
		assertTrue(this.assertEqualsIgnoreCase("0000", codepoint.toUTF16()));
	}
	
	@Test
	void testBetweenU0000AndUD7FF() {
		Codepoint codepoint = new Codepoint("D6FF");
		assertTrue(this.assertEqualsIgnoreCase("D6FF", codepoint.toUTF16()));
	}
	
	@Test
	void testUD7FF() {
		Codepoint codepoint = new Codepoint("D7FF");
		assertTrue(this.assertEqualsIgnoreCase("D7FF", codepoint.toUTF16()));
	}
	
	@Test
	void testE000() {
		Codepoint codepoint = new Codepoint("E000");
		assertTrue(this.assertEqualsIgnoreCase("E000", codepoint.toUTF16()));
	}
	
	@Test
	void testBetweenUE000AndUFFFF() {
		Codepoint codepoint = new Codepoint("0183A5");
		assertTrue(this.assertEqualsIgnoreCase("D820DFA5", codepoint.toUTF16()));
	}
	
	@Test
	void testFFFF() {
		Codepoint codepoint = new Codepoint("FFFF");
		assertTrue(this.assertEqualsIgnoreCase("FFFF", codepoint.toUTF16()));
	}
	
	@Test
	void testU10000() {
		Codepoint codepoint = new Codepoint("10000");
		assertTrue(this.assertEqualsIgnoreCase("D800DC00", codepoint.toUTF16()));
	}
	
	@Test
	void testBetweenU10000And10FFF() {
		Codepoint codepoint = new Codepoint("10DEF");
		assertTrue(this.assertEqualsIgnoreCase("D803DDEF", codepoint.toUTF16()));
	}
	
	@Test
	void testU10FFF() {
		Codepoint codepoint = new Codepoint("10FFF");
		assertTrue(this.assertEqualsIgnoreCase("D803DFFF", codepoint.toUTF16()));
	}
	
	private boolean assertEqualsIgnoreCase(String target, String actual) {
		return target.equalsIgnoreCase(actual);
	}

}
