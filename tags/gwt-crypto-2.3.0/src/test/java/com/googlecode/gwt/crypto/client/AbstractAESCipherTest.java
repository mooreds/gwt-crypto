package com.googlecode.gwt.crypto.client;

/**
 * Testing only 128 bit keys
 * @author SHadoW
 *
 */
public abstract class AbstractAESCipherTest extends BlockCipherTest {
	
	private static final byte[] key = {
		(byte)0x7b, (byte)0xb3, (byte)0x9e, (byte)0x49,
		(byte)0x3a, (byte)0x9b, (byte)0xaa, (byte)0xa1,
		(byte)0x8f, (byte)0x59, (byte)0x02, (byte)0x4f,
		(byte)0x30, (byte)0x5a, (byte)0x76, (byte)0x27 
	};

	@Override
	protected byte[] getKey() {
		return key;
	}

}
