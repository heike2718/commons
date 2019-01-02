//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.validation;

import java.util.Comparator;

/**
 * InvalidPropertiesComparator
 */
public class InvalidPropertiesComparator implements Comparator<InvalidProperty> {

	@Override
	public int compare(final InvalidProperty arg0, final InvalidProperty arg1) {
		return arg0.getSortnumber() - arg1.getSortnumber();
	}
}
