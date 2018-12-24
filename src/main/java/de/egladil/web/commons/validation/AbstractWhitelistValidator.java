//=====================================================
// Projekt: de.egladil.persistence.tools
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * AbstractWhitelistValidator validiert Strings gegen eine Whitelist. Ist der Wert null oder leer, wird er als valid
 * angesehen. Bei Pflichtattributen müssen also zusätzliche Annotationen angebracht werden.
 */
public abstract class AbstractWhitelistValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {

	/**
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(A constraintAnnotation) {
		// nix
	}

	/**
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(T value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		if (!(value instanceof String)) {
			return false;
		}
		String strValue = (String) value;
		if (strValue.isEmpty()) {
			return true;
		}

		Matcher matcher = getPattern().matcher(strValue);
		boolean matches = matcher.matches();
		return matches;
	}

	private Pattern getPattern() {
		Pattern pattern = Pattern.compile(getWhitelist());
		return pattern;
	}

	/**
	 * Gibt die Whitelist als regular expression zurück.
	 *
	 * @return
	 */
	protected abstract String getWhitelist();
}
