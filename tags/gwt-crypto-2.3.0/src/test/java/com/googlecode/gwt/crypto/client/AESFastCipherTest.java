package com.googlecode.gwt.crypto.client;

public class AESFastCipherTest extends AbstractAESCipherTest {

	@Override
	protected AbstractStreamCipher getCipher() {
		return new AESFastCipher();
	}

}
