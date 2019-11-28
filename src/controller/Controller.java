package controller;

import DAO.ConnectionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @FXML
    public ListView<OrderTicket> listTicket;
    @FXML
    public ToggleButton toggleStart;

    private ObservableList<Article> dataA;
    private ConnectionBD co;
    private Fetching fetching;
    private Thread fetchingThread;
    ObservableList list=FXCollections.observableArrayList();
    public Controller() {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        co = new ConnectionBD();
        feedList();
    }

    public void feedList(){
        list.removeAll(list);
        //OrderTicket ot1 = new OrderTicket(1,LocalDateTime.now() ,12,9);
        //list.add(ot1);

        //listTicket.getItems().addAll(list);
        listTicket.setCellFactory(new Callback<ListView<OrderTicket>, ListCell<OrderTicket>>() {
            @Override
            public ListCell<OrderTicket> call(ListView<OrderTicket> orderTicketListView) {
                ListCell<OrderTicket> cell = new ListCell<OrderTicket>(){
                    @Override
                    protected void updateItem(OrderTicket item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(null);
                        if (!empty && item != null) {
                            final String text = String.format("ORDER -> Ticket :%d \t Date: " + item.getTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\t ClientId: %d \t ArticleId: %d", 1, item.getClientId(), item.getArticleId());
                            setText(text);
                        }
                    }
                };
                return cell;
            }
        });
    }

    public void newOrderTicket(OrderTicket ot){
        list.add(ot);
        listTicket.getItems().addAll(ot);
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

    public void add_article(ArticleSupply art) {
        Connection con = new ConnectionBD().connexion();
        String sql = "insert into article(id,id_ticket,id_produit,price,quantity)"  + " values ( ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,art.getArticleId());
            stm.setInt(2,1);
            stm.setInt(3,6);
            stm.setDouble(4,art.getPrice());
            stm.setDouble(5,art.getQuantity());
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
            stm.setDouble(1,supplyTicket.getArticleSupply().getArticleId());
            ResultSet rs = stm.executeQuery();
            if(rs!=null) {
                while(rs.next()) {
                    int quantity = rs.getInt("quantity");
                    if(quantity > 0) {
                        PreparedStatement st = con.prepareStatement(updateSQl);
                        st.setDouble(1, supplyTicket.getArticleSupply().getQuantity());
                        st.setDouble(2, supplyTicket.getArticleSupply().getArticleId());
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

        co = new ConnectionBD();
        Connection con = co.connexion();
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
        //newOrderTicket(new OrderTicket(5, LocalDateTime.now(),45,39));
        if (toggleStart.isSelected()) {
            fetching = new Fetching(this);
            fetchingThread = new Thread(fetching, "fetching");
            fetchingThread.start();

            //newOrder(new OrderTicket(1, LocalDateTime.now(), 15, 20));
        }else{
            System.out.println("interupt");
			fetching.interrupt();
		}
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        if(!(fetching == null))
        {
            fetching.interrupt();
        }
    }
}