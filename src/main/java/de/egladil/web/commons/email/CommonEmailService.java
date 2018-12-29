//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.email;

import de.egladil.web.commons.error.EmailException;
import de.egladil.web.commons.error.InvalidMailAddressException;

/**
 * CommonEmailService
 */
public interface CommonEmailService {

	/**
	 * Versendet eine Mail ohne Anhang mit den entsprechenden Daten. Die Mail wird nur dann versendet, wenn alle
	 * Adressen gültige Mailadressen sind.<br>
	 * <br>
	 * Im Falle einer InvalidMailAddressException enthält diese die Information darüber, ob die Empfänger-Adresse
	 * ungültig war. allInvalidEmpfaenger und allValidEmpfaenger enthalten alle gültigen und ungültigen Mailadressen.
	 * Damit können Logging und ExceptionHandling aussagekräftiger gestaltet werden. Außerdem kann dann bei Bedarf die
	 * Mail noch einmal an die gültigen Mailadressen gesendet werden.
	 *
	 * @param maildaten EmailDaten darf nicht null sein.
	 * @param credentials EmailServiceCredentials
	 * @return boolean true, wenn gesendet, false sonst.
	 * @throws EgladilMailException
	 */
	boolean sendMail(EmailDaten maildaten, EmailServiceCredentials credentials) throws EmailException, InvalidMailAddressException;

}
