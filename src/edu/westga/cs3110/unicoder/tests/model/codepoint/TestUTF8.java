package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestUTF8 {

	@Test
	void testOneByte() {
		Codepoint codepoint = new Codepoint("0015");
		assertEquals(0x15, codepoint.toUTF8());
	}
	
	@Test
	void testTwoBytes() {
		Codepoint codepoint = new Codepoint("01A0");
		assertEquals(0xC6A0, codepoint.toUTF8());
	}
	
	@Test
	void testThreeBytes() {
		Codepoint codepoint = new Codepoint("4CE3");
		assertEquals(0xE4B3A3, codepoint.toUTF8());
	}
	
	@Test
	void testFourBytes() {
		Codepoint codepoint = new Codepoint("1AB341");
		assertEquals(0xF6AB8D81, codepoint.toUTF8());
	}

}
