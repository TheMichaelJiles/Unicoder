package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

/**
 * Class for testing getCodepointAsInt method in Codepoint
 * @author Michael Jiles
 *
 */
class TestGetCodepointAsInt {

	@Test
	void testGet() {
		Codepoint codepoint = new Codepoint("0000");
		assertEquals(0, Integer.decode("0x" + codepoint.getCodepointAsInt()));
	}

}
