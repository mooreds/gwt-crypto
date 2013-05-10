package com.googlecode.gwt.crypto.client;

import com.googlecode.gwt.crypto.bouncycastle.CipherParameters;
import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;

public interface StreamCipher {
    public String encrypt(String plainText) throws DataLengthException,
	    IllegalStateException, InvalidCipherTextException;

    public String decrypt(String cipherText) throws DataLengthException,
	    IllegalStateException, InvalidCipherTextException;

    public void setParameters(CipherParameters params)
	    throws IllegalArgumentException;

    public CipherParameters getParameters();
}
