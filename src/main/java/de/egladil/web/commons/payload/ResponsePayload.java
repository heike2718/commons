//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

import javax.json.bind.annotation.JsonbProperty;

/**
 * ResponsePayload
 */
public class ResponsePayload {

	@JsonbProperty
	private MessagePayload message;

	@JsonbProperty
	private Object data;

	/**
	 * Erzeugt eine Instanz von ResponsePayload
	 */
	public ResponsePayload() {
	}

	/**
	 * Erzeugt eine Instanz von ResponsePayload
	 */
	private ResponsePayload(final MessagePayload message) {
		super();
		this.message = message;
	}

	/**
	 * Erzeugt eine Instanz von ResponsePayload
	 */
	public ResponsePayload(final MessagePayload message, final Object payload) {
		super();
		this.message = message;
		this.data = payload;
	}

	public MessagePayload getMessage() {
		return message;
	}

	public void setMessage(final MessagePayload message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(final Object payload) {
		this.data = payload;
	}

	public static ResponsePayload messageOnly(final MessagePayload messagePayload) {
		return new ResponsePayload(messagePayload);
	}

}
