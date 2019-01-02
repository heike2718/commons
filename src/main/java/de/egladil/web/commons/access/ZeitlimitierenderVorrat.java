//=====================================================
// Projekt: authprovider
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.access;

import java.util.concurrent.TimeUnit;

import de.egladil.web.commons.access.impl.VorratImpl;

/**
 * ZeitlimitierenderVorrat ist eine feste Menge von Elementen, die es gestattet, Elemente zu entnehmen, die
 * Entnahmefrequenz jedoch limitiert. D.h. zwischen zwei aufeinanderfolgenden Entnahmen muss eine konfigurierte
 * Wartezeit verstrichen sein. Der Vorrat wird ab und zu aufgefüllt.<br>
 * <br>
 * Mögliche Verwendungsszenarien:
 * <ul>
 * <li><strong>Bremsen der Zugriffsrate auf auf eine Funktionalität (DOS-Prevention). </strong>Hierzu reicht ein Vorrat
 * von einem Element bei der Entnahme von einem Element. Die minimaleWartezeit entspricht der Zugriffsfrequenz auf die
 * Funktion (Anzahl calls je Zeiteinheit)</li>
 * <li><strong>Cache-Control. </strong>Hier entspricht die Anzahl der Elemente der Kapazität des Caches, die
 * minimaleWartezeit der Zeit, die ein Element im Zustand idle im Cache verbleiben soll, bevor es weggeräumt wird.</li>
 * </ul>
 */
public interface ZeitlimitierenderVorrat {

	/**
	 * Berechnet die verbleibende Zeit in Millisekunden, nach der ein neues Element verfügbar sein wird.
	 *
	 * @return long
	 */
	long getVerbleibendeZeit();

	/**
	 * Berechnet verbleibende Zeit in Millisekunden, nach der wieder anzahl neue Elemente verfügbar sein werden.<br>
	 * <br>
	 * getVerbleibendeZeit() ist äquivalent mit getVerbleibendeZeit(1)
	 *
	 * @param anzahl long
	 * @return long Millisekunden.
	 */
	long getVerbleibendeZeit(long anzahl);

	/**
	 * Versucht, ein Element aus dem ZeitlimitierenderVorrat zu holen.
	 *
	 * @return true falls das möglich war, false sonst.
	 */
	boolean entnimmElement();

	/**
	 * Versucht, anzahl Elemente aus dem ZeitlimitierenderVorrat zu holen.<br>
	 * <br>
	 * entnimmElement() ist äquivalent mit entnimmElemente(1)
	 *
	 * @return true falls das möglich war, false sonst.
	 */
	boolean entnimmElemente(long anzahl);

	/**
	 * Erzeugt ein ZeitlimitierenderVorrat-Objekt, das es gestattet aus einem Vorrat von maxAnzahlElemente Elementen
	 * frühestens nach Ablauf von [minimaleWartezeit zeiteinheit] eine gewisse Anzahl Elemente zu entnehmen.
	 *
	 * @param maxAnzahlElemente long Kapazitätrt des Vorrats.
	 * @param minimaleWartezeit long Anzahl Zeiteinheiten der minimalen Wartezeit.
	 * @param zeiteinheit TimeUnit
	 * @return ZeitlimitierenderVorrat
	 */
	static ZeitlimitierenderVorrat create(final long maxAnzahlElemente, final long minimaleWartezeit, final TimeUnit zeiteinheit) {
		return new VorratImpl(maxAnzahlElemente, minimaleWartezeit, zeiteinheit);
	}

}
