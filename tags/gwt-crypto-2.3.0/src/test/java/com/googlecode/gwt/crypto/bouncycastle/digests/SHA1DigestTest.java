package com.googlecode.gwt.crypto.bouncycastle.digests;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.junit.client.GWTTestCase;
import com.googlecode.gwt.crypto.common.CryptoTest;
import com.googlecode.gwt.crypto.gwtx.io.UnsupportedEncodingException;

@SuppressWarnings("unused")
public class SHA1DigestTest extends CryptoTest {
	@Override
	public void onModuleLoad() {
		//Do nothing
	}

	public void testCreateDigest() {
		try {
			String passwordString = "abcdefghi";
			SHA1Digest digest = new SHA1Digest();
			byte[] inputBytes = new byte[digest.getDigestSize()];
			byte[] pwBytes = passwordString.getBytes();
			for (int i = 0; i < pwBytes.length; i++) {
				inputBytes[i] = pwBytes[i];
			}

			digest.update(inputBytes, 0, inputBytes.length);

			int val = digest.doFinal(inputBytes, 0);
			
			
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}
		
	}


	public void testCreateEmptyDigest() {
		try {
			String passwordString = "";
			SHA1Digest digest = new SHA1Digest();
			byte[] inputBytes = new byte[digest.getDigestSize()];
			byte[] pwBytes = passwordString.getBytes();
			for (int i = 0; i < pwBytes.length; i++) {
				inputBytes[i] = pwBytes[i];
			}

			digest.update(inputBytes, 0, inputBytes.length);

			int val = digest.doFinal(inputBytes, 0);
			
			
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}
		
	}


	public void testCreateShortDigest() {
		try {
			String passwordString = "ab";
			SHA1Digest digest = new SHA1Digest();
			byte[] inputBytes = new byte[digest.getDigestSize()];
			byte[] pwBytes = passwordString.getBytes();
			for (int i = 0; i < pwBytes.length; i++) {
				inputBytes[i] = pwBytes[i];
			}

			digest.update(inputBytes, 0, inputBytes.length);

			int val = digest.doFinal(inputBytes, 0);
			
			
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}
		
	}


	public void testCreateUnicodeDigest() {
		try {
			String passwordString = "\u00D6 \u00C4 (German)\n";
			SHA1Digest digest = new SHA1Digest();
			byte[] inputBytes = new byte[digest.getDigestSize()];
			byte[] pwBytes = passwordString.getBytes();
			for (int i = 0; i < pwBytes.length; i++) {
				inputBytes[i] = pwBytes[i];
			}

			digest.update(inputBytes, 0, inputBytes.length);

			int val = digest.doFinal(inputBytes, 0);
			
			
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}
		
	}
	// see http://stackoverflow.com/questions/7071902/small-differences-in-sha1-hashes
	public void testDigestValuesVsApacheShiroHappa3() {
		try {
			String passwordString = "happa3";
			SHA1Digest sha1 = new SHA1Digest();
			 byte[] bytes;
			 byte[] result = new byte[sha1.getDigestSize()];
			 bytes = passwordString.getBytes();
			 sha1.update(bytes, 0, bytes.length);
			 int val = sha1.doFinal(result, 0);
			 assertEquals("fe7f3cffd8a5f0512a5f1120f1369f48cd6f47c2", byteArrayToHexString(result));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}catch (Exception e) {
			fail(e.toString());
		}
		
	}
	// see http://stackoverflow.com/questions/7071902/small-differences-in-sha1-hashes
	public void testDigestValuesVsApacheShiroHappa() {
		
		try {
			String passwordString = "happa";
			SHA1Digest sha1 = new SHA1Digest();
			 byte[] bytes;
			 byte[] result = new byte[sha1.getDigestSize()];
			 bytes = passwordString.getBytes();
			 sha1.update(bytes, 0, bytes.length);
			 int val = sha1.doFinal(result, 0);
			 assertEquals("fb3c3a741b4e07a87d9cb68f3db020d6fbfed00a", byteArrayToHexString(result));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}catch (Exception e) {
			fail(e.toString());
		}
		
	}
	
	// from http://stackoverflow.com/questions/4895523/java-string-to-sha1
	private static String byteArrayToHexString(byte[] b) throws Exception {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
		}

}

