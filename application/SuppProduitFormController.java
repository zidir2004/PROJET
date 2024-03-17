package application;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuppProduitFormController {


	@FXML
    private ChoiceBox<String> produitChoiceBox;

    @FXML
    private TextField quantiteField;

    @FXML
    private void initialize() {
    	List<String> typesProduits = chargerTypesProduitsDepuisDB();
        produitChoiceBox.getItems().addAll(typesProduits);
    }

    private List<String> chargerTypesProduitsDepuisDB() {
    	 List<String> typesProduits = new ArrayList<>();

    	    try (Connection connection = DatabaseConnection.getConnection();
    	         PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT type FROM produitfini");
    	         ResultSet resultSet = statement.executeQuery()) {

    	        while (resultSet.next()) {
    	            String typeProduit = resultSet.getString("type");
    	            typesProduits.add(typeProduit);
    	        }

    	    } catch (SQLException e) {
    	        e.printStackTrace(); // Gérer les exceptions SQL
    	    }

    	    return typesProduits;
    }
    
    @FXML
    private void ajouterProduit() {
        // Récupérez le produit sélectionné et la quantité à ajouter
        String produitSelectionne = produitChoiceBox.getValue();
        String quantiteAjouter = quantiteField.getText();

        // Vérifiez que les champs ne sont pas vides
        if (produitSelectionne == null || quantiteAjouter.isEmpty()) {
        	showAlert("Error", "aucun produit selectionnée , ou quantité null", Alert.AlertType.ERROR);
            return;
        }

        try {
            // Convertissez la quantité en un nombre entier
            int quantite = Integer.parseInt(quantiteAjouter);
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("Select quantite from produitfini  WHERE type = ?")) {
            	
                   statement.setString(1, produitSelectionne);
                   ResultSet resultSet = statement.executeQuery();
                   if(resultSet.next()) {
                       int quantiteStock = resultSet.getInt("quantite");
                       if(quantiteStock < quantite) {
                           showAlert("Error", "Pas assez de quantité dans le stock", Alert.AlertType.ERROR);
                           return;
                       }
                   } else {
                       // Gérer le cas où le produit n'est pas trouvé dans la base de données
                       showAlert("Error", "Produit non trouvé dans la base de données", Alert.AlertType.ERROR);
                       return;
                   }
                	   
                   

            }catch (SQLException e) {
                   e.printStackTrace(); // Gérer les exceptions SQL
               }
            
            // UPDATE produitfini SET quantite = quantite + ? WHERE type = ?
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE produitfini SET quantite = quantite - ? WHERE type = ?")) {

                statement.setInt(1, quantite);
                statement.setString(2, produitSelectionne);
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace(); // Gérer les exceptions SQL
            }

            
            Stage stage = (Stage) quantiteField.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            // Gérez l'exception si la conversion en nombre échoue
            e.printStackTrace();
        }
        
    }

	 private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
