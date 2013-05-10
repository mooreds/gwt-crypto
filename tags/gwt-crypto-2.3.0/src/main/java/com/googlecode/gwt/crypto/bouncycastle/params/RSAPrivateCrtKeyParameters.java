package com.googlecode.gwt.crypto.bouncycastle.params;

import java.math.BigInteger;

import com.googlecode.gwt.crypto.bouncycastle.engines.RSAEngine;

/**
 * Not really deprecated just untested, <b>use at your own risk</b>
 * this key uses different computational approach in {@link RSAEngine}
 * @author SHadoW
 *
 */
public class RSAPrivateCrtKeyParameters
    extends RSAKeyParameters
{
    private BigInteger  e;
    private BigInteger  p;
    private BigInteger  q;
    private BigInteger  dP;
    private BigInteger  dQ;
    private BigInteger  qInv;

    /**
     * 
     */
    @Deprecated
    public RSAPrivateCrtKeyParameters(
        BigInteger  modulus,
        BigInteger  publicExponent,
        BigInteger  privateExponent,
        BigInteger  p,
        BigInteger  q,
        BigInteger  dP,
        BigInteger  dQ,
        BigInteger  qInv)
    {
        super(true, modulus, privateExponent);

        this.e = publicExponent;
        this.p = p;
        this.q = q;
        this.dP = dP;
        this.dQ = dQ;
        this.qInv = qInv;
    }

    public BigInteger getPublicExponent()
    {
        return e;
    }

    public BigInteger getP()
    {
        return p;
    }

    public BigInteger getQ()
    {
        return q;
    }

    public BigInteger getDP()
    {
        return dP;
    }

    public BigInteger getDQ()
    {
        return dQ;
    }

    public BigInteger getQInv()
    {
        return qInv;
    }
}
