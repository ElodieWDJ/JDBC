package exemple.jdbc.entity;

import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public interface ArticleDaoJdbc {
	


	public class ArticleDaoJdbc implements ArticleDao {
		
		public static void main(String[] a ) {
			ArticleDaoJdbc ofo = new ArticleDaoJdbc();
			List<Article> listeArt = ofo.extraire();
			
			for (Article art : listeArt) {
				System.out.println(art);
			}
		}

		public List<Article> extraire() {
			Connection connection = null;
			List<Article> listeArt = new ArrayList<Article>();
			try {
				connection = this.getConnection();//Jeton d'accés et de permission à la base
				/**
				 * Récuperer un statement = accés aux données à partir de l'objet de la connexion
				 * Récuperer le résultat de la requête
				 * Ajouter ligne par ligne dans la liste des fournisseurs
				 */
				//Récupérer un buffer d'échange ac la BDD
				//Un tuyau de communication pour échanger avec la BDD pour faire des requêtes
				Statement statement = connection.createStatement();
				ResultSet Resultat = statement.executeQuery("SELECT * FROM article;");
				
				while(Result.next()) {
					listeArticles.add(new Article(
							result.getInt("id"),
							result.getString("designation"),
							result.getDouble("prix"),
							result.getInt("id_fou")));
				}
				
				result.close();
				s.close();
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
}
