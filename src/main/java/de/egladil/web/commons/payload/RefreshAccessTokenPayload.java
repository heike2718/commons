//=====================================================
// Project: commons
// (c) Heike Winkelvoß
//=====================================================
package de.egladil.web.commons.payload;

import javax.validation.constraints.NotNull;

import de.egladil.web.commons.validation.annotations.UuidString;

/**
 * RefreshAccessTokenPayload
 */
public class RefreshAccessTokenPayload {

	@NotNull
	@UuidString
	private String clientAccessToken;

	@NotNull
	@UuidString
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

	@Override
	public String toString() {
		return "RefreshAccessTokenPayload [clientAccessToken=" + clientAccessToken + ", userRefreshToken=" + userRefreshToken + "]";
	}

}
