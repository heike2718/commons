//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation.beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import de.egladil.web.commons.validation.annotations.ClientId;

/**
 * SignInLogInRequestPayload
 */
public class SignInLogInRequestPayload {

	@NotBlank
	@ClientId
	@Size(max = 50)
	private final String clientId;

	@NotBlank
	@URL
	private final String redirectUrl;

	/**
	 * Erzeugt eine Instanz von SignInLogInRequestPayload
	 */
	public SignInLogInRequestPayload(@NotBlank @Size(max = 50) final String clientId, @NotBlank @URL final String redirectUrl) {
		super();
		this.clientId = clientId;
		this.redirectUrl = redirectUrl;
	}

	public String getClientId() {
		return clientId;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

}
