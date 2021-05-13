/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainTests;

import collectionhelper.domain.CollectionHelperService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author joel
 */
public class CollectionHelperServiceTest {
    CollectionHelperService service;
    FakeCollectibleDao fakeCDao;
    FakeUserDao fakeUDao;
    List<String> names;
    public CollectionHelperServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.fakeCDao = new FakeCollectibleDao();
        this.fakeUDao = new FakeUserDao();
        this.service = new CollectionHelperService(fakeCDao, fakeUDao);
        String sqlDropUsers = "DROP TABLE IF EXISTS Users;";
        String sqlDropCollection = "DROP TABLE IF EXISTS Collection;";
        String sqlUsers = "CREATE TABLE Users (\n"
                + " Username Varchar NOT NULL, \n"
                + " UserPassword Varchar NOT NULL, \n"
                + " PRIMARY KEY (Username), \n"
                + "UNIQUE(Username)\n"
                + ");";
        String sqlCollection = "CREATE TABLE Collection (\n"
                + " CollectibleName Varchar NOT NULL, \n"
                + " CollectibleQuantity integer, \n"
                + " CollectibleUser Varchar NOT NULL, \n"
                + "PRIMARY KEY (CollectibleName), \n"
                + "FOREIGN KEY (CollectibleUser) REFERENCES Users(Username)\n"
                +");";
        try (Connection db = DriverManager.getConnection("jdbc:sqlite:test.db"); 
                Statement stmt = db.createStatement()) {
            if(db != null) {
                DatabaseMetaData meta = db.getMetaData();
                System.out.println("Driver name is: "+meta.getDriverName());
            }
            stmt.execute(sqlDropUsers);
            stmt.execute(sqlDropCollection);
            stmt.execute(sqlUsers);
            stmt.execute(sqlCollection);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.service.createUser("Mutsi", "mutsi1");
        try {
            this.names = this.service.getNames();
        } catch (Exception e) {
            System.out.println("Your test setup is broken");
        }
    }
    
    @After
    public void tearDown() {
        
    }
    
    public void getNames() {
        try {
            this.names = this.service.getNames();
        } catch (Exception e) {
            System.out.println("Something's wonky");
        }
    }
    
    @Test
    public void mutsiWasCreated() {
        assertEquals("Mutsi", names.get(0));
    }
    
    @Test
    public void newUserCanBeCreated() {
        this.service.createUser("Faija", "faija1");
        getNames();
        assertEquals("Faija", names.get(1));
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
