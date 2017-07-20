/**
 * 
 */
package com.hcl.challenge.service.impl;

import static com.hcl.challenge.constants.ClockTowerConstants.EMPTY_STRING;
import static com.hcl.challenge.constants.ClockTowerConstants.SPACE_STRING;
import static com.hcl.challenge.constants.ClockTowerConstants.HOURS_ROW_RED_LIGHT_ON;
import static com.hcl.challenge.constants.ClockTowerConstants.HOURS_TOP_ROW_TOTAL_LIGHT_COUNT;
import static com.hcl.challenge.constants.ClockTowerConstants.LIGHT_OFF;
import static com.hcl.challenge.constants.ClockTowerConstants.MINUTES_BOTTOM_ROW_TOTAL_LIGHT_COUNT;
import static com.hcl.challenge.constants.ClockTowerConstants.MINUTES_QUARTER_RED_LIGHT_ON;
import static com.hcl.challenge.constants.ClockTowerConstants.MINUTES_TOP_ROW_TOTAL_LIGHT_COUNT;
import static com.hcl.challenge.constants.ClockTowerConstants.MINUTES_YELLOW_LIGHT_ON;
import static com.hcl.challenge.constants.ClockTowerConstants.SECONDS_YELLOW_LIGHT_ON;

import com.hcl.challenge.exception.InvalidTimeException;
import com.hcl.challenge.service.BerlinClockTower;

/**
 * Class to implement BrlinClockTower interface all methods.
 * 
 * @author pushpeswar.r
 *
 */
public class BerlinClockTowerImpl implements BerlinClockTower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4614583755562637605L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcl.challenge.service.BerlinClockTower#getSeconds()
	 */
	@Override
	public String getSeconds(final Integer pSeconds) throws InvalidTimeException {
		StringBuilder secondsLights = new StringBuilder(SPACE_STRING);
		secondsLights.append(SPACE_STRING);
		
		if (pSeconds != null && (pSeconds % 2 != 0)) {
			secondsLights.append(SECONDS_YELLOW_LIGHT_ON);
		} else {
			secondsLights.append(LIGHT_OFF);
		}
		
		return secondsLights.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcl.challenge.service.BerlinClockTower#getTopHours()
	 */
	@Override
	public String getTopHours(final Integer pHours) throws InvalidTimeException {
		String topHoursLights = EMPTY_STRING;

		if (pHours != null) {
			Integer topRowHourLights = pHours / 5;
			topHoursLights = getClockHours(pHours, topRowHourLights);
		}

		return topHoursLights;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcl.challenge.service.BerlinClockTower#getBottomHours()
	 */
	@Override
	public String getBottomHours(final Integer pHours) throws InvalidTimeException {
		String topHoursLights = EMPTY_STRING;

		if (pHours != null) {
			Integer topRowHourLights = pHours % 5;
			topHoursLights = getClockHours(pHours, topRowHourLights);
		}

		return topHoursLights;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcl.challenge.service.BerlinClockTower#getTopMinutes()
	 */
	@Override
	public String getTopMinutes(final Integer pMinutes) throws InvalidTimeException {
		String topMinuteLights = EMPTY_STRING;

		if (pMinutes != null) {
			Integer topRowMinuteLightsOn = pMinutes / 5;
			topMinuteLights = getClockMinutes(pMinutes, topRowMinuteLightsOn, MINUTES_TOP_ROW_TOTAL_LIGHT_COUNT);
		}

		return topMinuteLights;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hcl.challenge.service.BerlinClockTower#getBottomMinutes()
	 */
	@Override
	public String getBottomMinutes(final Integer pMinutes) throws InvalidTimeException {
		String bottomMinuteLights = EMPTY_STRING;

		if (pMinutes != null) {
			Integer topRowMinuteLightsOn = pMinutes % 5;
			bottomMinuteLights = getClockMinutes(pMinutes, topRowMinuteLightsOn, MINUTES_BOTTOM_ROW_TOTAL_LIGHT_COUNT);
		}

		return bottomMinuteLights;
	}

	/**
	 * Method to calculate hours lights for both top and bottom rows.
	 * 
	 * @param pHours
	 *            - numeric hours
	 * @param pLoopLimit
	 *            - numeric loop limit to run loop and on lights.
	 * @return - String of expected clock lights on and off
	 */
	private String getClockHours(final Integer pHours, final Integer pLoopLimit) {
		StringBuilder hoursBuilder = new StringBuilder(EMPTY_STRING);
		
		if (pHours != null) {
			for (int indexHours = 0; indexHours < pLoopLimit; indexHours++) {
				hoursBuilder.append(HOURS_ROW_RED_LIGHT_ON);
			}
		}

		for (int indexHours = 0; indexHours < (HOURS_TOP_ROW_TOTAL_LIGHT_COUNT - pLoopLimit); indexHours++) {
			hoursBuilder.append(LIGHT_OFF); //put light off symbol to remaining hour lamps.
		}

		return hoursBuilder.toString();
	}

	/**
	 * Method to calculate minutes lights for both top and bottom rows.
	 * 
	 * @param pMinutes
	 *            - numeric minutes
	 * @param pLoopLimit
	 *            - numeric loop limit to run loop and on lights.
	 * @param pMinutesTotalRows
	 *            - numeric total rows minutes (4 or 11).
	 * @return - String of expected clock lights on and off
	 */
	private String getClockMinutes(final Integer pMinutes, final Integer pLoopLimit, final Integer pMinutesTotalRows) {
		StringBuilder minutesBuilder = new StringBuilder(EMPTY_STRING);
		
		if (pMinutes != null) {
			for (int indexMinutes = 1; indexMinutes <= pLoopLimit; indexMinutes++) {
				if ((indexMinutes % 3 == 0) && (pMinutesTotalRows == MINUTES_TOP_ROW_TOTAL_LIGHT_COUNT)) {
					minutesBuilder.append(MINUTES_QUARTER_RED_LIGHT_ON);
				} else {
					minutesBuilder.append(MINUTES_YELLOW_LIGHT_ON);
				}
			}
		}

		for (int indexMinutes = 0; indexMinutes < (pMinutesTotalRows - pLoopLimit); indexMinutes++) {
			minutesBuilder.append(LIGHT_OFF); //put light off symbol for remaining minute lamps.
		}

		return minutesBuilder.toString();
	}
}