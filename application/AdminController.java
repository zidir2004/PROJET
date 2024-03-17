package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * Contrôleur pour la fonctionnalité administrateur, gestion des utilisateurs et des rôles.
 * Gère des actions telles que l'ajout d'utilisateurs, la récupération de données depuis la base de données,
 * 
 * @author louiz
 */
public class AdminController {
	

	
    @FXML
    private Button logoutButton ;
	
    @FXML
    private ListView<String> userListView;
    
    
    
    private ObservableList<String> users = FXCollections.observableArrayList();
    
    @FXML
    private ChoiceBox<String> rolesComboBox;

    @FXML
    private Button addUserButton;
    @FXML
	protected TextField nom;
    @FXML
	protected TextField prenom;
    @FXML
	protected TextField email;
    @FXML
	protected TextField password;

   

    @FXML
    /**
     * Initialise le contrôleur, récupère les utilisateurs et les rôles depuis la base de données.
     */
    private void initialize() {
        // Initialise la ListView avec la liste des utilisateurs depuis la base de données
        fetchUsersFromDatabase();

        // Initialise la ChoiceBox avec la liste des rôles depuis la base de données
        fetchRolesFromDatabase();
    }
    /**
     * Récupère les rôles depuis la base de données et remplit la ChoiceBox des rôles.
     */
    private void fetchRolesFromDatabase() {
       

        ObservableList<String> roles = FXCollections.observableArrayList();

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT role FROM utilisateur");
                ResultSet resultSet = statement.executeQuery()) {

            // Ajoute les rôles récupérés depuis la base de données à la liste
            while (resultSet.next()) {
                String role = resultSet.getString("role");
                roles.add(role);
            }

            // Met à jour la ChoiceBox avec la nouvelle liste de rôles
            rolesComboBox.setItems(roles);

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de récupération depuis la base de données
        }
    }

    @FXML
    /**
     * Gère l'action d'ajouter un nouvel utilisateur.
     * Récupère les valeurs d'entrée, valide, insère dans la base de données, et met à jour la liste des utilisateurs.
     * 
     * @param event ActionEvent déclenché par le bouton d'ajout d'utilisateur.
     */
    private void addUser(ActionEvent event) {
        // Récupère les valeurs des champs
        String firstName = prenom.getText();
        String lastName = nom.getText();
        String emailValue = email.getText();
        String passwordValue = password.getText();
        String selectedRole = rolesComboBox.getValue();

        // Vérifie que tous les champs sont remplis
        if (firstName.isEmpty() || lastName.isEmpty() || emailValue.isEmpty() || passwordValue.isEmpty()
                || selectedRole == null) {
            // Gérer le cas où un champ est vide
            return;
        }

        // Insère le nouvel utilisateur dans la base de données
        insertUserIntoDatabase(firstName, lastName, emailValue, passwordValue, selectedRole);

        // Met à jour la liste des utilisateurs affichée
        fetchUsersFromDatabase();
    }

    private void insertUserIntoDatabase(String firstName, String lastName, String email, String password, String role) {
        // Assurez-vous d'adapter cette requête en fonction de votre base de données
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO utilisateur (nom, prenom, email, password, role) VALUES (?, ?, ?, ?, ?)")) {

            statement.setString(1, lastName);
            statement.setString(2, firstName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, role);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs d'insertion dans la base de données
        }
    }
    @FXML
    /**
     * Charge la page "gestock.fxml" lorsqu'une action est déclenchée.
     *
     * @param event L'événement associé à l'action.
     */
    private void gestiondestock(ActionEvent event) {
        Main2.loadFXML("gestock.fxml");
    }

    @FXML
    /**
     * Charge la page "commercial.fxml" lorsqu'une action est déclenchée.
     *
     * @param event L'événement associé à l'action.
     */
    private void commercial(ActionEvent event) {
        Main2.loadFXML("commercial.fxml");
    }

    @FXML
    /**
     * Charge la page "production.fxml" lorsqu'une action est déclenchée.
     *
     * @param event L'événement associé à l'action.
     */
    private void production(ActionEvent event) {
        Main2.loadFXML("production.fxml");
    }

    @FXML
    /**
     * Charge la page "comptable.fxml" lorsqu'une action est déclenchée.
     *
     * @param event L'événement associé à l'action.
     */
    private void compta(ActionEvent event) {
        Main2.loadFXML("comptable.fxml");
    }

    @FXML
    /**
     * Déconnecte l'utilisateur en fermant la fenêtre actuelle et charge la page "Sample.fxml".
     *
     * @param event L'événement associé à l'action.
     */
    private void logout(ActionEvent event) {
    	 Stage stage = (Stage) logoutButton.getScene().getWindow();
         stage.close();
         
         Main2.loadFXML("Sample.fxml");
    }

    /**
     * Récupère les utilisateurs depuis la base de données et met à jour la ListView des utilisateurs.
     */
    private void fetchUsersFromDatabase() {
        // Assurez-vous d'adapter ces informations en fonction de votre base de données
        
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT id_utilisateur,nom, role FROM utilisateur");
                ResultSet resultSet = statement.executeQuery()) {

            // Nettoie la liste des utilisateurs avant d'ajouter les nouveaux
            users.clear();

            // Ajoute les utilisateurs récupérés depuis la base de données à la liste
            while (resultSet.next()) {
            	int id = resultSet.getInt("id_utilisateur");
                String username = resultSet.getString("nom");
                String role = resultSet.getString("role");
                users.add(id +" - " + username + " - " + role);
            }

            // Met à jour la ListView avec la nouvelle liste d'utilisateurs
            userListView.setItems(users);

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de récupération depuis la base de données
        }
    }
    /**
     * Vérifie si l'utilisateur avec l'identifiant spécifié n'est pas un administrateur.
     *
     * @param userId L'identifiant de l'utilisateur.
     * @return true si l'utilisateur n'est pas un administrateur, false sinon.
     */
    private boolean isNotAdmin(int userId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT role FROM utilisateur WHERE id_utilisateur = ?")) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String role = resultSet.getString("role");
                // Vérifie si le rôle n'est pas "ADMIN"
                return !("admin".equals(role));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // En cas d'erreur ou si l'utilisateur n'est pas trouvé, retourner false par défaut
        return false;
    }
    
    
    
    @FXML
    /**
     * Supprime l'utilisateur sélectionné de la liste.
     * Affiche une alerte en cas de tentative de suppression d'un administrateur.
     */
    private void supprimer() {
        // Supprime l'utilisateur sélectionné de la liste
        String selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            int userId = Integer.parseInt(selectedUser.split(" - ")[0]); // Utilisez l'ID d'utilisateur pour la suppression
            if (isNotAdmin(userId)) {
                deleteUserFromDatabase(userId);
                initialize();
            } else {
                // Gérer le cas où l'utilisateur est un administrateur
            	 showAlert("Error", "L'utilisateur est admin impossible de le supprimer", Alert.AlertType.ERROR);
            }
        }
    }
    /**
     * Supprime un utilisateur de la base de données en utilisant son identifiant.
     *
     * @param userId L'identifiant de l'utilisateur à supprimer.
     */
    private void deleteUserFromDatabase(int userId) {
        // Assurez-vous d'adapter cette requête en fonction de votre base de données
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM utilisateur WHERE id_utilisateur = ?")) {

            statement.setInt(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de suppression depuis la base de données
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
