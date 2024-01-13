package houserental;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UINodeEditable<T> {

    private Rectangle RectangleContainer;
    private Label descriptionLabel = new Label();
    private Label priceLabel = new Label();
    private Label userLabel = new Label();
    private Button detailsBtn = new Button("Details");
    private Button editBtn = new Button("Edit");
    private Button editUsrBtn = new Button("Edit");

    private Label priceLabelDet = new Label();
    private Label viewLabelDet = new Label();
    private Label propTypeLabelDet = new Label();
    private Label noBedroomLabelDet = new Label();
    private Label descriptionLabelDet = new Label();

    private boolean isDetailsWinOpen = false;

    private Pane container = new Pane();
    private House house = new House();
    

    public UINodeEditable(T obj) {
        if (obj instanceof House) {
            initializeHouseNode((House) obj);
        } else if (obj instanceof User) {
            initializeUsrNode((User)obj);
            }
    }

    private void initializeHouseNode(House obj) {
        this.house = obj;
        RectangleContainer = new Rectangle(350, 100, Color.GRAY);
        RectangleContainer.setArcHeight(15);
        RectangleContainer.setArcWidth(15);

        descriptionLabel.setText(obj.getDescription());
        descriptionLabel.setLayoutX(container.getLayoutX() + 10);
        descriptionLabel.setLayoutY(container.getLayoutY() + 40);

        priceLabel.setText(String.valueOf(obj.getCostPerNight()) + "/Night");
        priceLabel.setFont(new Font(18));
        priceLabel.setLayoutX(container.getLayoutX() + 10);
        priceLabel.setLayoutY(container.getLayoutY() + 5);

        detailsBtn.setLayoutX(container.getLayoutX() + 285);
        detailsBtn.setLayoutY(container.getLayoutY() + 65);

        editBtn.setLayoutX(container.getLayoutX() + 235);
        editBtn.setLayoutY(container.getLayoutY() + 65);

        editBtn.setOnAction(e -> {
            adminCreateAccountMngScrn(house);
        });

        detailsBtn.setOnAction(e -> {
            showDetailsPopup();
        });

        container.setPadding(new Insets(2));
        container.getChildren().addAll(RectangleContainer, priceLabel, descriptionLabel, detailsBtn, editBtn);
    }

    private void showDetailsPopup() {
        if (!isDetailsWinOpen) {
            isDetailsWinOpen = true;
            Stage detailsStage = new Stage();
            detailsStage.initStyle(StageStyle.UNDECORATED);
            detailsStage.setResizable(false);

            // Add labels to show more info

            Button closeBtn = new Button("Close");
            closeBtn.setLayoutX(235);
            closeBtn.setLayoutY(365);
            closeBtn.setOnAction(evntClose -> {
                detailsStage.close();
                isDetailsWinOpen = false;
            });

            // Rest of the code for details popup...

            Scene popupScene = new Scene(new Pane(), 300, 400);
            detailsStage.setScene(popupScene);
            detailsStage.showAndWait();
        }
    }
     public void RefreshUsrNode(User obj) {
       
        userLabel.setText(obj.getUserName());
        
    }
    
       private GridPane UsrEditWindow(User obj){
            Stage st = new Stage();
        
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        int[] selectedAge = {0};
      
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        firstNameField.setText(obj.getFirstName());

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        lastNameField.setText(obj.getLastName());

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setText(obj.getEmail());
        
        Label phoneNumberLabel = new Label("Phone Number:");
        TextField phoneNumberField = new TextField();
        phoneNumberField.setText(obj.getPhone());
        
        Spinner<Integer> ageSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> ageValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 120, obj.getAge(), 1);
        ageSpinner.setValueFactory(ageValueFactory);
        ageSpinner.setEditable(true);
        ageSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
        System.out.println("Selected age value: " + newValue);
        selectedAge[0] = newValue;
})      ;
        
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setText(obj.getUserName());

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setText(obj.getPassword());
        
