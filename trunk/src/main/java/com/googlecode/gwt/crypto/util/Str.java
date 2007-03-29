package com.googlecode.gwt.crypto.util;

public class Str {
	public static byte[] toBytes(char[] inputChars) {
		byte[] out = new byte[inputChars.length];
		for (int i = 0; i < inputChars.length; i++) {
			out[i] = (byte) inputChars[i];
		}
		return out;
	}

	public static char[] toChars(byte[] in) {
		char[] out = new char[in.length];
		for (int i = 0; i < in.length; i++) {
			out[i] = (char) in[i];
		}
		return out;
	}
}
