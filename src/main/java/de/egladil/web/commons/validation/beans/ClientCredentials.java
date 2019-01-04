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
 * ClientCredentials
 */
public class ClientCredentials {

	@NotBlank
	@ClientId
	@Size(max = 50)
	private String clientId;

	@NotBlank
	@URL
	private String redirectUrl;

	/**
	* Erzeugt eine Instanz von ClientCredentials
	*/
	public ClientCredentials() {
	}

	/**
	 * Erzeugt eine Instanz von ClientCredentials
	 */
	public ClientCredentials(@NotBlank @Size(max = 50) final String clientId, @NotBlank @URL final String redirectUrl) {
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
