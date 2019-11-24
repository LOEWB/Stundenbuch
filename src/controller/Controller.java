package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.xdevapi.PreparableStatement;

import DAO.ConnectionBD;
import model.order;
import model.supply;
public class Controller {
	
	public void update(supply sup) {
		Connection con = new ConnectionBD().connexion();
		
		String sql = "update sup set id_supply = ?,id_article = ?,quantity = ?,price_article = ? where id_supply = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1,supply.id_supplier);
			stm.setInt(2,supply.id_article);
			stm.setInt(3,supply.quantity);
			stm.setInt(4,supply.price_article);
			int row = stm.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}}
	
	public void update2(order ord) {
		Connection con = new ConnectionBD().connexion();
		
		String sql = "update ord set id_order = ?,date_order = ?,id_client = ?,price = ? where id_order = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1,order.id_order);
			stm.setDate(2,order.date_order);
			stm.setInt(3,order.id_client);
			stm.setInt(4,order.price);
			
			int row = stm.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}}
}