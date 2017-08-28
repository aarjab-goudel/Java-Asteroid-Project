import sofia.micro.*;
import sofia.graphics.*;
import sofia.util.Random;
//-------------------------------------------------------------------------
/**
 *  A subclass of GameObjects
 *  The user controls the ship and destroys asteroids
 *  Shoots a bullet to destroy the asteroids
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.04.29)
 */
public class Ship extends GameObjects
{
    //~ Fields ................................................................
    private int numLives = 3;
    private int mediumWait;
    private int randomMediumWait = 0;
    private boolean checkMediumWait = true;
    private int bigWait = 0;
    private boolean checkBigAsteroid = true;
    private int addBigTime = 0;
    private boolean checkUFO = true;
    private int addUFOTime = 0;
    private int UFOWait = 0;
    private int addLife = 0;
    private int randomLife = 0;
    private boolean checkLife = true;
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Ship object.
     */
    public Ship()
    {
        /*# Do any work to initialize your class here. */
        this.setSpeed(0);
        this.setRotation(270);
        mediumWait = 0;
        bigWait = 0;
    }


    //~ Methods ...............................................................
    /**
     * Makes the ship go faster when pressing up
     * Accelerates the speed by 1
     */
    public void dpadNorthIsDown()
    {
        if (this.getSpeed() < 21)
        {
            this.setSpeed(this.getSpeed() + 2);
        }
        
    }
    
    /**
     * Makes the ship go slower when pressing down
     * Deaccelerate the speed by 1
     * The speed can never be less than 1 
     */
    public void dpadSouthIsDown()
    {
        if (this.getSpeed() > 0)
        {
            this.setSpeed(this.getSpeed() - 1); 
        }
       
    }
    
    /**
     * Turns 5 degrees to the right 
     * 
     */
    public void dpadEastIsDown()
    {
        this.turn(25);
    }
    
    /**
     * Turns 5 degrees to the left
     */
    public void dpadWestIsDown()
    {
        this.turn(-25);
    }
    
    /**
     * The ship shoots the bullet
     * Only shoots if the screen is touched/tapped
     * The bullet starts from the same position of ship
     */
    public void onScreenTouchDown()
    {
        ShipBullet bull = new ShipBullet(22, this.getRotation());
        Space thisSpace = (Space)(this.getWorld());
        if (thisSpace.countBullets() <= 4)
        {
            this.getWorld().add(bull, 
                this.getGridX(), this.getGridY());
        }
    }
    
    /**
     * Executes the following methods 
     * Executes after act is called
     */
    public void act()
    {
        this.mutateMediumWait();
        this.mutateBigWait();
        this.mutateUFOTime();
        this.mutateLife();
        this.move(this.getSpeed());
        this.resetRotation();
        this.wrapAround();
        if (this.getSpeed() > 0)
        {
            this.setSpeed(this.getSpeed() - 1);
        }
        this.addMediumAsteroid();
        this.addBigAsteroid();
        this.addUFO();
        this.addLife();
        this.touchUFOBullet();
        
    }
    
    /**
     * Checks if rotation is greater than 360
     * Checks if rotation is less than 0
     * If rotation is greater than 360, subtracts 360
     * If rotation is less than 0, adds 360
     */
    public void resetRotation()
    {
        double rot = this.getRotation(); 
        if (rot >= 360)
        {
            this.setRotation(rot - 360);
        }
        if (rot <= 0)
        {
            this.setRotation(rot + 360);
        }
    }
    
    /**
     * returns the number of lives this ship has
     */
    public int getLives()
    {
        return numLives;
    }
    
    /**
     * Adds a life to shipLives
     */
    public void addOneLife()
    {
        numLives = this.getLives() + 1;
    }
    
    /**
     * Returns the life variable
     */
    public int getAddLife()
    {
        return addLife;
    }
    
    /**
     * Decreases the life of the ship by 1
     * if the ship collides with an asteroid
     */
    public void hurtShip()
    {
        numLives = this.getLives() - 1;
    }
    
    /**
     * Returns the variable mediumWait to use for the space class
     */
    public int getMediumWait()
    {
        return mediumWait;
    }
    
    /**
     * Returns the variable largeWait 
     */
    public int getBigWait()
    {
        return bigWait;
    }
    
