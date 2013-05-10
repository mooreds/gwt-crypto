package com.googlecode.gwt.crypto.bouncycastle.generators;

import com.googlecode.gwt.crypto.bouncycastle.CipherKeyGenerator;
import com.googlecode.gwt.crypto.bouncycastle.params.DESParameters;


//import org.bouncycastle.crypto.CipherKeyGenerator;
//import org.bouncycastle.crypto.params.DESParameters;

public class DESKeyGenerator
    extends CipherKeyGenerator
{
    @Override
	public byte[] generateKey()
    {
        byte[]  newKey = new byte[DESParameters.DES_KEY_LENGTH];

        do
        {
            random.nextBytes(newKey);

            DESParameters.setOddParity(newKey);
        }
        while (DESParameters.isWeakKey(newKey, 0));

        return newKey;
    }
}
