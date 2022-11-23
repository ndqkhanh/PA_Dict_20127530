package gui;

import javax.swing.*;
import java.awt.*;

/**
 * gui
 * Created by khanh
 * Date 11/20/2022 - 12:59 AM
 * Description: ...
 */
public class SideBarUI extends JPanel {
    private final String[] menuNames = {"Dictionary", "History", "On this day slang word", "Quiz"};
    JButton dict, hist, rand, quiz;

    public SideBarUI() {
        JPanel dictPanel = new JPanel();
        dictPanel.setLayout(new BorderLayout());
        dict = new JButton(menuNames[0]);
        dict.setPreferredSize(new Dimension(200, 125));
        dictPanel.add(dict);

        JPanel histPanel = new JPanel();
        histPanel.setLayout(new BorderLayout());
        hist = new JButton(menuNames[1]);
        hist.setPreferredSize(new Dimension(200, 125));
        histPanel.add(hist);

        JPanel randPanel = new JPanel();
        randPanel.setLayout(new BorderLayout());
        rand = new JButton(menuNames[2]);
        rand.setPreferredSize(new Dimension(200, 125));
        randPanel.add(rand);

        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new BorderLayout());
        quiz = new JButton(menuNames[3]);
        quiz.setPreferredSize(new Dimension(200, 125));
        quizPanel.add(quiz);

        JPanel menuContainer = new JPanel();
        menuContainer.setLayout(new BorderLayout());
        JPanel menuButtons = new JPanel();
        menuButtons.setLayout(new BoxLayout(menuButtons, BoxLayout.Y_AXIS));
        menuButtons.add(dictPanel);
        menuButtons.add(histPanel);
        menuButtons.add(randPanel);
        menuButtons.add(quizPanel);
        menuContainer.add(menuButtons, BorderLayout.CENTER);
        add(menuContainer);
    }
}
