package com.googlecode.gwt.crypto.bouncycastle.params;

import com.googlecode.gwt.crypto.bouncycastle.CipherParameters;
import com.googlecode.gwt.crypto.util.Sys;


//import org.bouncycastle.crypto.CipherParameters;

public class ParametersWithIV
    implements CipherParameters
{
    private byte[]              iv;
    private CipherParameters    parameters;

    public ParametersWithIV(
        CipherParameters    parameters,
        byte[]              iv)
    {
        this(parameters, iv, 0, iv.length);
    }

    public ParametersWithIV(
        CipherParameters    parameters,
        byte[]              iv,
        int                 ivOff,
        int                 ivLen)
    {
        this.iv = new byte[ivLen];
        this.parameters = parameters;

        Sys.arraycopy(iv, ivOff, this.iv, 0, ivLen);
    }

    public byte[] getIV()
    {
        return iv;
    }

    public CipherParameters getParameters()
    {
        return parameters;
    }
}
