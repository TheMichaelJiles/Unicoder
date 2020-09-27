package edu.westga.cs3110.unicoder.model;

import java.math.BigInteger;

public class Codepoint {
	
	private String hexaDecimal;
	private int hexAsInt;
	
	public Codepoint(String hexaDecimal) {
		this.hexaDecimal = hexaDecimal;
		this.hexAsInt = Integer.decode("0x" + hexaDecimal);
	}
	
	public int toUTF32() {
		return this.hexAsInt;
	}
	
	public int toUTF16() {
		
		if (this.hexIsInUTF16TwoByteRange(this.hexAsInt)) {
			return this.hexAsInt;
		} else {
			int value = this.hexAsInt - 0x10000;
			int upper = value >> 10;
			int lower = value & 0b1111111111;
			int upperSurrogate = 0xD800 + upper;
			int lowerSurrogate = 0xDC00 + lower;
			int lowerSurrogateLength = Integer.toBinaryString(lowerSurrogate).length();
			return lowerSurrogate | (upperSurrogate << lowerSurrogateLength);
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
	
	public int toUTF8() {
		if (this.hexIsInUTF8OneByteRange(this.hexAsInt)) {
			return this.hexAsInt & 0b11111111;
		} else if (this.hexIsInUTF8TwoByteRange(this.hexAsInt)) {
			int upper = this.hexAsInt >> 6;
			int lower = this.hexAsInt & 0b111111;
			upper = 0b11000000 + upper;
			lower = 0b10000000 + lower;
			int lowerLength = Integer.toBinaryString(lower).length();
			return lower | (upper << lowerLength);
		} else if (this.hexIsInUTF8ThreeByteRange(this.hexAsInt)) {
			int upper = this.hexAsInt >> 12;
			upper = 0b11100000 + upper;
			int middle = (0b0000111111000000 & this.hexAsInt) >> 6;
			middle = 0b10000000 + middle;
			int lower = 0b111111 & this.hexAsInt;
			lower = 0b10000000 + lower;
			
			int middleLength = Integer.toBinaryString(middle).length();
			int firstTwo = middle | (upper << middleLength);
			int lowerLength = Integer.toBinaryString(middle).length();
			return lower | (firstTwo << lowerLength);
		}
		return -1;
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
