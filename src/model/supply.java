package model;


public class supply {

	
	public static Integer id;
	public static Integer id_article;
	public static Integer quantity;
	public static Integer price_article;

	
	public static String city;
	public static String country;


	
	public static Integer getId() {
		return id;
	}

	public static void setId(Integer id) {
		supply.id = id;
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

	
	public static String getCity() {
		return city;
	}

	public static void setCity(String city) {
		supply.city = city;
	}

	public static String getCountry() {
		return country;
	}

	public static void setCountry(String country) {
		supply.country = country;
	}
	
}
