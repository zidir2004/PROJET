

package application;
/**
 * La classe CommandeClient représente une commande passée par un client.
 * Elle contient des informations telles que l'identifiant de la commande, la description,
 * l'état de validation et l'identifiant du client associé.
 */
public class CommandeClient {
	//Attributs
	protected int id;
	protected String description;
	protected boolean valide;
	protected int id_client;
	
	/**
     * Constructeur de la classe CommandeClient.
     *
     * @param i  Identifiant de la commande
     * @param d  Description de la commande
     * @param v  État de validation de la commande
     * @param ic Identifiant du client associé
     */
	public CommandeClient(int i, String d, boolean v, int ic) {
		id=i;
		description=d;
		valide=v;
		id_client=ic;
	}
	
	/**
     * Retourne une représentation textuelle de la commande.
     * Cette méthode est temporaire et doit être revue pour une implémentation appropriée.
     *
     * @return Une représentation textuelle de l'identifiant de la commande.
     */
	public String toString() { //méthode tempoiraire, à revoir
		return String.valueOf(this.id);
	}
}