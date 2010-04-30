package com.googlecode.gwt.crypto.client;

import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
import com.googlecode.gwt.crypto.bouncycastle.engines.DESedeEngine;
import com.googlecode.gwt.crypto.bouncycastle.modes.CBCBlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.paddings.PaddedBufferedBlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.params.KeyParameter;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;
import com.googlecode.gwt.crypto.util.Str;



public class TripleDesCipher {
	private PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
			new CBCBlockCipher(new DESedeEngine()));
	private byte[] key = null;

	public TripleDesCipher() {
		super();
	}

	public byte[] getKey() {
		return key;
	}

	/**
	 * @param key must be between 16 and 24 bytes.  
	 */
	public void setKey(byte[] key) {
		this.key = key;
	}

	public String encrypt(String plainText) throws DataLengthException,
			IllegalStateException, InvalidCipherTextException {
		cipher.reset();
		cipher.init(true, new KeyParameter(key));

		// byte[] input = plainText.getBytes();
		byte[] input = Str.toBytes(plainText.toCharArray());
		byte[] output = new byte[cipher.getOutputSize(input.length)];

		int length = cipher.processBytes(input, 0, input.length, output, 0);
		int remaining = cipher.doFinal(output, length);
		byte[] result = Hex.encode(output, 0, output.length);
		return new String(Str.toChars(result));
	}

	public String decrypt(String cipherText) throws DataLengthException,
			IllegalStateException, InvalidCipherTextException {
		cipher.reset();
		cipher.init(false, new KeyParameter(key));

		byte[] input = Hex.decode(Str.toBytes(cipherText.toCharArray()));
		byte[] output = new byte[cipher.getOutputSize(input.length)];

		int length = cipher.processBytes(input, 0, input.length, output, 0);
		int remaining = cipher.doFinal(output, length);
		return new String(Str.toChars(output), 0, length + remaining);
	}

}
