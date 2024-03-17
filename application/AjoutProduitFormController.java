package application;

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
/**
 * Contrôleur pour la fenêtre d'ajout de quantité à un produit fini existant.
 * Gère l'ajout de quantité à un produit fini sélectionné dans la base de données.
 */
public class AjoutProduitFormController {

	@FXML
    public ChoiceBox<String> produitChoiceBox;

    @FXML
    public TextField quantiteField;

    @FXML
    /**
     * Initialise la fenêtre en remplissant la ChoiceBox avec les types de produits finis existants depuis la base de données.
     */
    public void initialize() {
    	List<String> typesProduits = chargerTypesProduitsDepuisDB();
        produitChoiceBox.getItems().addAll(typesProduits);
    }
    /**
     * Charge les types de produits finis depuis la base de données.
     *
     * @return Une liste contenant les types de produits finis.
     */
    public List<String> chargerTypesProduitsDepuisDB() {
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
    /**
     * Méthode appelée lors de l'action d'ajout de quantité à un produit fini existant.
     * Récupère le produit sélectionné et la quantité à ajouter, puis met à jour la quantité dans la base de données.
     */
    public void ajouterProduit() {
        // Récupérez le produit sélectionné et la quantité à ajouter
        String produitSelectionne = produitChoiceBox.getValue();
        String quantiteAjouter = quantiteField.getText();

        // Vérifiez que les champs ne sont pas vides
        if (produitSelectionne == null || quantiteAjouter.isEmpty()) {
        	showAlert("Error", "aucun produit selectionnée , ou quantité null", Alert.AlertType.ERROR);
            return;
        }

        try {
            // Convertit la quantité en un nombre entier
            int quantite = Integer.parseInt(quantiteAjouter);
            
            // UPDATE produitfini SET quantite = quantite + ? WHERE type = ?
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE produitfini SET quantite = quantite + ? WHERE type = ?")) {

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
    /**
     * Affiche une boîte de dialogue avec le titre, le contenu et le type d'alerte spécifiés.
     *
     * @param title   Le titre de la boîte de dialogue.
     * @param content Le contenu de la boîte de dialogue.
     * @param type    Le type de la boîte de dialogue (par exemple, INFORMATION, AVERTISSEMENT, ERREUR).
     */
	 private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
