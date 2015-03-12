Brill says: In the GWT forums someone asked is anyone had tried encryption from the client side in GWT... so I did.

## Usage ##

Code examples and Install at [Usage](http://code.google.com/p/gwt-crypto/wiki/Usage)

### Update 2013-05-10: ###

wokier says: gwt-crypto is now available on maven central.

### Update 2011-05-18: ###

shadow says: I added snapshots for GWT versions 2.1.0, 2.2.0, 2.3.0 (2.0.0 can't be supported due to missing BigInteger support) to maven repo and base64 support. I have also improved unit tests for RSA production mode.

### Update 2011-05-17: ###

shadow says: I udated the code with AES and RSA encryptions and tested it in GWT 2.3.0 and SVN trunk. I also performed some code cleanup to suppress warnings. Unit tests have also been greatly updated. Backward compatibility has been maintained. I will hopefully release new version using maven soon. Everything is in the trunk so feel free to give it a shot.

### Update 2009-10-08: ###

mooreds says: I updated the code to work with GWT 1.5.3 and tested it in 1.6.4 and 1.7.1, and fixed a bug or two.  Please download it and give it a try.  _If you are using it and it needs updating, let me know or send in a patch._

Also, make sure you understand what client side encryption does and does not do for you:  [ClientSideEncryptionConsiderations](http://code.google.com/p/gwt-crypto/wiki/ClientSideEncryptionConsiderations)