package application;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Contrôleur pour la fenêtre d'ajout de client.
 * Gère l'ajout d'un nouveau client dans la base de données.
 */	
public class AjoutClientController {
	//Attributs
	@FXML
	public TextField nom;
	@FXML
	public  TextField prenom;
	@FXML
	public  TextField adresse;
	@FXML
	public  TextField email;
	@FXML
	public  TextField telephone;
	/**
     * Méthode appelée lors de l'action d'ajout d'un nouveau client.
     * Récupère les données des champs de saisie, vérifie la validité, puis ajoute le client à la base de données.
     */
	public void ajouterProduit() {
		String nom=this.nom.getText();
		String prenom=this.prenom.getText();
		String adresse=this.adresse.getText();
		String email=this.email.getText();
		String telephone=this.telephone.getText();
		
		if(nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || email.isEmpty() || telephone.isEmpty()) {
			showAlert("Error", "aucun produit selectionnée , ou quantité null", Alert.AlertType.ERROR);
            return;
		}
		
		String querry="INSERT INTO `client`(`nom`, `prenom`, `adresse`, `email`, `telephone`) VALUES (?, ?, ?, ?, ?)";
		
		try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(querry)){
			statement.setString(1, nom);
			statement.setString(2, prenom);
			statement.setString(3, adresse);
			statement.setString(4, email);
			statement.setString(5, telephone);
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
        // Fermeture de la fenêtre après l'ajout du client
		Stage stage = (Stage) this.nom.getScene().getWindow();
        stage.close();
	}
	
	private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
