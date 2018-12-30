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

	private String vorname;

	private String nachname;

	private String email;

	/**
	 * Erzeugt eine Instanz von User
	 */
	public User() {
	}

	/**
	 * Erzeugt eine Instanz von User
	 */
	public User(final String subject, final String vorname, final String nachname, final String email) {
		super();
		this.subject = subject;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public String getVorname() {
		return vorname;
	}

	public String getEmail() {
		return email;
	}

	public String getNachname() {
		return nachname;
	}

	void setSubject(final String subject) {
		this.subject = subject;
	}

	void setVorname(final String vorname) {
		this.vorname = vorname;
	}

	void setNachname(final String nachname) {
		this.nachname = nachname;
	}

	void setEmail(final String email) {
		this.email = email;
	}
}
