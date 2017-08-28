import sofia.micro.*;
import sofia.graphics.*;
//-------------------------------------------------------------------------
/**
 *  A parent class for the objects in the game
 *  Allows for better organization of program
 *  Is not direcrtly used in the program
 *
 *  @author Aarjab Goudel (aarjab)
 *  @version (2016.04.29)
 */
public class GameObjects extends Actor
{
    //~ Fields ................................................................
    private int speed;
   


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new GameObjects object.
     */
    public GameObjects()
    {
        /*# Do any work to initialize your class here. */
    }


    //~ Methods ...............................................................
    /**
     * @return      Returns the speed of the gameObject
     */
    public int getSpeed()
    {
        return speed;
    }
    
    /**
     * Sets the speed for the gameObject
     * @param   s is the speed to be set
     */
    public void setSpeed(int s)
    {
        speed = s;
    }
    
     /**
     * Checks when to wrap around the world
     * Wraps around the world when it should
     */
    public void wrapAround()
    {
        int x = this.getWorld().getWidth() - 1;
        int y = this.getWorld().getHeight() - 1;
        int myX = this.getGridX();
        int myY = this.getGridY();
        double r = this.getRotation();
        if (myX == 0 && (r > 90 && r < 270))
        {
            this.setGridLocation(x, myY);   
        }
        else if (myY == 0 && (r > 180 && r < 360))
        {
            this.setGridLocation(myX, y);
        }
        else if (myY == y && (r > 0 && r < 180))
        {
            this.setGridLocation(myX, 0);
        }
        else if (myX == x && (r > 270 || r < 90))
        {
            this.setGridLocation(0, myY);
        }
       
    }
    
     /**
     * Displays "Game Over" as well as the score that the user recieved
     */
    public void gameOver()
    {
        Space thisS = (Space)(this.getWorld());
        TextShape game = new TextShape("Game Over! ");
        TextShape finalScoreText = new TextShape("Score: ");
        TextShape finalScore = new TextShape(String.valueOf(thisS.getScore()));
        game.setTypeSize(35);
        game.setColor(Color.white);
        finalScoreText.setTypeSize(35);
        finalScoreText.setColor(Color.white);
        finalScore.setTypeSize(35);
        finalScore.setColor(Color.white);
        thisS.add(game, 255, 235);
        thisS.add(finalScoreText, 235, 270);
        thisS.add(finalScore, 305, 270);
    }
    
       /**
     * Removes itself if it reaches the edge
     * Does not wrap around the world
     */
    public void removeEdge()
    {
        int x = this.getWorld().getWidth() - 1;
        int y = this.getWorld().getHeight() - 1;
        int myX = this.getGridX();
        int myY = this.getGridY();
        World space = this.getWorld();
        if (myX == 0)
        {
            space.remove(this);
        }
        else if (myY == 0)
        {
            space.remove(this);
        }
        else if (myX == x)
        {
            space.remove(this);
        }
        else if (myY == y)
        {
            space.remove(this);
        }
    }

}
