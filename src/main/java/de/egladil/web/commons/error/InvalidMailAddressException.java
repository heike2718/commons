//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.error;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.SendFailedException;

/**
 * InvalidMailAddressException
 */
public class InvalidMailAddressException extends RuntimeException {

	private final SendFailedException sendFailedException;

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * Erzeugt eine Instanz von EgladilInvalidMailAddressException
	 */
	public InvalidMailAddressException(final String message, final SendFailedException e) {
		super(message);
		this.sendFailedException = e;
	}

	/**
	 * Liefert die Membervariable invalidHiddenEmpfaenger
	 *
	 * @return die Membervariable invalidHiddenEmpfaenger
	 */
	public List<String> getAllInvalidAdresses() {
		Address[] addresses = sendFailedException.getInvalidAddresses();
		return toStringList(addresses);
	}

	/**
	 * Liefert die Membervariable validHiddenEmfaenger
	 *
	 * @return die Membervariable validHiddenEmfaenger
	 */
	public List<String> getAllValidSentAddresses() {
		Address[] addresses = sendFailedException.getValidSentAddresses();
		return toStringList(addresses);
	}

	public List<String> getAllValidUnsentAddresses() {
		Address[] addresses = sendFailedException.getValidUnsentAddresses();
		return toStringList(addresses);
	}

	private List<String> toStringList(final Address[] addresses) {
		List<String> result = new ArrayList<>();
		if (addresses != null) {
			for (int i = 0; i < addresses.length; i++) {
				Address a = addresses[i];
				if (!result.contains(a.toString())) {
					result.add(a.toString());
				}
			}
		}
		return result;
	}
}
