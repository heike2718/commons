//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.net;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URLConnection;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.egladil.web.commons.error.InvalidInputException;
import de.egladil.web.commons.error.RequestTimeoutException;
import de.egladil.web.commons.payload.ResponsePayload;

/**
 * ResponsePayloadReader
 */
public class ResponsePayloadReader implements ContentReader {

	@Override
	public byte[] getBytes(final URLConnection conn) throws IOException {
		try (InputStreamReader in = new InputStreamReader(conn.getInputStream(), "UTF-8")) {
			ObjectMapper objectMapper = new ObjectMapper();
			ResponsePayload payload = objectMapper.readValue(in, ResponsePayload.class);
			if ("INFO".equals(payload.getMessage().getLevel())) {
				return payload.getData().toString().getBytes();
			}

			throw new InvalidInputException(payload);
		} catch (SocketTimeoutException e) {
			throw new RequestTimeoutException(e.getMessage(), e);
		}
	}

}
