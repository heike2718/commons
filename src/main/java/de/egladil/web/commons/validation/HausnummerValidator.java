//=====================================================
// Projekt: de.egladil.web.commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import de.egladil.web.commons.validation.annotations.Hausnummer;

/**
 * PlzValidator
 *
 * [\w\-/]*
 */
public class HausnummerValidator extends AbstractWhitelistValidator<Hausnummer, String> {

	/** [\w\-/]*  */
	private static final String REGEXP = "[\\w\\- _/]*";

	/**
	 * @see de.egladil.common.validation.validators.AbstractWhitelistValidator#getWhitelist()
	 */
	@Override
	protected String getWhitelist() {
		return REGEXP;
	}
}
