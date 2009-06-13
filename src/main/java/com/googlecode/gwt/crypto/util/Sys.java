package com.googlecode.gwt.crypto.util;

public class Sys {
	public static void arraycopyBytes(byte[] src, int srcPos, byte[] dest,
			int destPos, int length) {
		checkJdkCompatabilityExceptions(src, srcPos, dest, destPos, length,
				src.length, dest.length);

		int destIdx = destPos;
		for (int s = 0; s < length; s++) {
			dest[destIdx++] = src[srcPos++];
		}

	}

	public static void arraycopyInt(int[] src, int srcPos, int[] dest,
			int destPos, int length) {
		int destIdx = destPos;
		for (int s = srcPos; s < length; s++) {
			dest[destIdx++] = src[s];
		}

	}

	private static void checkJdkCompatabilityExceptions(Object src, int srcPos,
			Object dest, int destPos, int length, int srcLen, int destLen) {
		if (src == null || dest == null) {
			// jdk compatability
			throw new NullPointerException("src and dest must not be null");
		}
		if (srcPos < 0 || destPos < 0 || length < 0) {
			// jdk compatability
			throw new IndexOutOfBoundsException(
					"srcPos, destPos and length must not be negative");
		}
		if (srcPos + length > srcLen) {
			throw new IndexOutOfBoundsException(
					"srcPos + length must not be greater than src.length");
		}
		if (destPos + length > destLen) {
			throw new IndexOutOfBoundsException(
					"destPos + length must not be greater than dest.length");
		}
	}
}
