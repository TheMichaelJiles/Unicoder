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

}
