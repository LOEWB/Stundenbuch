package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Article {
	private final IntegerProperty id;
	private final IntegerProperty idTicket;
	private final IntegerProperty price;
	private final IntegerProperty quantity;

	public Article(Integer id, Integer idTicket, Integer price, Integer quantity) {
		this.id=new SimpleIntegerProperty(id);
		this.idTicket=new SimpleIntegerProperty(idTicket);
		this.price=new SimpleIntegerProperty(price);
		this.quantity=new SimpleIntegerProperty(quantity);
	}

	public Integer getId(){
		return id.get();
	}

	public Integer getIdTicket(){ return  idTicket.get();}

	public Integer getPrice(){ return price.get(); }

	public Integer getQuantity(){ return  quantity.get(); }

	public void setId(Integer value){ id.set(value);}

	public void setIdTicket(Integer value){ idTicket.set(value);}

	public void setPrice(Integer value){ price.set(value); }

	public void setQuantity(Integer value){ quantity.set(value);}

	public IntegerProperty idProperty(){
		return id;
	}

	public IntegerProperty idTicketProperty(){
		return idTicket;
	}

	public IntegerProperty priceProperty(){
		return price;
	}

	public IntegerProperty quantityProperty(){
		return quantity;
	}
}

