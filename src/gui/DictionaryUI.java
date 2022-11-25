package gui;

import dictionary.Dictionary;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

/**
 * gui
 * Created by khanh
 * Date 11/23/2022 - 10:19 PM
 * Description: ...
 */
class ForcedListSelectionModel extends DefaultListSelectionModel {

    public ForcedListSelectionModel() {
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void clearSelection() {
    }

    @Override
    public void removeSelectionInterval(int index0, int index1) {
    }

}

class EditModal extends JPanel {
    JTextField oldField, newField;

    public EditModal(boolean isSlangType, JTable dictTable) {
        setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints slangLabelC = new GridBagConstraints();
        GridBagConstraints slangFieldC = new GridBagConstraints();
        GridBagConstraints defLabelC = new GridBagConstraints();
        GridBagConstraints defFieldC = new GridBagConstraints();

        JPanel slangLabelPanel = new JPanel();
        String tmp = isSlangType ? "slang" : "definition";
        JLabel slangLabel = new JLabel("Input old " + tmp);
        slangLabelPanel.add(slangLabel);

        JPanel slangFieldPanel = new JPanel();
        String old = dictTable.getValueAt(dictTable.getSelectedRow(), isSlangType ? 0 : 1).toString();
        oldField = new JTextField(old, 30);
        Font newTextFieldFont = new Font(oldField.getFont().getName(), oldField.getFont().getStyle(), 20);
        oldField.setFont(newTextFieldFont);
        oldField.setEditable(false);
        slangFieldPanel.add(oldField);

        JPanel defLabelPanel = new JPanel();
        JLabel defLabel = new JLabel("Input new " + tmp);
        defLabelPanel.add(defLabel);

        JPanel defFieldPanel = new JPanel();
        newField = new JTextField("", 30);
        newTextFieldFont = new Font(newField.getFont().getName(), newField.getFont().getStyle(), 20);
        newField.setFont(newTextFieldFont);
        defFieldPanel.add(newField);

        slangLabelC.fill = GridBagConstraints.HORIZONTAL;
        slangLabelC.weightx = 0.3;
        slangLabelC.gridx = 0;
        slangLabelC.gridy = 0;
        container.add(slangLabelPanel, slangLabelC);

        slangFieldC.fill = GridBagConstraints.HORIZONTAL;
        slangFieldC.weightx = 0.7;
        slangFieldC.gridx = 1;
        slangFieldC.gridy = 0;
        container.add(slangFieldPanel, slangFieldC);

        defLabelC.fill = GridBagConstraints.HORIZONTAL;
        defLabelC.weightx = 0.3;
        defLabelC.gridx = 0;
        defLabelC.gridy = 1;
        container.add(defLabelPanel, defLabelC);

        defFieldC.fill = GridBagConstraints.HORIZONTAL;
        defFieldC.weightx = 0.7;
        defFieldC.gridx = 1;
        defFieldC.gridy = 1;
        container.add(defFieldPanel, defFieldC);
        add(container, BorderLayout.CENTER);
    }

    public JTextField getOldField() {
        return oldField;
    }

    public JTextField getNewField() {
        return newField;
    }
}

class AddSlangModal extends JPanel {
    JTextField slangField, defField;

    public AddSlangModal() {
        setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints slangLabelC = new GridBagConstraints();
        GridBagConstraints slangFieldC = new GridBagConstraints();
        GridBagConstraints defLabelC = new GridBagConstraints();
        GridBagConstraints defFieldC = new GridBagConstraints();

        JPanel slangLabelPanel = new JPanel();
        JLabel slangLabel = new JLabel("Input slang");
        slangLabelPanel.add(slangLabel);

        JPanel slangFieldPanel = new JPanel();
        slangField = new JTextField("", 30);
        Font newTextFieldFont = new Font(slangField.getFont().getName(), slangField.getFont().getStyle(), 20);
        slangField.setFont(newTextFieldFont);
        slangFieldPanel.add(slangField);

        JPanel defLabelPanel = new JPanel();
        JLabel defLabel = new JLabel("Input definition");
        defLabelPanel.add(defLabel);

        JPanel defFieldPanel = new JPanel();
        defField = new JTextField("", 30);
        newTextFieldFont = new Font(defField.getFont().getName(), defField.getFont().getStyle(), 20);
        defField.setFont(newTextFieldFont);
        defFieldPanel.add(defField);

        slangLabelC.fill = GridBagConstraints.HORIZONTAL;
        slangLabelC.weightx = 0.3;
        slangLabelC.gridx = 0;
        slangLabelC.gridy = 0;
        container.add(slangLabelPanel, slangLabelC);

        slangFieldC.fill = GridBagConstraints.HORIZONTAL;
        slangFieldC.weightx = 0.7;
        slangFieldC.gridx = 1;
        slangFieldC.gridy = 0;
        container.add(slangFieldPanel, slangFieldC);

        defLabelC.fill = GridBagConstraints.HORIZONTAL;
        defLabelC.weightx = 0.3;
        defLabelC.gridx = 0;
        defLabelC.gridy = 1;
        container.add(defLabelPanel, defLabelC);

        defFieldC.fill = GridBagConstraints.HORIZONTAL;
        defFieldC.weightx = 0.7;
        defFieldC.gridx = 1;
        defFieldC.gridy = 1;
        container.add(defFieldPanel, defFieldC);
        add(container, BorderLayout.CENTER);
    }

