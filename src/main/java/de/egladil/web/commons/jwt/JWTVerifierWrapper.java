//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.jwt;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import de.egladil.web.commons.net.ContentReader;
import de.egladil.web.commons.net.ResponsePayloadReader;

/**
 * JWTVerifierWrapper wrapped einen auth0- JWTVerifyer. Der PublicKey wird nach dem ersten Aufruf gelesen und dann nicht
 * mehr geändert. D.h., wenn auth-cert ausgetauscht wird, müssen alle Anwendungen durchgestartet werden.
 */
public class JWTVerifierWrapper {

	private static JWTVerifierWrapper instance;

	private byte[] certData;

	private final ContentReader contentReader = new ResponsePayloadReader();

	public static JWTVerifierWrapper getInstance() {
		if (instance == null) {
			instance = new JWTVerifierWrapper();
		}
		return instance;
	}

	/**
	 * Erzeugt eine Instanz von JWTVerifierWrapper
	 *
	 * @param certificateVersion int der publicKey wird so lange gecached, wie die geforderte Version
	 */
	private JWTVerifierWrapper() {
		this.certData = null;
	}

	/**
	 * Verifiziert das gegebene token mit dem gegebenen publicKey.
	 *
	 * @param token String das token
	 * @param publicKeyData byte[] the PublicKeyBytes
	 * @return DecodedJWT
	 * @throws RuntimeException bei Problemen mit Crytographie.
	 * @throws JWTVerificationException wenn das token nicht valide ist.
	 */
	private DecodedJWT verify(final String token, final byte[] publicKeyData) throws RuntimeException, JWTVerificationException {

		KeyFactory factory;
		try {
			factory = KeyFactory.getInstance("RSA", "BC");
			X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyData);
			RSAPublicKey publicKey = (RSAPublicKey) factory.generatePublic(publicKeySpec);
			Algorithm algorithm = Algorithm.RSA256(publicKey, null);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("heike2718/authprovider").build();
			DecodedJWT jwt = verifier.verify(token);
			return jwt;
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e) {
			throw new RuntimeException("", e);
		}
	}

	/**
	 * Verifiziert das gegebene token mit dem gegebenen publicKey, der aus der URL geholt wird.
	 *
	 * @param token String the idToken
	 * @param certificateUrl URL zum PublicKey
	 * @param certificateVersion int
	 * @return DecodedJWT
	 * @throws RuntimeException
	 * @throws JWTVerificationException
	 */
	public DecodedJWT verify(final String token, final String certificateUrl) throws RuntimeException, JWTVerificationException {

		try {
			if (this.certData == null) {

				URL url = new URL(certificateUrl);
				URLConnection conn = url.openConnection();

				conn.setRequestProperty("Content-type", "application/json");
				conn.setRequestProperty("Accept", "*/*");

				this.certData = this.contentReader.getBytes(conn);
			}

			return this.verify(token, this.certData);

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
