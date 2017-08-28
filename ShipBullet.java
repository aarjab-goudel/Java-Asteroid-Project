import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  A subclass of GameObjects
 *  Simply follows a straight path after being shot
 *  Destroys an asteroid if it touches it
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.04.29)
 */
public class ShipBullet extends GameObjects
{
    //~ Fields ................................................................
   


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Bullet object.
     * @param    s is the speed of the bullet
     * @param    r is the rotation of the bullet
     */
    public ShipBullet(int s, double r)
    {
        /*# Do any work to initialize your class here. */
        this.setSpeed(s);
        this.setRotation(r);
    }


    //~ Methods ...............................................................
 
    
   
    
    /**
     * Executes the following methods
     * Executes the methods each time act is called
     */
    public void act()
    {
        this.removeEdge();
        this.move(this.getSpeed());
    }

}
