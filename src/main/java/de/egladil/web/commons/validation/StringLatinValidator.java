//=====================================================
// Projekt: de.egladil.persistence.tools
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import de.egladil.web.commons.validation.annotations.StringLatin;

/**
 * WhitelistValidator
 */
public class StringLatinValidator extends AbstractWhitelistValidator<StringLatin, String> {

	@Override
	protected String getWhitelist() {
		return IStringLatinConstants.NAME_WHITELIST_REGEXP;
	}

}
