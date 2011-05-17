package com.googlecode.gwt.crypto.common;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.junit.client.GWTTestCase;

public abstract class CryptoTest extends GWTTestCase implements EntryPoint {
	@Override
	public void onModuleLoad() {
	}
	
	@Override
	public final String getModuleName() {
		return "com.googlecode.gwt.crypto.Crypto_tests";
	}

}
