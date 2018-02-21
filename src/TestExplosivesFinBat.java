import org.jmlspecs.utils.JmlAssertionError;
import org.junit.Test;

/*
 * Fichier de tests pour la fonction finBat de la classe Explosives.java
 */
public class TestExplosivesFinBat {

	
    Explosives e;

	/* Test simple pour trouver un batiment pour assigner un produit, qui respecte les incompatibilités
	 */
	@Test
	public void  test_find_Bat() {
			e=new Explosives();
			e.add_incomp("Prod_Dyna","Prod_Mite");
			e.add_assign("Bat_1","Prod_Dyna");
			e.add_assign(e.findBat("Prod_Mite"), "Prod_Mite");  
	}
	
	/* Test pour trouver un batiment pour assigner un produit, qui respecte les incompatibilités
	 * test pour 4 produits, chacun avec une incompatibilité
	 */
	@Test
	public void  test_find_Bat2() {
			e=new Explosives();
			e.add_incomp("Prod_Dyna","Prod_Mite");
			e.add_incomp("Prod_Nitro","Prod_Glycerine");
			e.add_assign("Bat_1","Prod_Dyna");
			e.add_assign(e.findBat("Prod_Nitro"), "Prod_Nitro");
			e.add_assign(e.findBat("Prod_Mite"), "Prod_Mite");
			e.add_assign(e.findBat("Prod_Glycerine"), "Prod_Glycerine");
			System.out.println(e);  
	}
}
