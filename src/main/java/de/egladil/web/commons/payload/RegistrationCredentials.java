//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.egladil.web.commons.validation.annotations.Honeypot;
import de.egladil.web.commons.validation.annotations.Passwort;
import de.egladil.web.commons.validation.annotations.StringLatin;


/**
* RegistrationCredentials
*/
public class RegistrationCredentials {

	@StringLatin
	@NotBlank
	@Size(min = 1, max = 100)
	private String vorname;

	@StringLatin
	@NotBlank
	@Size(min = 1, max = 100)
	private String nachname;

	@NotNull
	@Email
	@Size(min = 1, max = 255)
	private String email;

	@StringLatin
	@Size(max = 255)
	private String loginName;

	@NotNull
	@Passwort
	private String passwort;

	@NotNull
	@Passwort
	private String passwortWdh;

	@Honeypot
	private String kleber;

	public String getVorname() {
		return vorname;
	}

	public void setVorname(final String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(final String nachname) {
		this.nachname = nachname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(final String passwort) {
		this.passwort = passwort;
	}

	public String getPasswortWdh() {
		return passwortWdh;
	}

	public void setPasswortWdh(final String passwortWdh) {
		this.passwortWdh = passwortWdh;
	}

	public String getKleber() {
		return kleber;
	}

	public void setKleber(final String kleber) {
		this.kleber = kleber;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(final String loginName) {
		this.loginName = loginName;
	}

}
