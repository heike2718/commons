//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.egladil.web.commons.validation.annotations.Honeypot;
import de.egladil.web.commons.validation.annotations.Passwort;
import de.egladil.web.commons.validation.annotations.StringLatin;
import de.egladil.web.commons.validation.beans.ClientCredentials;

/**
 * LoginCredentials sind die Daten für die Authentisierung eines ResourceOwners erforderlich sind. Das sind die Daten
 * des ResourceOwners:
 * <ul>
 * <li>Loginname/Email (der Kontext wird über den LoginCredentialsType mitgeteilt)</li>
 * <li>Passwort</li>
 * </ul>
 *
 * sowie des Clients, von dem aus zur LoginResource redirectet wurde:
 *
 * <ul>
 * <li>clientId</li>
 * <li>redirectUrl (Rücksprung)</li>
 * </ul>
 */
public class LoginCredentials {

	@NotNull
	private LoginCredentialsType loginCredentialsType;

	@NotNull
	@StringLatin
	@Size(max = 255)
	private String loginName;

	@NotNull
	@Passwort
	private String passwort;

	@NotNull
	private ClientCredentials clientCredentials;

	@Honeypot(message = "")
	private String kleber;

	public LoginCredentialsType getLoginCredentialsType() {
		return loginCredentialsType;
	}

	public void setLoginCredentialsType(final LoginCredentialsType loginCredentialsType) {
		this.loginCredentialsType = loginCredentialsType;
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

	public ClientCredentials getClientCredentials() {
		return clientCredentials;
	}

	public void setClientCredentials(final ClientCredentials clientCredentials) {
		this.clientCredentials = clientCredentials;
	}

}
