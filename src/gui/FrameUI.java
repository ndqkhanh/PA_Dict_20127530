package gui;

import javax.swing.*;
import java.awt.*;

/**
 * gui
 * Created by khanh
 * Date 11/20/2022 - 12:20 AM
 * Description: ...
 */
public class FrameUI extends JPanel {
    JSplitPane splitPane;
    SideBarUI sideBarUI;
    MainFrameUI mainFrameUI;

    public FrameUI() {
        sideBarUI = new SideBarUI();
        mainFrameUI = new MainFrameUI();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                sideBarUI, mainFrameUI);
        splitPane.setPreferredSize(new Dimension(900, 520));
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(210);
    }

    public JSplitPane getSplitPane() {
        return splitPane;
    }

    public void createAndShowGUI() {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        JFrame frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        FrameUI newContentPane = new FrameUI();
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.getContentPane().add(newContentPane.getSplitPane());

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
