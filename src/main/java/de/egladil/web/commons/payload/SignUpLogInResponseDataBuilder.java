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

	public SignUpLogInResponseDataBuilder withAccessToken(final String accessToken) {
		this.result.setAccessToken(accessToken);
		return this;
	}

	public SignUpLogInResponseDataBuilder withExpiresIn(final int expiresIn) {
		if (expiresIn < 0) {
			throw new IllegalArgumentException("expiresIn must not be < 0");
		}
		this.result.setExpiresIn(expiresIn);
		return this;
	}

	public SignUpLogInResponseDataBuilder withTokenType(final String tokenType) {
		this.result.setTokenType(tokenType);
		return this;
	}

	public SignUpLogInResponseDataBuilder withState(final AuthenticationTokenState state) {
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
