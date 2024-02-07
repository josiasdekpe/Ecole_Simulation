package com.ecole_sim.model;

import java.util.List;

public class Matiere {

  private int id;
  private String nom;
  private Directeur directeur;
  
  public Matiere(String nom, Directeur directeur) {
    this.nom = nom;
    this.directeur = directeur;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Directeur getDirecteur() {
    return directeur;
  }

  public void setDirecteur(Directeur directeur) {
    this.directeur = directeur;
  }
//Obtenir les enseignants de la matière
	public List<Enseignant> getEnseignants() {
	 return this.directeur.getEnseignantsPourMatiere(this); 
	}
	
	//Vérifier si un enseignant enseigne cette matière
	public boolean isEnseignedBy(Enseignant enseignant) {
	 for (Enseignant e : getEnseignants()) {
	   if (e.equals(enseignant)) {
	     return true;
	   }
	 }
	 return false;
	}
	  
  @Override
  public String toString() {
    return "Matiere [id=" + id + ", nom=" + nom + ", directeur=" + directeur + "]";
  }

}