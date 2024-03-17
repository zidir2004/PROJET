/**
 * La classe Main2 est la classe principale de l'application JavaFX.
 * Elle étend la classe Application et définit la configuration initiale et le point d'entrée de l'application.
 */
package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main2 extends Application {
	
	/**
     * La méthode start est appelée lors du démarrage de l'application.
     * Elle configure la fenêtre principale et charge le fichier FXML initial (Sample.fxml).
     *
     * @param primaryStage La fenêtre principale de l'application.
     * @throws Exception En cas d'erreur lors du chargement du fichier FXML.
     */
    public void start(Stage primaryStage) throws Exception {
        // Chargement du fichier sample.fxml pour la première fenêtre
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));

        // Configuration de la fenêtre principale (primaryStage)
        primaryStage.setTitle("Page de connexion"); // Définition du titre de la fenêtre
        primaryStage.setScene(new Scene(root,1000, 800)); // Création d'une scène avec la racine (root) et une taille spécifique
        primaryStage.show(); // Affichage de la fenêtre
    }

    /**
     * Méthode statique pour charger un fichier FXML spécifique en fonction du rôle de l'utilisateur.
     *
     * @param fxmlFile Le chemin vers le fichier FXML à charger.
     */
    public static void loadFXML(String fxmlFile) {
        try {
            // Chargement du fichier FXML
            FXMLLoader loader = new FXMLLoader(Main2.class.getResource(fxmlFile));
            Parent root = loader.load(); // Chargement de la vue associée au fichier FXML
            Stage stage = new Stage(); // Création d'une nouvelle fenêtre (Stage)
            stage.setScene(new Scene(root)); // Configuration de la scène avec la vue chargée
            stage.show(); // Affichage de la fenêtre
        } catch (Exception e) {
            e.printStackTrace(); // Affichage des détails de l'erreur s'il y a un problème lors du chargement du fichier FXML
        }
    }

    /**
     * Point d'entrée principal de l'application.
     *
     * @param args Les arguments de la ligne de commande (non utilisés dans cet exemple).
     */
    public static void main(String[] args) {
        launch(args); // Appel de la méthode launch pour démarrer l'application JavaFX
    }
}