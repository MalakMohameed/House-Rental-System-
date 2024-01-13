//Daniel @10/1 @ 12:05am
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
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Renter extends User implements Serializable{
    private int numberOfBooking;    
   // private List<House> houseList = new ArrayList<House>(); Moved to House Class 
   
    
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
    
    
     public static Renter getUserByID(String RenterID){ ///Error Handling
        
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
     public ArrayList<Renter> getUsers(){
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

    private static Pane createRenterMainScrn(Stage primaryStage, Renter rentObj){
        Pane rootDiv = new Pane();
      
        rootDiv.setPadding(new Insets(25, 25, 25, 25));
        
        String UserField = rentObj.getUserName();
        Label titleLabelWlcm = new Label("Welcome, " + UserField);
        titleLabelWlcm.setFont(new Font(22));
         titleLabelWlcm.setLayoutX(30);
         titleLabelWlcm.setLayoutY(30);
        
        
        Button rentScrnBtn = new Button("Rent NOW!");
        rentScrnBtn.setLayoutX(300);
         rentScrnBtn.setLayoutY(30);
        Button usrAccountBtn = new Button("My Account");
        usrAccountBtn.setLayoutX(400);
         usrAccountBtn.setLayoutY(30);
        
        Separator sep = new Separator();
        sep.setPrefWidth(rootDiv.getWidth());
         rootDiv.widthProperty().addListener((observable, oldValue, newValue) -> sep.setPrefWidth(newValue.doubleValue()));

        sep.setLayoutY(75);
        
        Separator VSep = new Separator();
        VSep.setOrientation(javafx.geometry.Orientation.VERTICAL);
          rootDiv.widthProperty().addListener((observable, oldValue, newValue) -> VSep.setPrefWidth(newValue.doubleValue()));
          rootDiv.heightProperty().addListener((observable, oldValue, newValue) -> {
            VSep.setPrefHeight(newValue.doubleValue()-50); // Adjust the starting point
            VSep.setLayoutY(80); // Set the starting point
           VSep.setLayoutX(-450);
        });
           
           
        
        
        usrAccountBtn.setOnAction(e->{
        
        rntShowAccountMngScrn(primaryStage, rentObj);
        });
        
      
//        House houseObj = new House();
//        houseObj.setDescription("House sample descriptionBAthroomBedroom, etc..");
//        houseObj.setCostPerNight(600);
//        houseObj.setView(View.garden);
//        houseObj.setCategory(Category.duplex);
//        houseObj.setNumberOfRentals(15);
//        
//        House houseObj1 = new House();
//        //houseObj1.setDescription("Another House sample descriptionBAthroomBedroom, etc..");
//        houseObj1.setCostPerNight(600);
//        houseObj1.setView(View.pool);
//        houseObj1.setCategory(Category.villa);
//        houseObj1.setNumberOfRooms(3);
//        houseObj1.setNumberOfRentals(3);

       /////////////////////////////////////
       
       Pane filterDivPane = new Pane();
       filterDivPane.setLayoutY(130);
       filterDivPane.setLayoutX(30);
       
       Label filterLabel = new Label("Filter Results");
       filterLabel.setFont(new Font(14));
       
       
       
       
       ComboBox<View> viewComboBox = new ComboBox<>();
       ArrayList<UINode> nodeContainer = new ArrayList<>();
       View[] filterView = {viewComboBox.getValue()};
       ObservableList<View> enumValues = FXCollections.observableArrayList(View.values());
       viewComboBox.setItems(enumValues);
       viewComboBox.getSelectionModel().selectFirst();
       
       viewComboBox.setLayoutY(35);
       
       
       
       GridPane itemsGrid = new GridPane();
       itemsGrid.setHgap(10);
       itemsGrid.setVgap(10);
       itemsGrid.setLayoutX(300);
       itemsGrid.setLayoutY(150);
       
       for(short i=0; i < House.getHouseList().size();i++)
       {
           UINode node = new UINode(House.getHouseList().get(i));
           nodeContainer.add(node);
           itemsGrid.getChildren().add(node.getNode());
       }
       
       Button updateFilterButton = new Button("Apply Filter");
       updateFilterButton.setLayoutY(165);
       
       Button resetFilterButton = new Button("Reset Filter");
       resetFilterButton.setLayoutX(80);
       resetFilterButton.setLayoutY(165);
       
       
       
       TextField maxPriceField = new TextField();
       maxPriceField.setPromptText("max. Price/night");
       maxPriceField.setPrefWidth(85);       
       maxPriceField.setLayoutY(85);
       //grid.add(maxPriceField,0,4);
       
       CheckBox showRentedCheck = new CheckBox("Show Rented/Unavil. Houses");
       showRentedCheck.setLayoutY(125);
       
       
       
       updateFilterButton.setOnAction(e->{
        View comboSelValue = viewComboBox.getValue();
        itemsGrid.getChildren().clear();    
           System.out.println(nodeContainer.size());
         int colCont = 0, rowCont = 0;
         
        for(short i=0; i < nodeContainer.size();i++)
        {
           
            if(nodeContainer.get(i).getHouse().getView().equals( comboSelValue))
            {
                if(nodeContainer.get(i).getHouse().getCostPerNight()<= Integer.valueOf( maxPriceField.getText()))
                {
                    if(!nodeContainer.get(i).getHouse().isRented() || showRentedCheck.isSelected()){
                         if(rowCont>4){
                     colCont++;
                     rowCont=0;
                     }
                         
                     itemsGrid.add(nodeContainer.get(i).getNode(),colCont,rowCont);
                    rowCont++;
                    }
                    
                }
            }
        }
        
        
       });
       
       resetFilterButton.setOnAction(ev->{
       itemsGrid.getChildren().clear();
        int colCont = 0, rowCont = 0;;
        for(short i=0; i < nodeContainer.size();i++)
        {
                  if(rowCont>4){
                     colCont++;
                      rowCont=0;
                     }
                     itemsGrid.add(nodeContainer.get(i).getNode(),colCont,rowCont);   
                     rowCont++;
        }     
       });
       
               
       
       filterDivPane.getChildren().add(updateFilterButton);
        filterDivPane.getChildren().add(resetFilterButton);
       filterDivPane.getChildren().add(filterLabel);
       filterDivPane.getChildren().add(viewComboBox);
       filterDivPane.getChildren().add(maxPriceField);      
       filterDivPane.getChildren().add(showRentedCheck);
       
       
       /////////////////////////////////////
       
       
        
        
        
        
       
        rootDiv.getChildren().add(titleLabelWlcm);
        //grid.add(viewComboBox,0,3);
         rootDiv.getChildren().add(sep);
        rootDiv.getChildren().add(VSep);
        rootDiv.getChildren().add(filterDivPane);
        rootDiv.getChildren().add(itemsGrid);
        rootDiv.getChildren().add(rentScrnBtn);
        rootDiv.getChildren().add(usrAccountBtn);
       
        return rootDiv;
        
    }
    
    public static void showRenterMainScrn(Stage primaryStage, Renter rentObj){
        
        System.out.println( rentObj.toString());
        Pane RenterScrnForm = createRenterMainScrn(primaryStage, rentObj);
        Scene scene = new Scene(RenterScrnForm, 500, 500);
        
        
     
        primaryStage.setTitle("EzRent-Renter");      
        primaryStage.setScene(scene);
         
        primaryStage.setMaximized(true);
        primaryStage.show();
        
    }
    
    private static void rntShowAccountMngScrn(Stage primaryStage, Renter rentObj){
        Pane root = rntCreateAccountMngScrn(primaryStage, rentObj);
        Scene scene = new Scene(root, 400,500);
        
        primaryStage.setTitle("EzRent-Renter");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    
    private static Pane rntCreateAccountMngScrn(Stage primaryStage, Renter rentObj){
        
        Pane root = new Pane();    
        root.setPadding(new Insets(25, 25, 25, 25));
        
        Label accDetailTitleLabel = new Label("Account Details");
        accDetailTitleLabel.setFont(new Font(22));
        accDetailTitleLabel.setLayoutX(30);
        accDetailTitleLabel.setLayoutY(100);
        
         ////Account Details
        
        GridPane AccSettingsDiv = new GridPane();
        AccSettingsDiv.setVgap(10);
        AccSettingsDiv.setHgap(15);
        AccSettingsDiv.setLayoutX(30);
        AccSettingsDiv.setLayoutY(150);
        
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
        
        
       
        AccSettingsDiv.add(firstNameLabel, 0, 1);
        AccSettingsDiv.add(firstNameField, 1, 1);
        AccSettingsDiv.add(lastNameLabel, 0, 2);
        AccSettingsDiv.add(lastNameField, 1, 2);
        AccSettingsDiv.add(emailLabel, 0, 3);
        AccSettingsDiv.add(emailField,1,3);
        AccSettingsDiv.add(phoneNumberLabel, 0, 4);
        AccSettingsDiv.add(phoneNumberField, 1, 4);
        AccSettingsDiv.add(ageLabel, 0, 5);
        AccSettingsDiv.add(ageField, 1, 5);
        AccSettingsDiv.add(usernameLabel, 0, 6);
        AccSettingsDiv.add(usernameField, 1, 6);
        AccSettingsDiv.add(passwordLabel, 0, 7);
        AccSettingsDiv.add(passwordField, 1, 7);
        AccSettingsDiv.add(saveUpdatedDataBtn,0,8);


        /////////
        
        Label bookingHistoryTitleLabel = new Label("Previous Bookings:");
        bookingHistoryTitleLabel.setFont(new Font(22));
        bookingHistoryTitleLabel.setLayoutX(410);
        bookingHistoryTitleLabel.setLayoutY(100);
        /////////
        
        ObservableList<Booking> data = FXCollections.observableArrayList(Booking.getBookings());
        
        TableView<Booking> tableView = new TableView<>();
        tableView.setItems(data);
        
        TableColumn<Booking, String> bookingIDCol = new TableColumn<>("Booking ID");
        bookingIDCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBookingID()));

        TableColumn<Booking, String> houseIdCol = new TableColumn<>("House ID ");
        houseIdCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRentedHouse().getHouseID()));
        
        
        TableColumn<Booking, String> startDateCol = new TableColumn<>("Date Of Rental");
        startDateCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(String.valueOf( cellData.getValue().getStartDate())));
        
        
        tableView.getColumns().addAll(bookingIDCol, houseIdCol, startDateCol);
        tableView.setPrefWidth(400);
        tableView.setPrefHeight(263);
        tableView.setLayoutX(410);
        tableView.setLayoutY(135);
        
        //root.getChildren().add();
        /////////
        
        Separator sep = new Separator();
        sep.setPrefWidth(root.getWidth());
        root.widthProperty().addListener((observable, oldValue, newValue) -> sep.setPrefWidth(newValue.doubleValue()));
        sep.setLayoutY(75);
        
        
        Separator VSep = new Separator();
        VSep.setOrientation(javafx.geometry.Orientation.VERTICAL);
        root.widthProperty().addListener((observable, oldValue, newValue) -> VSep.setPrefWidth(newValue.doubleValue()));
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            VSep.setPrefHeight(newValue.doubleValue()-50); // Adjust the starting point
            VSep.setLayoutY(80); // Set the starting point
            VSep.setLayoutX(-300);
        });
        
        
        root.getChildren().addAll(sep,VSep,AccSettingsDiv,accDetailTitleLabel,bookingHistoryTitleLabel,tableView);
       
        
        return root;
    }

    
   
    /////<-----
    
    
}


