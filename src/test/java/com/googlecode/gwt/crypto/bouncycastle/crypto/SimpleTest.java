package com.googlecode.gwt.crypto.bouncycastle.crypto;

import com.googlecode.gwt.crypto.common.CryptoTest;

public abstract class SimpleTest extends CryptoTest {
	@Override
	public void onModuleLoad() {
		//Do nothing
	}

    protected final void fail(
        String    message,
        Throwable throwable)
    {
        fail(message + " throws : " + throwable.getMessage());
    }
    
    protected final void fail(
        String message,
        Object expected,
        Object found)
    {
    	failNotEquals(message, expected, found);
    }
        
    protected final boolean areEqual(
        byte[] a,
        byte[] b)
    {
        return Arrays.areEqual(a, b);
    }
    
    public final void perform()
    {
    	try
    	{
    		performTest();
    		assertTrue(true);
    	}
    	catch (Exception e) 
		{
			fail("Exception: " + e.getMessage());
		}
    }
    
    @Override
    public final String getName() {
    	//TODO
    	return super.getName();
    }
    
    protected abstract void performTest() throws Exception;
    
    public final void testRun()
    {
    	perform();
    }
}
