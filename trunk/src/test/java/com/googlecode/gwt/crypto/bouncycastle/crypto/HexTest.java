package com.googlecode.gwt.crypto.bouncycastle.crypto;

import com.googlecode.gwt.crypto.bouncycastle.util.encoders.HexEncoder;

public class HexTest extends AbstractCoderTest
{
    @Override
	protected void gwtSetUp()
    {
        super.gwtSetUp();
        enc = new HexEncoder();
    }

    @Override
	protected char paddingChar()
    {
        return 0;
    }

    @Override
	protected boolean isEncodedChar(char c)
    {
        if ('A' <= c && c <= 'F')
        {
            return true;
        } 
        if ('a' <= c && c <= 'f')
        {
            return true;
        } 
        if ('0' <= c && c <= '9')
        {
            return true;
        } 
        return false;
    }

}