    /**
     * Increments wait once every time the act() method is called
     * Resets the wait variable once it reaches 75
     */
    public void mutateMediumWait()
    {
        if (checkMediumWait == true)
        {
            randomMediumWait = 66 + ((int)(Math.random() * (80 - 66)));
            checkMediumWait = false;
        }
        mediumWait = this.getMediumWait() + 1;
        if (this.getMediumWait() > randomMediumWait)
        {
            mediumWait = 0;
            checkMediumWait = true;
        }
    }
    
    /**
     * Increments bigWait once every time the act() method is called
     * Resets the mediumWait variable once it reaches 630
     */
    public void mutateBigWait()
    {
        if (checkBigAsteroid == true)
        {
            addBigTime = 730 + ((int)(Math.random() * (810 - 730)));
            checkBigAsteroid = false;
        }
        bigWait = this.getBigWait() + 1;
        if (this.getBigWait() > addBigTime)
        {
            bigWait = 0;
            checkBigAsteroid = true;
        }
    }
    
    /**
     * Increments the addLife by 1
     * Also sets a random number to randomLife if checkLife is true
     */
    public void mutateLife()
    {
        if (checkLife == true)
        {
            randomLife = 1420 + ((int)(Math.random() * (1960 - 1420)));
            checkLife = false;
        }
        addLife = this.getAddLife() + 1;
        if (this.getAddLife() > randomLife)
        {
            addLife = 0;
            checkLife = true;
        }
    }
    
    /**
     * Adds the Life Object to the world
     */
    public void addLife()
    {
        if (this.getAddLife() == randomLife)
        {
            int random = ((int)(Math.random() * 2)) + 1;
            int x = 5;
            int y = 5;
            int rotation = 0;
            Space thisS = (Space)(this.getWorld());
            if (random == 1)
            {
                x = 5;
                y = 5 + ((int)(Math.random() * (495 - 5)));
                rotation = 0;
            }
            else if (random == 2)
            {
                x = 495;
                y = y = 5 + ((int)(Math.random() * (495 - 5)));
                rotation = 180;
            }
            thisS.add(new Life(rotation), x, y);
            
        }
    }
    
    /**
     * Once the variable mediumWait reaches 50 or more, an asteroid is added to the world
     */
    public void addMediumAsteroid()
    {
        if (this.getMediumWait() == randomMediumWait)
        {
            Space thisS = (Space)(this.getWorld());
            int x = this.getGridX();
            int y = this.getGridY();
            int chanceX = (int)(Math.random() * 2) + 1;
            int chanceY = (int)(Math.random() * 2) + 1;
            int randomX = 10000;
            int randomY = 10000;
            if (chanceX == 1)
            {
                if (x >= 400)
                {
                    randomX =((int)((Math.random() * ((x - 100)))));
                }
                else
                {
                    randomX = (x + 100) + ((int)((Math.random() * (500 - (x + 100)))));
                }
            }
            else if (chanceX == 2)
            {
                if (x <= 100)
                {
                    randomX = (x + 100) + ((int)(Math.random() * (500 - (x + 100))));
                }
                else
                {
                    randomX = (int)(Math.random() * (x - 100));
                }
            }
            if (chanceY == 1)
            {
                if (y >= 400)
                {
                    randomY =((int)((Math.random() * ((y - 100)))));
                }
                else
                {
                    randomY = (y + 100) + ((int)((Math.random() * (500 - (y + 100)))));
                }   
            }
            else if (chanceY == 2)
            {
                if (y <= 100)
                {
                    randomY = (y + 100) + ((int)(Math.random() * (500 - (y + 100))));
                }
                else
                {
                    randomY = (int)(Math.random() * (y - 100));
                }
            }
            thisS.add(new Asteroid((Random.generator().nextInt(5)) + 1, 
                Random.generator().nextInt(360)), randomX, randomY);
            
        }
       
    }
    
