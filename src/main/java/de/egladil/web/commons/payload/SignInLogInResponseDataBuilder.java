//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

/**
 * SignInLogInResponseDataBuilder
 */
public class SignInLogInResponseDataBuilder {

	private SignInLogInResponseData result = new SignInLogInResponseData();

	/**
	 * Erzeugt eine Instanz von SignInLogInResponseDataBuilder
	 */
	private SignInLogInResponseDataBuilder() {
	}

	public static SignInLogInResponseDataBuilder instance() {
		return new SignInLogInResponseDataBuilder();
	}

	public SignInLogInResponseDataBuilder withAccessToken(final String accessToken) {
		this.result.setAccessToken(accessToken);
		return this;
	}

	public SignInLogInResponseDataBuilder withExpiresIn(final int expiresIn) {
		if (expiresIn < 0) {
			throw new IllegalArgumentException("expiresIn must not be < 0");
		}
		this.result.setExpiresIn(expiresIn);
		return this;
	}

	public SignInLogInResponseDataBuilder withTokenType(final String tokenType) {
		this.result.setTokenType(tokenType);
		return this;
	}

	public SignInLogInResponseDataBuilder withState(final String state) {
		this.result.setState(state);
		return this;
	}

	public SignInLogInResponseDataBuilder withIdToken(final String idToken) {
		this.result.setIdToken(idToken);
		return this;
	}

	public SignInLogInResponseData build() {
		return result;
	}

}
