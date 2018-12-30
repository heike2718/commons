//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.jwt;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.egladil.web.commons.error.InvalidInputException;
import de.egladil.web.commons.error.RequestTimeoutException;
import de.egladil.web.commons.payload.ResponsePayload;

/**
 * StaticContentProvider
 */
public class StaticContentProvider {

	/**
	 * Holt ein String-Data-Objekt aus dem endpoint. Der Response ist ein {@link ResponsePayload}- Objekt mit data als
	 * String.
	 *
	 * @param endpoint URL zu einer Resource, die ein ResponsePayload zurückliefert, dessen data- Objekt ein String ist.
	 * @param timeout int Timeoit in milliseconds
	 * @return byte[] das Data-Object
	 * @throws IOException
	 * @throws RequestTimeoutException falls die conn zu lange braucht.
	 */
	public byte[] getStaticContent(final String endpoint, final int timeout) throws IOException, RequestTimeoutException {
		URL url = new URL(endpoint);
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("Accept", "*/*");
		conn.setConnectTimeout(timeout);
		conn.setReadTimeout(timeout);

		byte[] cert = this.readPublicKey(conn);
		return cert;

	}

	private byte[] readPublicKey(final URLConnection conn) throws IOException {

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
