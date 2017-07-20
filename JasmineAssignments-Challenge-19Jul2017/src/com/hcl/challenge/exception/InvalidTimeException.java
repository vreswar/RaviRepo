/**
 * 
 */
package com.hcl.challenge.exception;

/**
 * Custom exception class used to throw whenever any invalid time input is provided in processing the Clock Tower.
 * 
 * @author pushpeswar.r
 *
 */
public class InvalidTimeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6291536811995229640L;

	/**
	 * constructor to take exception message and pass is to super class.
	 * 
	 * @param pException
	 */
	public InvalidTimeException(String pException) {
		super(pException);
	}
}