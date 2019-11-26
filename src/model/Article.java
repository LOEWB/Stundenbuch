package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Article {
	private final StringProperty nameA;
	private final StringProperty price;

	public Article(String nameA, String price) {
		this.nameA=new SimpleStringProperty(nameA);
		this.price=new SimpleStringProperty(price);
	}

	public String getNameA(){
		return nameA.get();
	}

	public String getPrice(){
		return price.get();
	}

	public void setNameA(String value){ nameA.set(value);}

	public void setPrice(String value){
		price.set(value);
	}

	public StringProperty nameAProperty(){
		return nameA;
	}

	public StringProperty priceProperty(){
		return price;
	}
}

