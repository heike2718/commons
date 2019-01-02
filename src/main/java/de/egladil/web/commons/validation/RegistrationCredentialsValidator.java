//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import de.egladil.web.commons.payload.RegistrationCredentials;
import de.egladil.web.commons.validation.annotations.ValidRegistrationCredentials;

/**
 * RegistrationCredentialsValidator
 */
public class RegistrationCredentialsValidator
	implements ConstraintValidator<ValidRegistrationCredentials, RegistrationCredentials> {

	@Override
	public boolean isValid(final RegistrationCredentials value, final ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		if (value.getPasswort() != null && !value.getPasswort().equals(value.getPasswortWdh())) {
			context.buildConstraintViolationWithTemplate("Die Passwörter stimmen nicht überein").addBeanNode()
				.addConstraintViolation();
			return false;

		}
		return true;
	}
}
