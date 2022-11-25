package gui;

import dictionary.Dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * gui
 * Created by khanh
 * Date 11/20/2022 - 12:19 AM
 * Description: ...
 */
public class QuizGameUI extends JPanel {
    HashMap<String, HashMap<String, Boolean>> curQuiz;
    JButton A, B, C, D, startButton, nextQuestion, stopButton;
    JComboBox<String> quizType;
    JLabel quesContent;
    String[] types = {"SLANG", "DEFINITION"};

    public QuizGameUI(Dictionary dict) {
        setLayout(new BorderLayout());
        JPanel quizPanel = new JPanel(new BorderLayout());
        quizType = new JComboBox<>(types);
        quizType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stopButton.isEnabled()) {
                    setNextQuestion(dict, Objects.equals(quizType.getSelectedItem(), types[0]));
                }
            }
        });
        quizType.setSelectedItem(0);
        quizType.setSize(new Dimension(100, 30));
        JPanel labelContainPanel = new JPanel();
        labelContainPanel.add(new JLabel("Quiz Mode"));
        labelContainPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        labelContainPanel.add(quizType);
        quizPanel.add(labelContainPanel, BorderLayout.LINE_START);

        JPanel headerContainer = new JPanel();
        headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.Y_AXIS));
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(Box.createRigidArea(new Dimension(0, 20)));
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("QUIZ GAME SIMULATOR");
        Font newTextFieldFont = new Font(titleLabel.getFont().getName(), titleLabel.getFont().getStyle(), 20);
        titleLabel.setFont(newTextFieldFont);
        titlePanel.add(titleLabel);
        header.add(titlePanel);
        header.add(Box.createRigidArea(new Dimension(0, 20)));

        headerContainer.add(header);
        headerContainer.add(quizPanel);
        headerContainer.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel answersPanel = new JPanel(new BorderLayout());
        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 50, 50));
        A = new JButton("Answer A");
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkCorrectAnswer(A.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                    A.setBackground(Color.GREEN);
                    JOptionPane.showMessageDialog(null,
                            "You are so excellent :)", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (checkCorrectAnswer(B.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                        B.setBackground(Color.GREEN);
                    }
                    if (checkCorrectAnswer(C.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                        C.setBackground(Color.GREEN);
                    }
                    if (checkCorrectAnswer(D.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                        D.setBackground(Color.GREEN);
                    }
                    A.setBackground(Color.RED);
                    JOptionPane.showMessageDialog(null,
                            "Better luck next time :(", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                setNextQuestion(dict, Objects.equals(quizType.getSelectedItem(), types[0]));
            }
        });

        B = new JButton("Answer B");
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkCorrectAnswer(B.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                    B.setBackground(Color.GREEN);
                    JOptionPane.showMessageDialog(null,
                            "You are so excellent :)", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (checkCorrectAnswer(A.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                        A.setBackground(Color.GREEN);
                    }
                    if (checkCorrectAnswer(C.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                        C.setBackground(Color.GREEN);
                    }
                    if (checkCorrectAnswer(D.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                        D.setBackground(Color.GREEN);
                    }
                    B.setBackground(Color.RED);
                    JOptionPane.showMessageDialog(null,
                            "Better luck next time :(", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                setNextQuestion(dict, Objects.equals(quizType.getSelectedItem(), types[0]));
            }
        });

        C = new JButton("Answer C");
        C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkCorrectAnswer(C.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                    C.setBackground(Color.GREEN);
                    JOptionPane.showMessageDialog(null,
                            "You are so excellent :)", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (checkCorrectAnswer(A.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                        A.setBackground(Color.GREEN);
                    }
                    if (checkCorrectAnswer(B.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                        B.setBackground(Color.GREEN);
                    }
                    if (checkCorrectAnswer(D.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                        D.setBackground(Color.GREEN);
                    }
                    C.setBackground(Color.RED);
                    JOptionPane.showMessageDialog(null,
                            "Better luck next time :(", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                setNextQuestion(dict, Objects.equals(quizType.getSelectedItem(), types[0]));
            }
        });

        D = new JButton("Answer D");
        D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkCorrectAnswer(D.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                    D.setBackground(Color.GREEN);
                    JOptionPane.showMessageDialog(null,
                            "You are so excellent :)", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    D.setBackground(Color.RED);
                    if (checkCorrectAnswer(A.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                        A.setBackground(Color.GREEN);
                    }
                    if (checkCorrectAnswer(B.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                        B.setBackground(Color.GREEN);
                    }
                    if (checkCorrectAnswer(C.getText(), Objects.equals(quizType.getSelectedItem(), types[0]))) {
                        C.setBackground(Color.GREEN);
                    }
                    JOptionPane.showMessageDialog(null,
                            "Better luck next time :(", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                setNextQuestion(dict, Objects.equals(quizType.getSelectedItem(), types[0]));
            }
        });


        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));

        JPanel quesPanel = new JPanel();
        JLabel quesLabel = new JLabel("Question:");
        newTextFieldFont = new Font(quesLabel.getFont().getName(), quesLabel.getFont().getStyle(), 20);
        quesLabel.setFont(newTextFieldFont);
        quesContent = new JLabel("");
        newTextFieldFont = new Font(quesContent.getFont().getName(), quesContent.getFont().getStyle(), 17);
        quesContent.setFont(newTextFieldFont);

        quesPanel.add(quesLabel);
        quesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        quesPanel.add(quesContent);

        questionPanel.add(quesPanel);
        questionPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        btnPanel.add(A);
        btnPanel.add(B);
        btnPanel.add(C);
        btnPanel.add(D);
        A.setEnabled(false);
        B.setEnabled(false);
        C.setEnabled(false);
        D.setEnabled(false);

        JPanel funcButtonsPanel = new JPanel(new BorderLayout());
        JPanel funcBtns = new JPanel();
        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                nextQuestion.setEnabled(true);
                setNextQuestion(dict, Objects.equals(quizType.getSelectedItem(), types[0]));
            }
        });

        stopButton = new JButton("Stop Game");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                nextQuestion.setEnabled(false);
                resetAndDisableAnswerButton();
                quesContent.setText("");
            }
        });

        nextQuestion = new JButton("Next Question");
        nextQuestion.setEnabled(false);
        nextQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNextQuestion(dict, Objects.equals(quizType.getSelectedItem(), types[0]));
            }
        });


        funcBtns.add(startButton);
        funcBtns.add(Box.createRigidArea(new Dimension(20, 0)));
        funcBtns.add(nextQuestion);
        funcBtns.add(Box.createRigidArea(new Dimension(20, 0)));
        funcBtns.add(stopButton);

        funcButtonsPanel.add(funcBtns, BorderLayout.CENTER);
        funcButtonsPanel.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.PAGE_END);

        answersPanel.add(questionPanel, BorderLayout.PAGE_START);
        answersPanel.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.LINE_START);
        answersPanel.add(btnPanel, BorderLayout.CENTER);
        answersPanel.add(Box.createRigidArea(new Dimension(50, 0)), BorderLayout.LINE_END);
        answersPanel.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.PAGE_END);

        add(headerContainer, BorderLayout.PAGE_START);
        add(answersPanel, BorderLayout.CENTER);
        add(funcButtonsPanel, BorderLayout.PAGE_END);
    }

    public void setNextQuestion(Dictionary dict, boolean isSlangType) {
        A.setBackground(new JButton().getBackground());
        B.setBackground(new JButton().getBackground());
        C.setBackground(new JButton().getBackground());
        D.setBackground(new JButton().getBackground());
        curQuiz = dict.generateRandom();
        ArrayList<String> answers = isSlangType ?
                dict.generateNewQuiz(curQuiz, true) :
                dict.generateNewQuiz(curQuiz, false);
        String[][] question = Dictionary.convertMapToList(curQuiz);
        if (isSlangType)
            quesContent.setText("What is the definition of " + question[0][0] + "?");
        else quesContent.setText("What is the slang word of " + question[0][1] + "?");
        setFourAnswerToButton(answers);
    }

    public void resetAndDisableAnswerButton() {
        A.setText("Answer A");
        B.setText("Answer B");
        C.setText("Answer C");
        D.setText("Answer D");
        A.setEnabled(false);
        B.setEnabled(false);
        C.setEnabled(false);
        D.setEnabled(false);
    }

    public void enableAnswerButton() {
        A.setEnabled(true);
        B.setEnabled(true);
        C.setEnabled(true);
        D.setEnabled(true);
    }

    public boolean checkCorrectAnswer(String answer, boolean isSlangType) {
        answer = answer.substring(3);
        if (isSlangType) {
            for (Map.Entry<String, HashMap<String, Boolean>> entry : curQuiz.entrySet()) {
                HashMap<String, Boolean> value = entry.getValue();
                if (value.get(answer) != null) return true;
            }
            return false;
        } else {
            return curQuiz.get(answer) != null;
        }
    }

    public void setFourAnswerToButton(ArrayList<String> answers) {
        if (answers.size() == 4) {
            enableAnswerButton();
            A.setText("A. " + answers.get(0));
            B.setText("B. " + answers.get(1));
            C.setText("C. " + answers.get(2));
            D.setText("D. " + answers.get(3));
        }
    }
}
