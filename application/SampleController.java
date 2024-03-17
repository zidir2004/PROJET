/**
 * La classe SampleController est le contrôleur associé à l'interface utilisateur définie dans le fichier FXML (Sample.fxml).
 * Elle gère les actions et la logique métier liées à cette interface, notamment le processus de connexion et la navigation
 * vers d'autres pages en fonction du rôle de l'utilisateur.
 */
package application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class SampleController {


	@FXML
    private Button login ;
	
    @FXML
    private TextField usernameField; 

    @FXML
    private PasswordField passwordField;

    /**
     * Méthode appelée lorsque le bouton de connexion est cliqué.
     * Elle récupère les informations saisies, vérifie leur validité, et ouvre le profil approprié en fonction du rôle.
     */
    @FXML
    public void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please fill all fields", Alert.AlertType.ERROR);
            return;
        }

        String role = checkUserRole(username, password);

        if (role != null) {
            openProfile(role);
            Stage stage = (Stage) login.getScene().getWindow();
            stage.close();
        } else {
            showAlert("Error", "Invalid username/password", Alert.AlertType.ERROR);
        }
    }
    /**
     * Vérifie le rôle de l'utilisateur en consultant la base de données.
     *
     * @param username Le nom d'utilisateur saisi.
     * @param password Le mot de passe saisi.
     * @return Le rôle de l'utilisateur ou null s'il n'est pas reconnu.
     */
    public String checkUserRole(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Établir la connexion à la base de données 
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/erp", "root", "");

            // Requête pour vérifier le rôle de l'utilisateur
            String query = "SELECT role FROM utilisateur WHERE email = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * Ouvre le profil approprié en fonction du rôle de l'utilisateur.
     *
     * @param role Le rôle de l'utilisateur.
     */
    private void openProfile(String role) {
        String fxmlFile;
        switch (role) {
            case "admin":
                fxmlFile = "Admin.fxml";
                break;
            case "stock":
                fxmlFile = "gestock.fxml";
                break;
            case "comptable":
                fxmlFile = "comptable.fxml";
                break;
            case "commercial":
                fxmlFile = "commercial.fxml";
                break;
            case "production":
                fxmlFile = "production.fxml";
                break;
            default:
                fxmlFile = "default.fxml"; // Fichier fxml par défaut si le rôle n'est pas reconnu
                break;
        }

       /*Cette ligne appelle la méthode statique loadFXML 
        de la classe Main pour charger un fichier FXML spécifique en fonction du rôle de l'utilisateur*/
        Main2.loadFXML(fxmlFile);
    }
    /**
     * Affiche une boîte de dialogue (Alert) dans l'interface utilisateur.
     *
     * @param title Le titre de la boîte de dialogue.
     * @param content Le contenu de la boîte de dialogue.
     * @param type Le type de la boîte de dialogue (INFORMATION, WARNING, ERROR, etc.).
     */
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}