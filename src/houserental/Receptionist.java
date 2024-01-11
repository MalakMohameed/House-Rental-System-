package houserental;
import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/////kakakakakakak
public class Receptionist extends User implements Serializable{
    
    private static int bookingCounter = 0;
    public ArrayList<Booking> bookingList = new ArrayList<Booking>();
    
   
     
    public Receptionist(){}
    
    public Receptionist(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword){
        //super = constructor to the user calss
        super(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword);
        //Same Note as in Renter class regarding the use of super class constructor
     }
     
    public void setReceptionistID(String ReceptionistID){
        this.userID = ReceptionistID;
    }
    
    public String getReceptionistID(){
        return this.userID;
    }
    
      
        public String generateBookingID(House house, String renterID, Date startDate, Date endDate) { //House ID Creation creiteria 
                                                                                                           ////Why is this function static?     //Fixed
        char renterInitial = renterID.isEmpty() ? '?' : renterID.charAt(0);
        
        String numberOfRoomsString = String.valueOf(house.getNumberOfRooms());
        String houseIDString = String.valueOf(house.getHouseID());
        
        String counterString = String.valueOf(bookingCounter++);
        char categoryInitial = house.getCategory().toString().charAt(0);
        char viewInitial = house.getView().toString().charAt(0);
        String datePart = String.valueOf(startDate.getTime() % 100000) + String.valueOf(endDate.getTime() % 100000);
        
        String bookingID = String.valueOf(renterInitial) + houseIDString + numberOfRoomsString + counterString +categoryInitial + viewInitial + datePart;
        return bookingID;
    }
//     public void createBooking(Renter renter, String HouseID ,int numberOfNights, Enum category, Enum view, Date dateOfRental, Date endOfRental, int numberOfRooms) {
//         
//        //i wanna check if a certain house is empty of not. //Fixed 
//        
//          int index = -1;
//        for (int i = 0; i < houseList.size(); i++) {
//            if (houseList.get(i).getNumberOfRooms() == numberOfRooms &&
//                houseList.get(i).getCategory() == category &&
//                houseList.get(i).getView() == view &&
//                !houseList.get(i).isRented()) {
//                index = i;
//                break;
//            } else {
//                System.out.println("House Category isn't found.");
//            }
//        }
//
//        if (index != -1) {
//            String bookingID = generateBookingID(houseList.get(index), renter.getRenterID(), dateOfRental, endOfRental);
//            
//            //Booking newBooking = new Booking(bookingID,this ,numberOfRooms, category, view, dateOfRental, endOfRental); // no constractor that matchs this attributes
//            Booking tempBook = null;
//            double cost = tempBook.calculateCost(numberOfNights);
//            Booking newBooking = new Booking(this, renter,houseList.get(houseList.indexOf(HouseID)),dateOfRental, endOfRental, numberOfNights, cost); 
//            bookingList.add(newBooking);
//            // saveBookingToFile(newBooking);
//        }
//    }
    
    public void specifyRentalDetails(String BookingID){
        
          int index = -1 ;
       for(int i = 0 ; i <bookingList.size(); i++){
           if(bookingList.get(i).getBookingID().equals(BookingID)){
               index = i;
               break;
           }
       }
       if (index != -1) {
            System.out.println("Booking ID: " + bookingList.get(index).getBookingID());
        System.out.println("Renter: " + bookingList.get(index).getRenter().getUserName()); 
        System.out.println("House: " + bookingList.get(index).getRentedHouse().getHouseID());
       }
       else{
             System.out.println("Booking not found.");
       }
    }
    
    public void selecteHouseCategoty(Category category){
        House newHouse = new House(); //no defualt constructor //Added Default Constructor
        newHouse.setCategory(category); //changed line to use setters 
    }
    
   public void cancelBooking(String bookingID) {
       
        bookingList.removeIf(booking -> booking.getBookingID().equals(bookingID));
       // removeBookingFromFile(bookingID);
    }

