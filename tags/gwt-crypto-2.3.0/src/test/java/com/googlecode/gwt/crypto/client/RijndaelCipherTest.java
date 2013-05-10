package com.googlecode.gwt.crypto.client;

public class RijndaelCipherTest extends AbstractAESCipherTest {

	@Override
	protected AbstractStreamCipher getCipher() {
		return new RijndaelCipher();
	}

}
