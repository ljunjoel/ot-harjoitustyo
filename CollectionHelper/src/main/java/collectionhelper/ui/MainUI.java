/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import collectionhelper.domain.Collection;
import collectionhelper.domain.User;

/**
 *
 * @author joel
 */
public class MainUI extends Application{
    Collection myCollection = new Collection();
    User myUsers = new User();
    
    @Override
    public void start(Stage stage) throws Exception {
       
        Label instructionText = new Label("Please type your username and password");
        Text usernameTextLogin = new Text("Username: ");
        Text usernameTextCreation = new Text("Username: ");
        TextField usernameField = new TextField();
        Text passwordTextLogin = new Text("Password: ");
        Text passwordTextCreation = new Text("Password: ");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button logoutButton = new Button("Logout");
        Button goToCreationButton = new Button ("Create User!");
        Button cancelButton = new Button("Cancel");
        Label errorMessage = new Label("");
        
        /*errorMessage.setMinWidth(Region.USE_PREF_SIZE);
        errorMessage.setMaxWidth(Region.USE_PREF_SIZE);*/
        
        GridPane loginPane = new GridPane();

        loginPane.add(instructionText, 0, 0);
        loginPane.add(usernameTextLogin, 0, 1);
        loginPane.add(usernameField, 1, 1);
        loginPane.add(passwordTextLogin, 0, 2);
        loginPane.add(passwordField, 1, 2);
        loginPane.add(loginButton, 1, 3);
        loginPane.add(goToCreationButton, 2, 3);
        loginPane.add(errorMessage, 1, 0);
        
        loginPane.setPrefSize(800, 600);
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.setPadding(new Insets(20, 20, 20, 20));
        
        Scene loginScene = new Scene(loginPane);
        
        Label creationInstructionText = new Label("Please enter the username and password you would like to use.");
        TextField createUsernameText = new TextField("");
        PasswordField createPasswordField = new PasswordField();
        Button createUserButton = new Button("Create User!");
        
        GridPane createUserPane = new GridPane();
        
        createUserPane.add(creationInstructionText, 0, 0);
        createUserPane.add(usernameTextCreation, 0, 1);
        createUserPane.add(createUsernameText, 1, 1);
        createUserPane.add(passwordTextCreation, 0, 2);
        createUserPane.add(createPasswordField, 1, 2);
        createUserPane.add(createUserButton, 1, 3);
        createUserPane.add(errorMessage, 1, 0);
        createUserPane.add(cancelButton, 2, 3);
        
        createUserPane.setPrefSize(800, 600);
        createUserPane.setAlignment(Pos.CENTER);
        createUserPane.setVgap(10);
        createUserPane.setHgap(10);
        createUserPane.setPadding(new Insets(20, 20, 20, 20));
        
        Scene createUserScene = new Scene(createUserPane);
        
        Label welcomeText = new Label("Welcome!");
        TextField articleName = new TextField("");
        TextField articleQuantity = new TextField("");
        //Button for adding articles, not buttons
        Button addButton = new Button("Add");
        Button reduceButton = new Button ("Reduce");
        Button printAllButton = new Button ("Print Collection");
        Button printSomeButton = new Button ("Search");

        GridPane userPane = new GridPane();
        userPane.setPrefSize(800, 600);
        userPane.add(welcomeText, 0, 0);
        userPane.add(articleName, 0, 1);
        userPane.add(articleQuantity, 0, 2);
        userPane.add(logoutButton, 0, 3);
        userPane.add(errorMessage, 1, 0);
        userPane.add(addButton, 1, 1);
        userPane.add(reduceButton, 1, 2);
        userPane.add(printAllButton, 1, 3);
        userPane.add(printSomeButton, 1, 4);
        
        userPane.setAlignment(Pos.CENTER);
        userPane.setVgap(10);
        userPane.setHgap(10);
        userPane.setPadding(new Insets(20, 20, 20, 20));

        Scene welcomeScene = new Scene(userPane);
        
        loginButton.setOnAction((event) -> {
          String username = usernameField.getText().trim();
          String password = passwordField.getText().trim();
          if(!(myUsers.getUser(username))) {
              errorMessage.setText("There is no such user");
              return;
          }
          if(!(password.equals(myUsers.getPassword(username)))) {
              errorMessage.setText("Have you forgotten your password? Because that isn't it");
              return;
          }
          stage.setScene(welcomeScene);
        });
        
        logoutButton.setOnAction((event) -> {
            stage.setScene(loginScene);
        });
        
        addButton.setOnAction((event) -> {
            String name = articleName.getText().trim();
            String quantityString = articleQuantity.getText().trim();
            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
            } catch (NumberFormatException e) {
                errorMessage.setText("Quantity is not a number!");
                return;
            }
            
            if(name.equals("")) {
                errorMessage.setText("Name missing!");
                return;
            }
            
            myCollection.addItem(name, quantity);
            errorMessage.setText("Item "+ name + " increased by "+quantity);
        });
        
        reduceButton.setOnAction((event) -> {
            String name = articleName.getText().trim();
            String quantityString = articleQuantity.getText().trim();
            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
            } catch (NumberFormatException e) {
                errorMessage.setText("Quantity is not a number!");
                return;
            }
            
            if(name.equals("")) {
                errorMessage.setText("Name missing!");
                return;
            }
            myCollection.reduceItem(name, quantity);
            errorMessage.setText("Item "+ name + " reduced by "+quantity);
        });
        
        printSomeButton.setOnAction ((event) -> {
            String name = articleName.getText().trim();
            if(name.equals("")) {
                errorMessage.setText("Name missing!");
                return;
            }
            try {
                myCollection.printItem(name);
            } catch (NullPointerException e) {
                errorMessage.setText("That item is not listed yet!");
            }
            
        });
        
        printAllButton.setOnAction ((event) -> {
            myCollection.printAll();
        });
        
        createUserButton.setOnAction ((event) -> {
           String username = createUsernameText.getText().trim();
           String password = createPasswordField.getText().trim();
           if(!(username.length() > 2)) {
               errorMessage.setText("That username is too short");
               return;
           }
           if(!(password.length() > 2)) {
               errorMessage.setText("That password is way too short! Make it at least 3 characters long. C'mon!");
               return;
           }
           myUsers.addUser(username, password);
           stage.setScene(loginScene);
        });
        
        goToCreationButton.setOnAction ((event) -> {
            stage.setScene(createUserScene);
        });
        
        cancelButton.setOnAction((event) -> {
           stage.setScene(loginScene); 
        });
        
        stage.setScene(loginScene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(MainUI.class);
    }
}
