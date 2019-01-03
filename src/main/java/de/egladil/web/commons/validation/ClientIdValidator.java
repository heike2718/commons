//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import de.egladil.web.commons.validation.annotations.ClientId;

/**
 * KuerzelValidator
 */
public class ClientIdValidator extends AbstractWhitelistValidator<ClientId, String> {

	private static final String REGEXP = "[a-zA-Z0-9+=]*";

	@Override
	protected String getWhitelist() {
		return REGEXP;
	}
}
