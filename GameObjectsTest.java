import sofia.micro.*;

// -------------------------------------------------------------------------
/**
 *  Tests all the methods of the GameObjects class
 *  Makes sure the class does what it is suppose to do
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.05.04)
 */
public class GameObjectsTest extends TestCase
{
    //~ Fields ................................................................
    private GameObjects obj;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new GameObjectsTest test object.
     */
    public GameObjectsTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        /*# Insert your own setup code here */
        
        obj = new GameObjects();
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Tests the get and set methods for speed
     * Expect to set a speed value
     * Expect to get the same value back
     */
    public void testGetAndSet()
    {
        obj.setSpeed(5);
        assertEquals(5, obj.getSpeed());
    }
    
    /**
     * Tests the WrapAround() method
     * Makes sure the object is transported depeding on location
     * Also depends on the rotation
     * Expect corresponding opposite location should be achieved
     */
    public void testWrapAround()
    {
        Space space = new Space(500, 500);
        space.add(obj, 0, 5);
        obj.setRotation(87);
        obj.wrapAround();
        assertEquals(0, obj.getGridX());
        assertEquals(5, obj.getGridY());
        obj.setRotation(94);
        obj.wrapAround();
        assertEquals(499, obj.getGridX());
        assertEquals(5, obj.getGridY());
        obj.setGridLocation(0, 5);
        obj.setRotation(300);
        obj.wrapAround();
        assertEquals(0, obj.getGridX());
        assertEquals(5, obj.getGridY());
        obj.setGridLocation(5, 0);
        obj.setRotation(94);
        obj.wrapAround();
        assertEquals(5, obj.getGridX());
        assertEquals(0, obj.getGridY());
        obj.setRotation(194);
        obj.wrapAround();
        assertEquals(5, obj.getGridX());
        assertEquals(499, obj.getGridY());
        obj.setRotation(400);
        obj.setGridLocation(0, 5);
        obj.wrapAround();
        assertEquals(0, obj.getGridX());
        assertEquals(5, obj.getGridY());
        obj.setGridLocation(40, 499);
        obj.setRotation(194);
        obj.wrapAround();
        assertEquals(40, obj.getGridX());
        assertEquals(499, obj.getGridY());
        obj.setRotation(56);
        obj.wrapAround();
        assertEquals(40, obj.getGridX());
        assertEquals(0, obj.getGridY());
        obj.setGridLocation(40, 499);
        obj.setRotation(-40);
        obj.wrapAround();
        assertEquals(40, obj.getGridX());
        assertEquals(499, obj.getGridY());
        obj.setGridLocation(499, 40);
        obj.wrapAround();
        obj.setRotation(56);
        assertEquals(0, obj.getGridX());
        assertEquals(40, obj.getGridY());
        obj.setGridLocation(499, 40);
        obj.setRotation(95);
        obj.wrapAround();
        assertEquals(499, obj.getGridX());
        assertEquals(40, obj.getGridY());
        obj.setRotation(300);
        obj.wrapAround();
        assertEquals(0, obj.getGridX());
        assertEquals(40, obj.getGridY());
        
    }
    

}
