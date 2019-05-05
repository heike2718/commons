//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.egladil.web.commons.validation.annotations.UuidString;

/**
 * UuidPayload
 */
public class UuidPayload {

	@UuidString
	@NotNull
	@Size(max = 42)
	private String uuid;

	/**
	 * Erzeugt eine Instanz von UuidPayload
	 */
	public UuidPayload() {
	}

	/**
	 * Erzeugt eine Instanz von UuidPayload
	 */
	public UuidPayload(final String uuid) {
		super();
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

}
