//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.crypto.impl;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;

import javax.enterprise.context.RequestScoped;

import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.HashService;
import org.apache.shiro.crypto.hash.SimpleHashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import de.egladil.web.commons.crypto.CryptoService;

/**
 * CryptoServiceImpl
 */
@RequestScoped
public class CryptoServiceImpl implements CryptoService {

	/**
	 * Erzeugt eine Instanz von CryptoServiceImpl
	 */
	public CryptoServiceImpl() {
	}

	@Override
	public Hash hashPassword(final char[] password, final String algorithmName, final ByteSource salt, final String pepper,
		final Integer iterations) {

		if (password == null || password.length == 0) {
			throw new IllegalArgumentException("password null oder leer");
		}

		final HashService hashService = getHashService(pepper);

		final SimpleByteSource passwdByteSource = new SimpleByteSource(password);
		final HashRequest hashRequest = new SimpleHashRequest(algorithmName, passwdByteSource, salt, iterations);

		final Hash hash = hashService.computeHash(hashRequest);
		return hash;
	}

	@Override
	public boolean verifyPassword(final char[] password, final String persistentHashValue, final String persistentSalt,
		final String pepper, final String algorithmName, final int numberIterations) throws IllegalArgumentException {

		final ByteSource salt = new SimpleByteSource(Base64.getDecoder().decode(persistentSalt));

		final Hash expectedHash = hashPassword(password, algorithmName, salt, pepper, numberIterations);

		final String expectedHashValue = new SimpleByteSource(expectedHash.getBytes()).toBase64();

		if (MessageDigest.isEqual(expectedHashValue.getBytes(), persistentHashValue.getBytes())) {
			return true;
		}
		return false;
	}

	private HashService getHashService(final String pepper) {

		final DefaultHashService hashService = new DefaultHashService();
		hashService.setPrivateSalt(new SimpleByteSource(pepper));
		return hashService;
	}

	@Override
	public String generateKuerzel(final int length, final char[] charPool) {
		if (charPool == null) {
			throw new IllegalArgumentException("charPool darf nicht null sein");
		}
		if (charPool.length < 26) {
			throw new IllegalArgumentException("charPool muss mindestlaenge 26 haben");
		}
		final StringBuilder sb = new StringBuilder();
		for (int loop = 0; loop < length; loop++) {
			final int index = new Random().nextInt(charPool.length);
			sb.append(charPool[index]);
		}
		final String nonce = sb.toString();
		return nonce;
	}

}
