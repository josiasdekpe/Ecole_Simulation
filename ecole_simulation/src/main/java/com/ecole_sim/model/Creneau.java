package com.ecole_sim.model;

import java.util.Date;

public class Creneau {

    private static int count = 0; // Compteur pour générer des identifiants uniques

    private int id;
    private Date date;
    private String plageHoraire;
    private Matiere matiere;
    private Enseignant enseignant;
    
    
    public Creneau(Date date, String plageHoraire, Matiere matiere) {
        this(date, plageHoraire, matiere, null);
    }
    

    public Creneau(Date date, String plageHoraire, Matiere matiere, Enseignant enseignant) {
        this.id = ++count; // Incrémenter le compteur et affecter l'ID du créneau
        this.date = date;
        this.plageHoraire = plageHoraire;
        this.matiere = matiere;
        this.enseignant = enseignant;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlageHoraire() {
        return plageHoraire;
    }

    public void setPlageHoraire(String plageHoraire) {
        this.plageHoraire = plageHoraire;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    @Override
    public String toString() {
        return "Creneau [id=" + id + ", date=" + date + ", plageHoraire=" + plageHoraire + ", matiere=" + matiere
                + ", enseignant=" + enseignant + "]";
    }

	public void setId(int newId) {
		// TODO Auto-generated method stub
		this.id = newId;
	}
}
