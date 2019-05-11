//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.error;

/**
 * CommonCryptoException
 */
public class CommonCryptoException extends RuntimeException {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * Erzeugt eine Instanz von CommonCryptoException
	 */
	public CommonCryptoException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Erzeugt eine Instanz von CommonCryptoException
	 */
	public CommonCryptoException(final String message) {
		super(message);
	}

}
