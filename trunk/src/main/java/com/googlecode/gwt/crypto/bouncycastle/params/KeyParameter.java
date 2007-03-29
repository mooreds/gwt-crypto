package com.googlecode.gwt.crypto.bouncycastle.params;

import com.googlecode.gwt.crypto.bouncycastle.CipherParameters;
import com.googlecode.gwt.crypto.util.Sys;


public class KeyParameter implements CipherParameters {
	private byte[] key;

	public KeyParameter(byte[] key) {
		this(key, 0, key.length);
	}

	public KeyParameter(byte[] key, int keyOff, int keyLen) {
		this.key = new byte[keyLen];

		Sys.arraycopyBytes(key, keyOff, this.key, 0, keyLen);
	}

	public byte[] getKey() {
		return key;
	}
}
