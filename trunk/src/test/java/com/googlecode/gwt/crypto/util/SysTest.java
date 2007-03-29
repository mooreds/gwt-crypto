package com.googlecode.gwt.crypto.util;

import junit.framework.TestCase;

public class SysTest extends TestCase {
	public void testCopyAllBytes() {
		byte[] a1 = { 1, 2, 3 };
		byte[] a2 = new byte[a1.length];
		Sys.arraycopyBytes(a1, 0, a2, 0, a1.length);
		assertEquals(1, a2[0]);
		assertEquals(2, a2[1]);
		assertEquals(3, a2[2]);
	}

	public void testCopyAllInts() {
		int[] a1 = { 1, 2, 3 };
		int[] a2 = new int[a1.length];
		Sys.arraycopyInt(a1, 0, a2, 0, a1.length);
		assertEquals(1, a2[0]);
		assertEquals(2, a2[1]);
		assertEquals(3, a2[2]);
	}
}
