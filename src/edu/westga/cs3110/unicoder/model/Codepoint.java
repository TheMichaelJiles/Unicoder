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

			return 0;
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
	
	public String toUTF8() {
		int value = Integer.decode("0x" + this.hexaDecimal);
		String valueAsBinary = Integer.toBinaryString(value);
		valueAsBinary = this.pad0sTo20(valueAsBinary);
		
		if (this.hexIsInUTF8OneByteRange(value)) {
			String segment = valueAsBinary.substring(12, 20);
			int result = Integer.parseInt(valueAsBinary, 2);
			return "0x" + Integer.toHexString(result);
		} else if (this.hexIsInUTF8TwoByteRange(value)) {
			String upper = "110" + valueAsBinary.substring(9, 14);
			String lower = "10" + valueAsBinary.substring(14, 20);

			
		}
		return "";
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
