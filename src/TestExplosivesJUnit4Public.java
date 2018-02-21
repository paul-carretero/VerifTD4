import org.jmlspecs.utils.JmlAssertionError;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * Fichier de tests pour la classe Explosives.java
 * Tests qui ont besoin d'avoir accès aux variables publiques (pour invalider des invariants) 
 */
public class TestExplosivesJUnit4Public {

	Explosives e;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		org.jmlspecs.utils.Utils.useExceptions = true;
	}
	
	/*
	 * Test qui ne respecte pas l'invariant de la propriété 1 : 
	 * Le nombre de produits incompatibles est positif et inférieur à 50
	 */
	@Test (expected = JmlAssertionError.class)
	public void Test_Prop1_error_inf() {
		e = new Explosives();
		e.nb_inc = -1;
		e.skip();
	}
	
	/*
	 * Test qui ne respecte pas l'invariant de la propriété 1 : 
	 * Le nombre de produits incompatibles est positif et inférieur à 50
	 */
	@Test (expected = JmlAssertionError.class)
	public void Test_Prop1_error_sup() {
		e = new Explosives();
		e.nb_inc = 51;
		e.skip();
	}
	
	
	/*
	 * Test qui ne respecte pas l'invariant de la propriété 2 : 
	 * Le nombre de produits assigné aux bâtiments est positif et inférieur à 30
	 */
	@Test (expected = JmlAssertionError.class)
	public void Test_Prop2_error_inf() {
		e = new Explosives();
		e.nb_assign = -1;
		e.skip();
	}
	
	
	/*
	 * Test qui ne respecte pas l'invariant de la propriété 2 : 
	 * Le nombre de produits assigné aux bâtiments est positif et inférieur à 30
	 */
	@Test (expected = JmlAssertionError.class)
	public void Test_Prop2_error_sup() {
		e = new Explosives();
		e.nb_assign = 31;
		e.skip();
	}
	
	
	/*
	 * Test qui ne respecte pas l'invariant de la propriété 6 : 
	 * L'icompatibilite est reciproque
	 */
	@Test (expected = JmlAssertionError.class)
	public void Test_Prop6_error() {
		e = new Explosives();
		e.incomp[0][0] = "Prod_1";
		e.incomp[0][1] = "Prod_2";
		e.nb_inc ++;
		e.skip();
	}
	
	/*
	 * Test qui ne respecte pas l'invariant de la propriété 7 : 
	 * Dans un meme batiment il ne peut pas y avoir de produits incompatibles entre eux
	 */
	@Test (expected = JmlAssertionError.class)
	public void Test_Prop7_error() {
		e = new Explosives();
		e.add_incomp("Prod_1", "Prod_2");
		e.assign[0][0] = "Bat_1";
		e.assign[0][0] = "Prod_1";
		e.nb_assign++;
		e.assign[0][0] = "Bat_1";
		e.assign[0][0] = "Prod_2";
		e.nb_assign++;
		e.skip();
	}
	
}
