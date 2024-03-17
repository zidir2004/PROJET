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

public class ProductionController {
	@FXML
    private ListView<produit> stockListView;
	
	@FXML
    private VBox notificationBox;

    @FXML
    private ListView<produit> notificationListView;
    
    @FXML
    private Button logoutButton ;

    @FXML
    private void initialize() {
        // Chargez les produits depuis la base de données
    	stockListView.getItems().clear();
        chargerProduitsDepuisDB();
        
        
    }
    
    @FXML
    private void logout() {
    	 Stage stage = (Stage) logoutButton.getScene().getWindow();
         stage.close();
         
         Main2.loadFXML("Sample.fxml");
    }
    
    
    private boolean notificationsVisible = false;

    @FXML
    private void afficherNotifications() {
       
        
    	notificationsVisible = !notificationsVisible; // Inversez la visibilité à chaque clic

        notificationBox.setVisible(notificationsVisible);

        if (notificationsVisible) {
            // Si les notifications sont maintenant visibles, chargez les notifications
            chargerNotifications();
        }
    	
    	
    }
    
    private void chargerNotifications() {
        

        // Affichez les commandes non validées dans la ListView des notifications
        notificationListView.getItems().clear();
        chargerProduitsAReapprovisionnerDepuisDB();
    }
    
    @FXML
    private void chargerProduitsAReapprovisionnerDepuisDB() {
        List<produit> produitsAReapprovisionner = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM produitfini WHERE quantite < seuil_reapprovisionnement");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                produit produit = new produit();
                produit.setId(resultSet.getInt("id_produit"));
                produit.setType(resultSet.getString("type"));
                float quantite_add = (resultSet.getFloat("seuil_reapprovisionnement")-resultSet.getFloat("quantite"))+200;
                produit.setQuantite(quantite_add);
                
                produitsAReapprovisionner.add(produit);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions SQL
        }

        notificationListView.getItems().addAll( produitsAReapprovisionner);
    }

    @FXML
    private void validerCommande() {
        // Récupérer la commande sélectionnée
        produit selectedProduit = notificationListView.getSelectionModel().getSelectedItem();
        if (selectedProduit != null) {
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("Select quantite from matierepremiere WHERE type = ?")) {

                statement.setString(1, selectedProduit.getType());
                ResultSet resultSet = statement.executeQuery();
                
                // Appelle next() pour déplacer le curseur sur la première ligne du résultat
                if (resultSet.next()) {
                    float quantite_n = resultSet.getFloat("quantite");

                    if (quantite_n > selectedProduit.getQuantite()) {
                        // Déduire la quantité de matière première pour la produire
                        mettreAJourStock(selectedProduit.getType(), selectedProduit.getQuantite());

                        // Mettre à jour la base de données pour ajouter le produit fini au stock
                        AjoutProduitFini(selectedProduit.getType(), selectedProduit.getQuantite());

                        // Actualiser la liste des notifications
                        afficherNotifications();
                        notificationBox.setVisible(false);
                        afficherNotifications();
                        initialize();
                    } else {
                        showAlert("Error", "quantité insuffisante ", Alert.AlertType.ERROR);
                    }
                } else {
                    // Gérer le cas où aucune ligne n'est retournée
                    showAlert("Error", "Aucune matière première trouvée pour le type sélectionné", Alert.AlertType.ERROR);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    

    private void mettreAJourStock(String type, float quantiteCommandee) {
        // Code pour déduire la quantité de produits commandée du stock
       
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE matierepremiere SET quantite = quantite - ? WHERE type = ?")) {

            statement.setFloat(1, quantiteCommandee);
            statement.setString(2,type );
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
    
    private void AjoutProduitFini(String type, float quantiteCommandee) {
    	 try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE produitfini SET quantite = quantite + ? WHERE type = ?")) {

                statement.setFloat(1, quantiteCommandee);
                statement.setString(2,type );
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace(); 
            }
    }

    

    
    public void refresh() {
    	initialize();
    }
   
    public void ajouterProduit() {
    	 Main2.loadFXML("ajoutMatierePremiere.fxml");
    	 
    }
  
    private void chargerProduitsDepuisDB() {
        // Code pour récupérer les produits depuis la base de données
        // Assurez-vous d'ajuster cela en fonction de votre schéma de base de données et de la connexion
        List<produit> produits = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM matierepremiere");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                produit produit = new produit();
                produit.setId(resultSet.getInt("id_matiere"));
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

