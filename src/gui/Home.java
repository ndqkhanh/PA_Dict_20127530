package gui;

import javax.swing.*;
import java.awt.*;

/**
 * gui
 * Created by khanh
 * Date 11/20/2022 - 12:20 AM
 * Description: ...
 */
public class Home extends JPanel {

    public Home() {
        super(new BorderLayout());


    }

    public static void createAndShowGUI() {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        JFrame frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // addComponentsToPane(frame.getContentPane());
        // Create and set up the content pane.
        JComponent newContentPane = new Home();

        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
