package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


public class order {
	
	public static Integer id_order;
	public static Date date_order;
	public static Integer id_client;
	public static Integer price;
	public static Integer getId_order() {
		return id_order;
	}
	public static void setId_order(Integer id_order) {
		order.id_order = id_order;
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
