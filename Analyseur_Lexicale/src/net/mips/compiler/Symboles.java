package net.mips.compiler;

public class Symboles {
	private Tokens token;
	private String nom;
	
	private int adresse;
	
	public int getAdresse() {
		return adresse;
	}
	public void setAdresse(int adresse) {
		this.adresse = adresse;
	}
	public Tokens getToken() {
		return token;
	}
	public void setToken(Tokens token) {
		this.token = token;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Symboles(Tokens token, String nom) {
		this.token = token;
		this.nom = nom;
	}
	public Symboles() {
		this.nom="";
	}
	
	
	
}
