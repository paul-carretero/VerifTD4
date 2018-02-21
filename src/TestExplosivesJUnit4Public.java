import static org.junit.Assert.fail;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * Fichier de tests pour la classe Explosives.java
 * Tests qui ont besoin d'avoir accès aux variables publiques (pour invalider des invariants) 
 */
public class TestExplosivesJUnit4Public {

	static int nb_inconclusive = 0;
    static int nb_fail = 0;

    Explosives e;

    public static void main(String args[]) {
    	String testClass = "TestExplosivesJUnit4";
     	org.junit.runner.JUnitCore.main(testClass);
     }


    private void handleJMLAssertionError(JmlAssertionError e) {
    	if (e.getClass().equals(JmlAssertionError.PreconditionEntry.class)) {
    	    System.out.println("\n INCONCLUSIVE "+(new Exception().getStackTrace()[1].getMethodName())+ "\n\t "+ e.getMessage());
            nb_inconclusive++;}
    else{
	    // test failure	
	    nb_fail++;
	    fail("\n\t" + e.getMessage());	
		}  
    }
	
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nb_inconclusive = 0;
		nb_fail = 0;
		org.jmlspecs.utils.Utils.useExceptions = true;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	     System.out.println("\n inconclusive tests: "+nb_inconclusive+" -- failures : "+nb_fail );
	}
	
	
	/*
	 * Test qui ne respecte pas l'invariant de la propriété 1 : 
	 * Le nombre de produits incompatibles est positif et inférieur à 50
	 */
	@Test 
	public void Test_Prop1_error_inf() {
		try {
			e = new Explosives();
			e.nb_inc = -1;
			e.skip();
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e);		
		}
	}
	
	/*
	 * Test qui ne respecte pas l'invariant de la propriété 1 : 
	 * Le nombre de produits incompatibles est positif et inférieur à 50
	 */
	@Test 
	public void Test_Prop1_error_sup() {
		try {
			e = new Explosives();
			e.nb_inc = 51;
			e.skip();
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e);		
		}
	}
	
	
	/*
	 * Test qui ne respecte pas l'invariant de la propriété 2 : 
	 * Le nombre de produits assigné aux bâtiments est positif et inférieur à 30
	 */
	@Test
	public void Test_Prop2_error_inf() {
		try {
			e = new Explosives();
			e.nb_assign = -1;
			e.skip();
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e);		
		}
	}
	
	
	/*
	 * Test qui ne respecte pas l'invariant de la propriété 2 : 
	 * Le nombre de produits assigné aux bâtiments est positif et inférieur à 30
	 */
	@Test 
	public void Test_Prop2_error_sup() {
		try {
			e = new Explosives();
			e.nb_assign = 31;
			e.skip();
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e);		
		}
	}
	
	
	/*
	 * Test qui ne respecte pas l'invariant de la propriété 6 : 
	 * L'icompatibilite est reciproque
	 */
	@Test 
	public void Test_Prop6_error() {
		try {
			e = new Explosives();
			e.incomp[0][0] = "Prod_1";
			e.incomp[0][1] = "Prod_2";
			e.nb_inc ++;
			e.skip();
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e);		
		}
	}
	
	/*
	 * Test qui ne respecte pas l'invariant de la propriété 7 : 
	 * Dans un meme batiment il ne peut pas y avoir de produits incompatibles entre eux
	 */
	@Test 
	public void Test_Prop7_error() {
		try {
			e = new Explosives();
			e.add_incomp("Prod_1", "Prod_2");
			e.assign[0][0] = "Bat_1";
			e.assign[0][0] = "Prod_1";
			e.nb_assign++;
			e.assign[0][0] = "Bat_1";
			e.assign[0][0] = "Prod_2";
			e.nb_assign++;
			e.skip();
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e);		
		}
	}
	
}
