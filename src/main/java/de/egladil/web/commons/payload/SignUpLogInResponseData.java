//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

import javax.json.bind.annotation.JsonbProperty;

/**
 * SignUpLogInResponseData enthalten die Daten, die nach einem SignUp oder einem LogIn zurückgegebenn werden.
 * <ul>
 * <li><strong>accessToken: </strong> autorisiert den Client gegenüber dem AuthProvider, wenn zusätzliche Daten wie
 * email und loginname gelesen werden sollen.</li>
 * <li><strong>refreshToken: </strong> autorisiert den Client gegenüber dem AuthProvider, ein neues JWT zu holen</li>
 * <li><strong>expiresAt: </strong> Ablauf der Gültigkeit des idTokens in Millisekunden seit 1.1.1970</li>
 * <li><strong>tokenType: </strong> Bearer</li>
 * <li><strong>state: </strong>Kontext zur Aktion (Login, SignUp): empty, login, signup. empty kommt mit, wenn
 * Authentisierung nicht erfolgreich war?</li>
 * <li><strong>idToken: </strong> das JWT</li>
 * </ul>
 */
public class SignUpLogInResponseData {

	@JsonbProperty
	private String accessToken;

	@JsonbProperty
	private String refreshToken;

	@JsonbProperty
	private long expiresAt;

	@JsonbProperty
	private String tokenType;

	@JsonbProperty
	private AuthenticationTokenState state;

	@JsonbProperty
	private String idToken;

	/**
	 * Erzeugt eine Instanz von SignUpLogInResponseData
	 */
	SignUpLogInResponseData() {
	}

	public String getAccessToken() {
		return accessToken;
	}

	void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiresAt() {
		return expiresAt;
	}

	void setExpiresAt(final long expiresAt) {
		this.expiresAt = expiresAt;
	}

	public String getTokenType() {
		return tokenType;
	}

	void setTokenType(final String tokenType) {
		this.tokenType = tokenType;
	}

	public AuthenticationTokenState getState() {
		return state;
	}

	public void setState(final AuthenticationTokenState state) {
		this.state = state;
	}

	public String getIdToken() {
		return idToken;
	}

	void setIdToken(final String idToken) {
		this.idToken = idToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	void setRefreshToken(final String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
