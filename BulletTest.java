import sofia.micro.*;

// -------------------------------------------------------------------------
/**
 *  Tests the bullet class
 *  Tests how the bullet should be removed
 *  Tests the act method of the Bullet class
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.05.04)
 */
public class BulletTest extends TestCase
{
    //~ Fields ................................................................
    private Space space;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new BulletTest test object.
     */
    public BulletTest()
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
        space = new Space(500, 500); 
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Tests the removeEdge() method
     * Bullet is removed when it reaches the corners
     * Also removes itsef when it touches the edge of the world
     */
    public void testRemoveEdge()
    {
        ShipBullet bull = new ShipBullet(10, 0);
        space.add(bull, 30, 30);
        bull.removeEdge();
        assertEquals(1, space.getObjects(ShipBullet.class).size());
        bull.setGridLocation(0, 5);
        bull.removeEdge();
        assertEquals(0, space.getObjects(ShipBullet.class).size());
        space.add(bull, 5, 0);
        bull.removeEdge();
        assertEquals(0, space.getObjects(ShipBullet.class).size());
        space.add(bull, 499, 5);
        bull.removeEdge();
        assertEquals(0, space.getObjects(ShipBullet.class).size());
        space.add(bull, 5, 499);
        bull.removeEdge();
        assertEquals(0, space.getObjects(ShipBullet.class).size());
        
    }
    
    /**
     * Tests the Act() method
     * Moves the bullet
     * Removes itself if it reaches the corner 
     * Also removes if it reaches the edge of the world
     */
    public void testAct()
    {
        ShipBullet bull = new ShipBullet(10, 0);
        space.add(bull, 300, 300);
        bull.act();
        assertEquals(1, space.getObjects(ShipBullet.class).size());
        assertEquals(310, bull.getGridX());
        assertEquals(300, bull.getGridY());
        this.run(space, 20);
        assertEquals(0, space.getObjects(ShipBullet.class).size());
    }

}
