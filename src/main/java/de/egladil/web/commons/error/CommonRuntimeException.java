//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.error;

/**
 * CommonRuntimeException
 */
public class CommonRuntimeException extends RuntimeException {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * Erzeugt eine Instanz von CommonRuntimeException
	 */
	public CommonRuntimeException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Erzeugt eine Instanz von CommonRuntimeException
	 */
	public CommonRuntimeException(final String arg0) {
		super(arg0);
	}

}
