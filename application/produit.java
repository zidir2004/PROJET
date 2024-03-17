/**
 * La classe Produit représente un produit stocké dans la base de données.
 * Chaque produit a un identifiant unique, un nom (type) et une quantité en stock.
 */	
package application;

	public class produit {
	    private int id_produit;   // Identifiant du produit dans la base de données
	    private String type;      // Nom du produit
	    private float quantite;    // Quantité en stock

	    /**
	     * Constructeur par défaut de la classe Produit.
	     */
	    public produit() {
	    }

	    /**
	     * Constructeur de la classe Produit avec tous les attributs.
	     *
	     * @param idProduit L'identifiant du produit dans la base de données.
	     * @param type      Le nom du produit.
	     * @param quantite  La quantité en stock du produit.
	     */
	    public produit(int id, String type, float quantite) {
	        this.id_produit = id;
	        this.type = type;
	        this.quantite = quantite;
	    }

	    /**
	     * Constructeur de la classe Produit sans l'identifiant.
	     * Utilisé lors de l'ajout d'un nouveau produit.
	     *
	     * @param type     Le nom du produit.
	     * @param quantite La quantité en stock du produit.
	     */
	    public produit(String type, float quantite) {
	        this.type = type;
	        this.quantite = quantite;
	    }

	    // Getters et setters

	    public String toString() {
	        return "type : "+getType() + " - Quantité: " + getQuantite();
	        // Ajoutez d'autres attributs au besoin
	    }
	    
	    public int getId() {
	        return id_produit;
	    }

	    public void setId(int id_produit) {
	        this.id_produit = id_produit;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public float getQuantite() {
	        return quantite;
	    }

	    public void setQuantite(float quantite) {
	        this.quantite = quantite;
	    }

	 
	  

	

		
	}