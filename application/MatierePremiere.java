package application;

public class MatierePremiere {
private int Id;
private String Type;
private float Quantite;
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}
public float getQuantite() {
	return Quantite;
}
public void setQuantite(float quantite) {
	Quantite = quantite;
}
public String toString() {
	return"Type : "+getType()+" - quantité manquante : "+getQuantite();
}
}