package gui;

import dictionary.Dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * gui
 * Created by khanh
 * Date 11/24/2022 - 1:01 PM
 * Description: ...
 */
public class RandomSlangUI extends JPanel {
    JTable randDictTable;

    public RandomSlangUI(Dictionary dict) {
        setLayout(new BorderLayout());
        // Data to be displayed in the JTable
        String[][] data = Dictionary.convertMapToList(dict.generateRandom());
        // Column Names
        String[] columnNames = {"Slang", "Definition"};
        randDictTable = new JTable(new MyTableModel(data, columnNames));
        randDictTable.setSelectionModel(new ForcedListSelectionModel());
        randDictTable.getTableHeader().setReorderingAllowed(false);
        randDictTable.setRowHeight(80);
        // adding it to JScrollPane
        JScrollPane dictSp = new JScrollPane(randDictTable);

        JPanel headerContainer = new JPanel();
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel lbPanel = new JPanel();
        JLabel titleLabel = new JLabel("RANDOM SLANG WORD OF THE DAY");
        Font newTextFieldFont = new Font(titleLabel.getFont().getName(), titleLabel.getFont().getStyle(), 20);
        titleLabel.setFont(newTextFieldFont);
        lbPanel.add(titleLabel);
        header.add(lbPanel);
        header.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel btnPanel = new JPanel();
        JButton genRandSlangBtn = new JButton("Generate Random Slang Word");
        genRandSlangBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Data to be displayed in the JTable
                String[][] data = Dictionary.convertMapToList(dict.generateRandom());
                randDictTable.setModel(new MyTableModel(data, columnNames));
            }
        });
        btnPanel.add(genRandSlangBtn);
        header.add(btnPanel);
        header.add(Box.createRigidArea(new Dimension(0, 20)));

        headerContainer.add(header);
        add(headerContainer, BorderLayout.PAGE_START);
        add(dictSp, BorderLayout.CENTER);
    }
}
