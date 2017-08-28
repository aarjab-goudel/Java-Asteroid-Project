import sofia.micro.*;
import java.util.*;
//-------------------------------------------------------------------------
/**
 *  A subclass of GameObjects
 *  Adds an extra life to shit ONLY if it has below three lives
 *  The ship has to either touch this object or the ship's bullet can also touch it
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.05.18)
 */
public class Life extends GameObjects
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Life object.
     */
    public Life(double r)
    {
        this.setSpeed(4);
        this.setRotation(r);
    }


    //~ Methods ...............................................................
    /**
     * Executes the following methods when act is called
     */
    public void act()
    {
        this.removeEdge();
        this.touchBulletOrShip();
        this.move(this.getSpeed());
    }
    
    /**
     * Increases the life of the ship when touched by the ship or when the bullet hits the ship
     */
    public void touchBulletOrShip()
    {
        if ((this.getOneIntersectingObject(Ship.class) != null) ||
            (this.getOneIntersectingObject(ShipBullet.class) != null))
        {
            Space thisS = (Space)(this.getWorld());
            List<Ship> allShips = thisS.getObjects(Ship.class);
            Ship ship = allShips.get(0);
            if (ship.getLives() == 2)
            {
                ship.addOneLife();
                thisS.getTextLives().setText("Life:  |  |  |");
            }
            else if (ship.getLives() == 1)
            {
                ship.addOneLife();
                thisS.getTextLives().setText("Life:  |  |");
            }
            thisS.remove(this);
                
            
        }
    }

}
