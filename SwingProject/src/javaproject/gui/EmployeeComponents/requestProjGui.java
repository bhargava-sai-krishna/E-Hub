package javaproject.gui.EmployeeComponents;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import project.project;
import client.client;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class requestProjGui extends JFrame {
    JLabel nameLabel = new JLabel("Project Name: ");
    JLabel domainLabel = new JLabel("Domain: ");
    JLabel releaseDateLabel = new JLabel("Release Date ( Format: YYYY-MM-DD ): ");
    JLabel startDateLabel = new JLabel("Expected Starting Date: ");
    JLabel descriptionLabel = new JLabel("Project Description");
    String cl_id;

    JTextField nameTextField = new JTextField(20);;
    JTextField releaseTextField = new JTextField(20);
    JTextField startTextField = new JTextField(20);
    JTextArea descriptionTextField = new JTextArea(10, 50);

    String[] domainChoices = { "WEB", "ANDROID", "SCIENTIFIC", "BUSINESS", "MEDICAL",
            "INDUSTRIAL & PROCESS CONTROL", "SYSTEMS SOFTWARE", "TOOL DEVELOPMENT" };
    JComboBox<String> domainOptions;

    JPanel title = new JPanel();
    JLabel menu=new JLabel("MENU");
    JButton requestButton = new JButton("Request");
    JButton resetButton = new JButton("Reset");
    JButton backButton = new JButton("Back");
    
    Font labelFont = new Font("Arial", Font.PLAIN, 20);

    public requestProjGui(String id) {
        cl_id = id;
        domainOptions = new JComboBox<String>(domainChoices);
        domainOptions.setBackground(Color.GREEN);
        menu.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        nameLabel.setForeground(Color.GREEN);
        nameLabel.setFont(labelFont);
        menu.add(nameLabel, constraints);

        constraints.gridx = 1;
        nameTextField.setForeground(Color.GREEN);
        nameTextField.setBackground(Color.DARK_GRAY);
        nameTextField.setBorder(new LineBorder(Color.DARK_GRAY));
        nameTextField.setCaretColor(Color.GREEN);
        menu.add(nameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        domainLabel.setForeground(Color.GREEN);
        domainLabel.setFont(labelFont);
        menu.add(domainLabel, constraints);
        menu.setForeground(Color.GREEN);

        constraints.gridx = 1;
        domainOptions.setForeground(Color.GREEN);
        domainOptions.setBackground(Color.DARK_GRAY);
        domainOptions.setBorder(new LineBorder(Color.DARK_GRAY));
        menu.add(domainOptions, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        descriptionLabel.setForeground(Color.GREEN);
        descriptionLabel.setFont(labelFont);
        menu.add(descriptionLabel, constraints);

        descriptionTextField.setLineWrap(true);
        constraints.gridx = 1;
        descriptionTextField.setForeground(Color.GREEN);
        descriptionTextField.setCaretColor(Color.GREEN);
        descriptionTextField.setBackground(Color.DARK_GRAY);
        descriptionTextField.setBorder(new LineBorder(Color.DARK_GRAY));
        menu.add(descriptionTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        releaseDateLabel.setForeground(Color.GREEN);
        releaseDateLabel.setFont(labelFont);
        menu.add(releaseDateLabel, constraints);
        //menu.setBackground(Color.BLACK);

        constraints.gridx = 1;
        releaseTextField.setForeground(Color.GREEN);
        releaseTextField.setCaretColor(Color.GREEN);
        releaseTextField.setBackground(Color.DARK_GRAY);
        releaseTextField.setBorder(new LineBorder(Color.DARK_GRAY));
        menu.add(releaseTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        requestButton.setOpaque(false);
        requestButton.setContentAreaFilled(false);
        requestButton.setForeground(Color.GREEN);
        requestButton.setBorderPainted(false);
        requestButton.setFont(labelFont);
        menu.add(requestButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        resetButton.setOpaque(false);
        resetButton.setContentAreaFilled(false);
        resetButton.setForeground(Color.CYAN);
        resetButton.setBorderPainted(false);
        resetButton.setFont(labelFont);
        menu.add(resetButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setForeground(Color.YELLOW);
        backButton.setFont(labelFont);
        menu.add(backButton, constraints);
        menu.setBackground(Color.BLACK);

        menu.setFont(labelFont);
        add(menu);
        setSize(1280, 1024);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setButtonActions();
    }

    void setButtonActions() {
        requestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                client cli = new client();
                project proj = new project();

                proj.setProjectName(nameTextField.getText());
                proj.setProjectLog(descriptionTextField.getText());
                proj.setProjectDeadline(releaseTextField.getText());
                proj.setProjectType(domainOptions.getItemAt(domainOptions.getSelectedIndex()));
                proj.setClientID(cl_id);
                cli.AddProject(proj);
                dispose();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                nameTextField.setText("");
                releaseTextField.setText("");
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                dispose();
            }
        });

    }
}

class cdriver {
    public static void main(String[] args) {
        new requestProjGui("CLE001");
    }
}
