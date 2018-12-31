//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import de.egladil.web.commons.error.RequestTimeoutException;

/**
 * SimpleContentReader schiebt die byte[] des InputStreams durch.
 */
public class SimpleContentReader implements ContentReader {

	@Override
	public byte[] getBytes(final URLConnection conn) throws IOException {
		try (InputStream in = conn.getInputStream()) {
			return IOUtils.toByteArray(in);
		} catch (SocketTimeoutException e) {
			throw new RequestTimeoutException(e.getMessage(), e);
		}
	}
}
