package controller;

import DAO.ConnectionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	@FXML
	public TableView<Customer> tableCustomer;
	@FXML
	public TableColumn<Customer, String> columnFirstName;
	@FXML
	public TableColumn<Customer, String> columnLastName;
	@FXML
	public TableColumn<Customer, String> columnCountry;
	@FXML
	public Button buttonLoadCustomer;

	@FXML
	public TableView<Provider> tableProvider;
	@FXML
	public TableColumn<Provider, String> columnCompany;
	@FXML
	public TableColumn<Provider, String> columnCity;
	@FXML
	public TableColumn<Provider, String> columnCountryP;
	@FXML
	public Button buttonLoadProvider;

	@FXML
	public TableView tableArticle;
	@FXML
	public TableColumn<Article, String> columnNameA;
	@FXML
	public TableColumn<Article, String> columnPrice;
	@FXML
	public Button buttonLoadArticle;

	private ObservableList<Customer> dataC;
	private ObservableList<Provider> dataP;
	private  ObservableList<Article> dataA;
	private ConnectionBD co;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		co=new ConnectionBD();
	}

	public void loadCustomerFromDatabase(ActionEvent actionEvent) {
		try{
			Connection con = co.connexion();
			dataC = FXCollections.observableArrayList();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM customer");
			while(rs.next()){
				dataC.add(new Customer(rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			System.err.println("Error" + e);
		}

		columnFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		columnLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
		columnCountry.setCellValueFactory(new PropertyValueFactory<Customer, String>("country"));

		tableCustomer.setItems(null);
		tableCustomer.setItems(dataC);
	}

	public void loadProviderFromDatabase(ActionEvent actionEvent) {
		try {
			Connection con = co.connexion();
			dataP = FXCollections.observableArrayList();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM provider");
			while (rs.next()){
				dataP.add(new Provider(rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		}catch (SQLException e){
			System.err.println("Error" + e);
		}

		columnCompany.setCellValueFactory(new PropertyValueFactory<Provider, String>("company"));
		columnCity.setCellValueFactory(new PropertyValueFactory<Provider, String>("city"));
		columnCountryP.setCellValueFactory(new PropertyValueFactory<Provider, String>("country"));

		tableProvider.setItems(null);
		tableProvider.setItems(dataP);
	}

	public void loadArticleFromDatabase(ActionEvent actionEvent) {
		try {
			Connection con = co.connexion();
			dataA = FXCollections.observableArrayList();
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM artcle");
			while (rs.next()){
				dataA.add(new Article(rs.getString(2), rs.getString(3)));
			}
		}catch (SQLException e){
			System.err.println("Error" + e);
		}

		columnNameA.setCellValueFactory((new PropertyValueFactory<Article, String>("name")));
		columnPrice.setCellValueFactory(new PropertyValueFactory<Article, String>("price"));

	}

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