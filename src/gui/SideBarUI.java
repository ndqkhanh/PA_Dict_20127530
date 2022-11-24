package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * gui
 * Created by khanh
 * Date 11/20/2022 - 12:59 AM
 * Description: ...
 */
public class SideBarUI extends JPanel {
    private final String[] menuNames = {"Dictionary", "History", "On this day slang word", "Quiz"};
    JButton dictBtn, histBtn, randBtn, quizBtn;

    public SideBarUI(MainFrameUI mainFrameUI) {
        setLayout(new BorderLayout());
        JPanel dictPanel = new JPanel();
        dictPanel.setLayout(new BorderLayout());
        dictBtn = new JButton(menuNames[0]);
        dictBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameUI.setMainPane(menuNames[0]);
            }
        });
        dictPanel.add(dictBtn);

        JPanel histPanel = new JPanel();
        histPanel.setLayout(new BorderLayout());
        histBtn = new JButton(menuNames[1]);
        histBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameUI.setMainPane(menuNames[1]);
            }
        });
        histPanel.add(histBtn);

        JPanel randPanel = new JPanel();
        randPanel.setLayout(new BorderLayout());
        randBtn = new JButton(menuNames[2]);
        randBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameUI.setMainPane(menuNames[2]);
            }
        });
        randPanel.add(randBtn);

        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new BorderLayout());
        quizBtn = new JButton(menuNames[3]);
        quizBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrameUI.setMainPane(menuNames[3]);
            }
        });
        quizPanel.add(quizBtn);

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

//    public void actionPerformed(ActionEvent e) {
//        String strCommand = e.getActionCommand();
//        if (strCommand.equals(menuNames[0])) {
//
//        }
//    }
}
