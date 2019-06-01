//=====================================================
// Projekt: checklistenserver
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.jsonp;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.egladil.web.commons.payload.ResponsePayload;

/**
 * ResponsePayloadMessageBodyWriter
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ResponsePayloadMessageBodyWriter implements MessageBodyWriter<ResponsePayload> {

	private static final Logger LOG = LoggerFactory.getLogger(ResponsePayloadMessageBodyWriter.class);

	/**
	 * Erzeugt eine Instanz von ResponsePayloadMessageBodyWriter
	 */
	public ResponsePayloadMessageBodyWriter() {
		LOG.debug("created");
	}

	@Override
	public boolean isWriteable(final Class<?> type, final Type genericType, final Annotation[] annotations,
		final MediaType mediaType) {
		return ResponsePayload.class.equals(type);
	}

	@Override
	public void writeTo(final ResponsePayload t, final Class<?> type, final Type genericType, final Annotation[] annotations,
		final MediaType mediaType, final MultivaluedMap<String, Object> httpHeaders, final OutputStream entityStream)
		throws IOException, WebApplicationException {
		LOG.debug("mapping");
		entityStream.write(new ObjectMapper().writeValueAsBytes(t));
	}

}
