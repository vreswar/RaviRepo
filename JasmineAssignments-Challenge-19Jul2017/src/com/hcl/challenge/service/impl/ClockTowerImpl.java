/**
 * 
 */
package com.hcl.challenge.service.impl;

import static com.hcl.challenge.constants.ClockTowerConstants.LINE_SEPARATOR_PROPERTY_KEY;
import static com.hcl.challenge.constants.ClockTowerConstants.LOG4J_PROPERTIES_PATH;
import static com.hcl.challenge.constants.ClockTowerConstants.SPACE_STRING;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.hcl.challenge.exception.InvalidTimeException;
import com.hcl.challenge.service.BerlinClockTower;
import com.hcl.challenge.service.ClockTower;

/**
 * Class to implement ClockTower interface
 * 
 * @author pushpeswar.r
 *
 */
public class ClockTowerImpl implements ClockTower {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5508054808511876898L;
	/** Static logger instance */
	private static final Logger LOGGER = Logger.getLogger(ClockTowerImpl.class);
	/** Berlin Clock Tower instance */
	private BerlinClockTower berlinClockTower;
	
	/**
	 * Constructor accepting BerlinClockTower implementation
	 */
	public ClockTowerImpl(BerlinClockTower pBerlinClockTower) {
		berlinClockTower = pBerlinClockTower;
	}

	/* (non-Javadoc)
	 * @see com.hcl.challenge.service.ClockTower#displayClock(java.lang.String)
	 */
	@Override
	public String displayClock(final LocalTime pLocalTime) throws InvalidTimeException {
		LOGGER.info("In displayClock method, received time input : " + pLocalTime);
		StringBuilder berlinClockBuilder = new StringBuilder();
		
		if(pLocalTime == null) {
			throw new InvalidTimeException("Input is invalid, cannot be a null value");
		} else {
			final String LINE_SEPARATOR = System.getProperty(LINE_SEPARATOR_PROPERTY_KEY);
			
			berlinClockBuilder.append(LINE_SEPARATOR);
			berlinClockBuilder.append(berlinClockTower.getSeconds(pLocalTime.getSecond()));
			berlinClockBuilder.append(LINE_SEPARATOR);
			berlinClockBuilder.append(SPACE_STRING).append(berlinClockTower.getTopHours(pLocalTime.getHour()));
			berlinClockBuilder.append(LINE_SEPARATOR);
			berlinClockBuilder.append(SPACE_STRING).append(berlinClockTower.getBottomHours(pLocalTime.getHour()));
			berlinClockBuilder.append(LINE_SEPARATOR);
			berlinClockBuilder.append(SPACE_STRING).append(berlinClockTower.getTopMinutes(pLocalTime.getMinute()));
			berlinClockBuilder.append(LINE_SEPARATOR);
			berlinClockBuilder.append(SPACE_STRING).append(berlinClockTower.getBottomMinutes(pLocalTime.getMinute()));
			berlinClockBuilder.append(LINE_SEPARATOR);
		}
		
		LOGGER.info("Got the final Clock Tower");
		LOGGER.info(berlinClockBuilder.toString());
		
		return berlinClockBuilder.toString();
	}
	
	/**
	 * configure log4j properties
	 */
	private void setLog4jProperties() {
		Properties properties = new Properties();
		
		try {
			properties.load(getClass().getResourceAsStream(LOG4J_PROPERTIES_PATH));
		} catch (IOException exception) {
			LOGGER.error("Error initializing properties", exception);
		}
		
		PropertyConfigurator.configure(properties);
	}
	
	public static void main(String[] args) {
		BerlinClockTower berlinTower = new BerlinClockTowerImpl();
		ClockTowerImpl clockTower = new ClockTowerImpl(berlinTower);
		clockTower.setLog4jProperties();
		
		try {
			clockTower.displayClock(LocalTime.now());
		} catch (Exception exception) {
			LOGGER.error("Exception in ClockTowerImpl class", exception);
		}
	}
}