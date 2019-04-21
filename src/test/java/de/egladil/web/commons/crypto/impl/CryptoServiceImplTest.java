//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.crypto.impl;

import static org.junit.Assert.assertTrue;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * CryptoServiceImplTest
 */
public class CryptoServiceImplTest {

	@Test
	@DisplayName("should hash and verify password")
	void hashPassword() {

		// Arrange
		String cryptoAlgorithm = "SHA-256";
		String pepper = "rvLp98VcgSaZ3bwUQ5kL";
		final ByteSource salt = new SimpleByteSource(this.generateSalt(128));

		final String base64Salt = salt.toBase64();
		System.out.println("Base64-Salt=" + base64Salt);
		char[] password = "errätst du nie hehehe".toCharArray();
		Integer iterations = 40;

		CryptoServiceImpl service = new CryptoServiceImpl();

		final Hash computedHash = service.hashPassword(password, cryptoAlgorithm, salt, pepper, iterations);

		final String base64Hash = computedHash.toBase64();
		System.out.println("Base64-Hash=" + base64Hash);

		// prüfen
		assertTrue(service.verifyPassword(password, base64Hash, base64Salt, pepper, cryptoAlgorithm, computedHash.getIterations()));
	}

	/**
	 * Generiert ein Salz der gegebenen Länge. Zu Testzwecken nicht private.
	 *
	 * @param saltLengthBits int ist in Bits, d.h. Ergebnis ist 1/8 so lang.
	 * @return
	 */
	private char[] generateSalt(final int saltLengthBits) {
		final int byteSize = saltLengthBits / 8; // generatedSaltSize is in *bits* - convert to byte size:
		return new SecureRandomNumberGenerator().nextBytes(byteSize).toBase64().toCharArray();
	}

}
