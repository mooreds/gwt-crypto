package com.googlecode.gwt.crypto.bouncycastle.crypto;

import com.googlecode.gwt.crypto.bouncycastle.BlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.DataLengthException;
import com.googlecode.gwt.crypto.bouncycastle.engines.NullEngine;
import com.googlecode.gwt.crypto.bouncycastle.params.KeyParameter;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

public class NullTest 
    extends CipherTest
{
    static SimpleTest[]  tests = 
    {
        new BlockCipherVectorTest(0, new NullEngine(),
                new KeyParameter(Hex.decode("00")), "00", "00")
    };
    
    public NullTest()
    {
        super(tests, new NullEngine(), new KeyParameter(new byte[2]));
    }

    @Override
	public void performTest()
        throws Exception
    {
        super.performTest();
        
        BlockCipher engine = new NullEngine();
        
        engine.init(true, null);
        
        byte[] buf = new byte[1];
        
        engine.processBlock(buf, 0, buf, 0);
        
        if (buf[0] != 0)
        {
            fail("NullCipher changed data!");
        }
        
        byte[] shortBuf = new byte[0];
        
        try
        {   
            engine.processBlock(shortBuf, 0, buf, 0);
            
            fail("failed short input check");
        }
        catch (DataLengthException e)
        {
            // expected 
        }
        
        try
        {   
            engine.processBlock(buf, 0, shortBuf, 0);
            
            fail("failed short output check");
        }
        catch (DataLengthException e)
        {
            // expected 
        }
    }
}
