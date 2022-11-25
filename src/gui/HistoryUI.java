package gui;

import dictionary.Dictionary;

import javax.swing.*;
import java.awt.*;

/**
 * gui
 * Created by khanh
 * Date 11/24/2022 - 12:56 PM
 * Description: ...
 */

public class HistoryUI extends JPanel {
    JTable histTable;
    String[][] historyList;

    public HistoryUI(Dictionary dict) {
        setLayout(new BorderLayout());
        historyList = Dictionary.convertMapToList(dict.getHistoryMap());

        // Column Names
        String[] columnNames = {"Slang", "Definition"};
        histTable = new JTable(new MyTableModel(historyList, columnNames));
        histTable.getTableHeader().setReorderingAllowed(false);
        histTable.setRowHeight(20);
        // adding it to JScrollPane
        JScrollPane dictSp = new JScrollPane(histTable);

        JPanel headerContainer = new JPanel();
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(Box.createRigidArea(new Dimension(0, 20)));
        JLabel titleLabel = new JLabel("SEARCHED HISTORY");
        Font newTextFieldFont = new Font(titleLabel.getFont().getName(), titleLabel.getFont().getStyle(), 20);
        titleLabel.setFont(newTextFieldFont);
        header.add(titleLabel);
        header.add(Box.createRigidArea(new Dimension(0, 20)));

        headerContainer.add(header);
        add(dictSp, BorderLayout.CENTER);
        add(headerContainer, BorderLayout.PAGE_START);
    }
}
