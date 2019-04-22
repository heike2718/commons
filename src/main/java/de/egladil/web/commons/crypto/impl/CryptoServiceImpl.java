//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.crypto.impl;

import java.security.MessageDigest;
import java.util.Base64;

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

		final HashService hashService = getHashService(pepper);

		final HashRequest hashRequest = new SimpleHashRequest(algorithmName, new SimpleByteSource(password), salt, iterations);

		final Hash hash = hashService.computeHash(hashRequest);
		return hash;
	}

	@Override
	public boolean verifyPassword(final char[] password, final String persistentHashValue, final String persistentSalt,
		final String pepper, final String algorithmName, final int numberIterations) {
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

}
