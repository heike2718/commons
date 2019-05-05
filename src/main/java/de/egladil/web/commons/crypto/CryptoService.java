//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.crypto;

import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.util.ByteSource;

/**
 * CryptoService
 */
public interface CryptoService {

	/**
	 *
	 * Berechnet einen Hash.
	 *
	 * @param password char[] Der Aufrufer muss ihn nach dem Aufruf nullen.
	 * @param algorithmName String
	 * @param salt ByteSource
	 * @param pepper String
	 * @param iterations Integer
	 * @return Hash
	 */
	Hash hashPassword(char[] password, String algorithmName, ByteSource salt, String pepper, final Integer iterations);

	/**
	 * Prüft das gegebene Passwort gegen das persistierte Passwort.
	 *
	 * @param password char[] der Aufrufer ist verantwortlich für das Löschen des char[]
	 * @param persistentHashValue String der Base64-encodete Passworthash aus der DB.
	 * @param persistentSalt String das Base64-encodete Salt aus der DB
	 * @param pepper String
	 * @param algorithmName String der zum Berechnen des PasswortHashes verwendete Algorithmus.
	 * @param numberIterations int die zum Berechnen des Passworthashes verwendete Anzahl Iterationen.
	 * @return boolean
	 */
	boolean verifyPassword(char[] password, String persistentHashValue, String persistentSalt, String pepper, String algorithmName,
		int numberIterations);

	/**
	 * Generiert einen Zufallsstring gegeben Länge mit den Zeichen aus charPool. Basiert auf Random.
	 *
	 * @param laenge int die Länge. Muss mindestens gleich 6 sein.
	 * @param charPool die verwendeten Zeichen. Muss Mindestlänge 26 haben.
	 * @return String
	 */
	public String generateKuerzel(final int length, final char[] charPool);

}