    /**
     * Once the variable bigWait reaches a certain number,the world adds a big asteroid
     */
    public void addBigAsteroid()
    {
        if (this.getBigWait() == addBigTime)
        {
            Space thisS = (Space)(this.getWorld());
            int x = this.getGridX();
            int y = this.getGridY();
            int chanceX = (int)(Math.random() * 2) + 1;
            int chanceY = (int)(Math.random() * 2) + 1;
            int randomX = 10000;
            int randomY = 10000;
            if (chanceX == 1)
            {
                if (x >= 400)
                {
                    randomX =((int)((Math.random() * ((x - 100)))));
                }
                else
                {
                    randomX = (x + 100) + ((int)((Math.random() * (500 - (x + 100)))));
                }
            }
            else if (chanceX == 2)
            {
                if (x <= 100)
                {
                    randomX = (x + 100) + ((int)(Math.random() * (500 - (x + 100))));
                }
                else
                {
                    randomX = (int)(Math.random() * (x - 100));
                }
            }
            if (chanceY == 1)
            {
                if (y >= 400)
                {
                    randomY =((int)((Math.random() * ((y - 100)))));
                }
                else
                {
                    randomY = (y + 100) + ((int)((Math.random() * (500 - (y + 100)))));
                }   
            }
            else if (chanceY == 2)
            {
                if (y <= 100)
                {
                    randomY = (y + 100) + ((int)(Math.random() * (500 - (y + 100))));
                }
                else
                {
                    randomY = (int)(Math.random() * (y - 100));
                }
            }
            thisS.add(new BigAsteroid((Random.generator().nextInt(2)) + 1, 
                Random.generator().nextInt(360)), randomX, randomY);
            
        }
       
        
    }
    
    /**
     * Returns the addUFOTime variable
     */
    public int getAddUFOTime()
    {
        return addUFOTime;
    }
    
     /**
     * Increments addUFOTime once every time the act() method is called
     * Resets the addUFOTime variable once it reaches a certain number
     */
    public void mutateUFOTime()
    {
        if (checkUFO == true)
        {
            UFOWait = 610 + ((int)(Math.random() * (610 - 510)));
            checkUFO = false;
        }
        addUFOTime = this.getAddUFOTime() + 1;
        if (addUFOTime > UFOWait)
        {
            addUFOTime = 0;
            checkUFO = true;
        }
    }
    
     /**
     * Once the variable addUFOTime is equal to UFOWait then the world adds a UFO
     */
    public void addUFO()
    {
        if (this.getAddUFOTime() == UFOWait)
        {
            Space thisS = (Space)(this.getWorld());
            int x = this.getGridX();
            int y = this.getGridY();
            int chanceX = (int)(Math.random() * 2) + 1;
            int chanceY = (int)(Math.random() * 2) + 1;
            int randomX = 10000;
            int randomY = 10000;
            if (chanceX == 1)
            {
                if (x >= 400)
                {
                    randomX =((int)((Math.random() * ((x - 100)))));
                }
                else
                {
                    randomX = (x + 100) + ((int)((Math.random() * (500 - (x + 100)))));
                }
            }
            else if (chanceX == 2)
            {
                if (x <= 100)
                {
                    randomX = (x + 100) + ((int)(Math.random() * (500 - (x + 100))));
                }
                else
                {
                    randomX = (int)(Math.random() * (x - 100));
                }
            }
            if (chanceY == 1)
            {
                if (y >= 400)
                {
                    randomY =((int)((Math.random() * ((y - 100)))));
                }
                else
                {
                    randomY = (y + 100) + ((int)((Math.random() * (500 - (y + 100)))));
                }   
            }
            else if (chanceY == 2)
            {
                if (y <= 100)
                {
                    randomY = (y + 100) + ((int)(Math.random() * (500 - (y + 100))));
                }
                else
                {
                    randomY = (int)(Math.random() * (y - 100));
                }
            }
            thisS.add(new UFO((Random.generator().nextInt(5)) + 2, 
                Random.generator().nextInt(360)), randomX, randomY);
            
        }
       
        
    }
    
    /**
     * Hurts the ship's life if touched by UFO's bullet
     */
    public void touchUFOBullet()
    {
        if (this.getOneIntersectingObject(UFOBullet.class) != null)
        {
            Space thisS = (Space)(this.getWorld());
            UFOBullet bull = this.getOneIntersectingObject(UFOBullet.class);
            if (this.getLives() == 3)
            {
                thisS.getTextLives().setText("Life:  |  |");
                this.hurtShip();
                                    
            }
            else if (this.getLives() == 2)
            {
                thisS.getTextLives().setText("Life:  |");
                this.hurtShip();
                    
            }
            else if (this.getLives() == 1)
            {
                thisS.getTextLives().setText("Life: ");
                this.hurtShip();
                this.gameOver();
                thisS.remove(this);
                
                    
            }
            thisS.remove(bull);
        }
    }
    
    /**
     * 
     */
  
    
  
    
}
