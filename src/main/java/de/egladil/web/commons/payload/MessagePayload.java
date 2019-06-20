//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

import javax.json.bind.annotation.JsonbProperty;

/**
 * MessagePayload
 */
public class MessagePayload {

	@JsonbProperty
	private String level;

	@JsonbProperty
	private String message;

	/**
	 * Erzeugt eine Instanz von MessagePayload
	 */
	public MessagePayload() {
	}

	/**
	 * Erzeugt eine Instanz von MessagePayload
	 */
	private MessagePayload(final String level, final String message) {
		super();
		this.level = level;
		this.message = message;
	}

	public String getLevel() {
		return level;
	}

	void setLevel(final String level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	void setMessage(final String message) {
		this.message = message;
	}

	public static MessagePayload info(final String message) {
		return new MessagePayload("INFO", message);
	}

	public static MessagePayload warn(final String message) {
		return new MessagePayload("WARN", message);
	}

	public static MessagePayload error(final String message) {
		return new MessagePayload("ERROR", message);
	}

}
