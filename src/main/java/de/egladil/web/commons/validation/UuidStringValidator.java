//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import de.egladil.web.commons.validation.annotations.UuidString;

/**
 * KuerzelValidator
 */
public class UuidStringValidator extends AbstractWhitelistValidator<UuidString, String> {

	private static final String REGEXP = "[a-zA-Z0-9\\-]*";

	/**
	 * @see de.egladil.common.validation.validators.AbstractWhitelistValidator#getWhitelist()
	 */
	@Override
	protected String getWhitelist() {
		return REGEXP;
	}
}
