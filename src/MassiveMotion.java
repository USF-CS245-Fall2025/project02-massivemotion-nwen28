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

        double genX = Double.parseDouble(prop.getProperty("gen_x"));
        double genY = Double.parseDouble(prop.getProperty("gen_y"));

        int bodySize = Integer.parseInt(prop.getProperty("body_size"));
        int bodyVelocity = Integer.parseInt(prop.getProperty("body_velocity"));

        int starPosX = Integer.parseInt(prop.getProperty("star_position_x"));
        int starPosY = Integer.parseInt(prop.getProperty("star_position_y"));
        int starSize = Integer.parseInt(prop.getProperty("star_size"));
        int starVelocityX = Integer.parseInt(prop.getProperty("star_velocity_x"));
        int star_velocityY = Integer.parseInt(prop.getProperty("star_velocity_y"));

        String listType = prop.getProperty("list");

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
        x1 = 100; y1 = 50;
        x2 = 200; y2 = 400;
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
        // TODO: Change the location of each ball. Here's an example of them moving across the screen:
        //       ... but to be clear, you should change this.
        x1 += 10;
        x2 -= 15;
        // These two "if" statements keep the balls on the screen in case they go off one side.
        if (x1 > 640)
            x1 = 0;
        if (x2 < 0)
            x2 = 640;

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
