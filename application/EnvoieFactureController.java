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
 * Contrôleur pour la fenêtre d'envoi de facture.
 * Gère l'envoi de facture pour les ventes sélectionnées.
 */
public class EnvoieFactureController {
	
	@FXML
	private ChoiceBox<String> venteChoiceBox;
	
	
	@FXML
	 /**
     * Initialise la fenêtre en remplissant la ChoiceBox avec les ventes non facturées depuis la base de données.
     */
    private void initialize() {
    	List<String> typesProduits = chargerVentesDepuisDB();
        venteChoiceBox.getItems().addAll(typesProduits);
    }
	 /**
     * Charge les ventes non facturées depuis la base de données.
     *
     * @return Une liste contenant les informations des ventes non facturées.
     */
	private List<String> chargerVentesDepuisDB() {
   	 List<String> ventes = new ArrayList<>();

   	    try (Connection connection = DatabaseConnection.getConnection();
   	         PreparedStatement statement = connection.prepareStatement("SELECT id_vente, prix, id_client FROM vente WHERE facture=0");
   	         ResultSet resultSet = statement.executeQuery()) {

   	        while (resultSet.next()) {
   	            String vente = resultSet.getString("id_vente");
   	            vente+="-Prix: ";
   	            vente+=resultSet.getString("prix");
   	            int id=resultSet.getInt("id_client");
   	            
   	            String querry2="SELECT prenom, nom FROM client WHERE id_client = ?";
   	            PreparedStatement statement2 = connection.prepareStatement(querry2);
   	            statement2.setInt(1, id);
   	            ResultSet resultSet2=statement2.executeQuery();
   	            if(resultSet2.next()) {
   	            	vente+=". Client : ";
   	            	vente+=resultSet2.getString("prenom") + " ";
   	            	vente+=resultSet2.getString("nom");
   	            }
   	            ventes.add(vente);
   	        }

   	    } catch (SQLException e) {
   	        e.printStackTrace(); // Gérer les exceptions SQL
   	    }

   	    return ventes;
   }
	
	@FXML
    private void envoieFacture() {
        // Récupérez les ventes sélectionnés
        String produitSelectionne = venteChoiceBox.getValue();

        // Vérifiez que les champs ne sont pas vides
        if (produitSelectionne == null) {
        	showAlert("Error", "aucune vente selectionnée", Alert.AlertType.ERROR);
            return;
        }
        int id=Character.getNumericValue(produitSelectionne.charAt(0));

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE vente SET facture = 1 WHERE id_vente = ?")) {

                statement.setInt(1, id);
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace(); // Gérer les exceptions SQL
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
