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
import java.util.TimeZone;

/**
 * CommonTimeUtils
 */
public final class CommonTimeUtils {

	public static final String DEFAULT_DATE_TIME_FORMAT = "dd.MM.yyyy kk:mm:ss";

	public static final String DEFAULT_DATE_MINUTES_FORMAT = "dd.MM.yyyy kk:mm";

	public static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";

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

	public static LocalDateTime now() {

		ZoneId systemDefaultZoneId = ZoneId.systemDefault();
		// System.out.println(systemDefaultZoneId);
		TimeZone tz = TimeZone.getTimeZone(systemDefaultZoneId);
		LocalDateTime ldt = LocalDateTime.now(tz.toZoneId());

		return ldt;

	}
}
