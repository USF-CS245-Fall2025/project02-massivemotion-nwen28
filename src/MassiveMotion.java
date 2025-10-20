import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;

    int windowSizeX;
    int windowSizeY;
    double genX;
    double genY;
    int bodyVelocity;
    int bodySize;

    //Initializes unspecified list
    List<CelestialBody> celestialBodies;

    /** MassiveMotion method opens, loads, and reads the property file
     * and creates the star object from the file and determines what type of list
     * celestialBodies is
     * @param propfile takes in the name of the property file
     */
    public MassiveMotion(String propfile) {
        // TODO: insert your code to read from configuration file here.

        Properties prop = new Properties();

        //Opens property file and loads it
        try{
            FileInputStream filename = new FileInputStream(propfile);
            prop.load(filename);
            filename.close();
        } catch (IOException e){
            System.out.println("File Not Found");
        }

        //Saves all the following information after being read from the property file
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

        //Checks what type of list the property file specifies and changes the list
        // initialized at the top to the now specified type of list
        if(listType.equalsIgnoreCase("arraylist")){
            celestialBodies = new ArrayList<>();
        } else if(listType.equalsIgnoreCase("single")){
            celestialBodies = new LinkedList<>();
        } else if(listType.equalsIgnoreCase("double")){
            celestialBodies = new DoublyLinkedList<>();
        } else if(listType.equalsIgnoreCase("dummyhead")){
            celestialBodies = new DummyHeadLinkedList<>();
        }

        //Creates the star that was saved in the property file and adds it to the list of celestialBodies
        CelestialBody star = new CelestialBody(starPosX, starPosY, starVelocityX, star_velocityY, starSize, Color.RED);
        celestialBodies.add(star);


        //Delay used to be 75 as default
        tm = new Timer(delay, this); // TODO: Replace the first argument with delay with value from config file.

    }

    /** paintComponent method iterates through list of celestial objects
     *  and draws them on the canvas with the given information
     * @param graphics 
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.

        //Iterates through the list of celestial bodies and creates a celestia object with 
        // the given parameters and draws it on the canvas
        for(int i = 0; i < celestialBodies.size(); i++){
            g.setColor(celestialBodies.get(i).getColor());
            g.fillOval(celestialBodies.get(i).getX(), celestialBodies.get(i).getY(), celestialBodies.get(i).getSize(), celestialBodies.get(i).getSize());
        }

        // Recommend you leave the next line as is
        tm.start();
    }


    /** actionPerformed method uses the probability of a new celestial 
     * object being created and if it is created, adds the obect
     * to the celestialBodies list
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //Generates a random number and see if it falls within the genX chance of spawning
        if(Math.random() <= genX){

            //Boolean that keeps track of whether the object is going to spawn
            // on the top or bottom
            boolean spawnLocation;

            //Random x coordinate along the window size
            int x = (int)(Math.random() * windowSizeX);
            int y;

            //50/50 chance of creating the celesial object on the top or bottom of the screen
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

            //Sets the velocity at 0 at the start
            int velX = 0;
            int velY = 0;

            //Keeps generating a velocity from the given information in the property file
            // until the velocity generated isn't equal to 0
            while(velX == 0){
                velX = (int)(Math.random() * (bodyVelocity - (-bodyVelocity) + 1)) + (-bodyVelocity);
            }

            while(velY == 0){
                velY = (int)(Math.random() * (bodyVelocity - (-bodyVelocity) + 1)) + (-bodyVelocity);
            }
            
            //Adds the newly created celestial object to the list of celestial bodies
            CelestialBody newBody = new CelestialBody(x, y, velX, velY, bodySize, Color.BLACK);
            celestialBodies.add(newBody);
        }

        //Generates a random number to see if the celestial object is created
        if(Math.random() <= genY){
            boolean spawnLocation;

            //Genereates a random y coordinate along the window size to spawn the object
            int y = (int)(Math.random() * windowSizeY);
            int x;

            //50/50 chance to create the object on the left or right side of the screen
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

        //Iterates backwards and changes the coordinates of the celestial body
        // to simulate the object "moving"
        for(int i = celestialBodies.size()-1; i >= 0; i--){
            CelestialBody body = celestialBodies.get(i);
            body.x += celestialBodies.get(i).getVelX();
            body.y += celestialBodies.get(i).getVelY();

            //Checks if the object has moved out of the bounds of the window and removes the object
            // from the celestialBodies list if it's true
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

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.windowSizeX, mm.windowSizeY); // TODO: Replace with the size from configuration!
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}