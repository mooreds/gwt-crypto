package com.googlecode.gwt.crypto.client;


public class TripleDesCipherTest extends BlockCipherTest {
	private final static byte[] key = new byte[]{
		(byte)4,(byte)8,(byte)3,(byte)80,(byte)12,(byte)-9,(byte)-5,(byte)101, 
		(byte)15,(byte)-8,(byte)3,(byte)0,(byte)90,(byte)-9,(byte)55,(byte)-41, 
		(byte)-9,(byte)90,(byte)3,(byte)100,(byte)-40,(byte)79,(byte)5,(byte)102};
	
	@Override
	protected AbstractStreamCipher getCipher() {
		return new TripleDesCipher();
	}
	
	@Override
	protected byte[] getKey() {
		return key;
	}
}
