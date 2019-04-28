//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.jwt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import de.egladil.web.commons.utils.CommonFileUtils;

/**
 * JWTVerifyerWrapperIT
 */
public class JWTVerifyerWrapperIT {

	private static final String PUB_KEY_PATH = "/home/heike/.keystore/public/authprov_public_key.pem";

	private byte[] publicKey;

	@BeforeEach
	public void setUp() throws IOException {
		publicKey = CommonFileUtils.readBytes(new File(PUB_KEY_PATH));

		// for (int counter = 0; counter < publicKey.length; counter++) {
		// System.out.print((char) publicKey[counter]);
		// }
	}

	@Test
	void verifyWithValidButExpiredJWT() throws IOException {
		// Arrange
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIzYTg2Njk5Mi01ZjE4LTRlMjItODg2OC05MGRhYzBjNzk0N2EiLCJpc3MiOiJoZWlrZTI3MTgvYXV0aHByb3ZpZGVyIiwiZXhwIjoxNTU2NDM4Mzg3LCJpYXQiOjE1NTY0MzgzODd9.UeYykmM1UiSQB5UFNV1pH1wah8s-MkSkC49SXDVexV1wC5pGtIJsfkPq26hdxEno_qUk6nsg0xEYB9NUltxzQ-9w2Tpvmote1iBpuRY4XTTrLcRWZ9bHh3OymO1lPhk5O1Z34fNSeeHnipQz02xKLKxsxVdCHIlTtx3adak_PsqJmBio6n2nOcOWo5C6vZI7L3FjXPjw2qtp7tjHMGJEGtx5fUbT07OrrkEFcoov9amTYGATbqv5eqpO7kj6jeQKEc4Y9p4CO6jevHqVTx-FhUvcTDt-GJ0nd5I6AKCy9Z8zshsAhB8aAyd44xN2nTYqi5AuVUfV1f6B7Ca1QbHqZw";

		try {
			JWTVerifierWrapper.getInstance().verify(token, publicKey);

			fail("keine TokenExpiredException");
		} catch (TokenExpiredException e) {
			assertEquals("The Token has expired on Sun Apr 28 09:59:47 CEST 2019.", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	void verifyJWTJonDoeFromJWTIO() {

		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
		try {
			JWTVerifierWrapper.getInstance().verify(token, publicKey);
			fail("keine JWTVerificationException");
		} catch (JWTVerificationException e) {
			assertEquals("The provided Algorithm doesn't match the one defined in the JWT's Header.", e.getMessage());
		}
	}

	@Test
	void verifyWithInvalidJWT() throws IOException {
		// Arrange
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIzYTg2Njk5Mi01ZjE4LTRlMjItODg2OC05MGRhYzBjNzk0N2EiLCJpc3MiOiJoZWlrZTI3MTgvYXV0aHByb3ZpZGVyIiwiZXhwIjoxNTU2NDM4Mzg3LCJpYXQiOjE1NTY0MzgzODd9.UeYykmM1UiSQB5UFNV1pH1wah8s-MkSkC49SXDVexV1wC5pGtIJsfkPq26hdxEno_qUk6nsg0xEYB9NUltxzQ-9w2Tpvmote1iBpuRY4XTTrLcRWZ9bHh3OymO1lPhk5O1Z34fNSeeHnipQz02xKLKxsxVdCHIlTtx3adak_PsqJmBio6n2nOcOWo5C6vZI7L3FjXPjw2qtp7tjHMGJEGtx5fUbT07OrrkEFcoov9amTYGATbqv5eqpO7kj6jeQKEc4Y9p4CO6jevHqVTx-FhUvcTDt-GJ0nd5I6AKCy9Z8zshsAhB8aAyd44xN2nTYqi5AuVUfV1f6B7Ca1QbHqZa";

		try {
			JWTVerifierWrapper.getInstance().verify(token, publicKey);

			fail("keine JWTVerificationException");
		} catch (JWTVerificationException e) {
			assertEquals("The Token's Signature resulted invalid when verified using the Algorithm: SHA256withRSA", e.getMessage());
		}
	}
}
