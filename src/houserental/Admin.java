package houserental;

import java.util.ArrayList;


public class Admin extends User
{
    
    private String AdminID;
    
    public Admin(){
        ///Parameterized Constructor///
    }
    
    public void addHouse(String houseID, ArrayList<House> houseList){
       
        ///TBD after HouseClass layout is set///
    }
    
    public void removeHouse(String houseID, ArrayList<House> houseList){
        houseList.removeIf(house->house.getHouseID() = houseID);
        
    }
    public Category viewHouseCategory(String houseID, ArrayList<House> houseList){
        
        int index = houseList.indexOf(houseID);
        return houseList.get(index).getCategory();
    }
    
    
    
}
