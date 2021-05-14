/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionhelper.ui;

import java.sql.*;
/**
 *
 * @author joel
 */
public class Main {
    public static void main(String[] args) {
        String sqlUsers = "CREATE TABLE IF NOT EXISTS Users (\n"
                + " Username Varchar NOT NULL, \n"
                + " UserPassword Varchar NOT NULL, \n"
                + " PRIMARY KEY (Username), \n"
                + " UNIQUE(Username)\n"
                + ");";
        String sqlCollection = "CREATE TABLE IF NOT EXISTS Collection (\n"
                + " CollectibleName Varchar NOT NULL, \n"
                + " CollectibleQuantity integer, \n"
                + " CollectibleUser Varchar NOT NULL, \n"
                + " FOREIGN KEY (CollectibleUser) REFERENCES Users(Username)\n"
                +");";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:collection.db"); 
                Statement stmt = conn.createStatement()) {
            if(conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Driver name is: "+meta.getDriverName());
            }
            stmt.execute(sqlUsers);
            stmt.execute(sqlCollection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        MainUI.main(args);
    }
}
