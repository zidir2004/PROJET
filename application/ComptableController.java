package application;

import java.sql.*;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
/**
 * Contrôleur pour la vue associée au rôle "Comptable".
 * Gère les fonctionnalités liées au calcul du chiffre d'affaires, du bénéfice, à la déconnexion,
 * à l'envoi de factures et à la modification des prix.
 */
public class ComptableController {
	//Attributs
	@FXML
    private Button logoutButton ;
	
	private double chiffreAffaire;
	private double benefice;
	
	@FXML
	private Label chiffreAffaireLabel;
	
	@FXML
	private Label beneficeLabel;
	
	@FXML
	 /**
     * Initialise la vue en calculant et affichant le chiffre d'affaires et le bénéfice.
     */
    private void initialize() {
    	this.chiffreAffaire=setChiffreAffaire();
    	this.benefice=setBenefice();
    	chiffreAffaireLabel.setText(String.valueOf(this.chiffreAffaire) + "€");
        beneficeLabel.setText(String.valueOf(this.benefice) + "€");
    }
	
	//Calcul du chiffre d'affaire
	 /**
     * Calcul du chiffre d'affaires en récupérant les prix des ventes depuis la base de données.
     *
     * @return Le chiffre d'affaires calculé.
     */
	public double setChiffreAffaire() {
		double x=0;
		try {
			Connection connection=DatabaseConnection.getConnection();
			String querry="SELECT prix FROM vente";
		    PreparedStatement statement=connection.prepareStatement(querry);
		    ResultSet resultSet=statement.executeQuery();
		    while(resultSet.next()) {
		    	x+=resultSet.getDouble("prix");
		    }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		this.chiffreAffaire=x;
		return x;
	}
	
	//Calcul du benefice
	/**
     * Calcul du bénéfice en soustrayant les coûts d'achat du chiffre d'affaires (sans TVA).
     *
     * @return Le bénéfice calculé.
     */
	public double setBenefice() {
		double x=0;
		double sansTVA=this.chiffreAffaire-(this.chiffreAffaire*20)/100; //Calcul du chiffre d'affaire sans TVA à 20%
		try {
			Connection connection=DatabaseConnection.getConnection();
			String querry="SELECT prix FROM achat";
		    PreparedStatement statement=connection.prepareStatement(querry);
		    ResultSet resultSet=statement.executeQuery();
		    while(resultSet.next()) {
		    	x+=resultSet.getDouble("prix");
		    }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		x=sansTVA-x;
		return x;
	}
	
	
	//Méthodes
	@FXML
	/**
     * Déconnecte l'utilisateur actuel et retourne à la vue de connexion.
     */
    private void logout() {
    	 Stage stage = (Stage) logoutButton.getScene().getWindow();
         stage.close();
         
         Main2.loadFXML("Sample.fxml");
    }
	
	@FXML
	 /**
     * Charge la vue d'envoi de facture.
     */
	public void envoieFacture() {
    	Main2.loadFXML("envoieFacture.fxml");
    }
	
	@FXML
	/**
     * Charge la vue de modification des prix.
     */
	public void modifierPrix() {
		Main2.loadFXML("modifierPrix.fxml");
	}
}
