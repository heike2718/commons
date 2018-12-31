//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.error;

/**
 * CommonConfigurationException
 */
public class CommonConfigurationException extends RuntimeException {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * Erzeugt eine Instanz von CommonConfigurationException
	 */
	public CommonConfigurationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Erzeugt eine Instanz von CommonConfigurationException
	 */
	public CommonConfigurationException(final String message) {
		super(message);
	}

}
