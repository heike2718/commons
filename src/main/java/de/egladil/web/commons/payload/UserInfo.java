//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

import javax.json.bind.annotation.JsonbProperty;

/**
 * UserInfo
 */
public class UserInfo {

	@JsonbProperty
	private String uid;

	@JsonbProperty
	private String email;

	/**
	 * Erzeugt eine Instanz von UserInfo
	 */
	public UserInfo() {
	}

	/**
	 * Erzeugt eine Instanz von UserInfo
	 */
	public UserInfo(final String subject, final String email) {
		super();
		this.uid = subject;
		this.email = email;
	}

	public String getUid() {
		return uid;
	}

	public String getEmail() {
		return email;
	}

	void setUid(final String subject) {
		this.uid = subject;
	}

	void setEmail(final String email) {
		this.email = email;
	}
}
