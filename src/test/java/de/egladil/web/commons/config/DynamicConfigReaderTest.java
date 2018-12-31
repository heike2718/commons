//=====================================================
// Projekt: authenticationprovider
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.config;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.egladil.web.commons.CommonTestUtils;
import de.egladil.web.commons.DynamicTestConfiguration;

/**
 * DynamicConfigReaderTest
 */
public class DynamicConfigReaderTest {

	@Test
	void serialize() {
		// Arrange
		ConfigRootProvider configRootProvider = Mockito.mock(ConfigRootProvider.class);
		Mockito.when(configRootProvider.getConfigRoot()).thenReturn(CommonTestUtils.getDevConfigRoot());
		Mockito.when(configRootProvider.getNameDynamicConfigFile()).thenReturn("testConfigPropertiesDynamic.json");

		DynamicConfigReader configReader = new DynamicConfigReader(configRootProvider);

		// Act
		final DynamicTestConfiguration config = (DynamicTestConfiguration) configReader.getConfig(DynamicTestConfiguration.class);

		// Assert
		assertEquals("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#+=_-_", config.getTempPwdCharPool());
	}
}
