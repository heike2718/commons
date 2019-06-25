//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PeriodChecker
 */
public class PeriodChecker {

	private static final Logger LOG = Logger.getLogger(PeriodChecker.class.getSimpleName());

	/**
	 * Vergleicht die Länge des Zeitintervalls zwischen startDate und endDate mit der erwarteten Zeitspanne.
	 *
	 * @param startDate Date
	 * @param endDate Date
	 * @param expectedPeriodMillis long Anzahl Millisekunden, die zwischen startDate und endDate liegen sollen.
	 * @return boolean true, wenn kürzer oder gleich lang wie expectedLengthPeriod, false sonst.
	 */
	public boolean isPeriodLessEqualExpectedPeriod(final Date startDate, final Date endDate, final long expectedPeriodMillis) {
		long diff = endDate.getTime() - startDate.getTime();

		if (LOG.isLoggable(Level.FINE)) {
			LOG.fine("Interval: " + expectedPeriodMillis + ", Ende: " + endDate + ", Start: " + startDate + ", Differenz: " + diff);
		}

		return diff <= expectedPeriodMillis;
	}
}
