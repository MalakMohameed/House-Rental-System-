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
        
        Button createBooking = new Button("Add Booking");
        Button deleteBooking = new Button("Remove Booking");
        Button signOut = new Button("Sign Out");
        createBooking.setOnAction(e->{
            primaryStage.close();
            ShowBookingAdd(primaryStage,receptionistObj);
        });
        deleteBooking.setOnAction(e->{
            primaryStage.close();
            ShowBookingRemove(primaryStage,receptionistObj);
        });
        signOut.setOnAction(e->{
            primaryStage.close();
            HouseRental.showLogin(primaryStage);
        });
        grid.add(createBooking,0,2);
        grid.add(deleteBooking,0,3);
        grid.add(signOut,0,4);
        return grid;
    }
        public static void ShowAccountMngScrn(Stage primaryStage, Receptionist receptionistObj){
            GridPane grid = createReceptionistMainScrn(primaryStage, receptionistObj);
            Scene scene = new Scene(grid, 400,500);
        
            primaryStage.setTitle("Receptionist");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
    }
        public static void ShowBookingRemove(Stage primaryStage, Receptionist receptionistObj){
            GridPane grid = receptionistObj.createBookingRemove(primaryStage,receptionistObj);
            Scene scene = new Scene(grid, 400,500);
        
            primaryStage.setTitle("Receptionist");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
        }
        public GridPane createBookingRemove(Stage primaryStage, Receptionist receptionistObj){
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);            grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25)); 
        
        Label deleteLabel = new Label("Booking ID");
        TextField deleteField = new TextField();
        Button deleteButton = new Button("Remove Booking");
        Button back = new Button("Back");
        
        deleteButton.setOnAction(e->{
            System.out.println(Booking.getBookings().get(0).getBookingID());
            for(int i = 0; i < Booking.getBookings().size();i++){
                if(deleteField.getText().equals(Booking.getBookings().get(i).getBookingID())){
                    Booking.getBookings().remove(i);
                    Booking.SerializeBooking();
                    System.out.println("Booking Removed");
                    break;
                }
            }
        });
        back.setOnAction(e->{
            primaryStage.close();
            ShowAccountMngScrn(primaryStage,receptionistObj);
        });
        grid.add(back,1,1);
        grid.add(deleteLabel,0,0);
        grid.add(deleteField,1,0);
        grid.add(deleteButton, 0, 1);
        return grid;
        }
        public static void ShowBookingAdd(Stage primaryStage, Receptionist receptionistObj){
        GridPane grid = receptionistObj.createBookingAdd(primaryStage,receptionistObj);
        Scene scene = new Scene(grid, 400,500);
        primaryStage.setTitle("Add Booking");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        }
        public GridPane createBookingAdd(Stage primaryStage,Receptionist receptionistObj){
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
        Button back = new Button("Back");
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
        Booking.getBookings().add(new Booking(receptionistObj,Renter.getUserByID(renterIDField.getText()),House.getHouseByID(houseIDField.getText()),startDateVar,endDateVar));
        Booking.SerializeBooking();
        User.SerializeBinary();
        System.out.println("Booking successful");
        // You might want to update the UI or provide additional feedback here.
    } catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
        // Handle the exception or provide appropriate user feedback.
    }
    });
        back.setOnAction(e->{
            primaryStage.close();
            ShowAccountMngScrn(primaryStage,receptionistObj);
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
        grid.add(back,1,4);
        return grid;
        }
}
