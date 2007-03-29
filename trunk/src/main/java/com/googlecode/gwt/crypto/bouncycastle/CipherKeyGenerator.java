package com.googlecode.gwt.crypto.bouncycastle;

import com.googlecode.gwt.crypto.util.SecureRandom;

//import org.bouncycastle.java.security.SecureRandom;

/**
 * The base class for symmetric, or secret, cipher key generators.
 */
public class CipherKeyGenerator {
	protected SecureRandom random;
	protected int strength;

	/**
	 * initialise the key generator.
	 * 
	 * @param param
	 *            the parameters to be used for key generation
	 */
	public void init(KeyGenerationParameters param) {
		this.random = param.getRandom();
		this.strength = (param.getStrength() + 7) / 8;
	}

	/**
	 * generate a secret key.
	 * 
	 * @return a byte array containing the key value.
	 */
	public byte[] generateKey() {
		byte[] key = new byte[strength];

		random.nextBytes(key);

		return key;
	}
}
