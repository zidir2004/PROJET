package application;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Contrôleur pour la vue associée au rôle "Commercial".
 * Gère les fonctionnalités liées aux matières premières, aux commandes et aux clients.
 */
public class CommercialController {
	 @FXML
	    private VBox notificationBox;

	    @FXML
	    private ListView<MatierePremiere> notificationListView;

	    private boolean notificationsVisible = false;
	    
	    
	    @FXML
	    private Button logoutButton ;
	    
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
	     * Affiche ou masque la boîte de notifications (matières premières à réapprovisionner).
	     * Charge les notifications si la boîte devient visible.
	     */
	    private void afficherNotifications() {
	        notificationsVisible = !notificationsVisible; // Inversez la visibilité à chaque clic

	        notificationBox.setVisible(notificationsVisible);

	        if (notificationsVisible) {
	            // Si les notifications sont maintenant visibles, chargez les notifications
	            chargerMatierePremiereAReapprovisionner();
	        }
	    }
	    @FXML
	    /**
	     * Charge la vue d'achat de matières premières.
	     */
	    private void AchatMatiere(){
	    	Main2.loadFXML("AjoutMatierePremiere.fxml");
	    }
	    
	    
	    @FXML
	    /**
	     * Valide la commande sélectionnée, réapprovisionne le stock de matières premières, et affiche une notification.
	     */
	    private void validerCommande() {
	        // Récupérer la commande sélectionnée
	        MatierePremiere selectedProduit = notificationListView.getSelectionModel().getSelectedItem();
	        if (selectedProduit != null) {
	        	 try (Connection connection = DatabaseConnection.getConnection();
	                     PreparedStatement statement = connection.prepareStatement("UPDATE matierepremiere SET quantite = quantite + ? where type = ? ")) {

	                    statement.setFloat(1,selectedProduit.getQuantite() );
	                    statement.setString(2,selectedProduit.getType());
	                    statement.executeUpdate();
	                    chargerMatierePremiereAReapprovisionner();
	                    showAlert("Succès", "le stock de matiére premiere a été réaprovisionné.", Alert.AlertType.INFORMATION);
	        }catch (SQLException e) {
                e.printStackTrace(); // Gérer les exceptions SQL
            }
	    }
	    }
	    /**
	     * Charge les matières premières à réapprovisionner depuis la base de données et les affiche dans la ListView des notifications.
	     */
	    private void chargerMatierePremiereAReapprovisionner() {
	        // Chargez les matières premières à réapprovisionner depuis la base de données
	        List<MatierePremiere> matieresAReapprovisionner = new ArrayList<>();

	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement("SELECT * FROM matierepremiere WHERE quantite < seuil_reapprovisionnement");
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                MatierePremiere matiere = new MatierePremiere();
	                matiere.setId(resultSet.getInt("id_matiere"));
	                matiere.setType(resultSet.getString("type"));
	                float quantite_a = (resultSet.getFloat("seuil_reapprovisionnement")- resultSet.getFloat("quantite"));
	                matiere.setQuantite(quantite_a);
	                // Récupérez d'autres attributs au besoin

	                matieresAReapprovisionner.add(matiere);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace(); // Gérer les exceptions SQL
	        }

	        // Affichez les matières premières à réapprovisionner dans la ListView des notifications
	        notificationListView.getItems().clear();
	        notificationListView.getItems().addAll(matieresAReapprovisionner);
	    }

	
	@FXML
	/**
	 *La méthode ouvre le menu de création de commande lorsqu'on appuie sur le bouton correspendant
	 */
	public void nouvelleCommande() {
    	Main2.loadFXML("CreationCommande.fxml");
    }
	
	
	
	
	/**
	 * La méthode ouvre le menu d'ajout client lorsqu'on appuie sur le bouton correspendant
	 */
	public void ajoutClient() {
        Main2.loadFXML("AjoutClient.fxml");
    }
	
	
	 private void showAlert(String title, String content, Alert.AlertType type) {
	        Alert alert = new Alert(type);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
}