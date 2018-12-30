//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.error;

import de.egladil.web.commons.payload.ResponsePayload;

/**
 * InvalidInputException
 */
public class InvalidInputException extends RuntimeException {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	private ResponsePayload responsePayload;

	/**
	 * Erzeugt eine Instanz von EmailException
	 */
	public InvalidInputException(final ResponsePayload payload) {
		this.responsePayload = payload;
	}

	public ResponsePayload getResponsePayload() {
		return responsePayload;
	}

}
