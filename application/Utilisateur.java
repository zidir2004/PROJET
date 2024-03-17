/**
 * La classe Utilisateur représente un utilisateur dans le système.
 * Chaque utilisateur a un identifiant unique, un nom, un prénom, une adresse e-mail, un rôle et un mot de passe.
 */
package application;
import java.sql.*;

public class Utilisateur {
    private int idUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String role;
    private String motDePasse;
    
    
 // getters et les setters
    
    public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	

    
}
