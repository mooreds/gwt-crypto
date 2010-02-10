package com.googlecode.gwt.crypto.client;

import junit.framework.TestCase;

public class TripleDesCipherTest extends TestCase {
	private final static byte[] key = new byte[]{
		(byte)4,(byte)8,(byte)3,(byte)80,(byte)12,(byte)-9,(byte)-5,(byte)101, 
		(byte)15,(byte)-8,(byte)3,(byte)0,(byte)90,(byte)-9,(byte)55,(byte)-41, 
		(byte)-9,(byte)90,(byte)3,(byte)100,(byte)-40,(byte)79,(byte)5,(byte)102};


	public void testEncryptDecrypt() throws Exception {
	 	String toEncrypt = "encrypt This";
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(key);
		String encrypted = cipher.encrypt(toEncrypt);
		String decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt, decrypted);
	}

	
	public void testEncryptDecryptSmall() throws Exception {
	 	String toEncrypt = "etl";
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(key);
		String encrypted = cipher.encrypt(toEncrypt);
		String decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt, decrypted);
	}
	
	
	public void testEncryptDecryptBlank() throws Exception {
	 	String toEncrypt = "";
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(key);
		String encrypted = cipher.encrypt(toEncrypt);
		String decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt, decrypted);
	}
	
	public void testEncryptNull() throws Exception {
	 	String toEncrypt = null;
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(key);
		try {
			String encrypted = cipher.encrypt(toEncrypt);
			fail ("should get NPE");
		} catch (NullPointerException expected) {}
	
	}


	public void testUnicodeEncryptDecrypt() throws Exception {
	 	String toEncrypt = "\u00D6 \u00C4 (German)\n";
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(key);
		String encrypted = cipher.encrypt(toEncrypt);
		String decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt, decrypted);

		// chinese sample from here: http://www.chinesecomputing.com/programming/java.html
 		String chinesesample = "\u4e00";
		encrypted = cipher.encrypt(chinesesample);
		decrypted = cipher.decrypt(encrypted);
		assertEquals(chinesesample, decrypted);

	}

	public void testMultipleEncryptDecrypt() throws Exception {
	 	String toEncrypt = "encrypt this";
	 	String toEncrypt2 = "now, encrypt this";
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(key);
		String encrypted = cipher.encrypt(toEncrypt);
		String decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt, decrypted);
		encrypted = cipher.encrypt(toEncrypt2);
		decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt2, decrypted);
	}

	public void testLargeStringEncryptDecrypt() throws Exception {
	 	String toEncrypt = " encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string ";
		TripleDesCipher cipher = new TripleDesCipher();
		cipher.setKey(key);
		String encrypted = cipher.encrypt(toEncrypt);
		String decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt, decrypted);
	}
}
