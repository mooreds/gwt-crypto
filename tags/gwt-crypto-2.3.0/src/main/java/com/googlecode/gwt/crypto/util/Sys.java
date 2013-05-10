package com.googlecode.gwt.crypto.util;

public class Sys {
	public static <E> void arraycopy(E[] src, int srcPos, E[] dest,
			int destPos, int length) {
		checkJdkCompatabilityExceptions(src, srcPos, dest, destPos, length,
				src.length, dest.length);
		
		int destIdx = destPos;
		for (int i = 0; i < length; i++) {
			dest[destIdx++] = src[srcPos++];
		}
	}
	
	public static void arraycopy(boolean[] src, int srcPos, boolean[] dest,
			int destPos, int length) {
		checkJdkCompatabilityExceptions(src, srcPos, dest, destPos, length,
				src.length, dest.length);

		int destIdx = destPos;
		for (int s = 0; s < length; s++) {
			dest[destIdx++] = src[srcPos++];
		}
	}
	
	public static void arraycopy(byte[] src, int srcPos, byte[] dest,
			int destPos, int length) {
		checkJdkCompatabilityExceptions(src, srcPos, dest, destPos, length,
				src.length, dest.length);

		int destIdx = destPos;
		for (int s = 0; s < length; s++) {
			dest[destIdx++] = src[srcPos++];
		}
	}
	
	public static void arraycopy(char[] src, int srcPos, char[] dest,
			int destPos, int length) {
		checkJdkCompatabilityExceptions(src, srcPos, dest, destPos, length,
				src.length, dest.length);

		int destIdx = destPos;
		for (int s = 0; s < length; s++) {
			dest[destIdx++] = src[srcPos++];
		}
	}
	
	public static void arraycopy(int[] src, int srcPos, int[] dest,
			int destPos, int length) {
		checkJdkCompatabilityExceptions(src, srcPos, dest, destPos, length,
				src.length, dest.length);

		int destIdx = destPos;
		for (int s = srcPos; s < length; s++) {
			dest[destIdx++] = src[s];
		}
	}
	
	public static void arraycopy(long[] src, int srcPos, long[] dest,
			int destPos, int length) {
		checkJdkCompatabilityExceptions(src, srcPos, dest, destPos, length,
				src.length, dest.length);

		int destIdx = destPos;
		for (int s = srcPos; s < length; s++) {
			dest[destIdx++] = src[s];
		}
	}
	
	public static void arraycopy(float[] src, int srcPos, float[] dest,
			int destPos, int length) {
		checkJdkCompatabilityExceptions(src, srcPos, dest, destPos, length,
				src.length, dest.length);

		int destIdx = destPos;
		for (int s = srcPos; s < length; s++) {
			dest[destIdx++] = src[s];
		}
	}
	
	public static void arraycopy(double[] src, int srcPos, double[] dest,
			int destPos, int length) {
		checkJdkCompatabilityExceptions(src, srcPos, dest, destPos, length,
				src.length, dest.length);

		int destIdx = destPos;
		for (int s = srcPos; s < length; s++) {
			dest[destIdx++] = src[s];
		}
	}
	
	@Deprecated
	public static void arraycopyBytes(byte[] src, int srcPos, byte[] dest,
			int destPos, int length) {
		arraycopy(src, srcPos, dest, destPos, length);
	}

	@Deprecated
	public static void arraycopyInt(int[] src, int srcPos, int[] dest,
			int destPos, int length) {
		arraycopy(src, srcPos, dest, destPos, length);
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
