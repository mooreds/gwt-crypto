package com.googlecode.gwt.crypto.client;

import com.googlecode.gwt.crypto.bouncycastle.CipherParameters;
import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
import com.googlecode.gwt.crypto.bouncycastle.engines.DESedeEngine;
import com.googlecode.gwt.crypto.bouncycastle.modes.CBCBlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.paddings.PaddedBufferedBlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.params.KeyParameter;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;
import com.googlecode.gwt.crypto.util.Str;

/** 
* Main class
*/


public class TripleDesCipher implements StreamCipher {
	private PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
			new CBCBlockCipher(new DESedeEngine()));
	private KeyParameter params = null;

	public TripleDesCipher() {
		super();
	}

	public byte[] getKey() {
		return params.getKey();
	}

	/**
	 * @param key must be between 16 and 24 bytes.  
	 */
	public void setKey(byte[] key) {
		this.params = new KeyParameter(key);
	}
	
	@Override
	public void setParameters(CipherParameters params) throws IllegalArgumentException
	{
		if (params == null) this.params = null;
		else if (! (params instanceof KeyParameter))
			throw new IllegalArgumentException();
		else this.params = (KeyParameter)params;
	}
	
	@Override
	public CipherParameters getParameters()
	{
		return params;
	}

	@Override
	public String encrypt(String plainText) throws DataLengthException,
			IllegalStateException, InvalidCipherTextException {
		cipher.reset();
		cipher.init(true, params);

		// byte[] input = plainText.getBytes();
		byte[] input = Str.toBytes(plainText.toCharArray());
		byte[] output = new byte[cipher.getOutputSize(input.length)];

		int length = cipher.processBytes(input, 0, input.length, output, 0);
		/*int remaining = */cipher.doFinal(output, length);
		byte[] result = Hex.encode(output, 0, output.length);
		return new String(Str.toChars(result));
	}

	@Override
	public String decrypt(String cipherText) throws DataLengthException,
			IllegalStateException, InvalidCipherTextException {
		cipher.reset();
		cipher.init(false, params);

		byte[] input = Hex.decode(Str.toBytes(cipherText.toCharArray()));
		byte[] output = new byte[cipher.getOutputSize(input.length)];

		int length = cipher.processBytes(input, 0, input.length, output, 0);
		int remaining = cipher.doFinal(output, length);
		return new String(Str.toChars(output), 0, length + remaining);
	}

}
