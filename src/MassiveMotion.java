import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;
    //protected Timer tm = new Timer(delay, this);

    int windowSizeX;
    int windowSizeY;
    double genX;
    double genY;
    int bodyVelocity;
    int bodySize;


    List<CelestialBody> celestialBodies;

    // TODO: Consider removing the next two lines (coordinates for two balls)
    protected int x1, y1;
    protected int x2, y2;


    // public MassiveMotion(String propfile) {
    public MassiveMotion(String propfile) {
        // TODO: insert your code to read from configuration file here.

        Properties prop = new Properties();

        try{
            FileInputStream filename = new FileInputStream(propfile);
            prop.load(filename);
            filename.close();
        } catch (IOException e){
            System.out.println("Erorr happened");
        }

        int delay = Integer.parseInt(prop.getProperty("timer_delay"));

        windowSizeX = Integer.parseInt(prop.getProperty("window_size_x"));
        windowSizeY = Integer.parseInt(prop.getProperty("window_size_y"));

        genX = Double.parseDouble(prop.getProperty("gen_x"));
        genY = Double.parseDouble(prop.getProperty("gen_y"));

        bodySize = Integer.parseInt(prop.getProperty("body_size"));
        bodyVelocity = Integer.parseInt(prop.getProperty("body_velocity"));

        int starPosX = Integer.parseInt(prop.getProperty("star_position_x"));
        int starPosY = Integer.parseInt(prop.getProperty("star_position_y"));
        int starSize = Integer.parseInt(prop.getProperty("star_size"));
        int starVelocityX = Integer.parseInt(prop.getProperty("star_velocity_x"));
        int star_velocityY = Integer.parseInt(prop.getProperty("star_velocity_y"));

        String listType = prop.getProperty("list");
        System.out.println("Using a: " + listType);

        if(listType.equalsIgnoreCase("arraylist")){
            celestialBodies = new ArrayList<>();
        } else if(listType.equalsIgnoreCase("single")){
            celestialBodies = new LinkedList<>();
        } else if(listType.equalsIgnoreCase("double")){
            //Doubly LinkedList
        } else if(listType.equalsIgnoreCase("dummyhead")){
            //Dummy Head
        }

        CelestialBody star = new CelestialBody(starPosX, starPosY, starVelocityX, star_velocityY, starSize, Color.RED);
        celestialBodies.add(star);


        //Delay used to be 75 as default
        tm = new Timer(delay, this); // TODO: Replace the first argument with delay with value from config file.

        // TODO: Consider removing the next two lines (coordinates) for random starting locations.
        //x1 = 100; y1 = 50;
        //x2 = 200; y2 = 400;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.

        // TODO: Paint each ball. Here's how to paint two balls, one after the other:
        //g.setColor(Color.BLUE);
        //g.fillOval(x1, y1, 20, 20);

        //g.setColor(Color.RED);
        //g.fillOval(x2, y2, 20, 20);

        for(int i = 0; i < celestialBodies.size(); i++){
            g.setColor(celestialBodies.get(i).getColor());
            g.fillOval(celestialBodies.get(i).getX(), celestialBodies.get(i).getY(), celestialBodies.get(i).getSize(), celestialBodies.get(i).getSize());
        }

        // Recommend you leave the next line as is
        tm.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(Math.random() <= genX){
            boolean spawnLocation;
            int x = (int)(Math.random() * windowSizeX);
            int y;
            if(Math.random() <= 0.5){
                spawnLocation = true;
            } else{
                spawnLocation = false;
            }

            if(spawnLocation == true){
                y = 0;
            } else{
                y = windowSizeY;
            }

            int velX = 0;
            int velY = 0;

            while(velX == 0){
                velX = (int)(Math.random() * (bodyVelocity - (-bodyVelocity) + 1)) + (-bodyVelocity);
            }

            while(velY == 0){
                velY = (int)(Math.random() * (bodyVelocity - (-bodyVelocity) + 1)) + (-bodyVelocity);
            }
            
            CelestialBody newBody = new CelestialBody(x, y, velX, velY, bodySize, Color.BLACK);
            celestialBodies.add(newBody);
        }

        if(Math.random() <= genY){
            boolean spawnLocation;
            int y = (int)(Math.random() * windowSizeY);
            int x;
            if(Math.random() <= 0.5){
                spawnLocation = true;
            } else{
                spawnLocation = false;
            }

            if(spawnLocation == true){
                x = 0;
            } else{
                x = windowSizeX;
            }

            int velX = 0;
            int velY = 0;

            while(velX == 0){
                velX = (int)(Math.random() * (bodyVelocity - (-bodyVelocity) + 1)) + (-bodyVelocity);
            }

            while(velY == 0){
                velY = (int)(Math.random() * (bodyVelocity - (-bodyVelocity) + 1)) + (-bodyVelocity);
            }
            
            CelestialBody newBody = new CelestialBody(x, y, velX, velY, bodySize, Color.BLACK);
            celestialBodies.add(newBody);
        }

        for(int i = celestialBodies.size()-1; i >= 0; i--){
            CelestialBody body = celestialBodies.get(i);
            body.x += celestialBodies.get(i).getVelX();
            body.y += celestialBodies.get(i).getVelY();

            if(body.getX() > windowSizeX || body.getX() < 0 || body.getY() > windowSizeY || body.getY() < 0){
                celestialBodies.remove(i);
            }

        }

        // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");
        
        MassiveMotion mm = new MassiveMotion(args[0]);
        //MassiveMotion mm = new MassiveMotion();

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.windowSizeX, mm.windowSizeY); // TODO: Replace with the size from configuration!
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


//test to see if this works
