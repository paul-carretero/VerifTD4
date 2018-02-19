import java.util.Random;

// Based on a B specification from Marie-Laure Potet.

public class Explosives{
    public int nb_inc = 0;
    public String [][] incomp = new String[50][2];
    public int nb_assign = 0;
    public String [][] assign = new String[30][2];
 
    
    /*
     * Le nombre de produits incompatibles est positif et inférieur à 50
     */ 
    /*@ public invariant // Prop 1
      @ (0 <= nb_inc && nb_inc < 50);
      @*/
    
    
    /*
     * Le nombre de produits assigné aux bâtiments est positif et inférieur à 30
     */
    /*@ public invariant // Prop 2
      @ (0 <= nb_assign && nb_assign < 30);
      @*/
    
    /*
     * La relation d'incompatibilité est établie entre deux produits
     * C'est à dire : les éléments dans le tableau d'incompatibilité sont tous des Produits (commencent par "Prod")
     */
    /*@ public invariant // Prop 3
      @ (\forall int i; 0 <= i && i < nb_inc; 
      @         incomp[i][0].startsWith("Prod") && incomp[i][1].startsWith("Prod"));
      @*/
    
    
    /*
     * La relation d'assignation est établie entre un batiment et un produit
     * C'est à dire : les elements dans le tableau d'incompatibilite sont des couples : (batiment, produit)  (les batiments commencent par "Bat" et les produits commencent par "Prod")" 
     */
    /*@ public invariant // Prop 4
      @ (\forall int i; 0 <= i && i < nb_assign; 
      @         assign[i][0].startsWith("Bat") && assign[i][1].startsWith("Prod"));
      @*/
    
    /*
     * Un produit ne peut pas etre incompatible avec lui meme  
     */
    /*@ public invariant // Prop 5
      @ (\forall int i; 0 <= i && i < nb_inc; !(incomp[i][0]).equals(incomp[i][1]));
      @*/
    
    /*
     * L'icompatibilite est reciproque
     * C'est a dire : Si un produit A est incompatible avec un produit B, alors le produit B est incompatible avec le produit A  
     */
    /*@ public invariant // Prop 6
      @ (\forall int i; 0 <= i && i < nb_inc; 
      @        (\exists int j; 0 <= j && j < nb_inc; 
      @           (incomp[i][0]).equals(incomp[j][1]) 
      @              && (incomp[j][0]).equals(incomp[i][1]))); 
      @*/
    
    /*  
     * Dans un meme batiment il ne peut pas y avoir de produits incompatibles entre eux
     */
    /*@ public invariant // Prop 7
      @ (\forall int i; 0 <= i &&  i < nb_assign; 
      @     (\forall int j; 0 <= j && j < nb_assign; 
      @        (i != j && (assign[i][0]).equals(assign [j][0])) ==>
      @        (\forall int k; 0 <= k && k < nb_inc;
      @           (!(assign[i][1]).equals(incomp[k][0])) 
      @              || (!(assign[j][1]).equals(incomp[k][1])))));
      @*/


    
    /*
     * Toutes les lignes du tableau des affectations (assign) sont differents deux a deux
     */
    /*@ public invariant // Prop 8
    @ (\forall int i; 0 <= i &&  i < nb_assign; 
    @     (\forall int j; 0 <= j && j < nb_assign; 
    @        (i != j && (assign[i][0]).equals(assign [j][0])) ==>
    @            !(assign[i][1]).equals(assign [j][1])
    @     )
    @ );              
    @*/
  

    /*
     * Un produit ne peut pas être stocké dans plus de trois bâtiments
     */
    /*@ public invariant // Prop 9
    @ (nb_assign > 2) ==>
    @ (\forall int i; 0 <= i &&  i < nb_assign; 
    @     (\forall int j; 0 <= j && j < nb_assign; 
    @         (\forall int k; 0 <= k &&  k < nb_assign; 
    @            (i != j && i != k && j != k && 
    @            (assign[i][1]).equals(assign [j][1])) ==> 
    @                (!(assign[i][1]).equals(assign [k][1]))
    @         )
    @     )
    @ );              
    @*/
  
