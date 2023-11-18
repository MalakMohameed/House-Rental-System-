package houserental;

import java.util.ArrayList;


public class Admin extends User
{
    private String AdminID;
    
    
    public void addHouse(String houseID, ArrayList<House> houseList){
       
        ///TBD after HouseClass layout is set///
    }
    
    public void removeHouse(String houseID, ArrayList<House> houseList){
        houseList.removeIf(house->house.getHouseID() = houseID);
        
    }
    
}
