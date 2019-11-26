package model;



public class customer1 {
	
	private Long id;
	  private String prenom_client; 
	  private String nom_client; 
	  private String ville;
	  private String pays; 
	  

	  public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
	public String getPrenom_client() {
		return prenom_client;
	}
	public void setPrenom_client(String prenom_client) {
		this.prenom_client = prenom_client;
	}
	public String getNom_client() {
		return nom_client;
	}
	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	


}
