//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.net;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * StaticContentProviderIT benötigt einen laufenden authprovider, der unter Port 9000 läuft.
 */
public class StaticContentProviderIT {

	@Test
	void getStaticContentSuccess() {
		// Arrange
		String endpoint = "http://localhost:9000/authprovider/auth/public-key";
		StaticContentProvider provider = new StaticContentProvider(new ResponsePayloadReader());

		// Act
		try {
			byte[] cert = provider.getStaticContent(endpoint, 300);

			String publicKeyText = new String(cert);

			assertTrue(publicKeyText.startsWith("-----BEGIN PUBLIC KEY-----"));
		} catch (IOException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
}
