package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public interface FournisseurDao {
	List<Fournisseur> extraire();
	void insert(Fournisseur fournisseur) throws Exception;
	int update(String ancienNom, String nouveauNom) throws Exception;;
	boolean delate (Fournisseur fournisseur);

}
