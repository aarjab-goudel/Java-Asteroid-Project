import sofia.micro.*;

// -------------------------------------------------------------------------
/**
 *  Tests the ship class
 *  Tests if the ship moves correctly
 *  Tests if the ship shoots its bullet correctly
 *  Tests if the ship turns correctly
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.04.05)
 */
public class ShipTest extends TestCase
{
    //~ Fields ................................................................
    private Space space;
    private Ship s;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new ShipTest test object.
     */
    public ShipTest()
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
        s = new Ship();
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Tests the dpadNorthIsDown method
     * Tests if speed is incremented by one
     * Also tests if the ship moves (speed) spaces
     */
    public void testDpadNorth()
    {
        space.add(s, 200, 200);
        s.dpadNorthIsDown();
        assertEquals(1, s.getSpeed());
        
    }
    
    /**
     * Tests the dpadSouthIsDown method
     * Tests if speed is decreased by one
     * Also tests if the ship moves (speed) spaces
     */
    public void testDpadSouth()
    {
        space.add(s, 200, 200);
        s.setSpeed(10);
        s.dpadSouthIsDown();
        assertEquals(9, s.getSpeed());
        s.setSpeed(0);
        s.dpadSouthIsDown();
        assertEquals(0, s.getSpeed());
       
    }
    
    /**
     * Tests if the ship turns right 
     * Only turns right when holding right key
     * Expected to turn 5 degrees to the right
     */
    public void testDpadEast()
    {
        space.add(s, 200, 200);
        s.dpadEastIsDown();
        assertEquals(275, s.getRotation(), 0.1);
       
    }
    
    
    /**
     * Tests if the ship turns left
     * Only turns left when holding left key
     * Expected to turn 5 degrees to the left
     */
    public void testDpadWest()
    {
        space.add(s, 200, 200);
        s.dpadWestIsDown();
        assertEquals(265, s.getRotation(), 0.1);
       
    }
    
    /**
     * Tests when the bullet is shot
     * Tests if there is a bullet in the space
     * Tests the speed of the bullet
     * Tests the rotation of the bullet
     */
    public void testOnScreenTouchDown()
    {
        space.add(s, 200, 200);
        s.onScreenTouchDown();
        this.run(space, 10);
        assertEquals(1, space.getObjects(ShipBullet.class).size());
        ShipBullet bull = space.getObjects(ShipBullet.class).get(0);
        assertEquals(s.getRotation(), bull.getRotation(), 0.1);
        assertEquals(10, bull.getSpeed());
    }
    
    /**
     * Tests the resetRotation method
     * If rotation is greater than 360, subtracts 360 to rotation
     * If rotation is less than 0, adds 360 to rotation
     */
    public void testResetRotation()
    {
        space.add(s, 200, 200);
        s.setRotation(400);
        s.resetRotation();
        assertEquals(40, s.getRotation(), 0.1);
        s.setRotation(360);
        s.resetRotation();
        assertEquals(0, s.getRotation(), 0.1);
        s.setRotation(40);
        s.resetRotation();
        assertEquals(40, s.getRotation(), 0.1);
        s.setRotation(0);
        s.resetRotation();
        assertEquals(360, s.getRotation(), 0.1);
        s.setRotation(1);
        s.resetRotation();
        assertEquals(1, s.getRotation(), 0.1);
        s.setRotation(-10);
        s.resetRotation();
        assertEquals(350, s.getRotation(), 0.1);
        
    }
    
  


}
