//=====================================================
// Projekt: de.egladil.persistence.tools
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import de.egladil.web.commons.validation.annotations.GeneratedPasswort;

/**
 * Validator für generiertes Passwort.
 */
public class GeneratedPasswortValidator extends AbstractWhitelistValidator<GeneratedPasswort, String> {

	private static final String REGEXP = "^[a-zA-Z0-9]{12}$";

	/**
	 * @see de.egladil.common.validation.validators.AbstractWhitelistValidator#getWhitelist()
	 */
	@Override
	protected String getWhitelist() {
		return REGEXP;
	}
}
