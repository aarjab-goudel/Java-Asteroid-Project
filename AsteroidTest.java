import sofia.micro.*;

// -------------------------------------------------------------------------
/**
 *  Tests the asteroid class
 *  Tests to see if the asteroid implements all methods
 *  
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.04.04)
 */
public class AsteroidTest extends TestCase
{
    //~ Fields ................................................................
    private Space space;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new AsteroidTest test object.
     */
    public AsteroidTest()
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
     * Tests removeGameObjects() method
     * Checks to see if the asteroid and the bullet remove
     * Checks to see if the asteroid and the ship remove
     */
    public void testRemoveGameObjects()
    {
        Asteroid a = new Asteroid(4, 0);
        ShipBullet b = new ShipBullet(10, 0);
        Ship s = new Ship();
        space.add(a, 10, 10);
        space.add(b, 200, 200);
        space.add(s, 300, 300);
        a.removeGameObjects();
        this.run(space, 10);
        assertEquals(1, space.getObjects(Asteroid.class).size());
        assertEquals(1, space.getObjects(ShipBullet.class).size());
        assertEquals(1, space.getObjects(Ship.class).size());
        space.remove(a);
        space.remove(b);
        space.remove(s);
        space.add(a, 50, 60);
        space.add(b, 50, 50);
        a.removeGameObjects();
        this.run(space, 10);
        assertEquals(0, space.getObjects(Asteroid.class).size());
        assertEquals(0, space.getObjects(ShipBullet.class).size());
        space.add(s, 50, 50);
        space.add(a, 50, 30);
        a.removeGameObjects();
        this.run(space, 10);
        assertEquals(0, space.getObjects(Asteroid.class).size());
        assertEquals(0, space.getObjects(Ship.class).size());
        
        
    }
    
    /**
     * Tests the act method
     * Asteroid wraps around the world
     * The asteroid collides with ship afterwards
     */
    public void testAct()
    {
        Asteroid a = new Asteroid(4, 0);
        Ship s = new Ship();
        space.add(a, 490, 100);
        space.add(s, 20,  100);
        this.run(space, 10);
        assertEquals(0, space.getObjects(Asteroid.class).size());
        assertEquals(0, space.getObjects(Ship.class).size());
    }

}
