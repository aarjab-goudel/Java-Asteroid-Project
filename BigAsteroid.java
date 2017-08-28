import sofia.micro.*;
import sofia.graphics.*;

//-------------------------------------------------------------------------
/**
 *  A subclass of Asteroid
 *  A bigger asteroid that breaks up into medium asteroids
 *  Occurs rarely
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.05.18)
 */
public class BigAsteroid extends Asteroid
{
    //~ Fields ................................................................



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new BigAsteroid object.
     */
    public BigAsteroid(int s, double r)
    {
        /*# Do any work to initialize your class here. */
        super(s, r);
    }


    //~ Methods ...............................................................
    /**
     * Removes itself if hit by an bullet or ship
     * Breaks up into middle size asteroids before removing itself
     */
    public void removeLargeGameObjects()
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
                this.addMediumAsteroids();
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
                this.addMediumAsteroids();
                this.getWorld().remove(this);
            }
            else if (this.getOneIntersectingObject(UFOBullet.class) != null)
            {
                UFOBullet bull = this.getOneIntersectingObject(UFOBullet.class);
                this.getWorld().remove(bull);
                this.addMediumAsteroids();
                this.getWorld().remove(this);
            }
           
        }
        
    }
    
    /**
     * Adds Medium sized asteroids after the big asteroid is removed from the world
     */
    public void addMediumAsteroids()
    {
        int x = this.getGridX();
        int y = this.getGridY();
        double r = this.getRotation();
        int randomPlus = (int)(Math.random() * 3) + 1;
        int s = (this.getSpeed() + randomPlus);
        Space thisS = (Space)(this.getWorld());
        thisS.add(new Asteroid(s, (r + 45)), x, y);
        thisS.add(new Asteroid(s, (r - 45)), x, y);
        
        
    }
    
    /**
     * Executes the following methods after each time act is called
     */
    public void act()
    {
        int spe = this.getSpeed();
        this.wrapAround();
        this.move(spe);
        this.removeLargeGameObjects();
    }

}
