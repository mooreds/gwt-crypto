package com.googlecode.gwt.crypto.client;

import com.googlecode.gwt.crypto.bouncycastle.AsymmetricBlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.BufferedAsymmetricBlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.CipherParameters;
import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
import com.googlecode.gwt.crypto.bouncycastle.encodings.PKCS1Encoding;
import com.googlecode.gwt.crypto.bouncycastle.engines.RSAEngine;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;
import com.googlecode.gwt.crypto.util.Str;
import com.googlecode.gwt.crypto.util.Sys;

/**
 * RSA encryption stream cipher using public encryption private decryption.
 * Note this is just a sample of usage, decryption using private key takes a
 * really looong time in the browser, but public key encryption is usable.
 * @author SHadoW
 *
 */
public class RSACipher implements StreamCipher {
	BufferedAsymmetricBlockCipher cipher;
	RSAParams params = null;
	
	public RSACipher(AsymmetricBlockCipher cipher) {
		this.cipher = new BufferedAsymmetricBlockCipher(cipher);
	}
	
	public RSACipher() {
		this(new PKCS1Encoding(new RSAEngine()));
	}
	
	@Override
	public CipherParameters getParameters() {
		return params;
	}

	@Override
	public void setParameters(CipherParameters params)
			throws IllegalArgumentException {
		if (params == null) this.params = null;
		else if (! (params instanceof RSAParams))
			throw new IllegalArgumentException();
		else this.params = (RSAParams)params;
	}
	
	private byte[] process(byte[] in) throws InvalidCipherTextException
	{
		int inLen = cipher.getInputBlockSize();
		int outLen = cipher.getOutputBlockSize();
		int blocks = (int)Math.ceil((float)in.length / inLen);
		
		byte[] result = new byte[blocks * outLen];
		
		int inPos = 0;
		int outPos = 0;
		
		for (int i = 0; i < blocks - 1; i++)
		{
			cipher.processBytes(in, inPos, inLen);
			byte[] tmp = cipher.doFinal();
			assert tmp.length == outLen;
			
			Sys.arraycopy(tmp, 0, result, outPos, outLen);
			
			inPos += inLen;
			outPos += outLen;
		}
		//Process last block
		inLen = in.length - inPos;
		cipher.processBytes(in, inPos, inLen);
		byte[] tmp = cipher.doFinal();
		//assert tmp.length == outLen;
		Sys.arraycopy(tmp, 0, result, outPos, tmp.length);
		outPos += tmp.length;
		
		if (outPos != result.length)
		{
			tmp = new byte[outPos];
			Sys.arraycopy(result, 0, tmp, 0, outPos);
			return tmp;
		}
		else return result;
	}

	@Override
	public String encrypt(String plainText) throws DataLengthException,
			IllegalStateException, InvalidCipherTextException {
		cipher.reset();
		cipher.init(true, params.getPublicKey());

		// byte[] input = plainText.getBytes();
		byte[] input = Str.toBytes(plainText.toCharArray());
		
		byte[] output = process(input);
		
		byte[] result = Hex.encode(output, 0, output.length);
		return new String(Str.toChars(result));
	}

	@Override
	public String decrypt(String cipherText) throws DataLengthException,
			IllegalStateException, InvalidCipherTextException {
		cipher.reset();
		cipher.init(false, params.getPrivateKey());

		byte[] input = Hex.decode(Str.toBytes(cipherText.toCharArray()));
		
		byte[] output = process(input);
		
		return new String(Str.toChars(output));
	}
}
