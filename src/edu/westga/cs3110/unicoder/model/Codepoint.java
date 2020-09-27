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
			int lower = value & 0b00000000001111111111;
			int upperSurrogate = 0xD800 + upper;
			int lowerSurrogate = 0xDC00 + lower;
			int lowerSurrogateLength = Integer.toBinaryString(lowerSurrogate).length();
			return lowerSurrogate | (upperSurrogate << lowerSurrogateLength);
		}
	}
	
	private String pad0sTo20(String value) {
		String zeros = "";
		for (int length = value.length(); length < 20; length++) {
			zeros += "0";
		}
		return zeros + value;
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

			
		}
		return -1;
	}
	
	private boolean hexIsInUTF8OneByteRange(int value) {
		int rangeStart = Integer.decode("0x0000");
		int rangeEnd = Integer.decode("0x007F");
		
		boolean isBetween = rangeStart <= value && value <= rangeEnd;
		
		return isBetween;
	}
	
	private boolean hexIsInUTF8TwoByteRange(int value) {
		int rangeStart = Integer.decode("0x0080");
		int rangeEnd = Integer.decode("0x07FF");
		
		boolean isBetween = rangeStart <= value && value <= rangeEnd;
		
		return isBetween;
	}

}
