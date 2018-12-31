//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons;

import de.egladil.web.commons.config.DynamicConfiguration;

/**
* DynamicTestConfiguration
*/
public class DynamicTestConfiguration implements DynamicConfiguration {

	private String tempPwdCharPool;

	public String getTempPwdCharPool() {
		return tempPwdCharPool;
	}

	public void setTempPwdCharPool(final String tempPwdCharPool) {
		this.tempPwdCharPool = tempPwdCharPool;
	}

}