    public double calculatePayment(String BookingID,Date dateOfRental,Date endOfRental){
        
        int index = -1 ;
       for(int i = 0 ; i <bookingList.size(); i++){
           if(bookingList.get(i).getBookingID().equals(BookingID)){
               index = i;
               break;
           }
       }
       if (index != -1) {
       long diffInMillies = Math.abs(endOfRental.getTime() - dateOfRental.getTime());
       long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
       return bookingList.get(index).calculateCost((int) diffInDays); //Fixed in Booking class changed data tyepe from int to long
       }
       else {
            System.out.println("Booking not found.");
            return 0.0; 
        }
    }

      public Receptionist getUserByID(String UserID){ ///Error Handling
        
        for (Receptionist e : Receptionists){
            if(e.userID.equals(UserID)){
                int index = Receptionists.indexOf(e);
                return Receptionists.get(index);
            }
        }
        return null;
    }
      
      
      public ArrayList<Booking> getBookingList()
      {
          return this.bookingList;
      }
    
    @Override
    public boolean login(String username, String Password){
        if(Receptionists!=null){
            for(Receptionist e: Receptionists)
            {
                if(e.userName.equals(username) && e.password.equals(Password))
                {
                return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public void signUp(){
        //readBin();
        for(Receptionist r :Receptionists){
            if(r.getReceptionistID().equals(r.getReceptionistID()))
            {
                System.out.println("User Already exists!");
            }
        }
        Receptionists.add(this);
        //writeBin();
    }
    
    
        public static GridPane createReceptionistMainScrn(Stage primaryStage, Receptionist receptionistObj){
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
         grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        String UserField = receptionistObj.getUserName();
        Label titleLabelWlcm = new Label("Welcome, " + UserField);
        
        Button createBooking = new Button("Add Booking");
        Button deleteBooking = new Button("Remove Booking");
        Button searchBookings = new Button("Search Booking");
        createBooking.setOnAction(e->{
            primaryStage.close();
            ShowBookingAdd(primaryStage,receptionistObj);
        });
        grid.add(titleLabelWlcm,0,0);
        grid.add(createBooking,0,2);
        grid.add(deleteBooking,0,3);
        grid.add(searchBookings,0,4);
        return grid;
    }
        public static void ShowAccountMngScrn(Stage primaryStage, Receptionist receptionistObj){
        GridPane grid = createReceptionistMainScrn(primaryStage, receptionistObj);
        Scene scene = new Scene(grid, 400,500);
        
        primaryStage.setTitle("Receptionist");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        public static void ShowBookingAdd(Stage primaryStage, Receptionist receptionistObj){
        GridPane grid = receptionistObj.createBookingAdd(receptionistObj);
        Scene scene = new Scene(grid, 400,500);
        primaryStage.setTitle("Add Booking");
        primaryStage.setScene(scene);
        primaryStage.show();
        }
        public GridPane createBookingAdd(Receptionist receptionistObj){
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);            grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));     
        Label renterID = new Label("RenterID:");
        TextField renterIDField = new TextField();

        Label houseID = new Label("HouseID:");
        TextField houseIDField = new TextField();

        Label startDate = new Label("Start Date:");
        DatePicker startDateField = new DatePicker();
        
        Label endDate = new Label("End Date:");
        DatePicker endDateField = new DatePicker();
        
        Button add = new Button("Add Booking");
        
add.setOnAction(e -> {
    try {
        LocalDate start = startDateField.getValue();
        LocalDate end = endDateField.getValue();

        Date startDateVar = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateVar = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());

        receptionistObj.bookingList.add(
            new Booking(
                receptionistObj,
                Renter.getUserByID(renterIDField.getText()),
                House.getHouseByID(houseIDField.getText()),
                startDateVar,
                endDateVar
            )
            
        );
        User.SerializeBinary();
        System.out.println("Booking successful");
        // You might want to update the UI or provide additional feedback here.
    } catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
        ex.printStackTrace(); 
        // Handle the exception or provide appropriate user feedback.
    }
});
        grid.add(renterID, 0, 0);
        grid.add(renterIDField, 1, 0);
        grid.add(houseID, 0, 1);
        grid.add(houseIDField, 1, 1);
        grid.add(startDate, 0, 2);
        grid.add(startDateField,1,2);
        grid.add(endDate, 0, 3);
        grid.add(endDateField, 1, 3);        
        grid.add(add,0,4);
        
        return grid;
        }
    
}
