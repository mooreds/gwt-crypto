package com.googlecode.gwt.crypto.bouncycastle.crypto;

import com.google.gwt.core.client.GWT;
import com.googlecode.gwt.crypto.bouncycastle.BlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.BufferedBlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.CipherParameters;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

/**
 * a basic test that takes a cipher, key parameter, and an input
 * and output string. This test wraps the engine in a buffered block
 * cipher with padding disabled.
 */
public class BlockCipherMonteCarloTest
    extends SimpleTest
{
    int                 id;
    int                 iterations;
    BlockCipher         engine;
    CipherParameters    param;
    byte[]              input;
    byte[]              output;

    public BlockCipherMonteCarloTest(
        int                 id,
        int                 iterations,
        BlockCipher         engine,
        CipherParameters    param,
        String              input,
        String              output)
    {
        this.id = id;
        this.iterations = iterations;
        this.engine = engine;
        this.param = param;
        this.input = Hex.decode(input);
        this.output = Hex.decode(output);
    }

    @Override
	public void performTest()
        throws Exception
    {
        BufferedBlockCipher cipher = new BufferedBlockCipher(engine);
        
        if (GWT.isProdMode() && (iterations > 100))
        {
        	//Infeasible iterations for javascript
        	iterations /= 100;
        	if (iterations < 25) iterations = 25; 
        }

        cipher.init(true, param);

        byte[]  out = new byte[input.length];

        System.arraycopy(input, 0, out, 0, out.length);

        for (int i = 0; i != iterations; i++)
        {
            int len1 = cipher.processBytes(out, 0, out.length, out, 0);

            cipher.doFinal(out, len1);
        }

        if (! GWT.isProdMode())
        {
        	if (!areEqual(out, output))
        	{
        		fail("MonteCarlo failed - " + "expected " + new String(Hex.encode(output)) + " got " + new String(Hex.encode(out)));
        	}
        }
        //else this can't possibly succeed in JS because pre-computed value is for more iterations

        cipher.init(false, param);

        for (int i = 0; i != iterations; i++)
        {
            int len1 = cipher.processBytes(out, 0, out.length, out, 0);

            cipher.doFinal(out, len1);
        }

        if (!areEqual(input, out))
        {
            fail("failed reversal");
        }
    }
}
