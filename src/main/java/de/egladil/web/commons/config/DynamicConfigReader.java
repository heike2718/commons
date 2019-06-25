//=====================================================
// Projekt: de.egladil.mkv.service
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.config;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.egladil.web.commons.error.CommonConfigurationException;

/**
 * DynamicConfigReader greift auf die Konfigurationsdatei dynamicConfigurationProperties.json zu und liest sie bei jeder
 * Anfrage neu aus. Dadurch muss bei Änderung der Datei die Anwendung nicht durchgestartet werden.
 */
@ApplicationScoped
public class DynamicConfigReader {

	private static final Logger LOG = Logger.getLogger(DynamicConfigReader.class.getName());

	@Inject
	private ConfigRootProvider configRootProvider;

	private final ObjectMapper objectMapper;

	/**
	 * DynamicConfigReader
	 */
	public DynamicConfigReader() {
		this.objectMapper = new ObjectMapper();
	}

	/**
	 * Erzeugt eine Instanz von DynamicConfigReader zu Testzwecken ohne CDI.
	 */
	public DynamicConfigReader(final ConfigRootProvider configRootProvider) {
		this();
		this.configRootProvider = configRootProvider;
	}

	/**
	 *
	 * @return Kontext jedes Mal neu ausgelesen.
	 * @throws EgladilConfigurationException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DynamicConfiguration getConfig(final Class clazz) throws CommonConfigurationException {

		String pathConfigFile = configRootProvider.getConfigRoot() + File.separator + configRootProvider.getNameDynamicConfigFile();

		final File configFile = new File(pathConfigFile);
		try {
			return (DynamicConfiguration) objectMapper.readValue(configFile, clazz);
		} catch (final IOException e) {
			LOG.severe(e.getMessage());
			throw new CommonConfigurationException("Konnte Konfigurationsfile [" + pathConfigFile + "] nicht lesen:  "
				+ e.getMessage() + " folgendes prüfen: config-root und name-dynamic-config-file in *-config.yaml", e);
		}
	}
}
