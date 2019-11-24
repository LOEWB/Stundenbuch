package model;




public class Article {
	
	private Long id_article;
	private order id_commande;
	private product id_product;
	private double prix;
	private Integer quantity;
	
	

	
	public order getId_commande() {
		return id_commande;
	}
	public void setId_commande(order id_commande) {
		this.id_commande = id_commande;
	}
	
	public product getId_product() {
		return id_product;
	}
	public void setId_product(product id_product) {
		this.id_product = id_product;
	}

	
	public Long getId_article() {
		return id_article;
	}
	public void setId_article(Long id_article) {
		this.id_article = id_article;
	}
	
	
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}



	

}

