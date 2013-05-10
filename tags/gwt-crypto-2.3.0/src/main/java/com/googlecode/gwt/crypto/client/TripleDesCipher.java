package com.googlecode.gwt.crypto.client;

import com.googlecode.gwt.crypto.bouncycastle.engines.DESedeEngine;

/** 
* Main class
*/


public class TripleDesCipher extends AbstractStreamCipher {
	public TripleDesCipher() {
		super(new DESedeEngine());
	}
}
