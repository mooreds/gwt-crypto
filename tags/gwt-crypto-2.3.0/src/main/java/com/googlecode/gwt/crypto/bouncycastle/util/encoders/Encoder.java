package com.googlecode.gwt.crypto.bouncycastle.util.encoders;

import com.googlecode.gwt.crypto.gwtx.io.IOException;
import com.googlecode.gwt.crypto.gwtx.io.OutputStream;

/**
 * Encode and decode byte arrays (typically from binary to 7-bit ASCII 
 * encodings).
 */
public interface Encoder
{
    int encode(byte[] data, int off, int length, OutputStream out) throws IOException;
    
    int decode(byte[] data, int off, int length, OutputStream out) throws IOException;

    int decode(String data, OutputStream out) throws IOException;
}
