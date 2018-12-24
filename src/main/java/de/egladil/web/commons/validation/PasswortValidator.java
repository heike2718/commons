//=====================================================
// Projekt: de.egladil.persistence.tools
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import javax.validation.ConstraintValidatorContext;

import de.egladil.web.commons.validation.annotations.Passwort;

/**
 * PasswortValidator
 *
 * (?=[^A-ZÄÖÜa-zäöüß]*[A-ZÄÖÜa-zäöüß])(?=[^\d]*[\d])[A-Za-z0-9ÄÖÜäöüß !"#\$%&'\(\)\*\+,\-\.\/:<=>\?@\[\]\^\\_ `'{|}~
 * ]{8,100}$
 */
public class PasswortValidator extends AbstractWhitelistValidator<Passwort, String> {

	// private static final String REGEXP =
	// "(?=[^A-ZÄÖÜ]*[A-ZÄÖÜ])(?=[^a-zäöüß]*[a-zäöüß])(?=[^\\d]*[\\d])[A-Za-z0-9ÄÖÜäöüß
	// !\"#\\$%&'\\(\\)\\*\\+,\\-\\.\\/:;<=>\\?@\\[\\]\\^\\\\_`'{|}~]{8,20}$";

	/**
	 * (?=[^A-ZÄÖÜa-zäöüß]*[A-ZÄÖÜa-zäöüß])(?=[^\d]*[\d])[A-Za-z0-9ÄÖÜäöüß!"#\$%&'\(\)\*\+,\-\.\/:<=>\?@\[\]\^\\_ `'{|}~
	 * ]{8,100}$
	 */
	private static final String REGEXP = "(?=[^A-ZÄÖÜa-zäöüß]*[A-ZÄÖÜa-zäöüß])(?=[^\\d]*[\\d])[A-Za-z0-9ÄÖÜäöüß!\"#\\$%&'\\(\\)\\*\\+,\\-\\.\\/:;<=>\\?@\\[\\]\\^\\\\_`'{|}~ ]{8,100}$";

	/**
	 * @see de.egladil.common.validation.validators.AbstractWhitelistValidator#getWhitelist()
	 */
	@Override
	protected String getWhitelist() {
		return REGEXP;
	}

	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		if (!super.isValid(value, context)) {
			return false;
		}
		final String trimmed = value.trim();
		if (trimmed.length() < value.length()) {
			return false;
		}
		return true;
	}
}
