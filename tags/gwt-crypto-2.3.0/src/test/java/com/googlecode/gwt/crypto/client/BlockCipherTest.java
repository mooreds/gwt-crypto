package com.googlecode.gwt.crypto.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.junit.client.GWTTestCase;
import com.googlecode.gwt.crypto.bouncycastle.BlockCipher;
import com.googlecode.gwt.crypto.common.CryptoTest;

import junit.framework.TestCase;

@SuppressWarnings("unused")
public abstract class BlockCipherTest extends CryptoTest {
	@Override
	public void onModuleLoad() {
		//Do nothing
	}
	
	protected abstract AbstractStreamCipher getCipher();
	
	protected abstract byte[] getKey();

	public void testEncryptDecrypt() throws Exception {
	 	String toEncrypt = "encrypt This";
		AbstractStreamCipher cipher = getCipher();
		cipher.setKey(getKey());
		String encrypted = cipher.encrypt(toEncrypt);
		String decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt, decrypted);
	}

	
	public void testEncryptDecryptSmall() throws Exception {
	 	String toEncrypt = "etl";
		AbstractStreamCipher cipher = getCipher();
		cipher.setKey(getKey());
		String encrypted = cipher.encrypt(toEncrypt);
		String decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt, decrypted);
	}
	
	
	public void testEncryptDecryptBlank() throws Exception {
	 	String toEncrypt = "";
		AbstractStreamCipher cipher = getCipher();
		cipher.setKey(getKey());
		String encrypted = cipher.encrypt(toEncrypt);
		String decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt, decrypted);
	}
	
	public void testEncryptNull() throws Exception {
	 	String toEncrypt = null;
		AbstractStreamCipher cipher = getCipher();
		cipher.setKey(getKey());
		try {
			String encrypted = cipher.encrypt(toEncrypt);
			fail ("should get NPE");
		} 
		catch (NullPointerException expected) {assertFalse("Should be JSE", GWT.isProdMode());}
		catch (JavaScriptException e) {assertTrue("Should be NPE", GWT.isProdMode());}
	
	}


	public void testUnicodeEncryptDecrypt() throws Exception {
	 	String toEncrypt = "\u00D6 \u00C4 (German)\n";
		AbstractStreamCipher cipher = getCipher();
		cipher.setKey(getKey());
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
		AbstractStreamCipher cipher = getCipher();
		cipher.setKey(getKey());
		String encrypted = cipher.encrypt(toEncrypt);
		String decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt, decrypted);
		encrypted = cipher.encrypt(toEncrypt2);
		decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt2, decrypted);
	}

	public void testLargeStringEncryptDecrypt() throws Exception {
	 	String toEncrypt = " encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string encrypt this large large string ";
		AbstractStreamCipher cipher = getCipher();
		cipher.setKey(getKey());
		String encrypted = cipher.encrypt(toEncrypt);
		String decrypted = cipher.decrypt(encrypted);
		assertEquals(toEncrypt, decrypted);
	}
	
	public void testShortKey() throws Exception {
		byte MyKey[] = { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
			};

		final AbstractStreamCipher cip = getCipher();
		try {
			cip.setKey(MyKey);
			String rEnc = cip.encrypt("");
			fail("Should throw illegal argument exception");
		} catch (IllegalArgumentException expected) {
			
		}
		
	}
	
	public void testKeyLengthNotMultipleOfEight() throws Exception {
		byte MyKey[] = new byte[getKey().length + 1];
		
		for (int i = 0; i < MyKey.length; i++) {
			MyKey[i] = (byte)i;
		}

		final AbstractStreamCipher cip = getCipher();
		
		try
		{
			cip.setKey(MyKey);
		
			String rEnc = cip.encrypt("");
			fail("Should throw illegal argument exception");
		}
		catch (IllegalArgumentException e) {
		}
	}
	
	public void testLongLongKey() throws Exception {
		byte MyKey[] = { 
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
				0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
			};	//2048bit key (should be longer than any block cipher could handle)

		final AbstractStreamCipher cip = getCipher();
			try {
				cip.setKey(MyKey);
				String rEnc = cip.encrypt("");
				fail("Should throw illegal argument exception");
			} catch (IllegalArgumentException expected) {
		}
	}
}
