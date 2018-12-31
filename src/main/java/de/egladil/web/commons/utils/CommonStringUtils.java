//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * CommonStringUtils
 */
public final class CommonStringUtils {

	/**
	 * Erzeugt eine Instanz von CommonStringUtils
	 */
	private CommonStringUtils() {
	}

	public static String extractOrigin(final String headerValue) {
		if (StringUtils.isBlank(headerValue)) {
			return null;
		}
		final String value = headerValue.replaceAll("http://", "").replaceAll("https://", "");
		final String[] token = StringUtils.split(value, "/");
		final String extractedOrigin = token == null ? value : token[0];
		return extractedOrigin;
	}

}
