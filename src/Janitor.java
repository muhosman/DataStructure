/**
 * Janitor of the library
 * @author Mustafa Gurler
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Janitor extends User implements ActionListener{
    JPanel panel1;
    /**
     * Tasks of Janitor
     */
    JButton viewTask;
    JButton updateTask;

    URL book = new URL("https://i.pinimg.com/originals/2c/fc/93/2cfc93d7665f5d7728782700e50596e3.png");
    final ArrayList<Image> icons = new ArrayList<Image>();

    /**
     * Constructor of Janitor and GUI
     * @throws Exception
     */
    public Janitor() throws Exception {
        setTitle("Janitor System");
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(0,1));
        setLocationRelativeTo( null );
        icons.add(ImageIO.read(book));
        setIconImages(icons);
        viewTask = new JButton("View List of Task");
        viewTask.setPreferredSize(new Dimension(150, 35));
        updateTask = new JButton(("Update Task"));
        updateTask.setPreferredSize(new Dimension(150,35));
        panel1.add(viewTask);
        panel1.add(updateTask);

        add(panel1,BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350,400);
        setLocationByPlatform(true);
        setResizable(false);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*if(e.getSource() == ){
            Admin admin = new Admin();
        }*/
    }


}
