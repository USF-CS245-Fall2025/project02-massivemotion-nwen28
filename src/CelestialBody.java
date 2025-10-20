import java.awt.Color;

/** CelestialBody class takes in x and y coordinates, x and y velocities,
 * size, and color of a celestial object and stores all that information
 * into a CelestialBody object
 */
public class CelestialBody {
    public int x;
    public int y;
    public double velocityX;
    public double velocityY;
    public int size;
    public Color color;

    //Constructor for CelestialBody
    public CelestialBody(int x, int y, double velocityX, double velocityY, int size, Color color){
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.size = size;
        this.color = color;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public double getVelX(){
        return velocityX;
    }

    public double getVelY(){
        return velocityY;
    }
    
    public int getSize(){
        return size;
    }

    public Color getColor(){
        return color;
    }

    public void setX(int newX){
        this.x = newX;
    }

    public void setY(int newY){
        this.y = newY;
    }
}
