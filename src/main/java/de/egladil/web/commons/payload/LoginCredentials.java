//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import de.egladil.web.commons.validation.annotations.Honeypot;
import de.egladil.web.commons.validation.annotations.Passwort;
import de.egladil.web.commons.validation.annotations.StringLatin;

/**
 * LoginCredentials
 */
public class LoginCredentials {

	@NotNull
	@URL
	private String redirectUrl;

	@Email
	private String email;

	@StringLatin
	@Size(max = 255)
	private String loginName;

	@NotNull
	@Passwort
	private String passwort;

	@Honeypot(message="")
	private String kleber;

	public String getEmail() {
		return email;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(final String loginName) {
		this.loginName = loginName;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(final String passwort) {
		this.passwort = passwort;
	}

	public String getKleber() {
		return kleber;
	}

	public void setKleber(final String kleber) {
		this.kleber = kleber;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(final String clientId) {
		this.redirectUrl = clientId;
	}

}
