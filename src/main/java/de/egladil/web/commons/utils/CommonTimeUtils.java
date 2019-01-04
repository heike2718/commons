//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

	public static Date transformFromLocalDateTime(final LocalDateTime ldt) {
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Erzeugt ein abgeschlossenes Zeitintervall.
	 *
	 * @param startTime Date
	 * @param amount int Anzahl Zeiteinheiten muss >= 0 sein, 0 ist zulässig.
	 * @param chronoUnit ChronoUnit
	 * @return TimeInterval
	 */
	public static TimeInterval getInterval(final LocalDateTime startTime, final int amount, final ChronoUnit chronoUnit) {
		if (amount < 0) {
			throw new IllegalArgumentException("amount must be >=0");
		}
		Date startsAt = transformFromLocalDateTime(startTime);
		if (amount == 0) {
			return new TimeInterval(startsAt, startsAt);
		}
		LocalDateTime endTime = startTime.plus(amount, chronoUnit);
		Date endsAt = transformFromLocalDateTime(endTime);

		return new TimeInterval(startsAt, endsAt);
	}
}
