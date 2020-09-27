package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

/**
 * Unit tests for UTF 32
 * @author Michael Jiles
 *
 */
class TestToUTF32 {

	@Test
	void testValidOutput() {
		Codepoint codepoint = new Codepoint("183A5");
		assertTrue(this.assertEqualsIgnoreCase("000183A5", codepoint.toUTF32()));
	}
	
	@Test
	void testZero() {
		Codepoint codepoint = new Codepoint("0");
		assertTrue(this.assertEqualsIgnoreCase("00000000", codepoint.toUTF32()));
	}
	
	private boolean assertEqualsIgnoreCase(String target, String actual) {
		return target.equalsIgnoreCase(actual);
	}
}
