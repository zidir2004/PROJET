package application;

import java.sql.*;
/**
 * Classe représentant une commande dans le système.
 */
public class Commande {
    private int idCommande;
    private int quantite;
    private int valide;
    private int idClient;
    private int idProduit;

    // constructeur, getters, setters, etc.
    /**
     * Obtient l'identifiant de la commande.
     * @return L'identifiant de la commande.
     */
    public int getIdCommande() {
        return idCommande;
    }
    /**
     * Définit l'identifiant de la commande.
     * @param idCommande Le nouvel identifiant de la commande.
     */
    public void setId(int idCommande) {
        this.idCommande = idCommande;
    }
    /**
     * Définit la quantité de produits dans la commande.
     * @param quantite La nouvelle quantité de produits.
     */
	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	/**
     * Obtient l'état de validation de la commande.
     * @return L'état de validation de la commande.
     */
	public int getValide() {
		return valide;
	}
	/**
     * Définit l'état de validation de la commande.
     * @param valide Le nouvel état de validation de la commande.
     */
	public void setValide(int valide) {
		this.valide = valide;
	}
	 /**
     * Obtient l'identifiant du client associé à la commande.
     * @return L'identifiant du client associé à la commande.
     */
	public int getIdClient() {
		return idClient;
	}
	/**
     * Définit l'identifiant du client associé à la commande.
     * @param idClient Le nouvel identifiant du client.
     */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	/**
     * Obtient l'identifiant du produit associé à la commande.
     * @return L'identifiant du produit associé à la commande.
     */
	public int getIdProduit() {
		return idProduit;
	}
	/**
     * Définit l'identifiant du produit associé à la commande.
     * @param idProduit Le nouvel identifiant du produit.
     */
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	/**
     * Obtient le type du produit associé à la commande en consultant la base de données.
     * @return Le type du produit associé à la commande.
     */
	public String getType() {
		String type="";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM produitfini WHERE id_produit = ?")){
					statement.setInt(1, this.getIdProduit());
					ResultSet resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						type = (resultSet.getString("type"));
					}
				}catch (SQLException e) {
					e.printStackTrace();
					
				}
		return type;
	}
	/**
     * Obtient une représentation textuelle de la commande.
     * @return Une chaîne représentant la commande.
     */
	 public String toString() {
	        return "Id_Commande: "+getIdCommande()+" - Type: "+ getType()+ " - Quantité: " + getQuantite();
	        // Ajoutez d'autres attributs au besoin
	    }
    // Ajoutez les autres getters et setters nécessaires
	 
	 /**
	     * La méthode renvoie le prix au kg du produit de la commande.
	     * @return Le prix au kg du produit de la commande.
	     */
	 public double getPrixProduit() {
		 double prix=-1;
		 try {
			 Connection connection=DatabaseConnection.getConnection();
			 String querry="SELECT prix_kg FROM produitfini WHERE id_produit = ?";
			 PreparedStatement statement=connection.prepareStatement(querry);
			 statement.setInt(1, this.idProduit);
			 ResultSet resultSet=statement.executeQuery();
			 
			 if(resultSet.next()) {
				 prix=resultSet.getDouble("prix_kg");
			 }
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return prix;
	 }
}