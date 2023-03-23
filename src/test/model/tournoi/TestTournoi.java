/*
 * @author Morgan Nayet              19 Feb 2023
 * Copyrights
 */
package src.test.model.tournoi;

import java.util.ArrayList;
import main.model.tournoi.Tournoi;
import main.model.tournoi.type.Poule;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Classe de tests JUnit de Tournoi
 * @author Morgan Nayet
 */
public class TestTournoi {

	private static ArrayList<Tournoi> tousLesTypesDeTournoi;
	
	@BeforeAll
	public static void creationTournois() {
		
		tousLesTypesDeTournoi = new ArrayList<>();
		
		// entrée de tous les types de tournoi
		tousLesTypesDeTournoi.add(new main.model.tournoi.type.LoserBracket("testTournoiLooserBracket", 4));
		tousLesTypesDeTournoi.add(new Poule("testTournoiPoule", 4));
	}
	
	@Test
	public void fermerWhenUseMustDontThrowError() {
		
		for(Tournoi tournoi : tousLesTypesDeTournoi) {
			assertDoesNotThrow(() -> tournoi.fermer());
		}
	}
}
