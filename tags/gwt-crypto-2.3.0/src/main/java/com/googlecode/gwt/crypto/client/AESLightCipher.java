package com.googlecode.gwt.crypto.client;

import com.googlecode.gwt.crypto.bouncycastle.engines.AESLightEngine;

public class AESLightCipher extends AbstractStreamCipher {
	public AESLightCipher() {
		super(new AESLightEngine());
	}
}
