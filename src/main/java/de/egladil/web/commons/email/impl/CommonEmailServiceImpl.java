//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.email.impl;

import java.util.Collection;
import java.util.Properties;

import javax.enterprise.context.RequestScoped;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.egladil.web.commons.email.CommonEmailService;
import de.egladil.web.commons.email.EmailDaten;
import de.egladil.web.commons.email.EmailServiceCredentials;
import de.egladil.web.commons.error.EmailException;
import de.egladil.web.commons.error.InvalidMailAddressException;

/**
 * CommonEmailServiceImpl
 */
@RequestScoped
public class CommonEmailServiceImpl implements CommonEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(CommonEmailServiceImpl.class.getName());

	@Override
	public synchronized boolean sendMail(final EmailDaten maildaten, final EmailServiceCredentials credentials)
		throws EmailException, InvalidMailAddressException {

		if (maildaten == null) {
			throw new IllegalArgumentException("maildaten null");
		}
		if (credentials == null) {
			throw new IllegalArgumentException("maildaten null");
		}
		final Collection<String> hiddenEmpfaenger = maildaten.getHiddenEmpfaenger();
		if (maildaten.getEmpfaenger() == null && (hiddenEmpfaenger == null || hiddenEmpfaenger.isEmpty())) {
			throw new EmailException("Es muss mindestens einen Empfänger oder versteckten Empfänger geben.");
		}

		final Properties mailProperties = createProperties(credentials);

		try {

			Session session = Session.getDefaultInstance(mailProperties, createMailAuthenticator(credentials));

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(credentials.getFromAddress()));
			if (maildaten.getEmpfaenger() != null) {
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(maildaten.getEmpfaenger(), true));
			}
			msg.setSubject(maildaten.getBetreff(), "UTF-8");
			msg.setText(maildaten.getText(), "UTF-8");

			if (hiddenEmpfaenger != null && !hiddenEmpfaenger.isEmpty()) {
				Address[] addresses = new Address[hiddenEmpfaenger.size()];
				int index = 0;
				for (String str : hiddenEmpfaenger) {
					addresses[index] = new InternetAddress(str, true);
					index++;
				}
				msg.addRecipients(Message.RecipientType.BCC, addresses);
			} else {
				LOG.debug("HiddenEmpfaender waren null oder leer: wird ignoriert");
			}

			Transport.send(msg);
			LOG.debug("Mail gesendet an {}", maildaten.getEmpfaenger());
			return true;
		} catch (SendFailedException e) {
			throw new InvalidMailAddressException("Mailversand: es gab ungültige Empfänger", e);
		} catch (MessagingException e) {
			String smtpHostPort = printSMTPConfiguration(mailProperties);
			String msg = "Mail an [empfaenger=" + maildaten.alleEmpfaengerFuersLog() + "] konnte nicht versendet werden. "
				+ smtpHostPort + ": " + e.getMessage();
			throw new EmailException(msg, e);
		}
	}

	private MailAuthenticator createMailAuthenticator(final EmailServiceCredentials credentials) {
		MailAuthenticator authenticator = new MailAuthenticator(credentials.getUser(), new String(credentials.getPassword()));
		return authenticator;
	}

	private Properties createProperties(final EmailServiceCredentials credentials) {
		Properties mailProperties = new Properties();
		mailProperties.setProperty("mail.smtp.host", credentials.getHost());
		mailProperties.setProperty("mail.smtp.auth", "true");

		// SSL-Konfiguration
		mailProperties.put("mail.smtp.starttls.enable", "true");
		int port = credentials.getPort();
		mailProperties.put("mail.smtp.port", port);
		mailProperties.put("mail.smtp.socketFactory.port", port);
		mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailProperties.put("mail.smtp.socketFactory.fallback", "true");
		return mailProperties;
	}

	private String printSMTPConfiguration(final Properties mailProperties) {
		StringBuffer sb = new StringBuffer("SMPT-Properties: ");
		for (Object key : mailProperties.keySet()) {
			sb.append(key + "=" + mailProperties.get(key));
			sb.append(", ");
		}
		return sb.toString();
	}
}
