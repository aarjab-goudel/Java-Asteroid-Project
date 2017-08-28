import sofia.micro.*;
import sofia.util.Random;
import sofia.graphics.*;
import java.util.*;
//-------------------------------------------------------------------------
/**
 *  A subclass of World
 *  The place where the game occurs
 *  Sets the size of the world and populates it
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.04.29)
 */
public class Space extends World
{
    //~ Fields ................................................................
    private TextShape text;
    private TextShape lives;
    private int score;
    private TextShape first;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Space object.
     * @param       width is the max y coordinate of the world
     * @param       height is the max x coordinate of the world
     */
    public Space(int width, int height)
    {
        // Nothing to initialize, leaving the world a default size
        super(width, height, 1);
        
    }
    
    /**
     * Default constructor for space
     * Creates a new Space object 
     * Creates a 500 x 500 size space object
     */
    public Space()
    {
        super(500, 500, 1);
        this.populate();
        text = new TextShape("Score: " + String.valueOf(score));
        this.add(text, 35, 8);
        text.setTypeSize(20);
        text.setColor(Color.white);
        TextShape author = new TextShape("By: Aarjab Goudel");
        this.add(author, 250, 10);
        author.setTypeSize(25);
        author.setColor(Color.white);
        lives = new TextShape("Life:  |  |  |");
        this.add(lives, 430, 8);
        lives.setTypeSize(20);
        lives.setColor(Color.white);
        first = new TextShape("Score: ");
        first.setTypeSize(20);
        first.setColor(Color.white);
        
    }


    //~ Methods ...............................................................
    /**
     * Adds a ship to the center of space
     * Randomly places 5 asteroids
     * Makes sure the asteroids are not too near the ship
     * 
     */
    public void populate()
    {
        this.add(new Ship(), this.getWidth() / 2,
            this.getHeight() / 2);
        int x = 0;
        int y = 0;
        int h = this.getHeight();
        int w = this.getWidth();
        for (int i = 0; i < 5; i++)
        {
            int randomX = Random.generator().nextInt(2);
            int randomY = Random.generator().nextInt(2);
            if (randomX == 0)
            {
                x = (int)(Math.random() * ((w / 2) - 75)); 
            
            }
            else if (randomX == 1)
            {
                x = ((w / 2) + 75) + ((int)(Math.random() * ((w - 1) - ((w / 2) + 75))));
            }
            if (randomY == 0)
            {
                y = (int)(Math.random() * ((h / 2) - 75));
            }
            else if (randomY == 1)
            {
                 y = ((h / 2) + 75) + ((int)(Math.random() * ((h - 1) - ((h / 2) + 75))));
            }
            this.add(new Asteroid((Random.generator().nextInt(5)) + 1,
                Random.generator().nextInt(360)), x, y);
        }
        
        
    }
    
    /**
     * Gets the score of the game
     */
    public int getScore()
    {
        return score;
    }
    
    /**
     * Increments the score when an asteroid is destroyed
     */
    public void incrementScore()
    {
        score = score + 1;
    }
    
    /**
     * Returns the textShape for the score
     */
    public TextShape getText()
    {
        return text;
    }
    /**
     * Returns the number of bullets in Space
     */
    public int countBullets()
    {
        int bulletSize = this.getObjects(ShipBullet.class).size();
        return bulletSize;
    }
    /**
     * Returns the TextShape for the lives of the ship
     */
    public TextShape getTextLives()
    {
        return lives;
    }
    
    /**
     * Returns the "Score" TextShape
     */
    public TextShape getFirst()
    {
        return first;
    }
    
  
   
}
