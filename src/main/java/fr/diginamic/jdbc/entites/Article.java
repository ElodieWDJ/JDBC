package fr.diginamic.jdbc.entites;

/**
 * @author antoinethebault
 *Fournisseur : represente un fournisseur avec son id unique et son nom
 */
public class Article {
	/**id : int*/
	private Integer id;
	private String designation;
	private Double prix;
	private int id_fou;


	/**Constructor
	 * @param id
	 * @param nom
	 */
	public Article(int id, String designation,Double prix,int id_fou ) {
		super();
		this.id = id;
		this.designation = designation;
		this.prix = prix;
		this.id_fou = id_fou;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public Double getPrix() {
		return prix;
	}


	public void setPrix(Double prix) {
		this.prix = prix;
	}


	public int getId_fou() {
		return id_fou;
	}


	public void setId_fou(int id_fou) {
		this.id_fou = id_fou;
	}

	
}
