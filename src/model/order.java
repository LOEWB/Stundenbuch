package model;

import java.sql.Date;

public class order {
	
	public static Integer id;
	public static Date date_order;
	public static Integer id_customer;
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
	public static Integer getId_customer() {
		return id_customer;
	}
	public static void setId_customer(Integer id_customer) {
		order.id_customer = id_customer;
	}
	public static Integer getPrice() {
		return price;
	}
	public static void setPrice(Integer price) {
		order.price = price;
	}





	
	
}
