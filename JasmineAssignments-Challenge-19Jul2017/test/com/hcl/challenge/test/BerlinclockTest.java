package com.hcl.challenge.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import org.junit.Before;
import org.junit.Test;

import com.hcl.challenge.exception.InvalidTimeException;
import com.hcl.challenge.service.BerlinClockTower;
import com.hcl.challenge.service.ClockTower;
import com.hcl.challenge.service.impl.BerlinClockTowerImpl;
import com.hcl.challenge.service.impl.ClockTowerImpl;

/**
 * Class to run possible tests on Clock features and Berlin clock output.
 * 
 * @author Vivekananda P
 *
 */
public class BerlinclockTest {

	ClockTower clockTower = null;
	BerlinClockTower berlinTower = null;

	@Before
	public void initalizeBerlinClock() {
		berlinTower = new BerlinClockTowerImpl();
		clockTower = new ClockTowerImpl(berlinTower);
	}

	@Test(expected = DateTimeParseException.class)
	public void negativeHourDateCheck() throws Exception {
		clockTower.displayClock(LocalTime.parse("-17:23:01"));
	}

	@Test(expected = InvalidTimeException.class)
	public void valiadateInvalidDate() throws Exception {
		clockTower.displayClock(null);
	}

	@Test
	public void valiadateZeroHoursTime() throws Exception {
		LocalTime currentTime = LocalTime.parse("00:00:00");
		assertEquals("OOOO", berlinTower.getTopHours(currentTime.getHour()));
	}

	@Test
	public void validatevalidHoursTime() throws Exception {
		LocalTime currentTime = LocalTime.parse("13:00:00");
		assertEquals("RROO", berlinTower.getTopHours(currentTime.getHour()));

	}

	@Test
	public void valiadateBottomZeroHoursTime() throws Exception {
		LocalTime currentTime = LocalTime.parse("00:00:00");
		assertEquals("OOOO", berlinTower.getBottomHours(currentTime.getHour()));
	}

	@Test
	public void validatevalidBottomHoursTime() throws Exception {
		LocalTime currentTime = LocalTime.parse("13:00:00");
		assertEquals("RRRO", berlinTower.getBottomHours(currentTime.getHour()));

	}

	@Test
	public void valiadateTopMinutesTime() throws Exception {
		LocalTime currentTime = LocalTime.parse("00:00:00");
		assertEquals("OOOOOOOOOOO", berlinTower.getTopMinutes(currentTime.getMinute()));
	}

	@Test
	public void validatevalidTopMinutesTime() throws Exception {
		LocalTime currentTime = LocalTime.parse("00:45:00");
		assertEquals("YYRYYRYYROO", berlinTower.getTopMinutes(currentTime.getMinute()));

	}

	@Test
	public void valiadateBottomMinutesTime() throws Exception {
		LocalTime currentTime = LocalTime.parse("00:00:00");
		assertEquals("OOOO", berlinTower.getBottomMinutes(currentTime.getMinute()));
	}

	@Test
	public void validatevalidBottomMinutesTime() throws Exception {
		LocalTime currentTime = LocalTime.parse("00:46:00");
		assertEquals("YOOO", berlinTower.getBottomMinutes(currentTime.getMinute()));

	}

	@Test
	public void valiadateSecondsTime() throws Exception {
		LocalTime currentTime = LocalTime.parse("00:00:00");
		assertEquals("    O", berlinTower.getSeconds(currentTime.getSecond()));
	}

	@Test
	public void validatevalidOddSecondsTime() throws Exception {
		LocalTime currentTime = LocalTime.parse("00:46:23");
		assertEquals("  Y", berlinTower.getSeconds(currentTime.getSecond()));

	}

	@Test
	public void validatevalidEvenSecondsTime() throws Exception {
		LocalTime currentTime = LocalTime.parse("00:46:24");
		assertEquals("    O", berlinTower.getSeconds(currentTime.getSecond()));

	}
}