//=====================================================
// Projekt: authenticationprovider
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.config;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import de.egladil.web.commons.CommonTestUtils;
import de.egladil.web.commons.DynamicTestConfiguration;

/**
 * DynamicConfigReaderTest
 */
public class DynamicConfigReaderTest {

	@Test
	void serialize() {
		// Arrange
		String pathDynamicConfigFile = CommonTestUtils.getDevConfigRoot() + File.separator + "testConfigPropertiesDynamic.json";
		DynamicConfigReader configReader = new DynamicConfigReaderImpl();

		// Act
		final DynamicTestConfiguration config = (DynamicTestConfiguration) configReader.getConfig(DynamicTestConfiguration.class,
			pathDynamicConfigFile);

		// Assert
		assertEquals("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#+=_-_", config.getTempPwdCharPool());
	}
}
