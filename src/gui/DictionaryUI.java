package gui;

import dictionary.Dictionary;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

/**
 * gui
 * Created by khanh
 * Date 11/23/2022 - 10:19 PM
 * Description: ...
 */

class MyTableModel extends AbstractTableModel {
    String[] columnNames;
    String[][] data;

    public MyTableModel(String[][] data, String[] columnNames) {
        this.columnNames = columnNames;
        this.data = data;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public String getValueAt(int row, int col) {
        return data[row][col];
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }
}

public class DictionaryUI extends JPanel {
    String[] types = {"Slang", "Definition"};
    String[] functionNames = {"Search", "Add", "Edit", "Delete", "Reset"};
    JButton searchBtn, addBtn, editBtn, delBtn, resetBtn;
    JComboBox<String> searchType;
    JTable dictTable;
    JTextField searchBar;

    public DictionaryUI(Dictionary dict) {
        setLayout(new BorderLayout());

        JPanel dictionaryPanel = new JPanel();
        dictionaryPanel.setLayout(new BoxLayout(dictionaryPanel, BoxLayout.Y_AXIS));

        JPanel searchTypePanel = new JPanel();
        searchType = new JComboBox<>(types);
        searchType.setPreferredSize(new Dimension(100, 30));
        searchTypePanel.add(searchType);

        JPanel searchPanel = new JPanel();
        JLabel searchLabel = new JLabel("Search");
        searchPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        searchPanel.add(searchLabel);
        searchPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        searchBar = new JTextField("", 25);
        Font newTextFieldFont = new Font(searchBar.getFont().getName(), searchBar.getFont().getStyle(), 20);
        searchBar.setFont(newTextFieldFont);
        searchPanel.add(searchBar);
        searchBtn = new JButton(functionNames[0]);
        searchBtn.setPreferredSize(new Dimension(80, 30));
        searchPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        searchPanel.add(searchBtn);
        searchPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        JPanel functionButtonsPanel = new JPanel();
        addBtn = new JButton(functionNames[1]);
        addBtn.setPreferredSize(new Dimension(80, 30));
        editBtn = new JButton(functionNames[2]);
        editBtn.setPreferredSize(new Dimension(80, 30));
        delBtn = new JButton(functionNames[3]);
        delBtn.setPreferredSize(new Dimension(80, 30));
        resetBtn = new JButton(functionNames[4]);
        resetBtn.setPreferredSize(new Dimension(80, 30));
        functionButtonsPanel.add(addBtn);
        functionButtonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        functionButtonsPanel.add(editBtn);
        functionButtonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        functionButtonsPanel.add(delBtn);
        functionButtonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        functionButtonsPanel.add(resetBtn);

        // Data to be displayed in the JTable
        String[][] data = Dictionary.convertMapToList(dict.getDictionary());
        // Column Names
        String[] columnNames = {"Slang", "Definition"};
        dictTable = new JTable(new MyTableModel(data, columnNames));
        // adding it to JScrollPane
        JScrollPane dictSp = new JScrollPane(dictTable);

        dictionaryPanel.add(searchTypePanel);
        dictionaryPanel.add(Box.createRigidArea(new Dimension(0, 2)));
        dictionaryPanel.add(searchPanel);
        dictionaryPanel.add(Box.createRigidArea(new Dimension(0, 2)));
        dictionaryPanel.add(functionButtonsPanel);
        dictionaryPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        add(dictionaryPanel, BorderLayout.PAGE_START);
        add(dictSp, BorderLayout.CENTER);
    }
}
