package com.googlecode.gwt.crypto.client;

import com.googlecode.gwt.crypto.bouncycastle.CipherParameters;
import com.googlecode.gwt.crypto.bouncycastle.params.RSAKeyParameters;

public class RSAParams implements CipherParameters{
	private RSAKeyParameters publicKey = null;
	private RSAKeyParameters privateKey = null;
	
	public RSAParams() {
	}
	
	public RSAParams(RSAKeyParameters publicKey, RSAKeyParameters privateKey)
	{
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	
	public RSAKeyParameters getPublicKey() {
		return publicKey;
	}
	
	public void setPublicKey(RSAKeyParameters publicKey) {
		this.publicKey = publicKey;
	}
	
	public RSAKeyParameters getPrivateKey() {
		return privateKey;
	}
	
	public void setPrivateKey(RSAKeyParameters privateKey) {
		this.privateKey = privateKey;
	}
}
