package com.googlecode.gwt.crypto.client;

public class AESLightCipherTest extends AbstractAESCipherTest {

	@Override
	protected AbstractStreamCipher getCipher() {
		return new AESLightCipher();
	}

}
