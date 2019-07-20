//=====================================================
// Projekt: de.egladil.mkv.service
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.config;

import java.io.File;
import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.egladil.web.commons.error.CommonConfigurationException;

/**
 * DynamicConfigReaderImpl greift auf die Konfigurationsdatei dynamicConfigurationProperties.json zu und liest sie bei
 * jeder Anfrage neu aus. Dadurch muss bei Änderung der Datei die Anwendung nicht durchgestartet werden.
 */
@ApplicationScoped
public class DynamicConfigReaderImpl implements DynamicConfigReader {

	private static final Logger LOG = LoggerFactory.getLogger(DynamicConfigReaderImpl.class.getName());

	private final ObjectMapper objectMapper;

	/**
	 * DynamicConfigReaderImpl
	 */
	public DynamicConfigReaderImpl() {
		this.objectMapper = new ObjectMapper();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DynamicConfiguration getConfig(final Class clazz, final String pathConfigFile) throws CommonConfigurationException {
		final File configFile = new File(pathConfigFile);
		try {
			return (DynamicConfiguration) objectMapper.readValue(configFile, clazz);
		} catch (final IOException e) {
			LOG.error(e.getMessage());
			throw new CommonConfigurationException("Konnte Konfigurationsfile [" + pathConfigFile + "] nicht lesen:  "
				+ e.getMessage() + " folgendes prüfen: config-root und name-dynamic-config-file in *-config.yaml", e);
		}
	}
}
