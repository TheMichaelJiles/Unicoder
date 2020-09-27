package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

/**
 * Class for testing Codepoint constructor
 * @author Michael Jiles
 *
 */
class TestConstructor {

	@Test
	void testInvalidConstruction() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Codepoint codepoint = new Codepoint("");
		});
	}
	
	@Test
	void validConstruction() {
		Codepoint codepoint = new Codepoint("0000");
		assertEquals(0, Integer.decode("0x" + codepoint.getCodepointAsInt()));
	}

}
