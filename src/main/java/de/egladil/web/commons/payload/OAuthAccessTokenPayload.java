//=====================================================
// Project: commons
// (c) Heike Winkelvoß
//=====================================================
package de.egladil.web.commons.payload;

/**
 * OAuthAccessTokenPayload
 * <ul>
 * <li><strong>accessToken: </strong> autorisiert den Client gegenüber dem AuthProvider für eine begrenzte Zeit</li>
 * <li><strong>refreshToken: </strong> autorisiert den Client gegenüber dem AuthProvider, ein neues accessToken zu
 * holen</li>
 * <li><strong>expiresAt: </strong> Ablauf der Gültigkeit des idTokens. <strong>in Millisekunden seit
 * 1.1.1970</strong></li>
 * <li><strong>tokenType: </strong> Bearer</li>
 * <li><strong>nonce: </strong>Kontext zur Aktion (Login, SignUp): empty, login, signup. empty kommt mit, wenn
 * Authentisierung nicht erfolgreich war?</li>
 * <li><strong>idToken: </strong> das JWT</li>
 * </ul>
 */
public class OAuthAccessTokenPayload {

	private String accessToken;

	private String refreshToken;

	private long expiresAt;

	private String nonce;

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public long getExpiresAt() {
		return expiresAt;
	}

	public String getNonce() {
		return nonce;
	}

	public void setAccessToken(final String accessToken) {
		this.accessToken = accessToken;
	}

	public void setRefreshToken(final String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setExpiresAt(final long expiresAt) {
		this.expiresAt = expiresAt;
	}

	public void setNonce(final String nonce) {
		this.nonce = nonce;
	}

}
