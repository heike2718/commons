//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.egladil.web.commons.validation.annotations.Honeypot;
import de.egladil.web.commons.validation.annotations.Passwort;
import de.egladil.web.commons.validation.annotations.StringLatin;
import de.egladil.web.commons.validation.annotations.ValidRegistrationCredentials;
import de.egladil.web.commons.validation.beans.ClientCredentials;

/**
 * SignUpCredentials enthalten die Credentials des ResourceOwners:
 * <ul>
 * <li>Mailadresse</li>
 * <li>Loginname - darf null sein. In diesem Fall wird automatisch die Mailadresse verwendet</li>
 * <li>Passwörter</li>
 * <li>Info, dass den Datenschutzbestimmungen zugestimmt wurde</li>
 * </ul>
 *
 * sowie des Clients, von dem aus zur Registrierungsresource redirectet wurde:
 *
 * <ul>
 * <li>clientId</li>
 * <li>redirectUrl (Rücksprung)</li>
 * </ul>
 */
@ValidRegistrationCredentials
public class SignUpCredentials {

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

	@AssertTrue(message = "Bitte stimmen Sie den Datenschutzhinweisen zu.")
	private boolean agbGelesen;

	@NotNull
	ClientCredentials clientCredentials;

	@Honeypot(message = "")
	private String kleber;

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

	public boolean isAgbGelesen() {
		return agbGelesen;
	}

	public void setAgbGelesen(final boolean agbGelesen) {
		this.agbGelesen = agbGelesen;
	}

	public ClientCredentials getClientCredentials() {
		return clientCredentials;
	}

	public void setClientCredentials(final ClientCredentials clientCredentials) {
		this.clientCredentials = clientCredentials;
	}

	public String printEmailLogin() {
		return "[email='" + email + "', loginName='" + loginName + "']";
	}

}
