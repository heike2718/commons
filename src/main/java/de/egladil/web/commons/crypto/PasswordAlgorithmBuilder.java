//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.crypto;

import de.egladil.web.commons.crypto.impl.PasswordAlgorithmImpl;

/**
 * PasswordAlgorithmBuilder
 *
 *
 */
// TODO in public static class in der PasswordAlgorithmBuilderInstance umwandeln wegen zu viel gluecodes
public class PasswordAlgorithmBuilder {

	private String internalPepper;

	private String internalAlgorithmName;

	private int internalNumberIterations;

	/**
	 * Erzeugt eine Instanz von PasswordAlgorithmBuilder
	 */
	public static PasswordAlgorithmBuilder instance() {
		return new PasswordAlgorithmBuilder();
	}

	private PasswordAlgorithmBuilder() {
	}

	public PasswordAlgorithmBuilder withAlgorithmName(final String algorithmName) {
		this.internalAlgorithmName = algorithmName;
		return this;
	}

	public PasswordAlgorithmBuilder withPepper(final String pepper) {
		this.internalPepper = pepper;
		return this;
	}

	public PasswordAlgorithmBuilder withNumberIterations(final int numberIterations) {
		this.internalNumberIterations = numberIterations;
		return this;
	}

	public PasswordAlgorithm build() {
		return new PasswordAlgorithmImpl(internalPepper, internalAlgorithmName, internalNumberIterations);
	}

}
