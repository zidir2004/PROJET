package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifierPrixController {

	@FXML
    private ChoiceBox<String> produitChoiceBox;

    @FXML
    private TextField prixField;

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
    private void changerPrix() {
        // Récupérez le produit sélectionné et la quantité à ajouter
        String produitSelectionne = produitChoiceBox.getValue();
        String prix = prixField.getText();

        // Vérifiez que les champs ne sont pas vides
        if (produitSelectionne == null || prix.isEmpty()) {
        	showAlert("Error", "aucun produit selectionnée , ou quantité null", Alert.AlertType.ERROR);
            return;
        }

        try {
            // Convertit la quantité en un nombre entier
            int Prix = Integer.parseInt(prix);
            
            // UPDATE produitfini SET quantite = quantite + ? WHERE type = ?
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE produitfini SET prix_kg = ? WHERE type = ?")) {

                statement.setInt(1, Prix);
                statement.setString(2, produitSelectionne);
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace(); // Gérer les exceptions SQL
            }
                
            
            
            Stage stage = (Stage) prixField.getScene().getWindow();
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
