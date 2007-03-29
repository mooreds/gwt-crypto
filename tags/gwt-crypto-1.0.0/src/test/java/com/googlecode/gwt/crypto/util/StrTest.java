package com.googlecode.gwt.crypto.util;

import junit.framework.TestCase;

public class StrTest extends TestCase {
	public void testToBytes() {
		char[] inputChars = "test string".toCharArray();
		byte[] output = Str.toBytes(inputChars);
		assertNotNull(output);
		assertEquals("test string", new String(output));

	}

	public void testToChars() {
		byte[] inputBytes = "test string".getBytes();
		char[] output = Str.toChars(inputBytes);
		assertNotNull(output);
		assertEquals("test string", new String(output));

	}
}
