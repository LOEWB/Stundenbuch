package controller;

import DAO.ConnectionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

//	@FXML
//    public ListView<Ticket> listTicket;
	@FXML
	public ToggleButton toggleStart;
	@FXML
	public ToggleButton toggleStop;

	private ObservableList<Customer> dataC;
	private ObservableList<Provider> dataP;
	private  ObservableList<Article> dataA;
	private ConnectionBD co;
	Fetching fetching;

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





	

	public void add_article(ArticleSupply art) {
		Connection con = new ConnectionBD().connexion();
		String sql = "insert into article(id,id_ticket,id_produit,price,quantity)"  + " values ( ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			  stm.setInt(1,art.getArticleId());
			  stm.setInt(2,1);
			  stm.setInt(3,6);
			  stm.setInt(4,art.getPrice());
			  stm.setInt(5,art.getQuantity());
			int row = stm.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}}
		
		public void newSupply(SupplyTicket supplyTicket) {

			Connection con = new ConnectionBD().connexion();
			String sql = "SELECT id FROM article where id=?";
			String updateSQl="update set quantity = quantity + ? from article where id= ? ";
		
			try {
				PreparedStatement stm = con.prepareStatement(sql);
				stm.setInt(1,supplyTicket.getArticleSupply().getArticleId());
				ResultSet rs = stm.executeQuery();
				if(rs!=null) {
					while(rs.next()) {
						int quantity = rs.getInt("quantity");
						if(quantity > 0) {
							PreparedStatement st = con.prepareStatement(updateSQl);
							st.setInt(1, supplyTicket.getArticleSupply().getQuantity());
							st.setInt(2, supplyTicket.getArticleSupply().getArticleId());
							int row = st.executeUpdate();
						}
					}
				}else {
					this.add_article(supplyTicket.getArticleSupply());
				  
				}
				
					
						} catch (SQLException e) {
				e.printStackTrace();
			}}

		
		
		
		
		
		
		public void newOrder(OrderTicket orderTicket) {

			Connection con = new ConnectionBD().connexion();
			String sql = "SELECT id FROM article where id=?";
			String updateSQl="update set quantity = quantity -1 from article where id= ? ";
		
			try {
				PreparedStatement stm = con.prepareStatement(sql);
				stm.setInt(1,orderTicket.getArticleId());
				ResultSet rs = stm.executeQuery();
				if(rs!=null) {
					while(rs.next()) {
						int quantity = rs.getInt("quantity");
						if(quantity > 0) {
							PreparedStatement st = con.prepareStatement(updateSQl);
							st.setInt(2,orderTicket.getArticleId() );
							
							int row = st.executeUpdate();
						}
					}
				}else {
					System.out.println("Article not found");
				}
				
					
						} catch (SQLException e) {
				e.printStackTrace();
			}}











	public void startTicket(ActionEvent actionEvent) {
		fetching.startFetching();
	}

	public void stopTicket(ActionEvent actionEvent) {
		fetching.interrupt();
	}
}
