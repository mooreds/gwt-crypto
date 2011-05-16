package com.googlecode.gwt.crypto.util;

import com.googlecode.gwt.crypto.common.CryptoTest;

public class StrTest extends CryptoTest {
	@Override
	public void onModuleLoad()
	{
		//Do nothing
	}

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
	
	public void testEmptyBytes() {
		byte[] inputBytes =new byte[]{};;
		char[] output = Str.toChars(inputBytes);
		assertNotNull(output);
		assertEquals("", new String(output));
	}
	

	
	public void testNullBytes() {
		byte[] inputBytes = null;
		char[] output = Str.toChars(inputBytes);
		assertNotNull(output);
		assertEquals("", new String(output));
	}
}
