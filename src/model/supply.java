package model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class supply {

	
	public static Integer id_supplier;
	
	public static Integer id_article;
	public static Integer quantity;
	public static Integer price_article;

	
	public static String ville;
	
	public static String pays;

	public static Integer getId_supplier() {
		return id_supplier;
	}

	public static void setId_supplier(Integer id_supplier) {
		supply.id_supplier = id_supplier;
	}

	public static Integer getId_article() {
		return id_article;
	}

	public static void setId_article(Integer id_article) {
		supply.id_article = id_article;
	}

	public static Integer getQuantity() {
		return quantity;
	}

	public static void setQuantity(Integer quantity) {
		supply.quantity = quantity;
	}

	public static Integer getPrice_article() {
		return price_article;
	}

	public static void setPrice_article(Integer price_article) {
		supply.price_article = price_article;
	}

	public static String getVille() {
		return ville;
	}

	public static void setVille(String ville) {
		supply.ville = ville;
	}

	public static String getPays() {
		return pays;
	}

	public static void setPays(String pays) {
		supply.pays = pays;
	}
	
	
	
}
