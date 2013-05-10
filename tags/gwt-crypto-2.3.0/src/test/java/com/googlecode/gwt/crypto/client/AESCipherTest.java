package com.googlecode.gwt.crypto.client;

public class AESCipherTest extends AbstractAESCipherTest {

	@Override
	protected AbstractStreamCipher getCipher() {
		return new AESCipher();
	}

}
