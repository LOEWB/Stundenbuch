package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty country;

	public Customer(String firstName, String lastName, String country) {
		this.firstName=new SimpleStringProperty(firstName);
		this.lastName=new SimpleStringProperty(lastName);
		this.country=new SimpleStringProperty(country);
	}

	public String getFirstName(){
		return firstName.get();
	}

	public String getLastName(){
		return lastName.get();
	}

	public String getCountry(){
		return  country.get();
	}

	public void setFirstName(String value){firstName.set(value);}

	public void setLastName(String value){
		lastName.set(value);
	}

	public void setCountry(String value){
		country.set(value);
	}

	public StringProperty firstNameProperty(){
		return firstName;
	}

	public StringProperty lastNameProperty(){
		return lastName;
	}

	public StringProperty countryProperty(){
		return country;
	}

}
