package com.googlecode.gwt.crypto.client;

import junit.framework.TestCase;

public class TripleDesCipherTest extends TestCase {
	private final static byte[] key = new byte[]{(byte)4,(byte)8,(byte)3,(byte)80,(byte)12,(byte)-9,(byte)-5,(byte)101, (byte)15,(byte)-8,(byte)3,(byte)0,(byte)90,(byte)-9,(byte)55,(byte)-41, (byte)-9,(byte)90,(byte)3,(byte)100,(byte)-40,(byte)79,(byte)5,(byte)102};

	private final static String toEncrypt = "encrypt This";

	public void testEncryptDecrypt() throws Exception {
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(key);
		String encrypted = cipher.encrypt(toEncrypt);
		String decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt, decrypted);
	}
}
