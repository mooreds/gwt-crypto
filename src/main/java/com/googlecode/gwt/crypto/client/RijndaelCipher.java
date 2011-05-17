package com.googlecode.gwt.crypto.client;

import com.googlecode.gwt.crypto.bouncycastle.engines.RijndaelEngine;

public class RijndaelCipher extends AbstractStreamCipher {
	public RijndaelCipher() {
		super(new RijndaelEngine());
	}
	
	public RijndaelCipher(int blockBits) {
		super(new RijndaelEngine(blockBits));
	}
}
