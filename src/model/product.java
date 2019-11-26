package model;

public class product {
	
	private Long id_product;
	private String nom;
	
	private supply supplier;
	private Integer price;
	private Boolean disp;
	
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	
	
	public supply getSupplier() {
		return supplier;
	}
	public void setSupplier(supply supplier) {
		this.supplier = supplier;
	}
	
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Boolean getDisp() {
		return disp;
	}
	public void setDisp(Boolean disp) {
		this.disp = disp;
	}
	public Long getId_product() {
		return id_product;
	}
	public void setId_product(Long id_product) {
		this.id_product = id_product;
	}
	



	
	
	
	
	
}
