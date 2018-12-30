//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.jwt;

import java.io.IOException;
import java.io.InputStream;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import de.egladil.web.commons.payload.ResponsePayload;

/**
 * JWTVerifierWrapper wrapped eine auth0- JWTVerifyer
 */
public class JWTVerifierWrapper {

	/**
	 * Verifiziert das gegebene token mit dem gegebenen publicKey.
	 *
	 * @param token String das token
	 * @param publicKeyData byte[] the PublicKeyBytes
	 * @return DecodedJWT
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
	 *
	 * Verifiziert das gegebene token mit dem gegebenen publicKey, der aus der URL geholt wird.
	 *
	 * @param token String the idToken
	 * @param certificateUrl URL zum PublicKey
	 * @return DecodedJWT
	 * @throws RuntimeException
	 * @throws JWTVerificationException
	 */
	public DecodedJWT verify(final String token, final String certificateUrl)
		throws RuntimeException, JWTVerificationException {

		URLConnection conn = null;
		try {
			URL url = new URL(certificateUrl);
			conn = url.openConnection();

			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "*/*");

			byte[] cert = this.readPublicKey(conn);
			return this.verify(token, cert);

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private byte[] readPublicKey(final URLConnection conn) throws IOException {

		try (InputStream in = conn.getInputStream()) {
			ObjectMapper objectMapper = new ObjectMapper();
			ResponsePayload payload = objectMapper.readValue(in, ResponsePayload.class);
			if ("INFO".equals(payload.getMessage().getLevel())) {
				return payload.getData().toString().getBytes();
			}

			throw new RuntimeException(payload.getMessage().getMessage());
		}

	}
}
