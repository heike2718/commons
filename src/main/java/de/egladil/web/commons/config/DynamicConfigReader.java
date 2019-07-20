//=====================================================
// Projekt: de.egladil.mkv.service
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.config;

import de.egladil.web.commons.error.CommonConfigurationException;

/**
 * DynamicConfigReaderImpl greift auf die Konfigurationsdatei dynamicConfigurationProperties.json zu und liest sie bei
 * jeder Anfrage neu aus. Dadurch muss bei Änderung der Datei die Anwendung nicht durchgestartet werden.
 */
public interface DynamicConfigReader {

	/**
	 * @param clazz Class
	 * @param pathDynamicConfigFile String
	 * @return DynamicConfiguration jedes Mal neu ausgelesen.
	 * @throws CommonConfigurationException
	 */
	@SuppressWarnings("rawtypes")
	DynamicConfiguration getConfig(Class clazz, String pathDynamicConfigFile) throws CommonConfigurationException;
}
