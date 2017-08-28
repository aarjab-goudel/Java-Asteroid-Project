import sofia.micro.*;

// -------------------------------------------------------------------------
/**
 *  Tests the Space class
 *  Makes sure all the methods behave accordingly
 *  Tests the constructor and the populate method
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.04.05)
 */
public class SpaceTest extends TestCase
{
    //~ Fields ................................................................


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new SpaceTest test object.
     */
    public SpaceTest()
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
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * Tests the default constructor
     * As well as the populate method
     * 
     */
    public void testSpace()
    {
        Space space = new Space();
        assertEquals(1, space.getObjects(Ship.class).size());
        assertEquals(5, space.getObjects(Asteroid.class).size());
        assertEquals(500, space.getWidth());
        assertEquals(500, space.getHeight());
    }

}
