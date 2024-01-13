package houserental;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;



public class Admin extends User implements Serializable
{
//    public String addIcon = "file:C:\\Users\\noor amgad\\OneDrive\\Desktop\\House-Rental-System\\add icon.png\";
    public Admin(){}
    
    public Admin(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword){ //i am adding temporary paremeters for my signup functions feel free to change but take signup into consideration if possible
        super(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword);
    }
    
    public void addHouse(House obj,ArrayList<House> houseList){
        
       houseList.add(obj);
    }
    
    
    
    public void removeHouse(String houseID, ArrayList<House> houseList){
        
        houseList.removeIf(house->house.getHouseID().equals(houseID));
    }
    public Category viewHouseCategory(String houseID, ArrayList<House> houseList){
        
        int index = houseList.indexOf(houseID);
        return houseList.get(index).getCategory();
    }

    public ArrayList<Booking> viewBookingPerReceptionist(String ReceptionistID){
        
        Receptionist receptionist = null;
        receptionist = receptionist.getUserByID(ReceptionistID);
        return receptionist.getBookingList();
        
    }
    public User getUserByID(String UserID){ ///Error Handling
        
        for (Admin e : Admins){
            if(e.userID.equals(UserID)){
                int index = Admins.indexOf(e);
                return Admins.get(index);
            }
        }   
        return null;
    }
   public void removeCustumer(Renter obj){
        Renters.remove(obj);
        obj=null;
             
   }
   public void addCustomer (Renter obj){
       Renters.add(obj);
   }
    
  
       
   
   

    @Override
    public boolean login(String username, String Password){
        if(Admins!=null){
        for(Admin e: Admins)
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
//        for(Admin r :Admins){
//            if(r.AdminID().equals(r.AdminID()))
//            {
//                System.out.println("User Already exists!");
//            }
//            
//        }
        Admins.add(this);
    }
   void removeIcon(House houseobj){
        Stage st = new Stage();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15,15,15,15));
        Label HouseIdLabel = new Label("House ID:");
        TextField HouseIdField = new TextField();
        Button removeHouseBtn = new Button("Remove!");
        
        removeHouseBtn.setOnAction(e->{
        removeHouse(HouseIdField.getText(),  House.getHouseList());
        });
        
        Button back = new Button("Back");
        back.setOnAction(e->{
            st.close();
           editHouseWindow(  houseobj.getHouseList());
        });
        
        grid.add(back,0,1);
        grid.add(removeHouseBtn,1,1);
        grid.add(HouseIdLabel,0,0);
        grid.add(HouseIdField,1,0);
        
