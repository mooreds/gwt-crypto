package com.googlecode.gwt.crypto.bouncycastle.crypto;

import com.google.gwt.core.client.GWT;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
import com.googlecode.gwt.crypto.bouncycastle.engines.DESEngine;
import com.googlecode.gwt.crypto.bouncycastle.paddings.BlockCipherPadding;
import com.googlecode.gwt.crypto.bouncycastle.paddings.PKCS7Padding;
import com.googlecode.gwt.crypto.bouncycastle.paddings.PaddedBufferedBlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.paddings.ZeroBytePadding;
import com.googlecode.gwt.crypto.bouncycastle.params.KeyParameter;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;
import com.googlecode.gwt.crypto.util.SecureRandom;
/**
 * General Padding tests.
 */
public class PaddingTest
    extends SimpleTest
{
    public PaddingTest()
    {
    }

    private void blockCheck(
        PaddedBufferedBlockCipher   cipher,
        BlockCipherPadding          padding,
        KeyParameter                key,
        byte[]                      data)
    {
        byte[]  out = new byte[data.length + 8];
        byte[]  dec = new byte[data.length];
        
        try
        {                
            cipher.init(true, key);
            
            int    len = cipher.processBytes(data, 0, data.length, out, 0);
            
            len += cipher.doFinal(out, len);
            
            cipher.init(false, key);
            
            int    decLen = cipher.processBytes(out, 0, len, dec, 0);
            
            decLen += cipher.doFinal(dec, decLen);
            
            if (!areEqual(data, dec))
            {
                fail("failed to decrypt - i = " + data.length + ", padding = " + padding.getPaddingName());
            }
        }
        catch (Exception e)
        {
            fail("Exception - " + e.toString(), e);
        }
    }
    
    public void testPadding(
        BlockCipherPadding  padding,
        SecureRandom        rand,
        byte[]              ffVector,
        byte[]              ZeroVector)
    {
        PaddedBufferedBlockCipher    cipher = new PaddedBufferedBlockCipher(new DESEngine(), padding);
        KeyParameter                 key = new KeyParameter(Hex.decode("0011223344556677"));
        
        //
        // ff test
        //
        byte[]    data = { (byte)0xff, (byte)0xff, (byte)0xff, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0 };
        
        if (ffVector != null)
        {
            padding.addPadding(data, 3);
            
            if (!areEqual(data, ffVector))
            {
                fail("failed ff test for " + padding.getPaddingName());
            }
        }
        
        //
        // zero test
        //
        if (ZeroVector != null)
        {
            data = new byte[8];
            padding.addPadding(data, 4);
            
            if (!areEqual(data, ZeroVector))
            {
                fail("failed zero test for " + padding.getPaddingName());
            }
        }
        
        for (int i = 1; i != (GWT.isProdMode() ? 20 : 200); i++)
        {
            data = new byte[i];
            
            rand.nextBytes(data);

            blockCheck(cipher, padding, key, data);
        }
    }
    
    @Override
	public void performTest()
    {
        SecureRandom    rand = new SecureRandom(new byte[20]);
        
        rand.setSeed(System.currentTimeMillis());
        
        testPadding(new PKCS7Padding(), rand,
                                    Hex.decode("ffffff0505050505"),
                                    Hex.decode("0000000004040404"));

        PKCS7Padding padder = new PKCS7Padding();
        try
        {
            padder.padCount(new byte[8]);

            fail("invalid padding not detected");
        }
        catch (InvalidCipherTextException e)
        {
            if (!"pad block corrupted".equals(e.getMessage()))
            {
                fail("wrong exception for corrupt padding: " + e);
            }
        } 

        /*testPadding(new ISO10126d2Padding(), rand,
                                    null,
                                    null);
        
        testPadding(new X923Padding(), rand,
                                    null,
                                    null);

        testPadding(new TBCPadding(), rand,
                                    Hex.decode("ffffff0000000000"),
                                    Hex.decode("00000000ffffffff"));*/

        testPadding(new ZeroBytePadding(), rand,
                                    Hex.decode("ffffff0000000000"),
                                    null);
        
        /*testPadding(new ISO7816d4Padding(), rand,
                                    Hex.decode("ffffff8000000000"),
                                    Hex.decode("0000000080000000"));*/
    }
}
