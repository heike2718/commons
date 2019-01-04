//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

/**
 * SignInLogInResponseData
 */
public class SignInLogInResponseData {

	private String accessToken;

	private int expiresIn;

	private String tokenType;

	private String state;

	private String idToken;

	/**
	 * Erzeugt eine Instanz von SignInLogInResponseData
	 */
	SignInLogInResponseData() {
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

	public String getState() {
		return state;
	}

	void setState(final String state) {
		this.state = state;
	}

	public String getIdToken() {
		return idToken;
	}

	void setIdToken(final String idToken) {
		this.idToken = idToken;
	}
}
