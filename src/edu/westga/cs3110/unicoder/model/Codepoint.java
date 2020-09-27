package edu.westga.cs3110.unicoder.model;

import java.math.BigInteger;

/**
 * Class for encoding hex strings to UTF8, 16, or 32
 * @author Michael Jiles
 *
 */
public class Codepoint {
	
	private int codepointAsInt;
	
	/**
	 * Constructs a new codepoint
	 * @param hexaDecimal the hex string to be encoded
	 */
	public Codepoint(String hexaDecimal) {
		this.codepointAsInt = Integer.decode("0x" + hexaDecimal);
	}
	
	/**
	 * Encodes the codepoint in UTF-32 encoding
	 * @return returns the codepoint in UTF-32 encoding, as an 8-digit hexadecimal string
	 */
	public String toUTF32() {
		return String.format("%1$08X", this.codepointAsInt);
	}
	
	/**
	 * Encodes the codepoint in UTF-16 encoding
	 * @return returns the codepoint in UTF-16 encoding, as either a 4-digit or 8-digit hexadecimal string (as appropriate)
	 */
	public String toUTF16() {
		if (this.hexIsInUTF16TwoByteRange(this.codepointAsInt)) {
			return String.format("%1$04X", this.codepointAsInt);
		} else {
			int value = this.codepointAsInt - 0x10000;
			int upper = value >> 10;
			int lower = value & 0b1111111111;
			int upperSurrogate = 0xD800 + upper;
			int lowerSurrogate = 0xDC00 + lower;
			int lowerSurrogateLength = Integer.toBinaryString(lowerSurrogate).length();
			return String.format("%1$08X", lowerSurrogate | (upperSurrogate << lowerSurrogateLength));
		}
	}
	
	private boolean hexIsInUTF16TwoByteRange(int value) {
		int rangeStart = 0x0000;
		int rangeEnd = 0xD7FF;
		
		int secondRangeStart = 0xE000;
		int secondRangeEnd = 0xFFFF;
		
		boolean betweenFirst = rangeStart <= value && value <= rangeEnd;
		boolean betweenSecond = secondRangeStart <= value && value <= secondRangeEnd;
		
		return betweenFirst || betweenSecond;
	}
	
	/**
	 * Encodes the codepoint in UTF-8 encoding
	 * @return returns the codepoint in UTF-8 encoding, as either a 2-digit, 4-digit, 6-digit, or 8-digit hexadecimal string 
	 * 		   (as appropriate).
	 */
	public String toUTF8() {
		if (this.hexIsInUTF8OneByteRange(this.codepointAsInt)) {
			return String.format("%1$02X", this.codepointAsInt);
		} else if (this.hexIsInUTF8TwoByteRange(this.codepointAsInt)) {
			int upper = this.codepointAsInt >> 6;
			int lower = this.codepointAsInt & 0b111111;
			upper = 0b11000000 + upper;
			lower = 0b10000000 + lower;
			int lowerLength = Integer.toBinaryString(lower).length();
			return String.format("%1$04X", lower | (upper << lowerLength));
		} else if (this.hexIsInUTF8ThreeByteRange(this.codepointAsInt)) {
			int upper = this.codepointAsInt >> 12;
			upper = 0b11100000 + upper;
			int middle = (0b111111000000 & this.codepointAsInt) >> 6;
			middle = 0b10000000 + middle;
			int lower = 0b111111 & this.codepointAsInt;
			lower = 0b10000000 + lower;
			int firstTwo = this.appendBinaryStrings(upper, middle);
			return String.format("%1$06X", this.appendBinaryStrings(firstTwo, lower));
		} else {
			int first = this.codepointAsInt >> 18;
			first = 0b11110000 + first;
			int second = (0b111111000000000000 & this.codepointAsInt) >> 12;
			second = 0b10000000 + second;
			int third = (0b111111000000 & this.codepointAsInt) >> 6;
			third = 0b10000000 + third;
			int fourth = 0b111111 & this.codepointAsInt;
			fourth = 0b10000000 + fourth;
			int firstTwo = this.appendBinaryStrings(first, second);
			int firstThree = this.appendBinaryStrings(firstTwo, third);
			return String.format("%1$08X", this.appendBinaryStrings(firstThree, fourth));
		}
	}
	
	private int appendBinaryStrings(int first, int second) {
		int secondLength = Integer.toBinaryString(second).length();
		return second | (first << secondLength);
	}
	
	private boolean hexIsInUTF8OneByteRange(int value) {
		int rangeStart = 0x0000;
		int rangeEnd = 0x007F;
		
		boolean isBetween = rangeStart <= value && value <= rangeEnd;
		
		return isBetween;
	}
	
	private boolean hexIsInUTF8TwoByteRange(int value) {
		int rangeStart = 0x0080;
		int rangeEnd = 0x07FF;
		
		boolean isBetween = rangeStart <= value && value <= rangeEnd;
		
		return isBetween;
	}
	
	private boolean hexIsInUTF8ThreeByteRange(int value) {
		int rangeStart = 0x0800;
		int rangeEnd = 0xFFFF;
		
		boolean isBetween = rangeStart <= value && value <= rangeEnd;
		
		return isBetween;
	}

}
