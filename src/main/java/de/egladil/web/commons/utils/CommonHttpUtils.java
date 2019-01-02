//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * CommonHttpUtils
 */
public final class CommonHttpUtils {

	/**
	 * Erzeugt eine Instanz von CommonHttpUtils
	 */
	private CommonHttpUtils() {
	}

	/**
	 * Holt Info aus Request zu Logzwecken.
	 *
	 * @param servletRequest
	 * @return
	 */
	public static String getRequesInfos(final HttpServletRequest servletRequest) {
		final Enumeration<String> headerNames = servletRequest.getHeaderNames();
		final StringBuffer sb = new StringBuffer();
		sb.append(" <--- Request Headers --- ");
		while (headerNames.hasMoreElements()) {
			final String headerName = headerNames.nextElement();
			sb.append(headerName);
			sb.append(":");
			final Enumeration<String> headerValues = servletRequest.getHeaders(headerName);
			if (headerValues.hasMoreElements()) {
				sb.append(headerValues.nextElement());
				sb.append(", ");
			}
			sb.append(" -- ");
		}
		sb.append(" Headers Request ---> ");
		final String dump = sb.toString();
		return dump;
	}

}
