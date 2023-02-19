/*
 * @author Morgan Nayet              19 Feb 2023
 * Copyrights
 */
package test.model.tournoi;

import main.model.tournoi.Tournoi;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * @author Morgan Nayet
 */
public class TestTournoi {

	private Tournoi tournoi;
	
	private void creationTournoi() {
		this.tournoi = new Tournoi("Le grand tournoi du café");
	}
}
