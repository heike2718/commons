//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Path;

import org.apache.commons.lang3.StringUtils;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;

import de.egladil.web.commons.payload.MessagePayload;
import de.egladil.web.commons.payload.ResponsePayload;

/**
 * ValidationUtils
 */
public class ValidationUtils {

	private static final Logger LOG = LogManager.getLogger(ValidationUtils.class.getName());

	/**
	 * Validation-Messages in verarbeitbarer Form extrahieren.
	 *
	 * @param errors
	 * @param bean
	 * @return
	 * @throws IllegalArgumentException
	 */
	private <T> Map<String, String> extractPropertiesAndMessages(final Set<ConstraintViolation<T>> errors, final Class<T> clazz)
		throws IllegalArgumentException {
		if (errors == null) {
			throw new IllegalArgumentException("errors darf nicht null sein");
		}
		if (clazz == null) {
			throw new IllegalArgumentException("clazz darf nicht null sein");
		}
		Set<String> fieldNames = fieldNames(clazz);
		Map<String, String> result = new HashMap<>();
		Iterator<ConstraintViolation<T>> iter = errors.iterator();
		while (iter.hasNext()) {
			ConstraintViolation<T> cv = iter.next();
			Path path = cv.getPropertyPath();
			String propName = path.toString();
			if (fieldNames.contains(propName)) {
				String message = result.get(propName);
				if (message == null) {
					message = cv.getMessage();
				} else {
					message += ", " + cv.getMessage();
				}
				result.put(propName, message);
			}
		}
		return result;
	}

	public <T> ResponsePayload toConstraintViolationMessage(final Set<ConstraintViolation<T>> errors, final Class<T> clazz) {
		Map<String, String> messages = this.extractPropertiesAndMessages(errors, clazz);

		Set<InvalidProperty> invalidProperties = new HashSet<>();

		if (!messages.isEmpty()) {
			for (String key : messages.keySet()) {

				final String message = messages.get(key);
				StringUtils.join(Arrays.stream(StringUtils.split(message, ',')).collect(Collectors.toSet()), ',');
				InvalidProperty prop = new InvalidProperty(key,
					StringUtils.join(Arrays.stream(StringUtils.split(message, ',')).collect(Collectors.toSet()), ','));
				invalidProperties.add(prop);
				if ("kleber".equals(key)) {
					LOG.warn("Possible BOT-Attac: " + prop.toString());
				}
			}
		}
		String crossValidation = extractCrossValidationMessage(errors);
		ValidationErrors payload = new ValidationErrors(invalidProperties, crossValidation);
		ResponsePayload result = new ResponsePayload(MessagePayload.error("Die Eingaben sind nicht korrekt."), payload);

		return result;
	}

	<T> String extractCrossValidationMessage(final Set<ConstraintViolation<T>> errors) {
		Iterator<ConstraintViolation<T>> iter = errors.iterator();
		Set<String> alle = new HashSet<>();
		while (iter.hasNext()) {
			final ConstraintViolation<T> cv = iter.next();
			if (StringUtils.isBlank(cv.getPropertyPath().toString())) {
				String message = cv.getMessage();
				alle.add(message);
			}
		}
		return StringUtils.join(alle, ',');
	}

	@SuppressWarnings("rawtypes")
	private <T> Set<String> fieldNames(final Class<T> clazz) {
		Set<String> result = new HashSet<>();
		Class superClazz = clazz;
		while (superClazz != null) {
			Field[] fields = superClazz.getDeclaredFields();
			for (Field f : fields) {
				result.add(f.getName());
			}
			superClazz = superClazz.getSuperclass();
		}
		return result;
	}
}
