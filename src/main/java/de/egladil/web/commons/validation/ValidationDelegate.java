//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;

import de.egladil.web.commons.error.InvalidInputException;
import de.egladil.web.commons.payload.MessagePayload;
import de.egladil.web.commons.payload.ResponsePayload;

/**
 * ValidationDelegate für Validierung von Ein- und Ausgabe-Objekten.
 */
public class ValidationDelegate {

	private static final Logger LOG = LogManager.getLogger(ValidationDelegate.class.getName());

	private final Validator validator;

	/**
	 * Erzeugt eine Instanz von ValidationDelegate
	 */
	public ValidationDelegate() {
		final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	/**
	 *
	 * @param payload
	 * @param clazz
	 * @throws InvalidInputException: 400-BAD REQUEST
	 */
	public <T> void check(final T payload, final Class<T> clazz) {
		if (payload == null) {
			LOG.error("Parameter payload darf nicht null sein!");
			throw new InvalidInputException(new ResponsePayload(MessagePayload.error("payload null"), payload));
		}
		final Set<ConstraintViolation<T>> errors = validator.validate(payload);
		handleValidationErrorsWithKleber(payload, errors, clazz);
	}

	/**
	 * @param loggableObject
	 * @param errors
	 * @throws IllegalArgumentException
	 * @throws InvalidInputException
	 */
	private <T> void handleValidationErrorsWithKleber(final T loggableObject, final Set<ConstraintViolation<T>> errors,
		final Class<T> clazz) throws IllegalArgumentException {

		if (!errors.isEmpty()) {
			final ValidationUtils validationUtils = new ValidationUtils();
			ResponsePayload responsePayload = validationUtils.toConstraintViolationMessage(errors, clazz);

			if (!"INFO".equals(responsePayload.getMessage().getLevel())) {
				LOG.debug("{}: {}", responsePayload.getMessage().getMessage(), responsePayload.getData().toString());
			}

			throw new InvalidInputException(responsePayload);
		}
	}
}
