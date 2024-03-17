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
 * Contrôleur pour la vue associée à la création d'une commande.
 * Gère la sélection du produit, de la quantité, du client et la création de la commande dans la base de données.
 */
public class CreationCommandeController {
	//Attributs
	@FXML
	//Attribut représentant la liste déroulante des produits en stock
    protected ChoiceBox<String> produitChoiceBox;
	
	@FXML
	//Attribut représentant la quantité à vendre du produit
	protected TextField quantiteField;
	
	@FXML
	//Attribut représentant la liste déroulante des clients enregistrés
	protected ChoiceBox<String> clientChoiceBox;
	
	//Méthodes
	@FXML
	//Méthode spéciale de JavaFX appellé lorsque le fichier est executé
	/**
     * Méthode spéciale de JavaFX appelée lorsque le fichier est exécuté.
     * Initialise la vue en chargeant les types de produits et les clients depuis la base de données.
     */
    private void initialize() {
    	List<String> typesProduits = chargerTypesProduitsDepuisDB();
        produitChoiceBox.getItems().addAll(typesProduits);
        List<String> clients = chargerClientsDepuisDB();
        clientChoiceBox.getItems().addAll(clients);
    }
	
	//Recupere la liste des produits depuis la base de données
	/**
	 * Récupère la liste des types de produits depuis la base de données.
	 *
	 * @return Une liste contenant les types de produits.
	 */
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
    
    //Recupere la liste des clients depuis la bdd
    /**
     * Récupère la liste des clients depuis la base de données.
     *
     * @return Une liste contenant les noms et prénoms des clients.
     */
    private List<String> chargerClientsDepuisDB(){
    	List<String> clients=new ArrayList<>();
    	
    	try (Connection connection = DatabaseConnection.getConnection();
   	         PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT nom, prenom FROM client");
   	         ResultSet resultSet = statement.executeQuery()) {

   	        while (resultSet.next()) {
   	            String client = resultSet.getString("nom");
   	            client+= " " + resultSet.getString("prenom");
   	            clients.add(client);
   	        }

   	    } catch (SQLException e) {
   	        e.printStackTrace(); // Gérer les exceptions SQL
   	    }
    	return clients;
    }
    
    //Recupere l'id d'un client à partir de son nom et prenom
    /**
     * Récupère l'identifiant d'un client à partir de son nom et prénom.
     *
     * @param nom    Le nom du client.
     * @param prenom Le prénom du client.
     * @return L'identifiant du client, -1 s'il n'est pas trouvé.
     */
    public int idClient(String nom, String prenom) {
    	int id=-1;
    	try (Connection connection = DatabaseConnection.getConnection();
      	         PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT id_client FROM client WHERE nom = ? AND prenom = ?");
      	         ) {
    		statement.setString(1, nom);
    		statement.setString(2, prenom);
    		ResultSet resultSet = statement.executeQuery();
      	        while (resultSet.next()) {
      	            id=resultSet.getInt("id_client");
      	        }

      	    } catch (SQLException e) {
      	        e.printStackTrace(); // Gérer les exceptions SQL
      	    }
    	return id;
    }
    
    //Recupere l'id d'un produit à partir de son type
    public int idProduit(String type) {
    	int id=-1;
    	try (Connection connection = DatabaseConnection.getConnection();
     	         PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT id_produit FROM produitfini WHERE type = ?");
     	         ) {
   		statement.setString(1, type);
   		ResultSet resultSet = statement.executeQuery();
     	        while (resultSet.next()) {
     	            id=resultSet.getInt("id_produit");
     	        }

     	    } catch (SQLException e) {
     	        e.printStackTrace(); // Gérer les exceptions SQL
     	    }
    	return id;
    }
    
    @FXML
    /**
     * Méthode appelée lors de l'action de création d'une commande.
     * Récupère le produit, la quantité et le client sélectionnés, puis crée la commande dans la base de données.
     */
    public void creerCommande() {
    	String produit = produitChoiceBox.getValue();
        String quantiteS = quantiteField.getText();
        String client = clientChoiceBox.getValue();
        
        if (produit == null || quantiteS.isEmpty() || client == null) {
        	showAlert("Error", "aucun produit selectionnée , ou quantité null", Alert.AlertType.ERROR);
            return;
        }
        
        try {
            // Convertit la quantité en un nombre entier
            int quantite = Integer.parseInt(quantiteS);
            String[] nom=client.split(" ");
            int id_client=idClient(nom[0], nom[1]);
            int id_produit=idProduit(produit);
            
            String querry="INSERT INTO `commande_client`(`quantite`, `id_client`, `id_produit`) VALUES (?, ?, ?)";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(querry)) {

                statement.setInt(1, quantite);
                statement.setInt(2, id_client);
                statement.setInt(3, id_produit);
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace(); // Gérer les exceptions SQL
            }
                
            
            
            Stage stage = (Stage) quantiteField.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            // Gére l'exception si la conversion en nombre échoue
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
