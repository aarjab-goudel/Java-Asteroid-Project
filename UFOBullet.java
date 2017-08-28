import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  A subclass of Bullet
 *  Works the same way with the bullet of the ship
 *  Destroys asteroids and ships
 *
 *  @author Aarjab Goudel (aajab)
 *  @version (2016.05.18)
 */
public class UFOBullet extends GameObjects
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new UFOBullet object.
     */
    public UFOBullet(int s, double r)
    {
        this.setSpeed(s);
        this.setRotation(r);
    }


    //~ Methods ...............................................................
    /**
     * Executes the following methods when this method is called
     */
    public void act()
    {
        this.removeEdge();
        this.move(this.getSpeed());
        
    }

}