    /*
     * Le nombre d’incompatibilités (nb_inc) ne peut jamais diminuer.
     */
    /*@ public constraint // Prop 10
    @ 	\old(nb_inc) <= nb_inc ;              
    @*/
    
    public void add_incomp(String prod1, String prod2){
	incomp[nb_inc][0] = prod1;
	incomp[nb_inc][1] = prod2;
	incomp[nb_inc+1][1] = prod1;
	incomp[nb_inc+1][0] = prod2;
	nb_inc = nb_inc+2;
     }
    
    //@ requires is_incomp_resp(bat, prod) ; 
    public void add_assign(String bat, String prod){
	assign[nb_assign][0] = bat;
	assign[nb_assign][1] = prod;
	nb_assign = nb_assign+1;
    }

    public void skip(){
    }
    
    
    
    public /*@ pure helper @*/ boolean is_incomp_resp(String bat, String prod) {
    	boolean resp = true;
    	for(int i = 0;i < nb_assign ; i++) {
    		if((assign[i][0]).equals(bat)) {
    			for(int k = 0; k < nb_inc; k++) {
    				if(incomp[k][0].equals(prod)) {
    					String prod_inc = incomp[k][1];
    					if(is_in_bat( bat, prod_inc)) {resp = false;}
    				}
    				if(incomp[k][1].equals(prod)) {
    					String prod_inc = incomp[k][0];
    					if(is_in_bat(bat, prod_inc)) {resp = false;}
    				}
    			}
    		}
    	} 
    	return resp;
    }
    
    public /*@ pure helper @*/ boolean is_in_bat(String bat, String prod) {
    	boolean is_in = false;
    	for(int i = 0; i < nb_assign ; i ++) {
    		if(assign[i][0].equals(bat)) {
    			is_in = is_in || assign[i][1].equals(prod);
    		}
    	}
    	return is_in;
    }
    
   public /*@ pure @*/ boolean is_in_assign(String bat) {
	   boolean assigned = false;
	   for(int i = 0; i < nb_assign ; i++) {
		   assigned = assigned || assign[i][0].equals(bat);
	   }
	   return assigned;
   }
   
   public /*@ pure @*/ String new_bat() {
	   String bat = "";
	   Random r = new Random();
	   bat = "Bat_" + r.nextInt(1000);
	   return bat;
   }

  /*
   * Fonction permettant de trouver un batiment dans lequel le produit peut être stocker sans risque de compatibilité.
   */  
  //@ requires prod.startsWith("Prod");
  //@ ensures \result.startsWith("Bat");
  public String findBat(String prod) {
	  String bat = "Bat_1";
	  boolean find = false;
	  // trouver un batiment où les incompatibilités sont respectées
	  for(int i = 0; i < nb_assign && !find ; i++ ) {
		if(is_incomp_resp(assign[i][0], prod)) {
			bat = assign[i][0];
			find = true;
		}  
	  }
	  // si aucun batiment déjà assigné ne respecte les incompatibilités, alors on alloue un nouveau batiment
	  if(!find) {
		 bat = new_bat();
		 while(is_in_assign(bat)) {
			 bat = new_bat();
		 }
	  }
	  return bat;
  }
    
  
  public /*@ pure @*/String toString() {
	  String s="";
	  s += "incompatibilités : \n";
	  for(int i = 0; i<nb_inc ; i ++) {
		  s+= "[" + incomp[i][0] + "," + incomp[i][1] + "]\n";
	  }
	  s += "----------------------------\n";
	  s += "assignations : \n";
	  for(int i = 0; i<nb_assign ; i ++) {
		  s+= "[" + assign[i][0] + "," + assign[i][1] + "]\n";
	  }
	  return s;
  }
}
