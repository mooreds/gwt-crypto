package com.googlecode.gwt.crypto.bouncycastle.crypto;

import com.googlecode.gwt.crypto.bouncycastle.util.encoders.UrlBase64Encoder;

public class UrlBase64Test extends AbstractCoderTest
{
    @Override
	protected void gwtSetUp()
    {
        super.gwtSetUp();
        enc = new UrlBase64Encoder();
    }

    @Override
	protected char paddingChar()
    {
        return '.';
    }

    @Override
	protected boolean isEncodedChar(char c)
    {
        if (Character.isLetterOrDigit(c))
        {
            return true;
        }
        else if (c == '-')
        {
            return true;
        }
        else if (c == '_')
        {
            return true;
        }
        return false;
    }
}
