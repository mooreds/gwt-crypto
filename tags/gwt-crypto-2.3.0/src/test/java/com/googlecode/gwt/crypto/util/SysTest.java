package com.googlecode.gwt.crypto.util;

import com.googlecode.gwt.crypto.common.CryptoTest;

public class SysTest extends CryptoTest {
	@Override
	public void onModuleLoad()
	{
		//Do nothing
	}

	public void testCopyAllBytes() {
		byte[] a1 = { 1, 2, 3 };
		byte[] a2 = new byte[a1.length];
		Sys.arraycopy(a1, 0, a2, 0, a1.length);
		assertEquals(1, a2[0]);
		assertEquals(2, a2[1]);
		assertEquals(3, a2[2]);
	}

	public void testCopyAllInts() {
		int[] a1 = { 1, 2, 3 };
		int[] a2 = new int[a1.length];
		Sys.arraycopy(a1, 0, a2, 0, a1.length);
		assertEquals(1, a2[0]);
		assertEquals(2, a2[1]);
		assertEquals(3, a2[2]);
	}
	public void testCopyAllBytesBig() {
		byte[] a1 = { 1, 2, 3, 4, 5 ,6 ,7, 8, 9, 10 ,11 ,12 ,13 ,14 ,15 ,16 ,17,18,19,20,21,22,23,24 };
		byte[] a2 = new byte[a1.length];
		Sys.arraycopy(a1, 0, a2, 0, a1.length);
		assertEquals(1, a2[0]);
		assertEquals(2, a2[1]);
		assertEquals(3, a2[2]);
		assertEquals(4, a2[3]);
		assertEquals(5, a2[4]);
		assertEquals(6, a2[5]);
		assertEquals(7, a2[6]);
		assertEquals(8, a2[7]);
		assertEquals(9, a2[8]);
		assertEquals(10, a2[9]);
		assertEquals(11, a2[10]);
		assertEquals(12, a2[11]);
		assertEquals(13, a2[12]);
		assertEquals(14, a2[13]);
		assertEquals(15, a2[14]);
		assertEquals(16, a2[15]);
		assertEquals(17, a2[16]);
		assertEquals(18, a2[17]);
		assertEquals(19, a2[18]);
		assertEquals(20, a2[19]);
		assertEquals(21, a2[20]);
		assertEquals(22, a2[21]);
		assertEquals(23, a2[22]);
		assertEquals(24, a2[23]);
	}
}
