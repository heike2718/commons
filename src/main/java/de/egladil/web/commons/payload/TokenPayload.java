//=====================================================
// Project: commons
// (c) Heike Winkelvoß
//=====================================================
package de.egladil.web.commons.payload;

/**
 * TokenPayload enthält ein JWT und ein ClientAccessToken, die beide nur eine kurze Gültigkeit haben.
 */
public class TokenPayload {

	private String jwt;

	private long jwtExpiresAtUnixEpochSeconds;

	private String clientAccessToken;

	/**
	 *
	 */
	public TokenPayload() {
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(final String jwt) {
		this.jwt = jwt;
	}

	public long getExpiresAtSeconds() {
		return jwtExpiresAtUnixEpochSeconds;
	}

	public void setExpiresAtSeconds(final long expiresAtSeconds) {
		this.jwtExpiresAtUnixEpochSeconds = expiresAtSeconds;
	}

	public String getClientAccessToken() {
		return clientAccessToken;
	}

	public void setClientAccessToken(final String clientAccessToken) {
		this.clientAccessToken = clientAccessToken;
	}

}