        Scene sc = new Scene(grid);
        st.setScene(sc);
        st.setMaximized(true);
        st.show();
    }
    private  Scene addIcon(House houseobj){
        
        Stage st = new Stage();
        
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
         
        int[] selectedCost = {0};
        int[] selectedValue ={0};
        int[] selectedBedrooms = {0};
        int[] selectedBathrooms = {0};
        int[] selectedRate = {0};
        int[] selectedZipCode ={0};
        int[] selectedFloorNumber={0};
        int[] selectedBuildingNumber={0};
        View[] selectedView ={View.farm};
        Category[] selectedCategory={Category.duplex};
        

        Label catlbl = new Label("Category:");
        ComboBox<Category> CategoryComboBox = new ComboBox<>();
        Category[] filterCat = {CategoryComboBox.getValue()};
        ObservableList<Category> enumValues = FXCollections.observableArrayList(Category.values());
        CategoryComboBox.setItems(enumValues);
        CategoryComboBox.setOnAction(e -> {
        selectedCategory[0] = CategoryComboBox.getValue();
        System.out.println("Selected category: " + selectedCategory);

    
        });
        
        
       
        Label viewlbl = new Label("View:");
        ComboBox<View> ViewComboBox = new ComboBox<>();
        View[] filterView = {ViewComboBox.getValue()};
        ObservableList<View> enumValuesS = FXCollections.observableArrayList((View.values()));
        ViewComboBox.setItems(enumValuesS);
        ViewComboBox.setOnAction(e -> {
        selectedView[0] = ViewComboBox.getValue();
        System.out.println("Selected view: " + selectedView);

 
           
        });
        
        Label rentelsNumberLabel = new Label("Number of Rentals:");
        Spinner<Integer> rentalsSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);//houseobj.getNumberOfRentals()
        rentalsSpinner.setValueFactory(valueFactory);
        rentalsSpinner.setEditable(true);
        rentalsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected value: " + newValue);
            selectedValue[0] = newValue;
            
        });


        Label bedroomsLabel = new Label("NumberOfBedrooms:");
        Spinner<Integer> bedroomsSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> bedroomsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        bedroomsSpinner.setValueFactory(bedroomsValueFactory);
        bedroomsSpinner.setEditable(true);
        bedroomsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected bedrooms value: " + newValue);
            selectedBedrooms[0] = newValue;
           
        });
        
        Label bathroomsLabel = new Label("NumberOfBathrooms:");
        Spinner<Integer> bathroomsSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> bathroomsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        bathroomsSpinner.setValueFactory(bathroomsValueFactory);
        bathroomsSpinner.setEditable(true);
        bathroomsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected bathrooms value: " + newValue);
            selectedBathrooms[0] = newValue;
        });

        
        Label DescriptionLabel = new Label("Description:");
        TextField DescriptionField = new TextField();
        DescriptionField.setText("");
        
         Separator sep = new Separator();
        sep.setPrefWidth(grid.getWidth());
        grid.widthProperty().addListener((observable, oldValue, newValue) -> sep.setPrefWidth(newValue.doubleValue()-250));
        sep.setLayoutY(75);
        
        Label LocationDataLabel = new Label("Location Of House");
        LocationDataLabel.setFont(new Font(18));
        
        Label countryLabel = new Label("Country:");
        TextField countryField = new TextField();
        countryField.setText("");
        
        Label cityLabel = new Label("City:");
        TextField cityField = new TextField();
        cityField.setText("");
        
        Label streetNameLabel = new Label("Street Name:");
        TextField streetNameField = new TextField();
        streetNameField.setText("");
        
       Label costLabel = new Label("Cost per Night:");
       Spinner<Integer> costSpinner = new Spinner<>();
       SpinnerValueFactory<Integer> costValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 10000000, 20, 1);
       costSpinner.setValueFactory(costValueFactory);
       costSpinner.setEditable(true);
       costSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
       System.out.println("Selected cost value: " + newValue);
        selectedCost[0] = newValue;
       });
        

       Label rateLabel = new Label("Rate:");
       Spinner<Integer> rateSpinner = new Spinner<>();
       SpinnerValueFactory<Integer> rateValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0, 1);
       rateSpinner.setValueFactory(rateValueFactory);
       rateSpinner.setEditable(true);
       rateSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
       System.out.println("Selected rate value: " + newValue);
       selectedRate[0] = newValue; 
       });
       
       Label zipCodeLabel = new Label("Zip Code:");
       Spinner<Integer> zipCodeSpinner = new Spinner<>();
       SpinnerValueFactory<Integer> zipCodeValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(10000, 99999, 0, 1);
       zipCodeSpinner.setValueFactory(zipCodeValueFactory);
       zipCodeSpinner.setEditable(true);
       zipCodeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
       System.out.println("Selected zip code value: " + newValue);
       selectedZipCode[0] = newValue;
       });
       
       Label floorNumberLabel = new Label("Floor Number:");
       Spinner<Integer> floorNumberSpinner = new Spinner<>();
       SpinnerValueFactory<Integer> floorNumberValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 0, 1);
       floorNumberSpinner.setValueFactory(floorNumberValueFactory);
       floorNumberSpinner.setEditable(true);
       floorNumberSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
       System.out.println("Selected floor number value: " + newValue);
       selectedFloorNumber[0] = newValue;
       });
       
       Label buildingNumberLabel = new Label("Building Number:");
       Spinner<Integer> buildingNumberSpinner = new Spinner<>();
       SpinnerValueFactory<Integer> buildingNumberValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 0, 1);
       buildingNumberSpinner.setValueFactory(buildingNumberValueFactory);
       buildingNumberSpinner.setEditable(true);
       buildingNumberSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
       System.out.println("Selected building number value: " + newValue);
       selectedBuildingNumber[0] = newValue;
       });
       
       
        Button cancelUpdatedDataBtn = new Button("Cancel");

        cancelUpdatedDataBtn.setStyle(
            "-fx-background-color: #800000;" + // Button background color
            "-fx-text-fill: white;" +           // Button text color
            "-fx-font-size: 14px;" +            // Button font size
            "-fx-font-family: 'Arial';"         // Button font family
        );
        cancelUpdatedDataBtn.setOnAction(e->{
        
            st.close();
        });
       
       
       
        Button saveUpdatedDataBtn = new Button("Save");

        saveUpdatedDataBtn.setStyle(
            "-fx-background-color: #008000;" + // Button background color
            "-fx-text-fill: white;" +           // Button text color
            "-fx-font-size: 14px;" +            // Button font size
            "-fx-font-family: 'Arial';"         // Button font family
        );
        
       saveUpdatedDataBtn.setOnAction(e->{
        House Obj = new House();
        System.out.println("Pre Save---->" + Obj.getCostPerNight() + " " +Obj.getDescription()+" " + Obj.getNumberOfBathrooms() + " " + Obj.getNumberOfRooms());
        Obj.setCostPerNight(selectedCost[0]);
        Obj.setNumberOfBathrooms(selectedBathrooms[0]);
        Obj.setNumberOfRentals(selectedValue[0]);
        Obj.setNumberOfRooms(selectedBedrooms[0]);
        Obj.setDescription(DescriptionField.getText()); 
        Obj.setRate(0);//
        Obj.getHouseLocation().setZipCode(100);
        
        Obj.getHouseLocation().setBuildingNumber(selectedBuildingNumber[0]);
        Obj.getHouseLocation().setFloorNumber(selectedFloorNumber[0]);
        Obj.getHouseLocation().setCountry(countryField.getText());
        Obj.getHouseLocation().setCity(cityField.getText());
        Obj.getHouseLocation().setStreetName(streetNameField.getText());
        System.out.println("After Save---->" + Obj.getCostPerNight() + " " +Obj.getDescription()+" " + Obj.getNumberOfBathrooms() + " " + Obj.getNumberOfRooms());

        House.getHouseList().add(Obj);
        System.out.println(Obj.getCostPerNight() + " " +Obj.getDescription()+" " + Obj.getNumberOfBathrooms() + " " + Obj.getNumberOfRooms());
       

        
        User.SerializeBinary();
        
        Stage newWindow = new Stage();
        st.close();
        
            
        });
        
        
        
        grid.add(catlbl, 0, 1);
        grid.add(CategoryComboBox, 1, 1);
        grid.add(viewlbl, 0, 2);
        grid.add(ViewComboBox, 1, 2);
        grid.add(rentelsNumberLabel, 0, 3);
        grid.add(rentalsSpinner,1,3);
        grid.add( bedroomsLabel, 0, 4);
        grid.add(bedroomsSpinner, 1, 4);
        grid.add( bathroomsLabel, 0, 5);
        grid.add(bathroomsSpinner, 1, 5);
        grid.add(DescriptionLabel, 0, 6);
        grid.add( DescriptionField, 1, 6);
        grid.add(costLabel, 0, 7);
        grid.add(costSpinner, 1, 7);
        grid.add(rateLabel, 0, 8);
        grid.add(rateSpinner, 1, 8);
        grid.add(sep,0,9);
        grid.add(LocationDataLabel,0,10);
        grid.add(countryLabel,0,14);
        grid.add(countryField,1,14);
        grid.add(cityLabel,0,15);
        grid.add(cityField,1,15);
        grid.add(streetNameLabel,0,16);
        grid.add(streetNameField,1,16);
        grid.add(buildingNumberLabel,0,17);
        grid.add(buildingNumberSpinner,1,17);
        grid.add(floorNumberLabel, 0, 18);
        grid.add(floorNumberSpinner, 1, 18);
        grid.add(zipCodeLabel,0,19);
        grid.add(zipCodeSpinner,1,19);
        grid.add(saveUpdatedDataBtn,0,25);
        grid.add(cancelUpdatedDataBtn,1,25);
        
       
         ScrollPane scrollPane = new ScrollPane(grid);
        // Set the scroll policy to always show the scroll bars
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        Scene popupScene = new Scene(scrollPane, 350, 400);
             st.setScene(popupScene);
             st.showAndWait();
        return popupScene;
    }
    
    
    public VBox createUserSearch(Stage primaryStage){
        VBox grid = new VBox();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        //grid.setHgap(10);           // grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        
        return grid;
    }
    
    public void showUserSearch(Stage primaryStage){
        
        GridPane grid = new GridPane();
        ArrayList<UINodeEditable<User>> usersNodeContainer = new ArrayList<>();
        System.out.println(User.getAllUsers().toString());
        for(short i = 0; i < User.getAllUsers().size();i++)
        {
            UINodeEditable<User> node = new UINodeEditable<User>(User.getAllUsers().get(i));
            usersNodeContainer.add(node);
            grid.add(node.getNode(),0,i);
        }
        
        
        
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPrefWidth(200);
       
       
        
        
        Pane rootDiv = new Pane();
        //rootDiv.getChildren().add(scrollPane);
        Scene scene = new Scene(scrollPane, 200,500);
        primaryStage.setTitle("User Search");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();              
    }
    
    public GridPane createUserRemove(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);            grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label deleteLabel = new Label("User ID");
        TextField deleteField = new TextField();
        Button deleteButton = new Button("Remove User");
        deleteButton.setOnAction(e->{
            for(int i = 0; i < Renters.size();i++){
                if(deleteField.getText().equals(Renters.get(i).userID)){
                    Renters.remove(i);
                    User.SerializeBinary();
                    System.out.println("Renter Removed");
                    break;
                }
            }
            for(int i = 0; i < Receptionists.size();i++){
                if(deleteField.getText().equals(Receptionists.get(i).userID)){
                    Receptionists.remove(i);
                    User.SerializeBinary();
                    System.out.println("Receptionist Removed");
                    break;
                }
            }            
        });
        Button back = new Button("back");
        back.setOnAction(e->{
            showAdminMainScrn(primaryStage);
        });
        grid.add(back, 1, 1);
        grid.add(deleteLabel,0,0);
        grid.add(deleteField,1,0);
        grid.add(deleteButton, 0, 1);
        return grid;
    }
    public void showUserRemove(Stage primaryStage){
            GridPane grid = createUserRemove(primaryStage);
            Scene scene = new Scene(grid, 400,500);
            primaryStage.setTitle("User Remove");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();       
    }
    public GridPane createUser(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);            grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Button createUser = new Button("Add User");
        Button deleteUser = new Button("Remove User");
        Button searchUsers = new Button("Search User");
        Button back = new Button("Back");
        grid.add(createUser,0,1);
        grid.add(deleteUser,0,2);
        grid.add(searchUsers,0,3);
        grid.add(back,0,4);
        createUser.setOnAction(e->{
            showUserAdd(primaryStage);
        });
        deleteUser.setOnAction(e->{
            showUserRemove(primaryStage);
        });
        searchUsers.setOnAction(e->{
            showUserSearch(primaryStage);
        });
        back.setOnAction(e->{
            showAdminMainScrn(primaryStage);
        });
        return grid;
    }
    public void showUser(Stage primaryStage){
            GridPane grid = createUser(primaryStage);
            Scene scene = new Scene(grid, 400,500);
            primaryStage.setTitle("User Add");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();        
    }
    
    
    
    private void editHouseWindow( ArrayList<House> houseList) {
    
        System.out.println("editHouseWindow() was invoked!");
      
        
        Stage primaryStage = new Stage();
        Pane rootDiv = new Pane();
      
        rootDiv.setPadding(new Insets(25, 25, 25, 25));
        
        String UserField = this.getUserName();
        Label titleLabelWlcm = new Label("Welcome, " + UserField);
        titleLabelWlcm.setFont(new Font(22));
         titleLabelWlcm.setLayoutX(30);
         titleLabelWlcm.setLayoutY(30);
               
        
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
          
          ////////
          
          Pane filterDivPane = new Pane();
       filterDivPane.setLayoutY(130);
       filterDivPane.setLayoutX(30);
       
       Label filterLabel = new Label("Filter Results");
       filterLabel.setFont(new Font(14));
       
       
       
       
       ComboBox<View> viewComboBox = new ComboBox<>();
       ArrayList<UINodeEditable> nodeContainer = new ArrayList<>();
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
       
        short icolCont = 0, irowCont = 0;
       for(short i=0; i < House.getHouseList().size();i++)
       {
           UINodeEditable node = new UINodeEditable(House.getHouseList().get(i));
           nodeContainer.add(node);
  
            if(irowCont>4){
                icolCont++;
                irowCont=0;
            }
            itemsGrid.add(nodeContainer.get(i).getNode(),icolCont,irowCont);
            irowCont++;
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
        int colCont = 0, rowCont = 0;
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
       
               
       Button addHouseBtn = new Button("Add House");
       addHouseBtn.setLayoutX(30);
       addHouseBtn.setLayoutY(500);
       addHouseBtn.setOnAction(e->{
       
           House houseObj = new House();
           
            
           
           Stage popupStage = new Stage();
           Scene scene =  addIcon(houseObj);
           popupStage.setScene(scene);
           popupStage.showAndWait();
           primaryStage.setScene(scene);
           
       });
       
       
       Button removeHouseBtn = new Button("Remove House");
       removeHouseBtn.setLayoutX(30);
       removeHouseBtn.setLayoutY(535);
       
       removeHouseBtn.setOnAction(e->{
           House houseObj = new House();
       removeIcon(houseObj);
           
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
        rootDiv.getChildren().add(addHouseBtn);
        rootDiv.getChildren().add(removeHouseBtn);
        rootDiv.getChildren().add(usrAccountBtn);
          
          ////////
          
          
          
          
          Scene scene = new Scene(rootDiv);
          primaryStage.setScene(scene);
          primaryStage.setMaximized(true);
          primaryStage.show();

            }

   public GridPane createUserAdd(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        
        Label phoneNumberLabel = new Label("Phone Number:");
        TextField phoneNumberField = new TextField();
        
        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();
        
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label userTypeLabel = new Label ("What kind of user ");
        ToggleGroup userType = new ToggleGroup();
        
        RadioButton admin = new RadioButton("Admin");
        admin.setToggleGroup(userType);

        RadioButton renter = new RadioButton("Renter");
        renter.setToggleGroup(userType);
        
        RadioButton receptionist = new RadioButton("Receptionist");
        receptionist.setToggleGroup(userType);
        Button signUp = new Button("SignUp");
        Button cancel = new Button("Cancel");
        signUp.setOnAction(e-> {
            RadioButton selectedUserType = (RadioButton) userType.getSelectedToggle();
            String selectedUser = (selectedUserType != null) ? selectedUserType.getText() : "";
                    if(selectedUser.equalsIgnoreCase("admin")){
                        Admin currentAdmin = new Admin(
                   firstNameField.getText(),
                    lastNameField.getText(),
                       emailField.getText(),
                       phoneNumberField.getText(), 
                              (int) Double.parseDouble(ageField.getText()),
                    usernameField.getText(),
                    passwordField.getText());
                        currentAdmin.signUp();
                        User.SerializeBinary();
                    }          
                    else if(selectedUser.equalsIgnoreCase("Renter")){
                            Renter currentRenter = new Renter(
                   firstNameField.getText(),
                    lastNameField.getText(),
                      emailField.getText(),
                      phoneNumberField.getText(), 
                              (int) Double.parseDouble(ageField.getText()),
                    usernameField.getText(),
                    passwordField.getText());
                            currentRenter.signUp();
                            System.out.println("User created: " + currentRenter.getUserName());
                            User.SerializeBinary();
                    }
                    else if(selectedUser.equalsIgnoreCase("Receptionist")){
                        System.out.println("in here");
                        Receptionist currentReceptionist = new Receptionist(
                   firstNameField.getText(),
                    lastNameField.getText(),
                       emailField.getText(),
                       phoneNumberField.getText(), 
                               (int)Double.parseDouble(ageField.getText()),
                    usernameField.getText(),
                    passwordField.getText());
                            currentReceptionist.signUp();
                            User.SerializeBinary();
                    }
        });
        Button back = new Button("Back");
        back.setOnAction(e->{
            showAdminMainScrn(primaryStage);
        });
        grid.add(back,2 , 8);
        grid.add(firstNameLabel, 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(lastNameLabel, 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(emailLabel, 0, 2);
        grid.add(emailField,1,2);
        grid.add(phoneNumberLabel, 0, 3);
        grid.add(phoneNumberField, 1, 3);
        grid.add(ageLabel, 0, 4);
        grid.add(ageField, 1, 4);
        grid.add(usernameLabel, 0, 5);
        grid.add(usernameField, 1, 5);
        grid.add(passwordLabel, 0, 6);
        grid.add(passwordField, 1, 6);
        grid.add(userTypeLabel, 0, 7);
        grid.add(admin, 1, 7);
        grid.add(renter, 2, 7);
        grid.add(receptionist, 3, 7);
        grid.add(signUp, 0, 8);
        grid.add(cancel, 1, 8);        
        return grid;
    }
    public void showUserAdd(Stage primaryStage){
        GridPane SignUpForm = createUserAdd(primaryStage);
        Scene scene = new Scene(SignUpForm, 500, 400);
        primaryStage.setTitle("SignUp Form");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();        
    }
    public static House getMostRented(){
     int numberOfRentals = 0;
     House mostRented = new House();
     ArrayList<House> houses = House.getHouseList();
     for(int i = 0; i<houses.size();i++){
         if(houses.get(i).getNumberOfRentals() > numberOfRentals){
             numberOfRentals = houses.get(i).getNumberOfRentals();
             mostRented = houses.get(i);
         }
     }
     return mostRented;
}
 
 
public static House getMostRevenue(){
    int totalCost = 0;
    House mostRevenue = new House();
    ArrayList<House> houses = House.getHouseList();
     for(int i = 0; i<houses.size();i++){
         if(houses.get(i).getNumberOfRentals() * houses.get(i).getCostPerNight() > totalCost){
             totalCost = houses.get(i).getNumberOfRentals() * houses.get(i).getCostPerNight();
             mostRevenue = houses.get(i);
         }
     }
     return mostRevenue;
}
    public GridPane createMostRevenue(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);            grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label category = new Label(Admin.getMostRevenue().getCategory().name());
        Label view = new Label(Admin.getMostRevenue().getView().name());
        Label rentals = new Label(String.valueOf(Admin.getMostRevenue().getNumberOfRentals()));
        Label HouseID = new Label(Admin.getMostRevenue().getHouseID());
        Label rooms = new Label(String.valueOf(Admin.getMostRevenue().getNumberOfRooms()));
        Label bathrooms = new Label(String.valueOf(Admin.getMostRevenue().getNumberOfBathrooms()));
        Label cost = new Label(String.valueOf(Admin.getMostRevenue().getCostPerNight()));
        Label rate = new Label(String.valueOf(Admin.getMostRevenue().getRate()));
        Label categoryLabel = new Label("Category");
        Label viewLabel = new Label("View");
        Label rentalsLabel = new Label("Number of rentals");
        Label HouseIDLabel = new Label("ID");
        Label roomsLabel = new Label("Number of rooms");
        Label bathroomsLabel = new Label("Number of bathrooms");
        Label costLabel = new Label("Cost");
        Label rateLabel = new Label("Rate");   
        Button back = new Button("Back");
        back.setOnAction(e->{
            primaryStage.close();
            showStats(primaryStage);
        });
        grid.add(category, 1, 1);
        grid.add(view, 1, 2);
        grid.add(rentals, 1, 3);
        grid.add(HouseID, 1, 4);
        grid.add(rooms, 1, 5);
        grid.add(bathrooms, 1, 6);
        grid.add(cost, 1, 7);
        grid.add(rate, 1, 8);
        grid.add(categoryLabel, 0, 1);
        grid.add(viewLabel, 0, 2);
        grid.add(rentalsLabel, 0, 3);
        grid.add(HouseIDLabel, 0, 4);
        grid.add(roomsLabel, 0, 5);
        grid.add(bathroomsLabel, 0, 6);
        grid.add(costLabel, 0, 7);
        grid.add(rateLabel, 0, 8);
        grid.add(back,1 , 9);
        return grid;
    }
    public GridPane createMostRented(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);            grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label category = new Label(Admin.getMostRented().getCategory().name());
        Label view = new Label(Admin.getMostRented().getView().name());
        Label rentals = new Label(String.valueOf(Admin.getMostRented().getNumberOfRentals()));
        Label HouseID = new Label(Admin.getMostRented().getHouseID());
        Label rooms = new Label(String.valueOf(Admin.getMostRented().getNumberOfRooms()));
        Label bathrooms = new Label(String.valueOf(Admin.getMostRented().getNumberOfBathrooms()));
        Label cost = new Label(String.valueOf(Admin.getMostRented().getCostPerNight()));
        Label rate = new Label(String.valueOf(Admin.getMostRented().getRate()));
        Label categoryLabel = new Label("Category");
        Label viewLabel = new Label("View");
        Label rentalsLabel = new Label("Number of rentals");
        Label HouseIDLabel = new Label("ID");
        Label roomsLabel = new Label("Number of rooms");
        Label bathroomsLabel = new Label("Number of bathrooms");
        Label costLabel = new Label("Cost");
        Label rateLabel = new Label("Rate");
        Button back = new Button("Back");
        back.setOnAction(e->{
            primaryStage.close();
            showStats(primaryStage);
        });
        grid.add(category, 1, 1);
        grid.add(view, 1, 2);
        grid.add(rentals, 1, 3);
        grid.add(HouseID, 1, 4);
        grid.add(rooms, 1, 5);
        grid.add(bathrooms, 1, 6);
        grid.add(cost, 1, 7);
        grid.add(rate, 1, 8);
        grid.add(categoryLabel, 0, 1);
        grid.add(viewLabel, 0, 2);
        grid.add(rentalsLabel, 0, 3);
        grid.add(HouseIDLabel, 0, 4);
        grid.add(roomsLabel, 0, 5);
        grid.add(bathroomsLabel, 0, 6);
        grid.add(costLabel, 0, 7);
        grid.add(rateLabel, 0, 8);
        grid.add(back,1 , 9);
        return grid;
    }
    public void showMostRevenue(Stage primaryStage){
        GridPane Stats = createMostRevenue(primaryStage);
        Scene scene = new Scene(Stats, 500, 400);
        primaryStage.setTitle("Revenue");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    public void showMostRented(Stage primaryStage){
        GridPane Stats = createMostRented(primaryStage);
        Scene scene = new Scene(Stats, 500, 400);
        primaryStage.setTitle("Rented");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    public GridPane createStats(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);            grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Button mostRevenue = new Button("Most Revenue");
        Button mostRented = new Button("Most Rented");
        mostRevenue.setOnAction(e->{
            showMostRevenue(primaryStage);
        });
        mostRented.setOnAction(e->{
            showMostRented(primaryStage);
        });
        Button back = new Button("Back");
        back.setOnAction(e->{
            primaryStage.close();
            showAdminMainScrn(primaryStage);
        });
        grid.add(mostRevenue, 0, 1);
        grid.add(mostRented,0,2);
        grid.add(back,0 , 3);
        return grid;
    }
    public void showStats(Stage primaryStage){
        GridPane Stats = createStats(primaryStage);
        Scene scene = new Scene(Stats, 500, 400);
        primaryStage.setTitle("Stats Form");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();    
    }
    public void showAdminMainScrn(Stage primaryStage){
   
        
        Image image = new Image("file:" + System.getProperty("user.dir") + "/" + "user_icon.png");       
        ImageView userimage = new ImageView(image);
        
        Image image2 = new Image("file:" + System.getProperty("user.dir") + "/" + "stat_icon.png");
        ImageView statimage = new ImageView(image2);
        
        Image image3 = new Image("file:" + System.getProperty("user.dir") + "/" + "list_icon_new.png");
        ImageView listimage = new ImageView(image3);
       
        Button userBtn = new Button();
        userBtn.setGraphic(userimage);
        userimage.setFitWidth(150);  
        userimage.setFitHeight(150);
        userBtn.setStyle("-fx-background-color: transparent;");
        userBtn.setOnAction(e->{
            showUser(primaryStage);
        });
        
       Button statBtn = new Button();
        statBtn.setGraphic(statimage);
        statBtn.setPrefSize(150, 150);
        statimage.setFitWidth(150);  
        statimage.setFitHeight(150);
        statBtn.setStyle("-fx-background-color: transparent;");
        statBtn.setOnAction(e->{
            showStats(primaryStage);
        });
        
        Button listBtn = new Button();
        listBtn.setGraphic(listimage);
        listBtn.setPrefSize(150, 150);
        listimage.setFitWidth(150);  
        listimage.setFitHeight(150);
        listBtn.setStyle("-fx-background-color: transparent;");
        listBtn.setOnAction(e ->{
        House obj = new House();      
        //ArrayList<House> houseList = new ArrayList<>();
        showAdminStatScreen(primaryStage);
        //editHouseWindow( House.getHouseList());//////////////////////////////////////////////////////////
        });
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Image backgroundImage = new Image("file:" + System.getProperty("user.dir") + "/" + "Untitled_design.png");
        BackgroundImage backgroundImg = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
               
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        Background background = new Background(backgroundImg);
        gridPane.setBackground(background);
        gridPane.add(userBtn, 1, 0);
        gridPane.add(statBtn,3,0);
        gridPane.add(listBtn, 2, 0);
        Scene sc = new Scene(gridPane,250,70);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Admin MainScreen");
        primaryStage.setMaximized(true);
        primaryStage.show();
        
        
    }
    
    public void showAdminStatScreen(Stage primaryStage){
       Pane rootDiv = new Pane();
       
       Label dashboardTitleLabel = new Label("Dashboard");
       dashboardTitleLabel.setFont(new Font(26));
       dashboardTitleLabel.setLayoutX(30);
       dashboardTitleLabel.setLayoutY(20);
       Button back = new Button("Back");
       ////
       back.setOnAction(e->{
           showAdminMainScrn(primaryStage);
       });
        Separator sep = new Separator();
        sep.setPrefWidth(rootDiv.getWidth());
        rootDiv.widthProperty().addListener((observable, oldValue, newValue) -> sep.setPrefWidth(newValue.doubleValue()));
        sep.setLayoutY(75);
       
       ///
       
        Separator VSep = new Separator();
        VSep.setOrientation(javafx.geometry.Orientation.VERTICAL);
        rootDiv.widthProperty().addListener((observable, oldValue, newValue) -> VSep.setPrefWidth(newValue.doubleValue()));
        rootDiv.heightProperty().addListener((observable, oldValue, newValue) -> {
            VSep.setPrefHeight(newValue.doubleValue()-50); // Adjust the starting point
            VSep.setLayoutY(80); // Set the starting point
            VSep.setLayoutX(0);
        });
       
        
        ////// USER DATA AND STAT
        Pane userDataDiv = new Pane();
        userDataDiv.setLayoutY(90);
        
        
        Label userDataTitleLabel = new Label("Receptionist Statistics");
        userDataTitleLabel.setFont(new Font(24));        
        userDataTitleLabel.setLayoutX(30);
        
        
        
        TableView<Receptionist> tableViewUsr = new TableView<>();
        ObservableList<Receptionist> Usrdata = FXCollections.observableArrayList(Receptionist.Receptionists);
        tableViewUsr.setItems(Usrdata);

        
        TableColumn<Receptionist, String> UsrIDCol = new TableColumn<>("User ID");
        UsrIDCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUserID()));

        TableColumn<Receptionist, String> usrnameCol = new TableColumn<>("Userame");
        usrnameCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUserName()));
       
        TableColumn<Receptionist, Integer> noRentalCol = new TableColumn<>("No. Rentals ");
        noRentalCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBookingList().size()));
        //houseCatCol.setCellValueFactory(new PropertyValueFactory<>("Category"));
        
        
        tableViewUsr.getColumns().addAll(UsrIDCol, usrnameCol,noRentalCol);
        tableViewUsr.setPrefWidth(300);
        tableViewUsr.setPrefHeight(150);
        tableViewUsr.setLayoutY(50);
        tableViewUsr.setLayoutX(30);
        
        
        
        userDataDiv.getChildren().addAll(userDataTitleLabel,tableViewUsr, back);
        
        //////
        
        ///// HOUSE DATA AND STAT
        
        short midScreenCord = 700;
        
        Pane houseDataDiv = new Pane();
        houseDataDiv.setLayoutX(midScreenCord+20);
        houseDataDiv.setLayoutY(90);
        
        
        Label houseDataTitleLabel = new Label("House Statistics");
        houseDataTitleLabel.setFont(new Font(24));        
       
        Label mostRevenueHouseLabel = new Label();
        
        
       
        TableView<House> tableView = new TableView<>();
        ObservableList<House> Housedata = FXCollections.observableArrayList(House.getHouseList());
        tableView.setItems(Housedata);

        
        TableColumn<House, String> houseIDCol = new TableColumn<>("House ID");
        houseIDCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHouseID()));

        TableColumn<House, Integer> NoRentalsCol = new TableColumn<>("No. of Rentals");
        NoRentalsCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNumberOfRentals()));
       
        TableColumn<House, String> houseCatCol = new TableColumn<>("House Category");
        //houseCatCol.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCategory()));
        houseCatCol.setCellValueFactory(new PropertyValueFactory<>("Category"));
        
        
        tableView.getColumns().addAll(houseIDCol, NoRentalsCol,houseCatCol);
        tableView.setPrefWidth(300);
        tableView.setPrefHeight(150);
        tableView.setLayoutY(50);
        tableView.setLayoutX(30);
        
        
        houseDataDiv.getChildren().addAll(houseDataTitleLabel,tableView);
        
        
        
        /////
              
       rootDiv.getChildren().addAll(dashboardTitleLabel,sep,VSep,userDataDiv,houseDataDiv);
       
       Scene scene = new Scene(rootDiv);
       primaryStage.setScene(scene);
       primaryStage.setTitle("Admin - DASHBOARD");
       primaryStage.setMaximized(true);
       primaryStage.show();
    }
    
    }