//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.utils;

import java.util.Date;

/**
 * TimeInterval abgeschlossenes Zeitintervall.
 */
public class TimeInterval {

	private final Date startTime;

	private final Date endTime;

	/**
	 * Erzeugt eine Instanz von TimeInterval
	 */
	public TimeInterval(final Date startTime, final Date endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

}
