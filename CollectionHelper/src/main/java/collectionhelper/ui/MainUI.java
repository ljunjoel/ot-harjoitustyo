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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author joel
 */
public class MainUI extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
       
        Label instructionText = new Label("Please type your username");
        PasswordField usernameField = new PasswordField();
        Button loginButton = new Button("Login");
        Label errorMessage = new Label("");
        
        GridPane loginPane = new GridPane();

        loginPane.add(instructionText, 0, 0);
        loginPane.add(usernameField, 0, 1);
        loginPane.add(loginButton, 0, 2);
        loginPane.add(errorMessage, 0, 3);
        
        loginPane.setPrefSize(300, 180);
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.setPadding(new Insets(20, 20, 20, 20));
        
        Scene loginScene = new Scene(loginPane);
        
        Button logoutButton = new Button("Logout");
        Label tervetuloaTeksti = new Label("Welcome!");

        StackPane welcomePane = new StackPane();
        welcomePane.setPrefSize(300, 180);
        welcomePane.getChildren().add(tervetuloaTeksti);
        welcomePane.setAlignment(Pos.TOP_CENTER);
        welcomePane.getChildren().add(logoutButton);
        welcomePane.setAlignment(Pos.BOTTOM_CENTER);

        Scene welcomeScene = new Scene(welcomePane);
        
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
        
        stage.setScene(loginScene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(MainUI.class);
    }
}
