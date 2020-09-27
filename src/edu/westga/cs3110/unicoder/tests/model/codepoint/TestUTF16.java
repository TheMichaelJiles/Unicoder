package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestUTF16 {

	@Test
	void testBetweenU0000AndUD7FF() {
		Codepoint codepoint = new Codepoint("D6FF");
		assertTrue(this.assertEqualsIgnoreCase("D6FF", codepoint.toUTF16()));
	}
	
	@Test
	void testBetweenUE000AndUFFFF() {
		Codepoint codepoint = new Codepoint("0183A5");
		assertTrue(this.assertEqualsIgnoreCase("D820DFA5", codepoint.toUTF16()));
	}
	
	private boolean assertEqualsIgnoreCase(String target, String actual) {
		return target.equalsIgnoreCase(actual);
	}

}
