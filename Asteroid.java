import sofia.micro.*;
import sofia.graphics.*;
//-------------------------------------------------------------------------
/**
 *  A subclass of GameObjects
 *  Floats in a straight path
 *  Wraps around the world
 *  Is removed if it is in contact with a bullet
 *  Destroys the ship if it becomes in contact with it
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.04.29)
 */
public class Asteroid extends GameObjects
{
    //~ Fields ................................................................
   
    
    
    

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Asteroid object.
     * @param      s is the speed of the asteroid 
     * @param      r is the rotation of the asteroid
     */
    public Asteroid(int s, double r)
    {
        /*# Do any work to initialize your class here. */
       
        this.setSpeed(s);
        this.setRotation(r);
    }


    //~ Methods ...............................................................
    /**
     * Executes the following after act is called
     * Moves based on its speed
     * And wraps around the world if it can
     * Gets removed from the world if it hit by a bullet
     */
    public void act()
    {
       
        int spe = this.getSpeed();
        this.wrapAround();
        this.move(spe);
        this.removeGameObjects();
    }
    
    /**
     * Checks to see if the asteroid should be removed
     * It should remove itself if it gets hit by a bullet
     * Or if it collides with a ship
     * If it collides with a ship, the ship must lose a life
     */
    public void removeGameObjects()
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
                thisSpace.add(change, 71, 8);
                ShipBullet bull = this.getOneIntersectingObject(ShipBullet.class);
                this.getWorld().remove(bull);
                this.addSmallerAsteroids();
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
                this.addSmallerAsteroids();
                this.getWorld().remove(this);
            }
            else if (this.getOneIntersectingObject(UFOBullet.class) != null)
            {
                UFOBullet bull = this.getOneIntersectingObject(UFOBullet.class);
                this.getWorld().remove(bull);
                this.addSmallerAsteroids();
                this.getWorld().remove(this);
            }
            
           
        }
    }
    
   
    
    /**
     * Adds smaller asteroids to the world when the bigger one is shot by a bullet
     */
    public void addSmallerAsteroids()
    {
        int x = this.getGridX();
        int y = this.getGridY();
        double r = this.getRotation();
        int randomPlus = (int)(Math.random() * 3) + 1;
        int s = (this.getSpeed() + randomPlus);
        Space thisS = (Space)(this.getWorld());
        thisS.add(new SmallAsteroid(s, (r + 45)), x, y);
        thisS.add(new SmallAsteroid(s, (r - 45)), x, y);
        
    }
    
    
}
