package com.googlecode.gwt.crypto.util;

public class Str {

   private static int[]delta;

   public static byte[] toBytes(char[] inputChars) {
        byte[] out = new byte[inputChars.length];
        delta = new int[inputChars.length];
	for (int i = 0; i < inputChars.length; i++) {
	    out[i] = (byte) inputChars[i];
            delta[i] = (int) inputChars[i] - out[i];
	}
	return out;
    }

    public static char[] toChars(byte[] in) {
	char[] out = new char[in.length];
	for (int i = 0; i < in.length; i++) {
            if (i < delta.length) {
                out[i] = (char) (in[i] + delta[i]);
            } else {
                out[i] = (char) in[i];
            }
        }
        return out;
    }

}
