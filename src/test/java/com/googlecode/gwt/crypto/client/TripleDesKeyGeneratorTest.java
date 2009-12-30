package com.googlecode.gwt.crypto.client;

import junit.framework.TestCase;

public class TripleDesKeyGeneratorTest extends TestCase {

	public void testEncodeKey() throws Exception {
		
		TripleDesKeyGenerator kg = new TripleDesKeyGenerator();
	    assertNotNull(kg);
	    byte[] key = kg.generateNewKey();
	    assertNotNull(key);
	    assertFalse(key.length == 0);
	    
	    String encodedKey = kg.encodeKey(key);
	    assertNotNull(encodedKey);
	    assertTrue(encodedKey.length() == 48);
	   
	}

}
