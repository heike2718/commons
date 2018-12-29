//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.email;

/**
 * EmailServiceCredentials
 */
public class EmailServiceCredentials {

	private final String host;

	private final int port;

	private final String user;

	private final char[] password;

	private final String fromAddress;

	/**
	 * Erzeugt eine Instanz von EmailServiceCredentials
	 */
	public EmailServiceCredentials(final String host, final int port, final String user, final char[] password, final String fromAddress) {
		super();
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		this.fromAddress = fromAddress;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUser() {
		return user;
	}

	public char[] getPassword() {
		return password;
	}

	public String getFromAddress() {
		return fromAddress;
	}

}
