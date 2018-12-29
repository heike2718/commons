//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.error;

/**
 * EmailException
 */
public class EmailException extends RuntimeException {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * Erzeugt eine Instanz von EmailException
	 */
	public EmailException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Erzeugt eine Instanz von EmailException
	 */
	public EmailException(final String message) {
		super(message);
	}

}
