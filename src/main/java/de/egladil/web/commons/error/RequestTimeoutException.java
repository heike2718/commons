//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.error;

/**
 * RequestTimeoutException
 */
public class RequestTimeoutException extends RuntimeException {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * Erzeugt eine Instanz von RequestTimeoutException
	 */
	public RequestTimeoutException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Erzeugt eine Instanz von RequestTimeoutException
	 */
	public RequestTimeoutException(final String message) {
		super(message);
	}

}
