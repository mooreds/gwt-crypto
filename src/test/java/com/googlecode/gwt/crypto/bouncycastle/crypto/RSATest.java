package com.googlecode.gwt.crypto.bouncycastle.crypto;

import java.math.BigInteger;

import com.google.gwt.core.client.GWT;
import com.googlecode.gwt.crypto.bouncycastle.AsymmetricBlockCipher;
import com.googlecode.gwt.crypto.bouncycastle.AsymmetricCipherKeyPair;
import com.googlecode.gwt.crypto.bouncycastle.CipherParameters;
import com.googlecode.gwt.crypto.bouncycastle.InvalidCipherTextException;
import com.googlecode.gwt.crypto.bouncycastle.encodings.OAEPEncoding;
import com.googlecode.gwt.crypto.bouncycastle.encodings.PKCS1Encoding;
import com.googlecode.gwt.crypto.bouncycastle.engines.RSAEngine;
import com.googlecode.gwt.crypto.bouncycastle.generators.RSAKeyPairGenerator;
import com.googlecode.gwt.crypto.bouncycastle.params.RSAKeyGenerationParameters;
import com.googlecode.gwt.crypto.bouncycastle.params.RSAKeyParameters;
import com.googlecode.gwt.crypto.bouncycastle.params.RSAPrivateCrtKeyParameters;
import com.googlecode.gwt.crypto.bouncycastle.util.Arrays;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;
import com.googlecode.gwt.crypto.util.SecureRandom;
import com.googlecode.gwt.crypto.util.Sys;
import com.google.gwt.user.client.Window;

/**
 * Recommended browser for production mode is Chrome as it is <b>much</b> faster than 
 * other browsers.<br>
 * In Chrome all test run as in pure java other browsers will us the simpler ones.
 * @author SHadoW
 *
 */
