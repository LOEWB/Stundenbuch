package model;

import java.sql.Date;

public class order {
	
	public static Integer id;
	public static Date date_order;
	public static Integer id_client;
	public static Integer price;
	
	
	
	
	public static Integer getId() {
		return id;
	}
	public static void setId(Integer id) {
		order.id = id;
	}
	public static Date getDate_order() {
		return date_order;
	}
	public static void setDate_order(Date date_order) {
		order.date_order = date_order;
	}
	public static Integer getId_client() {
		return id_client;
	}
	public static void setId_client(Integer id_client) {
		order.id_client = id_client;
	}
	public static Integer getPrice() {
		return price;
	}
	public static void setPrice(Integer price) {
		order.price = price;
	}





	
	
}
