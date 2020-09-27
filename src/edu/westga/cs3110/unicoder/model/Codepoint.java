package edu.westga.cs3110.unicoder.model;

import java.math.BigInteger;

public class Codepoint {
	
	private String hexaDecimal;
	
	public Codepoint(String hexaDecimal) {
		this.hexaDecimal = hexaDecimal;
	}
	
	public String toUTF32() {
		String result = "0x";
		for (int length = this.hexaDecimal.length(); length < 8; length++) {
			result += "0";
		}
		return result += this.hexaDecimal;
	}
	
	public String toUTF16() {
		int value = Integer.decode("0x" + this.hexaDecimal);
		
		if (this.hexIsInUTF16TwoByteRange(value)) {
			return "0x" + this.hexaDecimal;
		} else {
			int difference = value - Integer.decode("0x10000");
			String differenceBinary = Integer.toBinaryString(difference);
			differenceBinary = this.pad0sTo20(differenceBinary);
			String upper = differenceBinary.substring(0, 10);
			String lower = differenceBinary.substring(10, 20);
			int highSurrogate = Integer.decode("0xD800") + Integer.parseInt(upper, 2);
			int lowSurrogate = Integer.decode("0xDC00") + Integer.parseInt(lower, 2);
			return "0x" + Integer.toHexString(highSurrogate).toUpperCase() + Integer.toHexString(lowSurrogate).toUpperCase();
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
		int rangeStart = Integer.decode("0x0000");
		int rangeEnd = Integer.decode("0xD7FF");
		
		int secondRangeStart = Integer.decode("0xE000");
		int secondRangeEnd = Integer.decode("0xFFFF");
		
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
