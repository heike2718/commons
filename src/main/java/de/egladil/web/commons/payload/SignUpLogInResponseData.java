//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

/**
 * SignUpLogInResponseData enthalten die Daten, die nach einem SignUp oder einem SignIn
 */
public class SignUpLogInResponseData {

	private String accessToken;

	private int expiresIn;

	private String tokenType;

	private AuthenticationTokenState state;

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

	public int getExpiresIn() {
		return expiresIn;
	}

	void setExpiresIn(final int expiresIn) {
		this.expiresIn = expiresIn;
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
}
