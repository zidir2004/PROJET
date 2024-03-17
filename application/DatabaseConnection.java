
package application;
import java.sql.*;
import java.util.ArrayList;
/**
 * La classe DatabaseConnection gère la connexion à la base de données et fournit des fonctionnalités
 * pour récupérer des données, telles que les commandes clients et les informations sur les utilisateurs.
 */
public class DatabaseConnection {
	
	//Attributs
    private static final String URL = "jdbc:mysql://localhost:3306/ERP";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    //Instance unique de la classe DatabaseConnection
    private static DatabaseConnection instance; //singleton
    
    //Informations sur l'utilisateur connecté
    private Utilisateur user;
    
    
    public Utilisateur getUser() {
		return user;
	}
    
    
    //Récupération de toutes les commandes
    protected ArrayList<CommandeClient> commandes=new ArrayList<>();
    
    /**
     * Récupère toutes les commandes clients depuis la base de données et les stocke dans l'attribut 'commandes'.
     */
    public void setCommandes() {
    	try {
    		Connection connection= this.getConnection();
    		String querry="SELECT * FROM commande_client";
    		PreparedStatement statement = connection.prepareStatement(querry);
    		ResultSet resultset = statement.executeQuery();
    		
    		while(resultset.next()) {
    			int id=resultset.getInt("id_commande");
    			String description=resultset.getString("description");
    			boolean valide;
    			if(resultset.getInt("valide")==0) {
    				valide=false;
    			}
    			else valide=true;
    			int id_client=resultset.getInt("id_client");
    			
    			CommandeClient commande=new CommandeClient(id, description, valide, id_client);
    			commandes.add(commande);
    		}
    	}catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

	//Constructeur privé pour empêcher l'instanciation directe depuis l'extérieur
    //Nous pouvons créer un objet DatabaseConnection avec la méthode getInstance
    private DatabaseConnection() {
        
    }
    
    /**
     * Obtient l'instance unique de la classe DatabaseConnection.
     *
     * @return L'instance unique de DatabaseConnection.
     */
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    
    /**
     * Établit une connexion à la base de données.
     *
     * @return La connexion à la base de données.
     */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Gérer les exceptions de connexion
            return null;
        }
    }
    
    //Construit l'attribut user depuis l'utilisateur récupéré sur la bdd
    
    /**
     * Vérifie la connexion d'un utilisateur avec les informations fournies.
     *
     * @param email      L'email de l'utilisateur.
     * @param motDePasse Le mot de passe de l'utilisateur.
     */
    public void verifierConnexion(String email, String motDePasse) {
        Connection connection = getConnection();

        if (connection != null) {//Verifie si la connexion n'a pas échoué
            try {
                String query = "SELECT * FROM utilisateur WHERE email = ? AND password = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, email);
                statement.setString(2, motDePasse);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
                    utilisateur.setNom(resultSet.getString("nom"));
                    utilisateur.setPrenom(resultSet.getString("prenom"));
                    utilisateur.setEmail(resultSet.getString("email"));
                    utilisateur.setRole(resultSet.getString("role"));
                    utilisateur.setMotDePasse(resultSet.getString("password"));
                    user=utilisateur;
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
}