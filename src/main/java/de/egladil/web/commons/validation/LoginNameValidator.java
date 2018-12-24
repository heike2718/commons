//=====================================================
// Projekt: de.egladil.common.validation
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import de.egladil.web.commons.validation.annotations.LoginName;

/**
 * LoginNameValidator<br>
 * <br>
 *  Es gibt keine Längenbeschränkunguen und keine Not-Null-Beschränkungen. Diese müssen als zusätzliche Annotationen gesetzt werden!<br>
 *  <b>Erlaubte Zeichen: </b> Buchstaben, keine Umlaute, Ziffern, Unterstrich, Minus, Punkt, '@'.
 */
public class LoginNameValidator extends AbstractWhitelistValidator<LoginName, String> {

	private static final String REGEXP = "[\\w\\.\\-@]*";

	@Override
	protected String getWhitelist() {
		return REGEXP;
	}
}
