package com.googlecode.gwt.crypto.bouncycastle.crypto;

import com.googlecode.gwt.crypto.bouncycastle.BlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.BufferedBlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.CipherParameters;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

/**
 * a basic test that takes a cipher, key parameter, and an input
 * and output string. This test wraps the engine in a buffered block
 * cipher with padding disabled.
 */
public class BlockCipherVectorTest
    extends SimpleTest
{
    int                 id;
    BlockCipher         engine;
    CipherParameters    param;
    byte[]              input;
    byte[]              output;

    public BlockCipherVectorTest(
        int                 id,
        BlockCipher         engine,
        CipherParameters    param,
        String              input,
        String              output)
    {
        this.id = id;
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

        cipher.init(true, param);

        byte[]  out = new byte[input.length];

        int len1 = cipher.processBytes(input, 0, input.length, out, 0);

        cipher.doFinal(out, len1);

        if (!areEqual(out, output))
        {
            fail("failed - " + "expected " + new String(Hex.encode(output)) + " got " + new String(Hex.encode(out)));
        }

        cipher.init(false, param);

        int len2 = cipher.processBytes(output, 0, output.length, out, 0);

        cipher.doFinal(out, len2);

        if (!areEqual(input, out))
        {
            fail("failed reversal got " + new String(Hex.encode(out)));
        }
    }
}
