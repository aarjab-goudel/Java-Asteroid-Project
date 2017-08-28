import sofia.micro.*;
import java.util.*;
import sofia.graphics.*;
//-------------------------------------------------------------------------
/**
 *  A subclass of GameObjects
 *  Also shoots a bullet to destroy the ship
 *  
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.05.17)
 */
public class UFO extends GameObjects
{
    //~ Fields ................................................................
    private int bullet = 0;
    

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new UFO object.
     */
    public UFO(int s, double r)
    {
        this.setSpeed(s);
        this.setRotation(r);
    }


    //~ Methods ...............................................................
    /**
     * Executes the following methods after each time the act method is called
     */
    public void act()
    {
        this.wrapAround();
        this.moveAndShoot();
        this.touchShipOrBullet();
    }
    
    /**
     * Moves towards the Ship and tries to shoot it
     * 
     */
    public void moveAndShoot()
    {
        Ship currentShip = new Ship();
        Space currentSpace = (Space)(this.getWorld());
        if (currentSpace.getObjects(Ship.class).size() != 0)
        {
            List<Ship> allShips = currentSpace.getObjects(Ship.class);
            currentShip = allShips.get(0);
            this.turnTowards(currentShip);
            this.move(this.getSpeed());
            this.shootBullet();
        }
        
    }
    
    /**
     * Returns the int variable, bullet
     * 
     */
    public int getBullet()
    {
        return bullet;
    }
    
    /**
     * Increments the bullet variable. Also determines when the UFO will shoot
     */
    public void shootBullet()
    {
        bullet = this.getBullet() + 1;
        if (bullet == 20)
        {
            bullet = 0;
            UFOBullet bull = new UFOBullet(10, this.getRotation());
            this.getWorld().add(bull, this.getGridX(), this.getGridY());
        }
    }
    
    /**
     * Removes itself if it is hit by a bullet
     * Also increments the score if this object was hit by a ShipBullet
     */
    public void touchShipOrBullet()
    {
        if ((this.getOneIntersectingObject(ShipBullet.class) != null) ||
            (this.getOneIntersectingObject(Ship.class) != null))
        {
            Space thisS = (Space)(this.getWorld());
            if (this.getOneIntersectingObject(ShipBullet.class) != null)
            {
                thisS.incrementScore();
                TextShape change = thisS.getText();
                change.setText(String.valueOf(thisS.getScore()));
                thisS.add(thisS.getFirst(), 35, 8);
                thisS.add(change, 71, 8);
                ShipBullet bull = this.getOneIntersectingObject(ShipBullet.class);
                thisS.remove(bull);
                thisS.remove(this);
            }
            else if (this.getOneIntersectingObject(Ship.class) != null)
            {
                Ship ship = this.getOneIntersectingObject(Ship.class);
                if (ship.getLives() == 3)
                {
                    thisS.getTextLives().setText("Life:  |  |");
                    ship.hurtShip();
                                    
                }
                else if (ship.getLives() == 2)
                {
                    thisS.getTextLives().setText("Life:  |");
                    ship.hurtShip();
                    
                }
                else if (ship.getLives() == 1)
                {
                    thisS.getTextLives().setText("Life: ");
                    ship.hurtShip();
                    thisS.remove(ship);
                    this.gameOver();
                    
                }
                thisS.remove(this); 
            }
        }
    }

}
