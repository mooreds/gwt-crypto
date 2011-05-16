package com.googlecode.gwt.crypto.common;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.googlecode.gwt.crypto.bouncycastle.digests.SHA1DigestTest;
import com.googlecode.gwt.crypto.client.TripleDesCipherTest;
import com.googlecode.gwt.crypto.client.TripleDesKeyGeneratorTest;
import com.googlecode.gwt.crypto.util.StrTest;
import com.googlecode.gwt.crypto.util.SysTest;

public class AllCryptoTests extends GWTTestSuite
{
	public static Test suite()
	{
		TestSuite suite = new TestSuite("Test for gwt-crypto library");
		suite.addTestSuite(SysTest.class);
		suite.addTestSuite(StrTest.class);
		
		suite.addTestSuite(SHA1DigestTest.class);
		
		suite.addTestSuite(TripleDesKeyGeneratorTest.class); 
		suite.addTestSuite(TripleDesCipherTest.class);
		return suite;
	}
}
