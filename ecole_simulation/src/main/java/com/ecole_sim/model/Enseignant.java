package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Enseignant {

  private int id;
  private String nom;
  private String prenom;
  private List<Matiere> matieres = new ArrayList<>();
  private List<Creneau> creneaux = new ArrayList<>();

  public Enseignant(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
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

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public List<Matiere> getMatieres() {
    return matieres;
  }

  public void addMatiere(Matiere matiere) {
    matieres.add(matiere);
  }

  @Override
  public String toString() {
    return "Enseignant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", matieres=" + matieres + "]";
  }
  public Matiere getMatiereByName(String nomMatiere) {
	  for (Matiere m : matieres) {
	    if (m.getNom().equals(nomMatiere)) {
	      return m; 
	    }
	  }
	  return null;
	}

	public boolean enseigneMatiere(Matiere matiere) {
	  return matieres.contains(matiere);
	}

	public void addCreneau(Creneau creneau) {
	  if (!enseigneMatiere(creneau.getMatiere())) {
	    throw new IllegalArgumentException("L'enseignant n'enseigne pas cette matière");
	  }
	  creneaux.add(creneau);
	}

	public List<Creneau> getCreneaux() {
	  return creneaux;
	}

	public List<Date> getDisponibilites() {
	  List<Date> disponibilites = new ArrayList<>();
	  for (Creneau creneau : creneaux) {
	    disponibilites.add(creneau.getDate()); 
	  }
	  return disponibilites;
	}
}