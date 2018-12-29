//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.email;

import java.util.Collection;
import java.util.List;

/**
 * @author heikew
 *
 */
public interface EmailDaten {

	String getEmpfaenger();

	String getBetreff();

	String getText();

	Collection<String> getHiddenEmpfaenger();

	List<String> alleEmpfaengerFuersLog();
}
