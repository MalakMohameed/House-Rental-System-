/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houserental;

import java.io.Serializable;

/**
 *
 * @author yahia
 */
public class Location implements Serializable{
    public String country;
    public String city;
    public String streetName;
    public int zipCode;
    public int buildingNumber;
    public int floorNumber;
    
    Location(String country, String city, String streetName, int zipCode, int buildingNumber, int floorNumber){
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.buildingNumber = buildingNumber;
        this.floorNumber = floorNumber;
    }
}
