package application;

import java.sql.*;

public class stock {
	  
	        private Connection connection;

	        public stock(Connection connection) {
	            this.connection = connection;
	        }

	        // Ajoute un nouveau produit à la base de données
	        public void ajouterProduit(produit produit) {
	        	DatabaseConnection.getConnection();
	            String query = "INSERT INTO produitfini (type, quantite) VALUES (?, ?)";

	            try (
	            	PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setString(1, produit.getType());
	                preparedStatement.setFloat(2, produit.getQuantite());
	                preparedStatement.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        
	          // recuperer quantite
	        public float recupQuantite(int id) {
	            float quantite = 0; // Initialisez la variable de quantité

	            try {
	                Connection connection = DatabaseConnection.getConnection();
	                String query = "SELECT quantite FROM produitfini WHERE id_produit = ?";

	                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                    preparedStatement.setInt(1, id);
	                    ResultSet resultSet = preparedStatement.executeQuery();

	                    if (resultSet.next()) {
	                        quantite = resultSet.getFloat("quantite");
	                    }
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }

	            return quantite;
	        }

	        // Met à jour la quantité d'un produit dans la base de données
	        public void mettreAJourQuantite(int produitId, int venduQuantite) {
	            String query = "UPDATE produitfini SET quantite = ? WHERE id_produit = ?";

	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            	float newquantitie = recupQuantite(produitId)-venduQuantite;
	                preparedStatement.setFloat(1, newquantitie);
	                preparedStatement.setInt(2, produitId);
	                preparedStatement.executeUpdate();
	                supprimerProduit(produitId);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        // Supprime un produit de la base de données en utilisant son identifiant
	        public void supprimerProduit(int produitId) {
	            String query = "DELETE FROM produitfini WHERE id_produit = ? and quantite=0";

	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setInt(1, produitId);
	                preparedStatement.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    