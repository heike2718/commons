//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.egladil.web.commons.error.InvalidInputException;
import de.egladil.web.commons.payload.RegistrationCredentials;
import de.egladil.web.commons.payload.ResponsePayload;

/**
 * RegistrationCredentialsValidationTest
 */
public class RegistrationCredentialsValidationTest {

	private ValidationDelegate validationDelegate;

	@BeforeEach
	void setUp() {
		validationDelegate = new ValidationDelegate();
	}

	@Test
	void validCredentialsIsValid() {
		validationDelegate.check(getValidCredentials(), RegistrationCredentials.class);
	}

	@Test
	void agbErforderlich() {

		// Arrange
		final RegistrationCredentials credentials = getValidCredentials();
		credentials.setAgbGelesen(false);

		// Act + Assert
		final Throwable ex = assertThrows(InvalidInputException.class, () -> {
			validationDelegate.check(credentials, RegistrationCredentials.class);
		});
		InvalidInputException e = (InvalidInputException) ex;
		ResponsePayload responsePayload = e.getResponsePayload();
		assertNotNull(responsePayload);
		assertEquals("ERROR", responsePayload.getMessage().getLevel());
		assertEquals("Die Eingaben sind nicht korrekt.", responsePayload.getMessage().getMessage());
		@SuppressWarnings("unchecked")
		Collection<InvalidProperty> invalidProperties = (Collection<InvalidProperty>) responsePayload.getData();
		assertEquals(1, invalidProperties.size());
		InvalidProperty prop = invalidProperties.iterator().next();
		assertEquals("agbGelesen", prop.getName());
		assertEquals("Bitte stimmen Sie den Datenschutzhinweisen zu.", prop.getMessage());
	}

	@Test
	void passwordsNotEqual() {

		// Arrange
		final RegistrationCredentials credentials = getValidCredentials();
		credentials.setPasswortWdh("123start");

		// Act + Assert
		final Throwable ex = assertThrows(InvalidInputException.class, () -> {
			validationDelegate.check(credentials, RegistrationCredentials.class);
		});
		InvalidInputException e = (InvalidInputException) ex;
		ResponsePayload responsePayload = e.getResponsePayload();
		assertNotNull(responsePayload);
		assertEquals("ERROR", responsePayload.getMessage().getLevel());
		assertEquals("Die Eingaben sind nicht korrekt.", responsePayload.getMessage().getMessage());
		@SuppressWarnings("unchecked")
		Collection<InvalidProperty> invalidProperties = (Collection<InvalidProperty>) responsePayload.getData();
		assertEquals(1, invalidProperties.size());
		InvalidProperty prop = invalidProperties.iterator().next();
		assertEquals("CrossValidation", prop.getName());
		assertEquals("Die Passwörter stimmen nicht überein", prop.getMessage());
	}

	@Test
	void multipleViolations() {

		// Arrange
		final RegistrationCredentials credentials = getValidCredentials();
		credentials.setPasswortWdh("123start");
		credentials.setKleber("hui");

		// Act + Assert
		final Throwable ex = assertThrows(InvalidInputException.class, () -> {
			validationDelegate.check(credentials, RegistrationCredentials.class);
		});
		InvalidInputException e = (InvalidInputException) ex;
		ResponsePayload responsePayload = e.getResponsePayload();
		assertNotNull(responsePayload);
		assertEquals("ERROR", responsePayload.getMessage().getLevel());
		assertEquals("Die Eingaben sind nicht korrekt.", responsePayload.getMessage().getMessage());
		@SuppressWarnings("unchecked")
		List<InvalidProperty> invalidProperties = (List<InvalidProperty>) responsePayload.getData();
		assertEquals(2, invalidProperties.size());
		{
			InvalidProperty prop = invalidProperties.get(0);
			assertEquals("kleber", prop.getName());
			assertEquals("", prop.getMessage());
		}
		{
			InvalidProperty prop = invalidProperties.get(1);
			assertEquals("CrossValidation", prop.getName());
			assertEquals("Die Passwörter stimmen nicht überein", prop.getMessage());
		}
	}

	private RegistrationCredentials getValidCredentials() {
		RegistrationCredentials result = new RegistrationCredentials();
		result.setAgbGelesen(true);
		result.setEmail("bla@eladil.de");
		result.setLoginName("Herbert");
		result.setPasswort("start123");
		result.setPasswortWdh("start123");
		result.setRedirectUrl("https://mathe-jung-alt.de/mkv/dashboard");

		return result;
	}

}
