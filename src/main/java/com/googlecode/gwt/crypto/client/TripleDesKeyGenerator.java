package com.googlecode.gwt.crypto.client;

import com.googlecode.gwt.crypto.bouncycastle.KeyGenerationParameters;
import com.googlecode.gwt.crypto.bouncycastle.generators.DESedeKeyGenerator;
import com.googlecode.gwt.crypto.bouncycastle.params.DESedeParameters;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;
import com.googlecode.gwt.crypto.util.SecureRandom;
import com.googlecode.gwt.crypto.util.Str;


public class TripleDesKeyGenerator {
	private static final SecureRandom secureRandom = new SecureRandom();

	public TripleDesKeyGenerator() {
		super();
	}

	public byte[] generateNewKey() {
		KeyGenerationParameters keyGenerationParameters = new KeyGenerationParameters(
				secureRandom, DESedeParameters.DES_EDE_KEY_LENGTH * 8);
		DESedeKeyGenerator keyGenerator = new DESedeKeyGenerator();
		keyGenerator.init(keyGenerationParameters);
		byte[] key = keyGenerator.generateKey();
		return key;
	}

	public String encodeKey(byte[] key) {
		byte[] keyhex = Hex.encode(key);
		return new String(Str.toChars(keyhex));

	}

	public byte[] decodeKey(String testHexKey) {
		byte[] key = Hex.decode(testHexKey);
		return key;
	}

}
