//Daniel @19/11 @ 12:55pm
//check notes 
package houserental;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


///Some other JavaFX imports 
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Renter extends User implements Serializable{
    private int numberOfBooking; 
    private List<House> houseList = new ArrayList<House>();
   
    
    Renter(){}
    
    Renter(Renter rentObj){
        this.firstName= rentObj.firstName;
        this. lastName= rentObj.lastName;
        this. age= rentObj.age;
        this. email= rentObj.email;
        this.phone= rentObj.phone;
        this.userName= rentObj.userName;
        this.password= rentObj.password;
        this.userID= rentObj.userID;
    }
    
    Renter(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword){
        //super = constructor to the user calss
        super(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword); //2 here is the enum corresponding to renter in enum type ///check constructor calling super class 
        // this.setRenterID( User.generateUserId(newfirstName, newlastName, age, newphone, newemail, type));                                                                                                               /// constructor using diff param    
//         Users.add();
//         Renters.add();
        // System.out.println("Renter Const-->>" + getUserByID(this.getRenterID()).getUserName());
    }
    public void setRenterID(String RenterID){
        this.userID = RenterID;
    }
    
    public String getRenterID(){
        return this.userID;
    }
    
//    public void rateRent(int Rate){ ////Change this to have an enum of Rating and basically refactor into cleaner code.
//        int index = -1;
//        for(int i = 0 ; i <houseList.size(); i++){
//           if(houseList.get(i).getUserID().equals(this.getRenterID()) && Rate >= 5){ //five stars or less
//               index = i;
//               break;
//           }else{
//               System.out.println("");
//           }
//       }
//    }
//    public void viewBookingHistory() {
//        
//        ArrayList<Booking> bookingHistory = new ArrayList<>();
//        for (House house : houseList) {
//            ArrayList<Booking> houseBookings = house.getBookings();
//            for (Booking booking : houseBookings) {
//                if (booking.getRenter().getRenterID().equals(this.getRenterID())) {
//                    bookingHistory.add(booking);
//                    System.out.println("House ID: " + house.getHouseID());
//                }
//                   
//                
//            }
//        }
//    }
    
    
     public Renter getUserByID(String RenterID){ ///Error Handling
        
        for (Renter e : Renters){
            if(e.userID.equals(RenterID)){
                int index = Renters.indexOf(e);
                return Renters.get(index);
            }
        }
        return null;
        
    }
     public  Renter getUserByUserName(String UserName)
     {
         for (Renter e : Renters){
            if(e.userName.equals(UserName)){
                int index = Renters.indexOf(e);
                        System.out.println("User with UserName: "+ UserName + "Found!");
                return Renters.get(index);
            }
        }
          System.out.println("User with UserName: "+ UserName + " WAS NOT Found!");
        return null;
     }
    //
     public ArrayList<Renter> getAllUsers(){
         if(Renters.isEmpty()) { System.out.println("Empty List");return null;}
         else {
             return this.Renters;
         }
     }
     
    
    @Override
    public boolean login(String username, String Password){
       if(Renters!=null){
        for(Renter e: Renters)
        {
            if(e.userName.equals(username) && e.password.equals(Password))
            {
                this.firstName= e.firstName;
                this. lastName= e.lastName;
                this. age= e.age;
                this. email= e.email;
                this.phone= e.phone;
                this.userName= e.userName;
                this.password= e.password;
                this.userID= e.userID;
                return true;
            }
        }
               return false;
       }
              return false;
    }
    
    @Override
    public void signUp(){
       // readBin();
        for(Renter r :Renters){
            System.out.println(r.toString() + userID);
            if(r.getUserID().equals(userID))
            {
                System.out.println("User Already exists!");
            }       
        }
        Renters.add(this);
        //writeBin();
    }

    private static GridPane createRenterMainScrn(Stage primaryStage, Renter rentObj){
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
         grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        String UserField = rentObj.getUserName();
        Label titleLabelWlcm = new Label("Welcome, " + UserField);
        
        Button rentScrnBtn = new Button("Rent NOW!");
        Button usrAccountBtn = new Button("My Account");
        
        usrAccountBtn.setOnAction(e->{
        
        rntShowAccountMngScrn(primaryStage, rentObj);
        });
        
        
        grid.add(titleLabelWlcm,0,0);
        grid.add(rentScrnBtn,3,0);
        grid.add(usrAccountBtn,4,0);
        return grid;
        
    }
    
    public static void showRenterMainScrn(Stage primaryStage, Renter rentObj){
        
        System.out.println( rentObj.toString());
        GridPane RenterScrnForm = createRenterMainScrn(primaryStage, rentObj);
        Scene scene = new Scene(RenterScrnForm, 500, 500);
        
        primaryStage.setTitle("EzRent-Renter");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private static void rntShowAccountMngScrn(Stage primaryStage, Renter rentObj){
        GridPane grid = rntCreateAccountMngScrn(primaryStage, rentObj);
        Scene scene = new Scene(grid, 400,500);
        
        primaryStage.setTitle("EzRent-Renter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private static GridPane rntCreateAccountMngScrn(Stage primaryStage, Renter rentObj){
        
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        firstNameField.setText(rentObj.getFirstName());

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        lastNameField.setText(rentObj.getLastName());

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setText(rentObj.getEmail());
        
        Label phoneNumberLabel = new Label("Phone Number:");
        TextField phoneNumberField = new TextField();
        phoneNumberField.setText(rentObj.getPhone());
        
        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();
        //ageField.setText(this.getAge());
        
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setText(rentObj.getUserName());
        
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setText(rentObj.getPassword());
        
      
        
        Button saveUpdatedDataBtn = new Button("Save");
        
        saveUpdatedDataBtn.setOnAction(e->{
        
        rentObj.firstName= firstNameField.getText();
        rentObj. lastName= lastNameField.getText();
        //rentObj. age= rentObj.age;   ////Not Updating Age for the time being!!!!
        rentObj. email= emailField.getText();
        rentObj.phone= phoneNumberField.getText();
        rentObj.userName= usernameField.getText();
        rentObj.password= passwordLabel.getText();
        ////RegenID
        
        User.SerializeBinary();
        
        Stage newWindow = new Stage();
        Renter.showRenterMainScrn(newWindow, rentObj);
        primaryStage.close();
        
            
        });
        
        
        
        grid.add(firstNameLabel, 0, 1);
        grid.add(firstNameField, 1, 1);
        grid.add(lastNameLabel, 0, 2);
        grid.add(lastNameField, 1, 2);
        grid.add(emailLabel, 0, 3);
        grid.add(emailField,1,3);
        grid.add(phoneNumberLabel, 0, 4);
        grid.add(phoneNumberField, 1, 4);
        grid.add(ageLabel, 0, 5);
        grid.add(ageField, 1, 5);
        grid.add(usernameLabel, 0, 6);
        grid.add(usernameField, 1, 6);
        grid.add(passwordLabel, 0, 7);
        grid.add(passwordField, 1, 7);
        grid.add(saveUpdatedDataBtn,0,8);
        
       
        
        return grid;
    }

    
   
    
}
