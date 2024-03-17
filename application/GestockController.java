/**
 * Le contrôleur JavaFX pour la gestion du stock (GestockController).
 * Ce contrôleur est associé à une interface utilisateur définie dans un fichier FXML.
 */
package application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestockController {
	@FXML
    private ListView<produit> stockListView;
	
	@FXML
    private VBox notificationBox;

    @FXML
    private ListView<Commande> notificationListView;

    @FXML
    private Button logoutButton ;
    
    @FXML
    private void logout() {
    	 Stage stage = (Stage) logoutButton.getScene().getWindow();
         stage.close();
         
         Main2.loadFXML("Sample.fxml");
    }
    
    @FXML
    /**
     * Initialise le contrôleur après que tous les éléments FXML ont été chargés.
     * Charge les produits depuis la base de données lors de l'initialisation.
     */
    private void initialize() {
        // Chargez les produits depuis la base de données
    	stockListView.getItems().clear();
        chargerProduitsDepuisDB();
        
        
    }
    
    private boolean notificationsVisible = false;

    @FXML
    private void afficherNotifications() {
        // Ajoutez la logique pour afficher les commandes non validées
        // Vous pouvez remplir la notificationListView avec les détails des commandes non validées
    	notificationsVisible = !notificationsVisible; // Inversez la visibilité à chaque clic

        notificationBox.setVisible(notificationsVisible);

        if (notificationsVisible) {
            // Si les notifications sont maintenant visibles, chargez les notifications
            chargerNotifications();
        }
    	
    	
    }
    
    private void chargerNotifications() {
        // Code pour charger les notifications depuis la base de données
        // Vous pouvez utiliser votre méthode chargerCommandesNonValideesDepuisDB
        

        // Affichez les commandes non validées dans la ListView des notifications
        notificationListView.getItems().clear();
        chargerCommandesNonValideesDepuisDB();
    }
    
    @FXML
    private void chargerCommandesNonValideesDepuisDB() {
        // Ajoutez la logique pour récupérer les commandes non validées depuis la base de données
        List<Commande> commandesNonValidees = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM commande_client WHERE valide = 0");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
            	 Commande commande = new Commande();
            	 commande.setId(resultSet.getInt("id_commande"));
                 commande.setQuantite(resultSet.getInt("quantite"));
                 commande.setValide(resultSet.getInt("valide"));
                 commande.setIdClient(resultSet.getInt("id_client"));
                 commande.setIdProduit(resultSet.getInt("id_produit"));

                 commandesNonValidees.add(commande);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions SQL
        }
        notificationListView.getItems().addAll(commandesNonValidees);
    }

    @FXML
    private void validerCommande() {
        // Récupérer la commande sélectionnée
        Commande selectedCommande = notificationListView.getSelectionModel().getSelectedItem();

        if (selectedCommande != null) {
        	//verifier que le stock est suffisant: 
        	try (Connection connection = DatabaseConnection.getConnection();
                    PreparedStatement statement = connection.prepareStatement("Select quantite from produitfini WHERE id_produit = ?")) {

                   statement.setInt(1, selectedCommande.getIdProduit());
                   ResultSet resultSet = statement.executeQuery();
                   
                   // Appelle next() pour déplacer le curseur sur la première ligne du résultat
                   if (resultSet.next()) {
                       float quantite_n = resultSet.getFloat("quantite");

                       if (quantite_n >selectedCommande.getQuantite() ) {
        	
                    	   double prix=selectedCommande.getQuantite()*selectedCommande.getPrixProduit();
                       	int id_client=selectedCommande.getIdClient();
                       	
                       	//Ajout d'une vente dans la bdd
                       	try {
                       		Connection connection2=DatabaseConnection.getConnection();
                       		String querry="INSERT INTO `vente`(`prix`, `id_client`) VALUES (?, ?)";
                       		PreparedStatement statement2=connection2.prepareStatement(querry);
                       		statement2.setDouble(1, prix);
                       		statement2.setInt(2, id_client);
                       		statement2.executeUpdate();
                       	}catch(SQLException e) {
                       		e.printStackTrace();
                       	}
                    	   
                    	   
            // Déduire la quantité de produits commandée du stock
            mettreAJourStock(selectedCommande.getIdProduit(), selectedCommande.getQuantite());

            // Mettre à jour la base de données pour marquer la commande comme validée
            marquerCommandeCommeValidee(selectedCommande.getIdCommande());

            // Actualiser la liste des notifications
            afficherNotifications();
            notificationBox.setVisible(false);
            afficherNotifications();
            initialize();
                       }else {
                    	   showAlert("Error", "quantité insuffisante ", Alert.AlertType.ERROR);
                       }
        }else {
        	showAlert("Error", "probléme produit introuvable ", Alert.AlertType.ERROR);
        }
    }catch (SQLException e) {
        e.printStackTrace();
    }
            
        }
    }

    private void mettreAJourStock(int idProduit, int quantiteCommandee) {
        // Code pour déduire la quantité de produits commandée du stock
        // Assurez-vous d'ajuster cela en fonction de votre schéma de base de données
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE produitfini SET quantite = quantite - ? WHERE id_produit = ?")) {

            statement.setInt(1, quantiteCommandee);
            statement.setInt(2, idProduit);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions SQL
        }
    }

    private void marquerCommandeCommeValidee(int idCommande) {
        // Code pour mettre à jour la base de données et marquer la commande comme validée
        // Assurez-vous d'ajuster cela en fonction de votre schéma de base de données
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE commande_client SET valide = 1 WHERE id_commande = ?")) {

            statement.setInt(1, idCommande);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions SQL
        }
    }

    @FXML
    private void refuserCommande() {
        // Ajoutez la logique pour refuser la commande sélectionnée
        Commande selectedCommande = notificationListView.getSelectionModel().getSelectedItem();
        if (selectedCommande != null) {
        	 updateCommandeStatus(selectedCommande.getIdCommande(), 2);
        	 afficherNotifications();
        	 afficherNotifications();
        }
    }
    
    private void updateCommandeStatus(int commandeId, int newStatus) {
        // Assurez-vous d'adapter cette requête en fonction de votre base de données
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE commande_client SET valide = ? WHERE id_commande = ?")) {

            statement.setInt(1, newStatus);
            statement.setInt(2, commandeId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de mise à jour dans la base de données
        }
    }

    
    
    public void refresh() {
    	initialize();
    }
   
    public void ajouterProduit() {
    	 Main2.loadFXML("AjoutProduitForm.fxml");
    	 
    }
    public void supprimerProduit() {
    	Main2.loadFXML("SuppProduitForm.fxml");
    }
    private void chargerProduitsDepuisDB() {
        // Code pour récupérer les produits depuis la base de données
        List<produit> produits = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM produitfini");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                produit produit = new produit();
                produit.setId(resultSet.getInt("id_produit"));
                produit.setType(resultSet.getString("type"));
                produit.setQuantite(resultSet.getFloat("quantite"));
                // Récupérez d'autres attributs au besoin

                produits.add(produit);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions SQL
        }

        // Ajoutez les produits à la ListView
        stockListView.getItems().addAll(produits);
    }
    
    
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    
}