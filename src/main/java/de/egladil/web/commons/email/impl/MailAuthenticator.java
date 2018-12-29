//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.email.impl;

import java.io.Serializable;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author root
 *
 */
public class MailAuthenticator extends Authenticator implements Serializable {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	private String user;

	private String pwd;

	/**
	 * @param user
	 * @param pwd
	 */
	public MailAuthenticator(final String user, final String pwd) {
		super();
		this.user = user;
		this.pwd = pwd;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.mail.Authenticator#getPasswordAuthentication()
	 */
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(user, pwd);
	}

}
