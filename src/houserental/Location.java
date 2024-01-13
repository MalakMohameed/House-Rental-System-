package houserental;

import java.io.Serializable;

/**
 *
 
@author yahia*/
public class Location implements Serializable{
    public String country;
    public String city;
    public String streetName;
    public int zipCode;
    public int buildingNumber;
    public int floorNumber;

    Location(){
        
    }
    
    Location(String country, String city, String streetName, int zipCode, int buildingNumber, int floorNumber){
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.buildingNumber = buildingNumber;
        this.floorNumber = floorNumber;
    }
    public String getCountry(){
        return country;
    }
    public String getCity(){
        return city;
    }
    public String getStreetName(){
        return streetName;
    }
    public int getZipCode(){
        return zipCode;
    }
    public int getBuildingNumber(){
        return zipCode;
    }
    public int getFloorNumber(){
        return floorNumber;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setStreetName(String streetName){
        this.streetName = streetName;
    }
    public void setZipCode(int zipcode){
        this.zipCode = zipcode;
    }
    public void setBuildingNumber(int BuildingNumber){
        this.buildingNumber = BuildingNumber;
    }
    public void setFloorNumber(int floorNumber){
        this.floorNumber = floorNumber;
    }
}