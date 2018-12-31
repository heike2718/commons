//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.config;

/**
 * ConfigRootProvider
 */
public interface ConfigRootProvider {

	/**
	 * Pfad zum anwendungsspezifischen Konfigurationsverzeichnis.
	 *
	 * @return string
	 */
	String getConfigRoot();

	/**
	 * Name des Files für den DynamicConfigReader.
	 *
	 * @return String
	 */
	String getNameDynamicConfigFile();

}
