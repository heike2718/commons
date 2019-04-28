//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.jwt;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import de.egladil.web.commons.error.CommonRuntimeException;
import de.egladil.web.commons.net.ContentReader;
import de.egladil.web.commons.net.ResponsePayloadReader;
import de.egladil.web.commons.utils.CommonKeyUtils;

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
	 * Erzeugt eine Instanz von JWTVerifierWrapper.
	 *
	 * @param certificateVersion int der publicKey wird so lange gecached, wie die geforderte Version
	 */
	public JWTVerifierWrapper() {
		this.certData = null;
	}

	/**
	 * Verifiziert das gegebene token mit dem gegebenen publicKey.<br>
	 * <br>
	 * Aus Testgründen Sichtbarkeit erhöht.
	 *
	 * @param token String das token
	 * @param publicKeyData byte[] the PublicKeyBytes
	 * @throws JWTVerificationException wenn das token manipuliert war
	 * @throws TokenExpiredException wenn das token nicht mehr gültig ist.
	 * @throws CommonRuntimeException bei einer IOException
	 */
	DecodedJWT verify(final String token, final byte[] publicKeyData)
		throws JWTVerificationException, TokenExpiredException, CommonRuntimeException {

		try {
			PublicKey publicKey = CommonKeyUtils.getPublicKeyRSA(publicKeyData);
			Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("heike2718/authprovider").build();
			DecodedJWT jwt = verifier.verify(token);
			return jwt;
		} catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new CommonRuntimeException("Fehler beim Verifizieren eines JWT: " + e.getMessage(), e);
		}
	}

	/**
	 * Verifiziert das gegebene token mit dem gegebenen publicKey, der aus der URL geholt wird.
	 *
	 * @param token String der JWT-String
	 * @param String die URL, mit der der PublicKey geholt werden kann.
	 *
	 * @return DecodedJWT
	 *
	 * @throws JWTVerificationException wenn das token manipuliert war
	 * @throws TokenExpiredException wenn das token nicht mehr gültig ist.
	 * @throws CommonRuntimeException bei einer IOException
	 */
	public DecodedJWT verify(final String token, final String certificateUrl)
		throws JWTVerificationException, TokenExpiredException, CommonRuntimeException {
		try {
			if (this.certData == null) {
				initPublicKey(certificateUrl);
			}

			return this.verify(token, this.certData);
		} catch (IOException e) {
			throw new CommonRuntimeException("IOException beim Holen des PublicKey aus URL '" + certificateUrl
				+ "' oder beim Umwandeln in PEM: " + e.getMessage(), e);
		}
	}

	private void initPublicKey(final String certificateUrl) throws MalformedURLException, IOException {
		URL url = new URL(certificateUrl);
		URLConnection conn = url.openConnection();

		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Accept", "*/*");

		this.certData = this.contentReader.getBytes(conn);
	}
}
