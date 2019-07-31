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
 * <li><strong>expiresAt: </strong> Ablauf der Gültigkeit des accessTokens. <strong>in Millisekunden seit
 * 1.1.1970</strong></li>
 * </ul>
 */
public class OAuthAccessTokenPayload {

	private String accessToken;

	private String refreshToken;

	private long expiresAt;

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public long getExpiresAt() {
		return expiresAt;
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
}
