//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * CommonTimeUtils
 */
public final class CommonTimeUtils {

	/**
	 * Erzeugt eine Instanz von CommonTimeUtils
	 */
	private CommonTimeUtils() {
	}

	public static LocalDateTime transformFromDate(final Date date) {

		return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());

	}
}
