//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

/**
 * User
 */
public class User {

	private String subject;

	private String email;

	/**
	 * Erzeugt eine Instanz von User
	 */
	public User() {
	}

	/**
	 * Erzeugt eine Instanz von User
	 */
	public User(final String subject, final String email) {
		super();
		this.subject = subject;
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}
	public String getEmail() {
		return email;
	}

	void setSubject(final String subject) {
		this.subject = subject;
	}

	void setEmail(final String email) {
		this.email = email;
	}
}
