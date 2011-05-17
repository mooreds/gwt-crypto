package com.googlecode.gwt.crypto.client;

import com.googlecode.gwt.crypto.bouncycastle.engines.AESEngine;

public class AESCipher extends AbstractStreamCipher {
	public AESCipher() {
		super(new AESEngine());
	}
}
