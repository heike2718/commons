//=====================================================
// Project: commons
// (c) Heike Winkelvoß
//=====================================================
package de.egladil.web.commons.payload;

/**
 * RefreshAccessTokenPayload
 */
public class RefreshAccessTokenPayload {

	private String clientAccessToken;

	private String userRefreshToken;

	public String getClientAccessToken() {
		return clientAccessToken;
	}

	public void setClientAccessToken(final String clientAccessToken) {
		this.clientAccessToken = clientAccessToken;
	}

	public String getUserRefreshToken() {
		return userRefreshToken;
	}

	public void setUserRefreshToken(final String userRefreshToken) {
		this.userRefreshToken = userRefreshToken;
	}

}
