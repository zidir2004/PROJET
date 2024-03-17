package application;
import java.sql.*;
import java.util.HashMap;

public class IDandPasswords {
	//Attributs
	protected HashMap<String,String> logininfo;
	private final String jdbc_url;
	private final String jdbc_user;
	private final String jdbc_pwd;
	
	//Constructeur
	public IDandPasswords() {
		jdbc_url="jdbc:mysql://localhost:3306/ERP";
		jdbc_user="root";
		jdbc_pwd="";
		logininfo=new HashMap<String,String>();
	}
	
	//Getters
	public HashMap<String, String> getLogininfo() {
		return logininfo;
	}

	/**
	 * Récupération des utilisateurs
	 */
	public void recupUsers() {
		try {
			Connection connexion=DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pwd);
			String querry="SELECT email, password FROM utilisateur"; //requête SQL
			PreparedStatement preparedstatement=connexion.prepareStatement(querry);
			ResultSet results=preparedstatement.executeQuery();
			
			/**
			 * Parcourt le resultat de la requete SQL
			 */
			while(results.next()) {
				String email=results.getString("email");
				String pwd=results.getString("password");
				this.logininfo.put(email, pwd);
			}
		}catch(SQLException ex) {
			ex.printStackTrace(System.err);
		}
	}

}
