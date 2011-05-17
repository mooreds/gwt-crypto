package com.googlecode.gwt.crypto.client;

import com.googlecode.gwt.crypto.bouncycastle.engines.AESFastEngine;

public class AESFastCipher extends AbstractStreamCipher {
	public AESFastCipher() {
		super(new AESFastEngine());
	}
}