    public JTextField getSlangField() {
        return slangField;
    }

    public JTextField getDefField() {
        return defField;
    }
}

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
    AddSlangModal addSlangModal;
    String[] types = {"SLANG", "DEFINITION"};
    String[] functionNames = {"SEARCH", "ADD", "EDIT", "DELETE", "RESET"};
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
        searchType.setSelectedItem(0);
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
        searchBtn.setPreferredSize(new Dimension(100, 40));
        searchPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        searchPanel.add(searchBtn);
        searchPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }

            public void updateTable() {
                // Data to be displayed in the JTable
                String[][] data;
                // Column Names
                String[] columnNames = {"Slang", "Definition"};
                try {
                    if (Objects.equals(searchType.getSelectedItem(), types[0])) {
                        data = Dictionary.convertMapToList(dict.searchBySlang(searchBar.getText()));
                    } else {
                        data = Dictionary.convertMapToList(dict.searchByDef(searchBar.getText()));
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                MyTableModel tbModel = new MyTableModel(data, columnNames);
                dictTable.setModel(tbModel);
            }
        });
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
            }

            public void removeUpdate(DocumentEvent e) {
                getBackTable();
            }

            public void insertUpdate(DocumentEvent e) {
            }

            public void getBackTable() {
                String[] columnNames = {"Slang", "Definition"};
                if (searchBar.getText().equals("")) {
                    String[][] data = Dictionary.convertMapToList(dict.getDictionary());
                    MyTableModel tbModel = new MyTableModel(data, columnNames);
                    dictTable.setModel(tbModel);
                }
            }
        });

        JPanel functionButtonsPanel = new JPanel();
        addBtn = new JButton(functionNames[1]);
        addBtn.setPreferredSize(new Dimension(100, 40));
        addBtn.addActionListener(new ActionListener() {
            public void getBackDict() {
                String[] columnNames = {"Slang", "Definition"};
                String[][] data = Dictionary.convertMapToList(dict.getDictionary());
                MyTableModel tbModel = new MyTableModel(data, columnNames);
                dictTable.setModel(tbModel);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = new String[]{
                        "Overwrite",
                        "Duplicate",
                        "Cancel"
                };
                String[] temp = new String[]{
                        "Add", "Cancel"
                };
                addSlangModal = new AddSlangModal();
                int result = JOptionPane.showOptionDialog(null, addSlangModal,
                        "Alert", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, temp, temp[0]);

                if (result == 0) {
                    if (addSlangModal.getSlangField().getText().equals("") || addSlangModal.getDefField().getText().equals("")) {
                        JOptionPane.showMessageDialog(null,
                                "[WARNING]: You must fill all fields", "Alert",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        try {
                            if (dict.getDictionary().get(addSlangModal.getSlangField().getText()) != null) {
                                JOptionPane.showMessageDialog(null,
                                        "[ALERT]: This slang word is already exist\nPlease choose on your decision", "Alert",
                                        JOptionPane.WARNING_MESSAGE);
                                int dupResult = JOptionPane.showOptionDialog(null, addSlangModal,
                                        "Alert", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                                        null, options, null);
                                if (dupResult == 0) {
                                    dict.addSlangWord(addSlangModal.getSlangField().getText(),
                                            addSlangModal.getDefField().getText(), true, true);
                                    JOptionPane.showMessageDialog(null,
                                            "[SUCCESS]: Overwrite slang word successfully", "Alert",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    getBackDict();
                                } else if (dupResult == 1) {
                                    dict.addSlangWord(addSlangModal.getSlangField().getText(),
                                            addSlangModal.getDefField().getText(), false, true);
                                    JOptionPane.showMessageDialog(null,
                                            "[SUCCESS]: Duplicate slang word successfully", "Alert",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    getBackDict();
                                }
                            } else {
                                dict.addSlangWord(addSlangModal.getSlangField().getText(),
                                        addSlangModal.getDefField().getText(), false, true);
                                JOptionPane.showMessageDialog(null,
                                        "[SUCCESS]: Add slang word successfully", "Alert",
                                        JOptionPane.INFORMATION_MESSAGE);
                                getBackDict();
                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        editBtn = new JButton(functionNames[2]);
        editBtn.setPreferredSize(new Dimension(100, 40));
        editBtn.addActionListener(new ActionListener() {
            public void getBackDict() {
                String[] columnNames = {"Slang", "Definition"};
                String[][] data = Dictionary.convertMapToList(dict.getDictionary());
                MyTableModel tbModel = new MyTableModel(data, columnNames);
                dictTable.setModel(tbModel);
            }

            public void updateTable() {
                String[] options = new String[]{
                        "Edit",
                        "Cancel"
                };
                try {
                    if (dictTable.getSelectedRow() != -1) {
                        if (Objects.equals(searchType.getSelectedItem(), types[0])) {
                            EditModal slangModal = new EditModal(true, dictTable);
                            int result = JOptionPane.showOptionDialog(null, slangModal,
                                    "Alert", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                                    null, options, null);
                            if (result == 0) {
                                if (slangModal.getNewField().getText().equals("")) {
                                    JOptionPane.showMessageDialog(null,
                                            "[ALERT]: The new value is required!!!\nPlease type again!!!", "Alert",
                                            JOptionPane.WARNING_MESSAGE);
                                } else {
                                    dict.editSlang(slangModal.getOldField().getText(), slangModal.getNewField().getText());
                                    System.out.println("out");
                                    JOptionPane.showMessageDialog(null,
                                            "[SUCCESS]: Edit slang successfully", "Alert",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    getBackDict();
                                }
                            }
                        } else {
                            EditModal defModal = new EditModal(false, dictTable);
                            int result = JOptionPane.showOptionDialog(null, defModal,
                                    "Alert", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                                    null, options, null);
                            if (result == 0) {
                                if (defModal.getNewField().getText().equals("")) {
                                    JOptionPane.showMessageDialog(null,
                                            "[ALERT]: The new value is required!!!\nPlease type again!!!", "Alert",
                                            JOptionPane.WARNING_MESSAGE);
                                } else {
                                    dict.editDef(dictTable.getValueAt(dictTable.getSelectedRow(), 0).toString(),
                                            defModal.getOldField().getText(), defModal.getNewField().getText());
                                    JOptionPane.showMessageDialog(null,
                                            "[SUCCESS]: Edit definition successfully", "Alert",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    getBackDict();
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "[FAILED]: Please choose one row in the table to edit", "Alert",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });


        delBtn = new JButton(functionNames[3]);
        delBtn.setPreferredSize(new Dimension(100, 40));
        delBtn.addActionListener(new ActionListener() {
            public void getBackDict() {
                String[] columnNames = {"Slang", "Definition"};
                String[][] data = Dictionary.convertMapToList(dict.getDictionary());
                MyTableModel tbModel = new MyTableModel(data, columnNames);
                dictTable.setModel(tbModel);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (dictTable.getSelectedRow() != -1) {
                        dict.deleteSlangWord(dictTable.getValueAt(dictTable.getSelectedRow(), 0).toString());
                        JOptionPane.showMessageDialog(null,
                                "[SUCCESS]: Delete slang word successfully", "Alert",
                                JOptionPane.INFORMATION_MESSAGE);
                        getBackDict();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "[FAILED]: Please choose one row in the table to delete", "Alert",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        resetBtn = new JButton(functionNames[4]);
        resetBtn.setPreferredSize(new Dimension(100, 40));
        resetBtn.addActionListener(new ActionListener() {
            public void getBackDict() throws IOException {
                dict.importFromFile("slang.txt", false, false);
                String[] columnNames = {"Slang", "Definition"};
                String[][] data = Dictionary.convertMapToList(dict.getDictionary());
                MyTableModel tbModel = new MyTableModel(data, columnNames);
                dictTable.setModel(tbModel);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null,
                            "[SUCCESS]: Reset dictionary successfully", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                    getBackDict();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

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
        dictTable.setSelectionModel(new ForcedListSelectionModel());
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
