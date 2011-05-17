package com.googlecode.gwt.crypto.common;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.googlecode.gwt.crypto.bouncycastle.crypto.AESFastTest;
import com.googlecode.gwt.crypto.bouncycastle.crypto.AESLightTest;
import com.googlecode.gwt.crypto.bouncycastle.crypto.AESTest;
import com.googlecode.gwt.crypto.bouncycastle.crypto.DESTest;
import com.googlecode.gwt.crypto.bouncycastle.crypto.DESedeTest;
import com.googlecode.gwt.crypto.bouncycastle.crypto.NullTest;
import com.googlecode.gwt.crypto.bouncycastle.crypto.PaddingTest;
import com.googlecode.gwt.crypto.bouncycastle.crypto.RSATest;
import com.googlecode.gwt.crypto.bouncycastle.digests.SHA1DigestTest;
import com.googlecode.gwt.crypto.client.AESCipherTest;
import com.googlecode.gwt.crypto.client.AESFastCipherTest;
import com.googlecode.gwt.crypto.client.AESLightCipherTest;
import com.googlecode.gwt.crypto.client.RijndaelCipherTest;
import com.googlecode.gwt.crypto.client.TripleDesCipherTest;
import com.googlecode.gwt.crypto.client.TripleDesKeyGeneratorTest;
import com.googlecode.gwt.crypto.util.StrTest;
import com.googlecode.gwt.crypto.util.SysTest;

public class AllCryptoTests extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for gwt-crypto library");
		suite.addTestSuite(BootTest.class);
		
		suite.addTestSuite(SysTest.class);
		suite.addTestSuite(StrTest.class);

		suite.addTestSuite(SHA1DigestTest.class);

		suite.addTestSuite(TripleDesKeyGeneratorTest.class);
		
		suite.addTestSuite(TripleDesCipherTest.class);
		suite.addTestSuite(RijndaelCipherTest.class);
		suite.addTestSuite(AESCipherTest.class);
		suite.addTestSuite(AESFastCipherTest.class);
		suite.addTestSuite(AESLightCipherTest.class);
		
		//Tests wrapped from BouncyCastle tests
		suite.addTestSuite(AESFastTest.class);
		suite.addTestSuite(AESLightTest.class);
		suite.addTestSuite(AESTest.class);
		suite.addTestSuite(DESedeTest.class);
		suite.addTestSuite(DESTest.class);
		suite.addTestSuite(NullTest.class);
		suite.addTestSuite(PaddingTest.class);
		suite.addTestSuite(RSATest.class);
		//suite.addTestSuite(.class);
		return suite;
	}
}
