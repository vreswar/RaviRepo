/**
 * 
 */
package com.hcl.challenge.service;

import java.io.Serializable;

import com.hcl.challenge.exception.InvalidTimeException;

/**
 * Interface to put standard to have different method to display for Berlin clock tower.
 * 
 * @author pushpeswar.r
 *
 */
public interface BerlinClockTower extends Serializable {
	/** Method to calculate clock tower seconds row lights on and off */
	String getSeconds(Integer pSeconds) throws InvalidTimeException;
	/** Method to calculate clock tower first hours row lights on and off */
	String getTopHours(Integer pHours) throws InvalidTimeException;
	/** Method to calculate clock tower second hours row lights on and off */
	String getBottomHours(Integer pHours) throws InvalidTimeException;
	/** Method to calculate clock tower first minutes row lights on and off */
	String getTopMinutes(Integer pMinutes) throws InvalidTimeException;
	/** Method to calculate clock tower second minutes row lights on and off */
	String getBottomMinutes(Integer pMinutes) throws InvalidTimeException;
}