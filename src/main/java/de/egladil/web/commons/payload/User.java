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

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(final String name) {
		this.vorname = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(final String nachname) {
		this.nachname = nachname;
	}

}
