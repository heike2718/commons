//=====================================================
// Projekt: authprovider
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.access.impl;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

import de.egladil.web.commons.access.ZeitlimitierenderVorrat;

/**
 * VorratImpl
 */
public class VorratImpl implements ZeitlimitierenderVorrat {

	private StopWatch stoppuhr;

	private final long maxAnzahlElemente;

	private final long minimaleWartezeit;

	private final TimeUnit zeiteinheit;

	private long aktuelleAnzahlElemente;

	private long verstricheneZeiteinheiten;

	/**
	 * Erzeugt eine Instanz von VorratImpl
	 */
	public VorratImpl(final long maxAnzahlElemente, final long minimaleWartezeit, final TimeUnit zeiteinheit) {
		super();
		this.maxAnzahlElemente = maxAnzahlElemente;
		this.aktuelleAnzahlElemente = maxAnzahlElemente;

		this.minimaleWartezeit = minimaleWartezeit;
		this.zeiteinheit = zeiteinheit;

		this.stoppuhr = StopWatch.createStarted();
	}

	@Override
	public synchronized long getVerbleibendeZeit() {
		return getVerbleibendeZeit(1);
	}

	@Override
	public synchronized long getVerbleibendeZeit(final long anzahl) {
		if (anzahl > maxAnzahlElemente) {
			throw new IllegalArgumentException("Es stehen maximal " + maxAnzahlElemente + " zur Verfügung.");
		}
		aktualisiereAktuelleAnzahlElemente();
		if (aktuelleAnzahlElemente > anzahl) {
			return 0;
		}

		long verbleibendeZeit = getVerstricheneZeit();
		long minimaleWartezeitJeElement = getMinimaleWartezeitInMillisekunden();

		if (verbleibendeZeit < minimaleWartezeitJeElement) {
			verbleibendeZeit = minimaleWartezeitJeElement - verbleibendeZeit;
		}
		final long remaining = anzahl - aktuelleAnzahlElemente - 1;
		if (remaining > 0) {
			verbleibendeZeit += minimaleWartezeitJeElement * remaining;
		}
		return verbleibendeZeit;
	}

	@Override
	public synchronized boolean entnimmElement() {
		return entnimmElemente(1);
	}

	@Override
	public synchronized boolean entnimmElemente(final long anzahl) {
		if (anzahl > maxAnzahlElemente) {
			throw new IllegalArgumentException("Es stehen maximal " + maxAnzahlElemente + " zur Verfügung.");
		}
		aktualisiereAktuelleAnzahlElemente();

		if (anzahl <= aktuelleAnzahlElemente) {
			aktuelleAnzahlElemente -= anzahl;
			return true;
		}

		return false;
	}

	private void aktualisiereAktuelleAnzahlElemente() {
		long wartezeitMinimum = getMinimaleWartezeitInMillisekunden();
		long verstricheneZeit = getVerstricheneZeit();
		if (!isWartezeitVerstrichen(verstricheneZeit, wartezeitMinimum)) {
			return;
		}
		this.verstricheneZeiteinheiten = verstricheneZeit % wartezeitMinimum;
		long verfuegbareAnzahl = verstricheneZeit / wartezeitMinimum;

		if (verfuegbareAnzahl > maxAnzahlElemente - aktuelleAnzahlElemente) {
			verfuegbareAnzahl = maxAnzahlElemente - aktuelleAnzahlElemente;
		}
		if (verfuegbareAnzahl > 0) {
			aktuelleAnzahlElemente += verfuegbareAnzahl;
		}
		stoppuhrNeuStarten();

	}

	private boolean isWartezeitVerstrichen(final long verstricheneZeit, final long wartezeitMinimum) {
		return verstricheneZeit >= wartezeitMinimum;
	}

	private void stoppuhrNeuStarten() {
		stoppuhr.reset();
		stoppuhr.start();
	}

	private long getVerstricheneZeit() {
		return stoppuhr.getTime(zeiteinheit) + verstricheneZeiteinheiten;
	}

	private long getMinimaleWartezeitInMillisekunden() {
		return zeiteinheit.toMillis(minimaleWartezeit);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VorratImpl [aktuelleAnzahlElemente=");
		builder.append(aktuelleAnzahlElemente);
		builder.append(", verstricheneZeiteinheiten=");
		builder.append(verstricheneZeiteinheiten);
		builder.append("]");
		return builder.toString();
	}

}
