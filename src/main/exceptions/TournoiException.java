/*
 * @author Morgan Nayet              22 Mar 2023
 * Copyrights
 */
package main.exceptions;

/**
 * 
 * @author Morgan Nayet
 */
public class TournoiException extends Exception {

	/**
	 * Exception concernant les tournois de l'application
	 * 
	 * @param message concernant l'évènement survenu
	 */
	public TournoiException(String message) {
		super(message);
	}
}
