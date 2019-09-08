//=====================================================
// Project: checklistenserver
// (c) Heike Winkelvoß
//=====================================================
package de.egladil.web.commons.payload;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import de.egladil.web.commons.utils.CommonTimeUtils;

/**
 * LogEntry
 */
public class LogEntry {

	private long timestamp;

	private String clientAccessToken;

	private String message;

	private TSLogLevel level;

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public TSLogLevel getLevel() {
		return level;
	}

	public void setLevel(final TSLogLevel level) {
		this.level = level;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(final long timestamp) {
		this.timestamp = timestamp;
	}

	public String getClientAccessToken() {
		return clientAccessToken;
	}

	public void setClientAccessToken(final String clientAccessToken) {
		this.clientAccessToken = clientAccessToken;
	}

	@Override
	public String toString() {
		return CommonTimeUtils.transformFromDate(new Date(timestamp)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss,SSS"))
			+ " - " + clientAccessToken + " -  " + message;
	}

}
