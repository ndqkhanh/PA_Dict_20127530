package gui;

import dictionary.Dictionary;

import javax.swing.*;
import java.awt.*;

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

    public MainFrameUI(Dictionary dict) {
        setLayout(new BorderLayout());
        dictUI = new DictionaryUI(dict);
        histUI = new HistoryUI();
        randomSlangUI = new RandomSlangUI();
        quizUI = new QuizGameUI();
        mainPane = new JScrollPane();
        setMainPane("Dictionary");
        add(mainPane, BorderLayout.CENTER);
    }

    public DictionaryUI getDictUI() {
        return dictUI;
    }

    public void setMainPane(String str) {
        switch (str) {
            case "Dictionary":
                mainPane.setViewportView(dictUI);
                break;
            case "History":
                mainPane.setViewportView(histUI);
                break;
            case "On this day slang word":
                mainPane.setViewportView(randomSlangUI);
                break;
            case "Quiz":
                mainPane.setViewportView(quizUI);
                break;
        }
    }
}
