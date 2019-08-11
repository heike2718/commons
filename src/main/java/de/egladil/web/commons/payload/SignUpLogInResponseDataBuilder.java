//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

/**
 * SignUpLogInResponseDataBuilder
 */
public class SignUpLogInResponseDataBuilder {

	private SignUpLogInResponseData result = new SignUpLogInResponseData();

	/**
	 * Erzeugt eine Instanz von SignUpLogInResponseDataBuilder
	 */
	private SignUpLogInResponseDataBuilder() {
	}

	public static SignUpLogInResponseDataBuilder instance() {
		return new SignUpLogInResponseDataBuilder();
	}

	public SignUpLogInResponseDataBuilder withRefreshToken(final String refreshToken) {
		this.result.setRefreshToken(refreshToken);
		return this;
	}

	public SignUpLogInResponseDataBuilder withExpiresAt(final long expiresIn) {
		if (expiresIn < 0) {
			throw new IllegalArgumentException("expiresIn must not be < 0");
		}
		this.result.setExpiresAt(expiresIn);
		return this;
	}

	public SignUpLogInResponseDataBuilder withTokenType(final String tokenType) {
		this.result.setTokenType(tokenType);
		return this;
	}

	public SignUpLogInResponseDataBuilder withState(final String state) {
		this.result.setState(state);
		return this;
	}

	public SignUpLogInResponseDataBuilder withIdToken(final String idToken) {
		this.result.setIdToken(idToken);
		return this;
	}

	public SignUpLogInResponseData build() {
		return result;
	}

}
