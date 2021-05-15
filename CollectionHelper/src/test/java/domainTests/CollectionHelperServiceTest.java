/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainTests;

import collectionhelper.domain.Collectible;
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
 * Class for testing the functionality of the CollectionHelperService class.
 */
public class CollectionHelperServiceTest {
    CollectionHelperService service;
    FakeCollectibleDao fakeCDao;
    FakeUserDao fakeUDao;
    List<String> names;
    List<Collectible> items;
    
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
                + " UNIQUE(Username)\n"
                + ");";
        String sqlCollection = "CREATE TABLE Collection (\n"
                + " CollectibleName Varchar NOT NULL, \n"
                + " CollectibleQuantity integer, \n"
                + " CollectibleUser Varchar NOT NULL, \n"
                + " FOREIGN KEY (CollectibleUser) REFERENCES Users(Username)\n"
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
        this.service.createItem("Dude", 1, "Mutsi");
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
    
    public void getSearch(String search) {
        try {
            this.items = this.service.searchItems(search);
        } catch (Exception e) {
            System.out.println("Couldn't search items");
        }
    }
    
    public void addDudeFaijalle() {
        this.service.createUser("Faija", "faija1");
        this.service.createItem("Dude", 2, "Faija");
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
    @Test
    public void noLoginWithWrongPassword() {
        assertFalse(this.service.login("Mutsi", "Mutsi1"));
        assertEquals("", this.service.getLoggedIn());
    }
    
    @Test
    public void noLoginWithWrongUser() {
        assertFalse(this.service.login("Faija", "faija1"));
        assertEquals("", this.service.getLoggedIn());
    }
    
    @Test
    public void loginWorks1() {
        assertTrue(this.service.login("Mutsi", "mutsi1"));
    }
    
    @Test
    public void loginWorks2() {
        this.service.login("Mutsi", "mutsi1");
        assertEquals("Mutsi", this.service.getLoggedIn());
    }
    
    @Test
    public void dudeWasCreated() {
        this.service.login("Mutsi", "mutsi1");
        getSearch("Dude");
        assertEquals("Dude", this.items.get(0).getName());
    }
    
    @Test
    public void searchWorks() {
        addDudeFaijalle();
        this.service.login("Mutsi", "mutsi1");
        getSearch("ud");
        assertEquals("Dude", this.items.get(0).getName());
        assertEquals(1, this.items.get(0).getQuantity());
        assertEquals(1, this.items.size());
    }
    
    @Test
    public void searchIsCaseInsensitive() {
        addDudeFaijalle();
        this.service.login("Mutsi", "mutsi1");
        getSearch("du");
        assertEquals("Dude", this.items.get(0).getName());
        assertEquals(1, this.items.get(0).getQuantity());
        assertEquals(1, this.items.size());
    }
    
    @Test
    public void getAllItemsWorks() {
        addDudeFaijalle();
        this.service.login("Mutsi", "mutsi1");
        this.items = this.service.getAllItems();
        assertEquals("Dude", this.items.get(0).getName());
        assertEquals(1, this.items.get(0).getQuantity());
        assertEquals(1, this.items.size());
    }
    
    @Test
    public void addingWorks() {
        addDudeFaijalle();
        this.service.login("Mutsi", "mutsi1");
        this.service.addToItem(new Collectible("Dude", 1, "Mutsi"), 2);
        this.items = this.service.getAllItems();
        assertEquals("Dude", this.items.get(0).getName());
        assertEquals(3, this.items.get(0).getQuantity());
        assertEquals(1, this.items.size());
    }
    
    @Test
    public void reducingWorks() {
        addDudeFaijalle();
        this.service.login("Mutsi", "mutsi1");
        this.service.addToItem(new Collectible("Dude", 1, "Mutsi"), 2);
        this.service.reduceFromItem(new Collectible("Dude", 3, "Mutsi"), 1);
        this.items = this.service.getAllItems();
        assertEquals("Dude", this.items.get(0).getName());
        assertEquals(2, this.items.get(0).getQuantity());
        assertEquals(1, this.items.size());
    }
    
    @Test
    public void removingWorks() {
        this.service.login("Mutsi", "mutsi1");
        this.service.removeItem(new Collectible("Dude", 1, "Mutsi"));
        this.items = this.service.getAllItems();
        assertEquals(0, this.items.size());
    }
    @Test
    public void removingWorksCorrectly() {
        addDudeFaijalle();
        this.service.login("Faija", "faija1");
        this.service.removeItem(new Collectible("Dude", 1, "Mutsi"));
        this.items = this.service.getAllItems();
        assertEquals("Dude", this.items.get(0).getName());
        assertEquals(2, this.items.get(0).getQuantity());
        assertEquals(1, this.items.size());
    }
}
