//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.egladil.web.commons.validation.beans.ClientCredentials;

/**
 * RegistrationCredentialsTest
 */
public class RegistrationCredentialsTest {

	@Test
	void serialize() throws JsonProcessingException {
		// Arrange
		SignUpCredentials cred = new SignUpCredentials();
		ClientCredentials clientCredentials = new ClientCredentials("WLJLH4vsldWapZrMZi2U5HKRBVpgyUiRTWwX7aiJd8nX",
			"http://localhost:4200");
		cred.setAgbGelesen(true);
		cred.setClientCredentials(clientCredentials);
		cred.setEmail("zezeze@egladil.de");
		cred.setLoginName("zezeze");
		cred.setPasswort("start123");
		cred.setPasswortWdh("start123");

		// Act + Assert
		System.out.println(new ObjectMapper().writeValueAsString(cred));
	}

}
