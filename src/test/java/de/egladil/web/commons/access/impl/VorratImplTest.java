//=====================================================
// Projekt: authprovider
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.access.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * VorratImplTest
 */
public class VorratImplTest {

	@Nested
	class Einzelentnahmen {

		@Test
		void verbleibendeZeitWennWartezeitVerstrichen() {
			// Arrange
			long maximaleAnzahlElemente = 17;
			long minimaleWartezeit = 531;
			TimeUnit zeiteinheit = TimeUnit.MILLISECONDS;

			// Act
			VorratImpl vorrat = new VorratImpl(maximaleAnzahlElemente, minimaleWartezeit, zeiteinheit);
			pause(532);
			long verbleibendeZeit = vorrat.getVerbleibendeZeit();

			// Assert
			assertEquals(0, verbleibendeZeit);
		}

		@Test
		void verbleibendeZeitWennWartezeitExaktErreicht() {
			// Arrange
			long maximaleAnzahlElemente = 17;
			long minimaleWartezeit = 531;
			TimeUnit zeiteinheit = TimeUnit.MILLISECONDS;

			// Act
			VorratImpl vorrat = new VorratImpl(maximaleAnzahlElemente, minimaleWartezeit, zeiteinheit);
			pause(531);
			long verbleibendeZeit = vorrat.getVerbleibendeZeit();

			// Assert
			assertEquals(0, verbleibendeZeit);
		}

		@Test
		void verbleibendeZeitWennWartezeitNichtVerstrichen() {
			// Arrange
			long maximaleAnzahlElemente = 17;
			long minimaleWartezeit = 500;
			TimeUnit zeiteinheit = TimeUnit.MILLISECONDS;

			// Act
			VorratImpl vorrat = new VorratImpl(maximaleAnzahlElemente, minimaleWartezeit, zeiteinheit);
			pause(20);
			long verbleibendeZeit = vorrat.getVerbleibendeZeit();

			// Assert
			assertEquals(0, verbleibendeZeit);
		}

		@Test
		void entnehmenUndLangeGenugWarten() {
			// Arrange
			long maximaleAnzahlElemente = 1;
			long minimaleWartezeit = 23;
			TimeUnit zeiteinheit = TimeUnit.MILLISECONDS;
			VorratImpl vorrat = new VorratImpl(maximaleAnzahlElemente, minimaleWartezeit, zeiteinheit);

			// Act + Assert 1
			for (int i = 1; i < 35; i++) {
				pause(23);
				System.out.println("=== Act + Assert " + i + " ===");
				System.out.println("vorher: " + vorrat.toString());

				boolean entnommen = vorrat.entnimmElemente(1);
				long verbleibendeZeit = vorrat.getVerbleibendeZeit(1);

				if (entnommen) {
					System.out.println(
						"nachher: [verbleibende Zeit=" + verbleibendeZeit + " ms], " + vorrat.toString());

				} else {
					System.out.println(
						"nachher: ===> [verbleibende Zeit=" + verbleibendeZeit + " ms], " + vorrat.toString());
				}
				assertTrue("Fehler bei i=" + i, entnommen);
			}
		}

		@Test
		void dos() {
			// Arrange (ca 67 Zugriffe je Sekunde sind noch akzeptabel)
			long maximaleAnzahlElemente = 1;
			long minimaleWartezeit = 33;
			TimeUnit zeiteinheit = TimeUnit.MILLISECONDS;


			VorratImpl vorrat = new VorratImpl(maximaleAnzahlElemente, minimaleWartezeit, zeiteinheit);

			int anzahlAblehnungen = 0;

			// Act + Assert (35 mal im Abstand von 7s zugreifen. Das entspricht einer Zugriffsrate von 143 je Sekunde)
			for (int i = 1; i < 35; i++) {
				pause(7);
				System.out.println("=== Act + Assert " + i + " ===");

				boolean entnommen = vorrat.entnimmElemente(1);
				long verbleibendeZeit = vorrat.getVerbleibendeZeit(1);

				if (entnommen) {
					System.out.println(
						"zugelassen: [verbleibende Zeit=" + verbleibendeZeit + " ms], " + vorrat.toString());

				} else {
					anzahlAblehnungen++;
					System.out.println(
						"abgelehnt [verbleibende Zeit=" + verbleibendeZeit + " ms], " + vorrat.toString());
				}
//				assertTrue("Fehler bei i=" + i, entnommen);
			}

			assertEquals(26, anzahlAblehnungen);
		}

	}

	@Nested
	class Mehrfachentnahmen {

		@Test
		void entnehmenUndLangeGenugWarten() {
			// Arrange
			long maximaleAnzahlElemente = 3;
			long minimaleWartezeit = 8;
			TimeUnit zeiteinheit = TimeUnit.MILLISECONDS;
			VorratImpl vorrat = new VorratImpl(maximaleAnzahlElemente, minimaleWartezeit, zeiteinheit);

			List<Integer> umklapppunkte = Arrays.asList(new Integer[] {1, 19});

			// Act + Assert 1
			for (int i = 1; i < 17; i++) {
				pause(9);
				System.out.println("=== Act + Assert " + i + " ===");
				System.out.println("vorher: " + vorrat.toString());

				boolean entnommen = vorrat.entnimmElemente(2);
				long verbleibendeZeit = vorrat.getVerbleibendeZeit(2);

				if (entnommen) {
					System.out.println(
						"nachher: [verbleibende Zeit=" + verbleibendeZeit + " ms], " + vorrat.toString());

				} else {
					System.out.println(
						"nachher: ===> [verbleibende Zeit=" + verbleibendeZeit + " ms], " + vorrat.toString());
				}
//				if (!umklapppunkte.contains(Integer.valueOf(i)) && i % 2 == 1) {
//					assertFalse("Fehler bei i=" + i, entnommen);
//				} else {
//					assertTrue("Fehler bei i=" + i, entnommen);
//				}
			}
		}
	}

	private void pause(final long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			//
		}
	}

}