//        firstNameField.setText(obj.getFirstName()); 
//        lastNameField.setText(obj.getLastName());
//        emailField.setText(obj.getEmail());
//        phoneNumberField.setText(obj.getPhone());
//        ageField.setText(String.valueOf(selectedAge[0]));
//        usernameField.setText(obj.getUserName());
//        passwordField.setText(obj.getPassword());
        
        Button saveUpdatedDataBtn = new Button("Save");

       saveUpdatedDataBtn.setOnAction(e->{
        
       obj.setFirstName(firstNameField.getText());
       obj.setLastName(lastNameField.getText());
       obj.setEmail(emailField.getText());
       obj.setPhone(phoneNumberField.getText());
       obj.setAge(selectedAge[0]);
       obj.setUserName(usernameField.getText());
       obj.setPassword(passwordField.getText());
       
        
       
        RefreshUsrNode( obj);
        

        
        User.SerializeBinary();
        
        Stage newWindow = new Stage();
        st.close();
        
            
        });
        
        
        
        grid.add(firstNameLabel, 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(lastNameLabel, 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(emailLabel, 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(phoneNumberLabel, 0, 3);
        grid.add(phoneNumberField, 1, 3);
        grid.add(ageSpinner, 1, 4); 
        grid.add(usernameLabel, 0, 5);
        grid.add(usernameField, 1, 5);
        grid.add(passwordLabel, 0, 6);
        grid.add(passwordField, 1, 6);
        grid.add(saveUpdatedDataBtn, 0, 7);
     
       
        Scene popupScene = new Scene(grid, 300, 400);
             st.setScene(popupScene);
             st.showAndWait();
        return grid;
       }

       private  GridPane adminCreateAccountMngScrn( House Obj){
        
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
        boolean[] selectedBoolean = {false};
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
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, Obj.getNumberOfRentals());
        rentalsSpinner.setValueFactory(valueFactory);
        rentalsSpinner.setEditable(true);
        rentalsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected value: " + newValue);
            selectedValue[0] = newValue;
            
        });


        Label bedroomsLabel = new Label("NumberOfBedrooms:");
        Spinner<Integer> bedroomsSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> bedroomsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, Obj.getNumberOfRooms());
        bedroomsSpinner.setValueFactory(bedroomsValueFactory);
        bedroomsSpinner.setEditable(true);
        bedroomsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected bedrooms value: " + newValue);
            selectedBedrooms[0] = newValue;
           
        });
        
       Label costLabel = new Label("Cost per Night:");
       Spinner<Integer> costSpinner = new Spinner<>();
       SpinnerValueFactory<Integer> costValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 10000000, 20, 1);
       costSpinner.setValueFactory(costValueFactory);
       costSpinner.setEditable(true);
       costSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
       System.out.println("Selected cost value: " + newValue);
        selectedCost[0] = newValue;
       });
        
        Label bathroomsLabel = new Label("NumberOfBathrooms:");
        Spinner<Integer> bathroomsSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> bathroomsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, Obj.getNumberOfBathrooms());
        bathroomsSpinner.setValueFactory(bathroomsValueFactory);
        bathroomsSpinner.setEditable(true);
        bathroomsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected bathrooms value: " + newValue);
            selectedBathrooms[0] = newValue;
        });

        
        Label DescriptionLabel = new Label("Description:");
        TextField DescriptionField = new TextField();
        DescriptionField.setText(Obj.getDescription());
        
      
        
        
        Label rateLabel = new Label("Rate:");
       Spinner<Integer> rateSpinner = new Spinner<>();
       SpinnerValueFactory<Integer> rateValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, Obj.getRate(), 1);
       rateSpinner.setValueFactory(rateValueFactory);
       rateSpinner.setEditable(true);
       rateSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
       System.out.println("Selected rate value: " + newValue);
       selectedRate[0] = newValue; 
       });
        
        Button saveUpdatedDataBtn = new Button("Save");

       saveUpdatedDataBtn.setOnAction(e->{
        System.out.println("Pre Save---->" + Obj.getCostPerNight() + " " +Obj.getDescription()+" " + Obj.getNumberOfBathrooms() + " " + Obj.getNumberOfRooms());
        Obj.setCostPerNight( selectedCost[0]);
        Obj.setNumberOfBathrooms(selectedBathrooms[0]);
        Obj.setNumberOfRentals(selectedValue[0]);
        Obj.setNumberOfRooms(selectedBedrooms[0]);
        Obj.setDescription(DescriptionField.getText());
        Obj.setRate(selectedRate[0]);
        
          System.out.println(Obj.getCostPerNight() + " " +Obj.getDescription()+" " + Obj.getNumberOfBathrooms() + " " + Obj.getNumberOfRooms());
        RefreshNode();
        

        
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
        grid.add(saveUpdatedDataBtn,0,9);
     
       
        Scene popupScene = new Scene(grid, 300, 400);
             st.setScene(popupScene);
             st.showAndWait();
        return grid;
    

  
    }

    public Pane getNode() {
        return container;
    }

    public House getHouse() {
        return this.house;
    }

    public void RefreshNode() {
        descriptionLabel.setText(house.getDescription());
        priceLabel.setText(String.valueOf(house.getCostPerNight()) + "/Night");

        priceLabelDet.setText(String.valueOf(house.getCostPerNight()));
        viewLabelDet.setText(String.valueOf(house.getView()));
        propTypeLabelDet.setText(String.valueOf(house.getCategory()));
        noBedroomLabelDet.setText(String.valueOf(house.getNumberOfRooms()));
        descriptionLabelDet.setText(house.getDescription());
    }
    private void initializeUsrNode(User obj) {
        
        RectangleContainer = new Rectangle(500, 100, Color.GRAY);
        RectangleContainer.setArcHeight(10);
        RectangleContainer.setArcWidth(10);
        



        userLabel.setText(String.valueOf(obj.getUserName()) );
        userLabel.setFont(new Font(18));
        userLabel.setLayoutX(container.getLayoutX() + 10);
        userLabel.setLayoutY(container.getLayoutY() + 25);
        
        String userType = "";
        if(obj instanceof Renter){
            userType = "Renter";
        }
        else if(obj instanceof Admin){
            userType = "Admin";
        }
        if(obj instanceof Receptionist){
            userType = "Receptionist";
        } 
        
        Label userIDLabel = new Label(obj.getUserID());
        userIDLabel.setFont(new Font(18));
        userIDLabel.setLayoutX(container.getLayoutX() + 10);
        userIDLabel.setLayoutY(container.getLayoutY() + 5);
       
        
        Label userTypeLabel = new Label(userType);
        userTypeLabel.setFont(new Font(11));
        userTypeLabel.setLayoutX(container.getLayoutX() + 10);
        userTypeLabel.setLayoutY(container.getLayoutY() + 45);
        
        Label userEmailLabel = new Label(obj.getEmail());
        userEmailLabel.setFont(new Font(11));
        userEmailLabel.setLayoutX(container.getLayoutX() + 10);
        userEmailLabel.setLayoutY(container.getLayoutY() + 60);
        
        
        Label phoneNumberLabel = new Label(obj.getPhone());
        phoneNumberLabel.setFont(new Font(11));
        phoneNumberLabel.setLayoutX(container.getLayoutX() + 10);
        phoneNumberLabel.setLayoutY(container.getLayoutY() + 75);
        

        editUsrBtn.setLayoutX(container.getLayoutX() + 370);
        editUsrBtn.setLayoutY(container.getLayoutY() + 40);
        
        Button deleteUserButton = new Button("Delete User");
        deleteUserButton.setLayoutX(container.getLayoutX() + 420);
        deleteUserButton.setLayoutY(container.getLayoutY() + 40);
        
        deleteUserButton.setStyle( 
            "-fx-background-color: #800000;" + 
            "-fx-text-fill: white;" +           
            "-fx-font-size: 11px;" +            
            "-fx-font-family: 'Arial';" );
        
        
         editUsrBtn.setStyle( 
            "-fx-background-color: #FFC300;" + 
            "-fx-text-fill: white;" +           
            "-fx-font-size: 11px;" +            
            "-fx-font-family: 'Arial';" );
        

        editUsrBtn.setOnAction(e -> {
            UsrEditWindow(obj);
        });

        
        deleteUserButton.setOnAction(e->{
        
            
        });
      

        container.setPadding(new Insets(2));
        container.getChildren().addAll(RectangleContainer, userLabel,userTypeLabel, editUsrBtn,deleteUserButton,userEmailLabel,phoneNumberLabel,userIDLabel);
    }
    
    
}