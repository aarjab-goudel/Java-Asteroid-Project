import sofia.micro.*;
import sofia.graphics.*;
//-------------------------------------------------------------------------
/**
 *  This is a subclass of Asteroid
 *  This object is created when the Asteroid object is removed from the world
 *  
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.05.17)
 */
public class SmallAsteroid extends Asteroid
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new AsteroidOne object.
     */
    public SmallAsteroid(int s, double r)
    {
        /*# Do any work to initialize your class here. */
        super(s, r);
    }


    //~ Methods ...............................................................
    /**
     * Executes the following methods everytime act is called
     */
    public void act()
    {
        int spe = this.getSpeed();
        this.wrapAround();
        this.move(spe);
        this.removeSmallGameObjects();
    }
    
     /**
     * Removes the ship, bullet, and the smaller asteroid if any of these objects interact
     */
    public void removeSmallGameObjects()
    {
        if ((this.getOneIntersectingObject(ShipBullet.class) != null) ||
            (this.getOneIntersectingObject(Ship.class) != null)
            || (this.getOneIntersectingObject(UFO.class) != null) ||
            (this.getOneIntersectingObject(UFOBullet.class) != null))
        {
            Space thisSpace = (Space) (this.getWorld());
            if (this.getOneIntersectingObject(ShipBullet.class) != null)
            {
                
                thisSpace.incrementScore();
                TextShape change = thisSpace.getText();
                change.setText(String.valueOf(thisSpace.getScore()));
                thisSpace.add(thisSpace.getFirst(), 35, 8);
                thisSpace.add(change, 75, 8);
                ShipBullet bull = this.getOneIntersectingObject(ShipBullet.class);
                this.getWorld().remove(bull);
                this.getWorld().remove(this);
            }
            else if (this.getOneIntersectingObject(Ship.class) != null)
            {
                Ship ship = this.getOneIntersectingObject(Ship.class);
                int x = ship.getGridX();
                int y = ship.getGridY();
                if (ship.getLives() == 3)
                {
                    thisSpace.getTextLives().setText("Life:  |  |");
                    ship.hurtShip();
                                        
                }
                else if (ship.getLives() == 2)
                {
                    thisSpace.getTextLives().setText("Life:  |");
                    ship.hurtShip();
                
                }
                else if (ship.getLives() == 1)
                {
                    thisSpace.getTextLives().setText("Life: ");
                    ship.hurtShip();
                    thisSpace.remove(ship);
                    this.gameOver();
                    
                }
                this.getWorld().remove(this);
               
            }
            else if (this.getOneIntersectingObject(UFO.class) != null)
            {
                UFO interUFO = this.getOneIntersectingObject(UFO.class);
                this.getWorld().remove(interUFO);
                this.getWorld().remove(this);
            }
            else if (this.getOneIntersectingObject(UFOBullet.class) != null)
            {
                UFOBullet bull = this.getOneIntersectingObject(UFOBullet.class);
                this.getWorld().remove(bull);
                this.getWorld().remove(this);
            }
           
        }
    }

}
