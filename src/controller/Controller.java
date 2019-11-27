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
    public TableView<Article> tableArticle;
    @FXML
    public TableColumn<Article, Integer> columnId;
    @FXML
    public TableColumn<Article, Integer> columnIdTicket;
    @FXML
    public TableColumn<Article, Integer> columnPrice;
    @FXML
    public TableColumn<Article, Integer> columnQuantity;

    @FXML
    public Button buttonLoadArticle;

    //	@FXML
//    public ListView<Ticket> listTicket;
    @FXML
    public ToggleButton toggleStart;

    private ObservableList<Article> dataA;
    private ConnectionBD co;
    Fetching fetching;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        co = new ConnectionBD();
    }

    public void loadArticleFromDatabase(ActionEvent actionEvent) {
        try {
            Connection con = co.connexion();
            dataA = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM artcle");
            while (rs.next()) {
                dataA.add(new Article(rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }

        columnId.setCellValueFactory((new PropertyValueFactory<Article, Integer>("id")));
        columnIdTicket.setCellValueFactory(new PropertyValueFactory<Article, Integer>("idTicket"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Article, Integer>("price"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<Article, Integer>("quantity"));
    }

    public void add_supply(supply sup) {
        Connection con = new ConnectionBD().connexion();
        String sql = "inser into supply(id,id_article,quantity,price_article,city,country)" + " values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, supply.id);
            stm.setInt(2, supply.id_article);
            stm.setInt(3, supply.quantity);
            stm.setInt(4, supply.price_article);
            stm.setString(5, supply.city);
            stm.setString(6, supply.country);

            int row = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_supply(supply sup) {
        Connection con = new ConnectionBD().connexion();

        String sql = "update sup set id= ?,id_article=?,quantity = ?,price_article = ?,city=?,country=? where id= ?";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, supply.id);
            stm.setInt(2, supply.id_article);
            stm.setInt(3, supply.quantity);
            stm.setInt(4, supply.price_article);
            stm.setString(5, supply.city);
            stm.setString(6, supply.country);

            int row = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete_supply(supply sup) {
        Connection con = new ConnectionBD().connexion();

        String sql = "delete from supply where id= ?";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, supply.id);


            int row = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add_order(order ord) {
        Connection con = new ConnectionBD().connexion();

        String sql = "inser into order(id,date_order,id_customer,price)" + " values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, order.id);
            stm.setDate(2, order.date_order);
            stm.setInt(3, order.id_customer);
            stm.setInt(4, order.price);

            int row = stm.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_order(order ord) {
        Connection con = new ConnectionBD().connexion();

        String sql = "update ord set id = ?,date_order = ?,id_customer = ?,price = ? where id= ?";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, order.id);
            stm.setDate(2, order.date_order);
            stm.setInt(3, order.id_customer);
            stm.setInt(4, order.price);

            int row = stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete_order(order ord) {
        Connection con = new ConnectionBD().connexion();

        String sql = "delete from supply where id= ?";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, order.id);
            int row = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void startTicket(ActionEvent actionEvent) {

        if (toggleStart.isSelected()) {
			fetching.startFetching();
        }else{
        	fetching.interrupt();
		}
    }

}