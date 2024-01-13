///Restored from local backup 
//Daniel 10/1 @12:06am /// ///
///KAKAKAKAKAKAA
//ff to match all beanches 

package houserental;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HouseRental extends Application{ 

    public static void main(String[] args) {
        
     User.DeserializeBinary();
     House.DeserializeHouse();
     Booking.DeserializeBooking();
//     House.getHouseList().add(new House(Category.apartment,View.beach,13,false,3,1,"",50,22,"Egypt","Alexandria","street",820,60,9));
//     House.getHouseList().add(new House(Category.apartment,View.city,3,false,5,3,"",95,65,"Egypt","Cairo","street",1810,19,4));
//     House.getHouseList().add(new House(Category.apartment,View.city,36,false,2,1,"",65,32,"Egypt","Cairo","street",1865,36,16));
//     House.getHouseList().add(new House(Category.apartment,View.rooftop,5,false,3,2,"smth",50,22,"Egypt","Ras-Sedr","street",8600,29,4));
//     House.getHouseList().add(new House(Category.apartment,View.city,3,false,5,2,"",75,65,"Egypt","Cairo","street",1810,19,4));
//     House.getHouseList().add(new House(Category.apartment,View.city,26,false,3,1,"",55,32,"Egypt","Cairo","street",1830,36,6));
//     House.getHouseList().add(new House(Category.apartment,View.beach,6,true,4,2,"smth",50,22,"Egypt","Cairo","street",1820,29,4));
//     House.SerializeHouse();
//     System.out.println("Created Houses and saved to files...");       
     launch(args);
        
    }

    @Override
    public void start(Stage primaryStage){
        showLogin(primaryStage);
    }
    public static void showSignUp(Stage primaryStage){
        GridPane SignUpForm = createSignUp(primaryStage);
        Scene scene = new Scene(SignUpForm, 500, 400);

        primaryStage.setTitle("SignUp Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void showLogin(Stage primaryStage){
        GridPane LoginForm = createLogin(primaryStage);
        Scene scene = new Scene(LoginForm, 500, 400);
        
        primaryStage.setTitle("Login Form");
        primaryStage.setScene(scene);
        primaryStage.show();       
    }
    public static GridPane createSignUp(Stage primaryStage){
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
        Label userTypeLabel = new Label ("Sign up as: ");
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
                    if(!User.isValidEmail(emailField.getText()))
                    {
                        Alert ad = new Alert(AlertType.ERROR);                       
                        ad.setHeaderText(null);
                        ad.setContentText("Invalid email, please try again");
                        ButtonType bt = new ButtonType("Ok");
                        ad.show();
                        
                    }
                    else if(((int) Double.parseDouble(ageField.getText()) < 18)){
                        Alert ad = new Alert(AlertType.ERROR);                       
                        ad.setHeaderText(null);
                        ad.setContentText("Invalid age, please try again");
                        ButtonType bt = new ButtonType("Ok");
                        ad.show();
                    }
                    else if(selectedUser.equalsIgnoreCase("admin" )&& (User.isValidEmail(emailField.getText())) && ((int) Double.parseDouble(ageField.getText()) > 18)){
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
                        Stage newWindow = new Stage();
                        showLogin(newWindow);
                        primaryStage.close();
                        }          
                    else if(selectedUser.equalsIgnoreCase("Renter")&&(User.isValidEmail(emailField.getText())) && ((int) Double.parseDouble(ageField.getText()) > 18)){
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
                            Stage newWindow = new Stage();
                            showLogin(newWindow);
                            primaryStage.close();
                            }
                    else if(selectedUser.equalsIgnoreCase("Receptionist")&& (User.isValidEmail(emailField.getText())) && ((int) Double.parseDouble(ageField.getText()) > 18)){
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
                            Stage newWindow = new Stage();
                            showLogin(newWindow);
                            primaryStage.close();
                            }
        });
        
         cancel.setOnAction(e -> {
            firstNameField.clear();
            lastNameField.clear();
            emailField.clear();
            phoneNumberField.clear();
            ageField.clear();
            usernameField.clear();
            passwordField.clear();
            userType.selectToggle(null);  
        });
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
    public static GridPane createLogin(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        
        Button login = new Button("Login");
        Button cancel = new Button("Cancel");
        Button signUp = new Button("Create new account");
        login.setOnAction(e->{
            
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();
            Admin adminUsr = new Admin();
            Receptionist receptionistUsr = new Receptionist();
            Renter renterUsr = new Renter();
            System.out.println("Attempting Login with credentials: " + enteredUsername + " and " +enteredPassword );
            if(adminUsr.login(enteredUsername, enteredPassword))
                {
                    System.out.println("logged in as admin");
                    adminUsr.showAdminMainScrn(primaryStage);
                }
            else if(receptionistUsr.login(enteredUsername, enteredPassword))
                {
                    Receptionist.ShowAccountMngScrn(primaryStage, receptionistUsr);
                }

            else if(renterUsr.login(enteredUsername, enteredPassword))
                {               
                    
                    System.out.println("logged in as renter: " + renterUsr.getUserName());
                    Renter.showRenterMainScrn(primaryStage, renterUsr);
                }
        });
        cancel.setOnAction(e->{
            usernameField.clear();
            passwordField.clear();
        });
        signUp.setOnAction(e->{
            Stage newWindow = new Stage();
            showSignUp(newWindow);
            primaryStage.close();
        });
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(login, 0, 2);
        grid.add(cancel,1,2);
        grid.add(signUp, 2, 2);
        return grid;
    }
}


///Last Edit 21/11 @9:42 ... /// 