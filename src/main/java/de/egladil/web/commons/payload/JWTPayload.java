//=====================================================
// Project: commons
// (c) Heike Winkelvoß
//=====================================================
package de.egladil.web.commons.payload;

/**
 * JWTPayload
 */
public class JWTPayload {

	private String jwt;

	private long expiresAtSeconds;

	/**
	 *
	 */
	public JWTPayload() {
	}

	/**
	 * @param jwt
	 * @param expiresAtSeconds
	 */
	public JWTPayload(final String jwt, final long expiresAtSeconds) {
		this.jwt = jwt;
		this.expiresAtSeconds = expiresAtSeconds;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(final String jwt) {
		this.jwt = jwt;
	}

	public long getExpiresAtSeconds() {
		return expiresAtSeconds;
	}

	public void setExpiresAtSeconds(final long expiresAtSeconds) {
		this.expiresAtSeconds = expiresAtSeconds;
	}

}
