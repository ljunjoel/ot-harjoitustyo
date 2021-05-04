/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainTests;

import collectionhelper.domain.Collection;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel
 */
public class CollectionTest {
    Collection collection;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    
    public CollectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        collection = new Collection();
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void addsNewProperly() {
        this.collection.addItem("Tatti", 3);
        this.collection.printAll();
        assertEquals("Name: Tatti Quantity: 3", outputStreamCaptor.toString().trim());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
