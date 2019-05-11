//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.payload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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


	@Test
	void deserialize() throws JsonParseException, JsonMappingException, IOException {

		// Arrange
		String json = "{\"agbGelesen\":true,\"clientCredentials\":{\"clientId\":\"WLJLH4vsldWapZrMZi2U5HKRBVpgyUiRTWwX7aiJd8nX\",\"redirectUrl\":\"http://localhost:4200\"},\"email\":\"aaaa@egladil.de\",\"kleber\":null,\"loginName\":\"aaaa\",\"passwort\":\"start123\",\"passwortWdh\":\"start123\"}";

		// Act
		SignUpCredentials cred = new ObjectMapper().readValue(json.getBytes(), SignUpCredentials.class);

		// Assert
		final ClientCredentials clientCredentials = cred.getClientCredentials();
		assertNotNull(clientCredentials);
		assertEquals("WLJLH4vsldWapZrMZi2U5HKRBVpgyUiRTWwX7aiJd8nX", clientCredentials.getClientId());
		assertEquals("http://localhost:4200", clientCredentials.getRedirectUrl());
	}

}
