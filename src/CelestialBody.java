import java.awt.Color;

public class CelestialBody {
    public int x;
    public int y;
    public double velocityX;
    public double velocityY;
    public int size;
    public Color color;

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
