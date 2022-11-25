package gui;

import dictionary.Dictionary;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * gui
 * Created by khanh
 * Date 11/24/2022 - 12:13 AM
 * Description: ...
 */
public class MainFrameUI extends JPanel {
    JScrollPane mainPane;
    DictionaryUI dictUI;
    HistoryUI histUI;
    RandomSlangUI randomSlangUI;
    QuizGameUI quizUI;

    public MainFrameUI(Dictionary dict) throws IOException {
        setLayout(new BorderLayout());
        mainPane = new JScrollPane();
        setMainPane("Dictionary", dict);
        add(mainPane, BorderLayout.CENTER);
    }

    public DictionaryUI getDictUI() {
        return dictUI;
    }

    public void setMainPane(String str, Dictionary dict) throws IOException {
        switch (str) {
            case "Dictionary":
                dictUI = new DictionaryUI(dict);
                mainPane.setViewportView(dictUI);
                break;
            case "History":
                dict.getHistoryMap().importFromFile("history.dat");
                histUI = new HistoryUI(dict.getHistoryMap().getHistoryMap());
                mainPane.setViewportView(histUI);
                break;
            case "On this day slang word":
                randomSlangUI = new RandomSlangUI(dict);
                mainPane.setViewportView(randomSlangUI);
                break;
            case "Quiz":
                quizUI = new QuizGameUI();
                mainPane.setViewportView(quizUI);
                break;
        }
    }
}
