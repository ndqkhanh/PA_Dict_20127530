package gui;

import dictionary.History;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * gui
 * Created by khanh
 * Date 11/24/2022 - 12:56 PM
 * Description: ...
 */

public class HistoryUI extends JPanel {
    JTable histTable;
    String[][] historyList;

    public HistoryUI(ArrayList<History> historyMap) {
        setLayout(new BorderLayout());
        historyList = new String[historyMap.size()][];
        final int[] i = {0};
        for (History h : historyMap) {
            String[] tmp = {h.getFormatLdt(), h.getHistorySearchText(), h.getSearchType() ? "Definition" : "Slang"};
            historyList[i[0]] = tmp;
            i[0] = i[0] + 1;
        }

        // Column Names
        String[] columnNames = {"Updated Date", "Searched Words", "Type"};
        histTable = new JTable(new MyTableModel(historyList, columnNames));
        // adding it to JScrollPane
        JScrollPane dictSp = new JScrollPane(histTable);

        JPanel headerContainer = new JPanel();
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(Box.createRigidArea(new Dimension(0, 10)));
        header.add(new JLabel("SEARCHED HISTORY"));
        header.add(Box.createRigidArea(new Dimension(0, 10)));

        headerContainer.add(header);
        add(dictSp, BorderLayout.CENTER);
        add(headerContainer, BorderLayout.PAGE_START);
    }
}
