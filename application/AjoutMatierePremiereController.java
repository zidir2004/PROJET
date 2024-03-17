package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Contrôleur pour la fenêtre d'ajout de matière première.
 * Gère l'ajout de quantité à une matière première existante dans la base de données.
 */
public class AjoutMatierePremiereController {
    @FXML
    private ChoiceBox<String> nomMatiereChoiceBox;

    @FXML
    private TextField quantiteField;

   

    @FXML
    private void initialize() {
        // Remplis la ChoiceBox avec les noms de matières premières existantes depuis la base de données
        List<String> nomsMatieres = chargerNomsMatieresDepuisDB();
        nomMatiereChoiceBox.getItems().addAll(nomsMatieres);
    }
    /**
     * Charge les noms des matières premières depuis la base de données.
     *
     * @return Une liste contenant les noms des matières premières.
     */
    public List<String> chargerNomsMatieresDepuisDB() {
        List<String> nomsMatieres = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT type FROM matierepremiere");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String nomMatiere = resultSet.getString("type");
                nomsMatieres.add(nomMatiere);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions SQL
        }

        return nomsMatieres;
    }


    @FXML
    /**
     * Méthode appelée lors de l'action d'ajout de quantité à une matière première existante.
     * Récupère les données des champs de saisie, vérifie la validité, puis met à jour la quantité dans la base de données.
     */
    private void ajouterMatierePremiere() {
        // Récupère les valeurs des champs
    	String nomMatiereSelectionne = nomMatiereChoiceBox.getValue();
        String quantite = quantiteField.getText();

        // Vérifie que les champs ne sont pas vides
        if (nomMatiereSelectionne.isEmpty() || quantite.isEmpty() ) {
            // Affiche une alerte pour informer l'utilisateur que tous les champs doivent être remplis
            showAlert("Erreur", "Veuillez remplir tous les champs.", Alert.AlertType.ERROR);
            return;
        }

        try {
            // Convertit les valeurs en types appropriés
            float quantiteValue = Float.parseFloat(quantite);

            // Ajoute la logique pour mettre à jour la base de données avec la nouvelle matière première
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE produitfini SET quantite = quantite + ? where type = ? ")) {

            	statement.setFloat(1, quantiteValue);
                statement.setString(2, nomMatiereSelectionne);
                
               

                statement.executeUpdate();
                try (Connection connection2 = DatabaseConnection.getConnection();
                        PreparedStatement statement2 = connection2.prepareStatement("UPDATE matierepremiere SET quantite = quantite - ? where type = ? ")) {

                   	statement2.setFloat(1, quantiteValue);
                       statement2.setString(2, nomMatiereSelectionne);
                       
                      

                       statement2.executeUpdate();} catch(SQLException e2) {
                    	   e2.printStackTrace();
                       }

                // Affiche une alerte pour informer l'utilisateur que la matière première a été ajoutée
                showAlert("Succès", "La matière première a été ajoutée avec succès.", Alert.AlertType.INFORMATION);
            }

        } catch (NumberFormatException | SQLException e) {
            // Gère les exceptions (conversion en nombre, erreurs SQL, etc.)
            e.printStackTrace();
            showAlert("Erreur", "Une erreur s'est produite. Veuillez réessayer.", Alert.AlertType.ERROR);
        }
        
        Stage stage = (Stage) quantiteField.getScene().getWindow();
        stage.close();
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
