//=====================================================
// Projekt: de.egladil.bv.aas
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import de.egladil.web.commons.validation.RegistrationCredentialsValidator;

/**
 * Schulregistrierungsanfrage ist valid, wenn clientId MKV sowie password und passwordRepeated gleich sind.<br>
 * <br>
 * Alle anderen Regel werden direkt auf den Attributen validiert.
 */
@Target({ ElementType.TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegistrationCredentialsValidator.class)
@Documented
public @interface ValidRegistrationCredentials {

	String message() default "{de.egladil.constraints.registrierung}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
