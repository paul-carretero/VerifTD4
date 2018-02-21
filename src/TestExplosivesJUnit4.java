import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * Fichier de tests pour la classe Explosives.java
 * Tests qui n'ont pas besoin d'avoir accès aux variables publiques (pour invalider des invariants) 
 */
public class TestExplosivesJUnit4 {

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
	
	@Test
	public void  testSequence_0() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_Nitro","Prod_Glycerine");
			e.add_incomp("Prod_Dyna","Prod_Mite");
			e.add_assign("Bat_1","Prod_Dyna");
			e.add_assign("Bat_1","Prod_Nitro");
			e.add_assign("Bat_2","Prod_Mite");
			e.add_assign("Bat_1","Prod_Glycerine");
			
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}

	/* Test qui respecte la propriété 8 (invariant) : 
	 * Toutes les lignes du tableau des affectations (assign) sont differents deux a deux 
	 */
	@Test
	public void  test_Prop8() {
		e=new Explosives();
		e.add_incomp("Prod_Dyna","Prod_Mite");
		e.add_assign("Bat_1","Prod_Dyna");
		e.add_assign("Bat_2","Prod_Mite");
	}
	
	/* Test qui ne respecte pas la propriété 8 (invariant) : 
	 * Toutes les lignes du tableau des affectations (assign) sont differents deux a deux 
	 */
	@Test (expected  = JmlAssertionError.class)
	public void  test_Prop8_error() {
		e=new Explosives();
		e.add_incomp("Prod_Dyna","Prod_Mite");
		e.add_assign("Bat_1","Prod_Dyna");
		e.add_assign("Bat_1","Prod_Dyna");
	}
	
	/* Test qui ne respecte pas la propriété 9 (invariant): 
	 * Un produit ne peut pas être stocké dans plus de trois bâtiments
	 */
	@Test (expected  = JmlAssertionError.class)
	public void  test_Prop9_error() {
		e=new Explosives();
		e.add_incomp("Prod_Dyna","Prod_Mite");
		e.add_assign("Bat_1","Prod_Dyna");
		e.add_assign("Bat_2","Prod_Dyna");
		e.add_assign("Bat_3","Prod_Dyna");
	}
	


}
