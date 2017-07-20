/**
 * 
 */
package com.hcl.challenge.service;

import java.io.Serializable;
import java.time.LocalTime;

import com.hcl.challenge.exception.InvalidTimeException;

/**
 * Interface to put contract for clock time display
 * 
 * @author pushpeswar.r
 * @version 1.0
 */
public interface ClockTower extends Serializable {
	/** Method to display clock time for a provided LocalTime instance */
	String displayClock(LocalTime pTimeHHMMSS) throws InvalidTimeException;
}