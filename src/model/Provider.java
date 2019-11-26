package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Provider {

    private final StringProperty company;
    private final StringProperty city;
    private final StringProperty countryP;

    public Provider(String company, String city, String countryP) {
        this.company=new SimpleStringProperty(company);
        this.city=new SimpleStringProperty(city);
        this.countryP=new SimpleStringProperty(countryP);
    }

    public String getCompany(){
        return company.get();
    }

    public String getCity(){
        return city.get();
    }

    public String getCountryP(){
        return countryP.get();
    }

    public void setCompany(String value){ company.set(value);}

    public void setCity(String value){
        city.set(value);
    }

    public void setCountryP(String value){
        countryP.set(value);
    }

    public StringProperty companyProperty(){
        return company;
    }

    public StringProperty cityProperty(){
        return city;
    }

    public StringProperty countryPProperty(){ return countryP; }
}
