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
import collectionhelper.domain.Collection;

/**
 *
 * @author joel
 */
public class MainUI extends Application{
    Collection myCollection = new Collection();
    
    @Override
    public void start(Stage stage) throws Exception {
       
        Label instructionText = new Label("Please type your password");
        PasswordField usernameField = new PasswordField();
        Button loginButton = new Button("Login");
        Label errorMessage = new Label("");
        
        GridPane loginPane = new GridPane();

        loginPane.add(instructionText, 0, 0);
        loginPane.add(usernameField, 0, 1);
        loginPane.add(loginButton, 0, 2);
        loginPane.add(errorMessage, 0, 3);
        
        loginPane.setPrefSize(800, 600);
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.setPadding(new Insets(20, 20, 20, 20));
        
        Scene loginScene = new Scene(loginPane);
        
        Button logoutButton = new Button("Logout");
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
          if (!usernameField.getText().trim().equals("salasana")) {
              errorMessage.setText("There's no such user!");
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
            myCollection.printItem(name);
        });
        
        printAllButton.setOnAction ((event) -> {
            myCollection.printAll();
        });
        
        stage.setScene(loginScene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(MainUI.class);
    }
}
