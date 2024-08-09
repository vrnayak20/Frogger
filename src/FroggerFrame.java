import java.awt.*;
import javax.swing.*;

public class FroggerFrame extends JFrame
{
    private FroggerPanel p;
    public FroggerFrame(String title)
    {
        // create the frame with the given title
        super(title);

        // set x button to close program
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // sets the user to not be able to resize the frame
        setResizable(false);

        // creates the frame (hidden at this point)
        pack();

        // creates the panel
        p = new FroggerPanel();

        // get insets
        Insets insets = getInsets();

        // calculating window size
        int width = p.getWidth() + insets.left + insets.right;
        int height = p.getHeight() + insets.top + insets.bottom;

        // set the preferred size
        setPreferredSize(new Dimension(width, height));

        // turn off layout options
        setLayout(null);

        // adds the panel to the frame
        add(p);

        // adjusts to be the size we set with setPreferredSize
        pack();

        // show the screen
        setVisible(true);
    }
    public FroggerPanel getP()
    {
        return p;
    }
}