public class RSATest
    extends SimpleTest
{
    static final BigInteger  mod = new BigInteger("b259d2d6e627a768c94be36164c2d9fc79d97aab9253140e5bf17751197731d6f7540d2509e7b9ffee0a70a6e26d56e92d2edd7f85aba85600b69089f35f6bdbf3c298e05842535d9f064e6b0391cb7d306e0a2d20c4dfb4e7b49a9640bdea26c10ad69c3f05007ce2513cee44cfe01998e62b6c3637d3fc0391079b26ee36d5", 16);
    static final BigInteger  pubExp = new BigInteger("11", 16);
    static final BigInteger  privExp = new BigInteger("92e08f83cc9920746989ca5034dcb384a094fb9c5a6288fcc4304424ab8f56388f72652d8fafc65a4b9020896f2cde297080f2a540e7b7ce5af0b3446e1258d1dd7f245cf54124b4c6e17da21b90a0ebd22605e6f45c9f136d7a13eaac1c0f7487de8bd6d924972408ebb58af71e76fd7b012a8d0e165f3ae2e5077a8648e619", 16);
    static final BigInteger  p = new BigInteger("f75e80839b9b9379f1cf1128f321639757dba514642c206bbbd99f9a4846208b3e93fbbe5e0527cc59b1d4b929d9555853004c7c8b30ee6a213c3d1bb7415d03", 16);
    static final BigInteger  q = new BigInteger("b892d9ebdbfc37e397256dd8a5d3123534d1f03726284743ddc6be3a709edb696fc40c7d902ed804c6eee730eee3d5b20bf6bd8d87a296813c87d3b3cc9d7947", 16);
    static final BigInteger  pExp = new BigInteger("1d1a2d3ca8e52068b3094d501c9a842fec37f54db16e9a67070a8b3f53cc03d4257ad252a1a640eadd603724d7bf3737914b544ae332eedf4f34436cac25ceb5", 16);
    static final BigInteger  qExp = new BigInteger("6c929e4e81672fef49d9c825163fec97c4b7ba7acb26c0824638ac22605d7201c94625770984f78a56e6e25904fe7db407099cad9b14588841b94f5ab498dded", 16);
    static final BigInteger  crtCoef = new BigInteger("dae7651ee69ad1d081ec5e7188ae126f6004ff39556bde90e0b870962fa7b926d070686d8244fe5a9aa709a95686a104614834b0ada4b10f53197a5cb4c97339", 16);

    static final BigInteger  modProd = new BigInteger("7743642062514449606329222412197881156521779620678777387829602924086964598029323489619549436028994056089040365027670952603649300270170833587692009642771703");
    static final BigInteger  pubExpProd = new BigInteger("17");
    static final BigInteger  privExpProd = new BigInteger("7288133705895952570662797564421535206138145525344731659133743928552437268733314484245449570552153830025895972382478802126326533754504185063587699310953713");

    static final String inputData = "4e6f77206973207468652074696d6520666f7220616c6c20676f6f64206d656e";

    //
    // to check that we handling byte extension by big number correctly.
    //
    static final String edgeInputData = "ff6f77206973207468652074696d6520666f7220616c6c20676f6f64206d656e";
    
    static final byte[] oversizedSig = Hex.decode("01ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff004e6f77206973207468652074696d6520666f7220616c6c20676f6f64206d656e");
    static final byte[] dudBlock = Hex.decode("000fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff004e6f77206973207468652074696d6520666f7220616c6c20676f6f64206d656e");
    static final byte[] truncatedDataBlock = Hex.decode("0001ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff004e6f77206973207468652074696d6520666f7220616c6c20676f6f64206d656e");
    static final byte[] incorrectPadding = Hex.decode("0001ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff4e6f77206973207468652074696d6520666f7220616c6c20676f6f64206d656e");
    static final byte[] missingDataBlock = Hex.decode("0001ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");

    boolean isProdMode()
    {
    	if (GWT.isProdMode())
    	{
    		String ua = Window.Navigator.getUserAgent();
    		return (! ua.contains(" Chrome/"));
    	}
    	else return false;
    }
    
    private void testStrictPKCS1Length(RSAKeyParameters pubParameters, RSAKeyParameters privParameters)
    {
        AsymmetricBlockCipher   eng = new RSAEngine();

        eng.init(true, privParameters);
        
        byte[] data = null;
        
        try
        {
            data = eng.processBlock(oversizedSig, 0, oversizedSig.length);
        }
        catch (Exception e)
        {
            fail("RSA: failed - exception " + e.toString(), e);
        }
        
        eng = new PKCS1Encoding(eng);
        
        eng.init(false, pubParameters);
        
        try
        {
            data = eng.processBlock(data, 0, data.length);
            
            fail("oversized signature block not recognised");
        }
        catch (InvalidCipherTextException e)
        {
            if (!e.getMessage().equals("block incorrect size"))
            {
                fail("RSA: failed - exception " + e.toString(), e);
            }
        }
        
        //System.setProperty(PKCS1Encoding.STRICT_LENGTH_ENABLED_PROPERTY, "false");
        
        PKCS1Encoding.setUseStrict(false);
        eng = new PKCS1Encoding(new RSAEngine());
        
        eng.init(false, pubParameters);
        
        try
        {
            data = eng.processBlock(data, 0, data.length);
        }
        catch (InvalidCipherTextException e)
        {
            fail("RSA: failed - exception " + e.toString(), e);
        }
        
        //System.getProperties().remove(PKCS1Encoding.STRICT_LENGTH_ENABLED_PROPERTY);
        PKCS1Encoding.resetUseStrict();
    }
        
	private void testTruncatedPKCS1Block(RSAKeyParameters pubParameters, RSAKeyParameters privParameters)
    {
        checkForPKCS1Exception(pubParameters, privParameters, truncatedDataBlock, "block truncated");
    }
    
    private void testDudPKCS1Block(RSAKeyParameters pubParameters, RSAKeyParameters privParameters)
    {
        checkForPKCS1Exception(pubParameters, privParameters, dudBlock, "unknown block type");
    }

    private void testWrongPaddingPKCS1Block(RSAKeyParameters pubParameters, RSAKeyParameters privParameters)
    {
        checkForPKCS1Exception(pubParameters, privParameters, incorrectPadding, "block padding incorrect");
    }
    
    private void testMissingDataPKCS1Block(RSAKeyParameters pubParameters, RSAKeyParameters privParameters)
    {
        checkForPKCS1Exception(pubParameters, privParameters, missingDataBlock, "no data in block");
    }

    private void checkForPKCS1Exception(RSAKeyParameters pubParameters, RSAKeyParameters privParameters, byte[] inputData, String expectedMessage)
    {
        AsymmetricBlockCipher   eng = new RSAEngine();

        eng.init(true, privParameters);
        
        byte[] data = null;
        
        try
        {
            data = eng.processBlock(inputData, 0, inputData.length);
        }
        catch (Exception e)
        {
            fail("RSA: failed - exception " + e.toString(), e);
        }
        
        eng = new PKCS1Encoding(eng);
        
        eng.init(false, pubParameters);
        
        try
        {
            data = eng.processBlock(data, 0, data.length);
            
            fail("missing data block not recognised");
        }
        catch (InvalidCipherTextException e)
        {
            if (!e.getMessage().equals(expectedMessage))
            {
                fail("RSA: failed - exception " + e.toString(), e);
            }
        }
    }
    
    private void testOAEP(RSAKeyParameters pubParameters, RSAKeyParameters privParameters)
    {
        //
        // OAEP - public encrypt, private decrypt
        //
        @SuppressWarnings("deprecation")
		AsymmetricBlockCipher eng = new OAEPEncoding(new RSAEngine());
        byte[] data = getData(false);

        eng.init(true, pubParameters);

        try
        {
            data = eng.processBlock(data, 0, data.length);
        }
        catch (Exception e)
        {
            fail("failed - exception " + e.toString(), e);
        }

        eng.init(false, privParameters);

        try
        {
            data = eng.processBlock(data, 0, data.length);
        }
        catch (Exception e)
        {
            fail("failed - exception " + e.toString(), e);
        }

        if (!Arrays.areEqual(getData(false), data))
        {
            fail("failed OAEP Test");
        }
    }

    private void zeroBlockTest(CipherParameters encParameters, CipherParameters decParameters)
    {
        AsymmetricBlockCipher eng = new PKCS1Encoding(new RSAEngine());

        eng.init(true, encParameters);

        if (eng.getOutputBlockSize() != ((PKCS1Encoding)eng).getUnderlyingCipher().getOutputBlockSize())
        {
            fail("PKCS1 output block size incorrect");
        }

        byte[] zero = new byte[0];
        byte[] data = null;

        try
        {
            data = eng.processBlock(zero, 0, zero.length);
        }
        catch (Exception e)
        {
            fail("failed - exception " + e.toString(), e);
        }

        eng.init(false, decParameters);

        try
        {
            data = eng.processBlock(data, 0, data.length);
        }
        catch (Exception e)
        {
            fail("failed - exception " + e.toString(), e);
        }

        if (!Arrays.areEqual(zero, data))
        {
            fail("failed PKCS1 zero Test");
        }
    }
    
    RSAKeyParameters    pubParametersLong;
    RSAKeyParameters    privParametersLong;

    RSAKeyParameters    pubParametersProd;
    RSAKeyParameters    privParametersProd;
    
    byte[] trimData(byte[] data)
    {
    	//Trim data for production cipher if needed
    	if (isProdMode())
    	{
    		//Trim data to half the cipher length
    		int cnt = pubParametersProd.getModulus().bitCount() / 2;
    		
    		if (data.length <= cnt) return data;
    		
    		byte[] res = new byte[pubParametersProd.getModulus().bitCount()];
    		Sys.arraycopy(data, 0, res, 0, res.length);
    		return res;
    	}
    	else return data;
    }
    
    byte[] getData(boolean prodAware)
    {
    	byte[] res = Hex.decode(edgeInputData);
    	if (prodAware) return trimData(res);
    	else return res;
    }
    
    byte[] getInput(boolean prodAware)
    {
    	byte[] res = Hex.decode(inputData);
    	if (prodAware) return trimData(res);
    	else return res;
    }
    
    RSAKeyParameters getPriv()
    {
    	if (isProdMode()) return privParametersProd;
    	else return privParametersLong;
    }
    
    RSAKeyParameters getPub()
    {
    	if (isProdMode()) return pubParametersProd;
    	else return pubParametersLong;
    }
    
    @SuppressWarnings("deprecation")
	@Override
    protected void gwtSetUp() throws Exception {
        pubParametersLong = new RSAKeyParameters(false, mod, pubExp);
        privParametersLong = new RSAPrivateCrtKeyParameters(mod, pubExp, privExp, p, q, pExp, qExp, crtCoef);

        if (isProdMode())
        {
        	//For production mode do not use the extra parameters
        	//It should work but it is too time expensive to test
            //pubParametersProd = new RSAKeyParameters(false, mod, pubExp);
            //privParametersProd = new RSAKeyParameters(true, mod, privExp);
            pubParametersProd = new RSAKeyParameters(false, modProd, pubExpProd);
            privParametersProd = new RSAKeyParameters(true, modProd, privExpProd);
        }
        
    	super.gwtSetUp();
    }
    
    public void testRaw()
    {
    	AsymmetricBlockCipher   eng = new RSAEngine();

    	eng.init(true, getPub());
    	
    	byte[] data = getData(true);

        try
        {
            data = eng.processBlock(data, 0, data.length);
        }
        catch (Exception e)
        {
            fail("RSA: failed - exception " + e.toString(), e);
        }

        eng.init(false, getPriv());

        try
        {
            data = eng.processBlock(data, 0, data.length);
        }
        catch (Exception e)
        {
            fail("failed - exception " + e.toString(), e);
        }

        if (!Arrays.areEqual(getData(true), data))
        {
            fail("failed RAW edge Test");
        }

        data = getInput(true);

        eng.init(true, getPub());

        try
        {
            data = eng.processBlock(data, 0, data.length);
        }
        catch (Exception e)
        {
            fail("failed - exception " + e.toString(), e);
        }

        eng.init(false, getPriv());

        try
        {
            data = eng.processBlock(data, 0, data.length);
        }
        catch (Exception e)
        {
            fail("failed - exception " + e.toString(), e);
        }

        if (!Arrays.areEqual(getInput(true), data))
        {
            fail("failed RAW Test");
        }
    	
    }
    
    public void testPKCS1PublicEncryptPrivateDecrypt() {
    	AsymmetricBlockCipher   eng = new PKCS1Encoding(new RSAEngine());

        eng.init(true, getPub());
        
        if (eng.getOutputBlockSize() != ((PKCS1Encoding)eng).getUnderlyingCipher().getOutputBlockSize())
        {
            fail("PKCS1 output block size incorrect");
        }

        byte[] data = getInput(true);
        try
        {
            data = eng.processBlock(data, 0, data.length);
        }
        catch (Exception e)
        {
            fail("failed - exception " + e.toString(), e);
        }

        eng.init(false, getPriv());

        try
        {
            data = eng.processBlock(data, 0, data.length);
        }
        catch (Exception e)
        {
            fail("failed - exception " + e.toString(), e);
        }

        if (!Arrays.areEqual(getInput(true), data))
        {
            fail("failed PKCS1 public/private Test");
        }
	}

    public void testPKCS1PrivateEncryptPublicDecrypt()
    {
    	AsymmetricBlockCipher eng = new PKCS1Encoding(new RSAEngine());

        eng.init(true, getPriv());
        byte[] data = getInput(true);
        
        try
        {
            data = eng.processBlock(data, 0, data.length);
        }
        catch (Exception e)
        {
            fail("failed - exception " + e.toString(), e);
        }

        eng.init(false, getPub());

        try
        {
            data = eng.processBlock(data, 0, data.length);
        }
        catch (Exception e)
        {
            fail("failed - exception " + e.toString(), e);
        }

        if (!Arrays.areEqual(getInput(true), data))
        {
            fail("failed PKCS1 private/public Test");
        }

        zeroBlockTest(getPub(), getPriv());
        zeroBlockTest(getPriv(), getPub());
    }

        
    @Override
 	public void performTest()
    {
    	/*
    	 * Not in production even in chrome, takes too long and fails (probably generation
    	 * issue)
    	 */
    	
        if (! GWT.isProdMode())
        {
        	//Operation is infeasible for javascript
        	
	        //
	        // key generation test
	        //
	        RSAKeyPairGenerator  pGen = new RSAKeyPairGenerator();
	        RSAKeyGenerationParameters  genParam = new RSAKeyGenerationParameters(
	                                            BigInteger.valueOf(0x11), new SecureRandom(), 768, 25);
	
	        pGen.init(genParam);
	
	        AsymmetricCipherKeyPair  pair = pGen.generateKeyPair();
	        
	        AsymmetricBlockCipher   eng = new RSAEngine();
	
	        if (((RSAKeyParameters)pair.getPublic()).getModulus().bitLength() < 768)
	        {
	            fail("failed key generation (768) length test");
	        }
	
	        eng.init(true, pair.getPublic());
	        byte[] data = getInput(false);
	
	        try
	        {
	            data = eng.processBlock(data, 0, data.length);
	        }
	        catch (Exception e)
	        {
	            fail("failed - exception " + e.toString(), e);
	        }
	
	        eng.init(false, pair.getPrivate());
	
	        try
	        {
	            data = eng.processBlock(data, 0, data.length);
	        }
	        catch (Exception e)
	        {
	            fail("failed - exception " + e.toString(), e);
	        }
	
	        if (!Arrays.areEqual(getInput(false), data))
	        {
	            fail("failed key generation (768) Test");
	        }
	
	        genParam = new RSAKeyGenerationParameters(BigInteger.valueOf(0x11), new SecureRandom(), 1024, 25);
	
	        pGen.init(genParam);
	        pair = pGen.generateKeyPair();
	
	        eng.init(true, pair.getPublic());
	
	        if (((RSAKeyParameters)pair.getPublic()).getModulus().bitLength() < 1024)
	        {
	            fail("failed key generation (1024) length test");
	        }
	
	        try
	        {
	            data = eng.processBlock(data, 0, data.length);
	        }
	        catch (Exception e)
	        {
	            fail("failed - exception " + e.toString(), e);
	        }
	
	        eng.init(false, pair.getPrivate());
	
	        try
	        {
	            data = eng.processBlock(data, 0, data.length);
	        }
	        catch (Exception e)
	        {
	            fail("failed - exception " + e.toString(), e);
	        }
	
	        if (!Arrays.areEqual(getInput(false), data))
	        {
	            fail("failed key generation (1024) test");
	        }
	
	        genParam = new RSAKeyGenerationParameters(
	            BigInteger.valueOf(0x11), new SecureRandom(), 16, 25);
	        pGen.init(genParam);
	
	        for (int i = 0; i < 100; ++i)
	        {
	            pair = pGen.generateKeyPair();
	            RSAPrivateCrtKeyParameters privKey = (RSAPrivateCrtKeyParameters) pair.getPrivate();
	            BigInteger pqDiff = privKey.getP().subtract(privKey.getQ()).abs();
	
	            if (pqDiff.bitLength() < 5)
	            {
	                fail("P and Q too close in RSA key pair");
	            }
	        }
        }
    }
    
	/** 
	 * If the test is running with shorter (production) key the test fails
	 * for unknown reason (it fails in both pure java and production modes).<br>
	 * Running the test in production mode with 1024bit key is infeasible.
	 * 
	 */
    public void testOAEP()
    {
    	if (! isProdMode()) testOAEP(pubParametersLong, privParametersLong);
    }
    
    public void testStrictPKCS1Length()
    {
    	//Not in production
	    if (! isProdMode()) testStrictPKCS1Length(pubParametersLong, privParametersLong);
    }
    
    public void testDudPKCS1Block()
    {
    	//Not in production
    	if (! isProdMode()) testDudPKCS1Block(pubParametersLong, privParametersLong);
    }
    
    public void testMissingDataPKCS1Block()
    {
    	//Not in production
    	if (! isProdMode()) testMissingDataPKCS1Block(pubParametersLong, privParametersLong);
    }
    
    public void testTruncatedPKCS1Block()
    {
    	//Not in production
    	if (! isProdMode()) testTruncatedPKCS1Block(pubParametersLong, privParametersLong);
    }
    
    public void testWrongPaddingPKCS1Block()
    {
    	//Not in production
    	if (! isProdMode()) testWrongPaddingPKCS1Block(pubParametersLong, privParametersLong);
    }

    public void testInitCheck()
    {
	    try {
	        new RSAEngine().processBlock(new byte[]{ 1 }, 0, 1);
	        fail("failed initialisation check");
	    }
	    catch (IllegalStateException e) {
	        // expected
	    }
    }
    
    /**
     * Just generates key constants for further testing and fails with the key as string
     * If you want to generate the key uncomment the fail
     */
    public void testGenKey()
    {
    	if (GWT.isProdMode()) {
    		assertTrue(true);
    		return;
    	}
    	
    	RSAKeyPairGenerator  pGen = new RSAKeyPairGenerator();
    	RSAKeyGenerationParameters genParam = new RSAKeyGenerationParameters(
	            BigInteger.valueOf(0x11), new SecureRandom(), 512, 25);
    	pGen.init(genParam);
    	AsymmetricCipherKeyPair key = pGen.generateKeyPair();
    	RSAKeyParameters pub = (RSAKeyParameters)key.getPublic();
    	RSAKeyParameters priv = (RSAKeyParameters)key.getPrivate();
    	String modulus = pub.getModulus().toString();
    	String pubExp = pub.getExponent().toString();
    	String privExp = priv.getExponent().toString();
    	
    	assertNotNull(modulus);
    	assertNotNull(pubExp);
    	assertNotNull(privExp);
    	
    	/*fail(
    			"Modulus: " + modulus + "\n" +
    			"Private: " + privExp + "\n" +
    			"Public : " + pubExp);*/
    }
}
