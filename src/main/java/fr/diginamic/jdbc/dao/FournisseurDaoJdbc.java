package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;


public class FournisseurDaoJdbc implements FournisseurDao {
	
	public static void main(String[] a ) {
		FournisseurDaoJdbc ofo = new FournisseurDaoJdbc();
		List<Fournisseur> listeFour = ofo.extraire();
		System.out.println(listeFour.size());
		for (Fournisseur fo : listeFour) {
			System.out.println(fo);
		}
	}

	public List<Fournisseur> extraire() {
		Connection connection = null;
		List<Fournisseur> listeFour = new ArrayList<Fournisseur>();
		try {
			connection = this.getConnection();//Jeton d'accés et de permission à la base
			/**
			 * Récuperer un statement = accés aux données à partir de l'objet de la connexion
			 * Récuperer le résultat de la requête
			 * Ajouter ligne par ligne dans la liste des fournisseurs
			 */
			//Récupérer un buffer d'échange ac la BDD
			//Un tuyau de communication pour échanger avec la BDD pour faire des requêtes
			Statement monCanal = connection.createStatement();
			ResultSet monResultat = monCanal.executeQuery("SELECT * FROM fournisseur;");
			
			while(monResultat.next()) {
				listeFour.add(new Fournisseur(monResultat.getInt("id_Fournisseur"),monResultat.getString("nom")));
			}
			
			monResultat.close();
			monCanal.close();
		}
		catch(Exception e) {
			System.err.println("Erreur d'execution : " + e.getMessage());
			
		}
		finally {
			try {
				if(connection != null) connection.close();
			}
			catch(SQLException e) {
				System.err.println("Problème de connexion close : " + e.getMessage());
			}
			
		}
		return listeFour;
	}

	/**fait un insert dans la base de compta sur la table fournisseur
	 * @throws Exception */
	public void insert(Fournisseur fournisseur)  {

		try (Connection connection = getConnection();
			 Statement statement = connection.createStatement()) {

			statement.executeUpdate("INSERT INTO fournisseur (id, nom) VALUES ("+fournisseur.getId()+", '"+fournisseur.getNom()+"') ");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * fait un update dans la table fournisseur en changeant le nom ancienNom par nouveauNom
	 * @return 
	 * @throws Exception 
	 */
	public int update(String ancienNom, String nouveauNom) {

		try (Connection connection = getConnection();
			 Statement statement = connection.createStatement()) {

			 statement.executeUpdate("UPDATE fournisseur SET nom='"+nouveauNom+"' WHERE nom='"+ancienNom+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 *supprime le fournisseur specifie dans la table fournisseur
	 * @throws Exception 
	 */
	public boolean delete(Fournisseur fournisseur) throws Exception {

		try (Connection connection = getConnection();
			 Statement statement = connection.createStatement()) {

			return statement.executeUpdate("DELETE FROM fournisseur where id="+fournisseur.getId()) == 1;
		} catch (SQLException e) {
			throw new Exception("Erreur de communication avec la base de données", e);
		}
	}

	public Connection getConnection() {
		// recupere le fichier properties
		ResourceBundle db = ResourceBundle.getBundle("database");

		try {
			// enregistre le pilote
			Class.forName(db.getString("db.driver"));

			return DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
					db.getString("db.pass"));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public boolean delate(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		return false;
	}

}