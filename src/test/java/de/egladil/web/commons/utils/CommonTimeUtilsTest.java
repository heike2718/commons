//=====================================================
// Projekt: commons
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.web.commons.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * CommonTimeUtilsTest
 */
public class CommonTimeUtilsTest {

	@Test
	void getInterval360Minuten() throws ParseException {

		// Arrange
		Date start = new SimpleDateFormat(CommonTimeUtils.DEFAULT_DATE_FORMAT).parse("22.04.2019");
		Date end = new SimpleDateFormat(CommonTimeUtils.DEFAULT_DATE_TIME_FORMAT).parse("22.04.2019 06:00:00");

		// Act
		TimeInterval interval = CommonTimeUtils.getInterval(CommonTimeUtils.transformFromDate(start), 360, ChronoUnit.MINUTES);

		// Assert
		assertNotNull(interval);
		System.out.println(interval.toString());
		assertEquals(end.getTime(), interval.getEndTime().getTime());

	}

}
