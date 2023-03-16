package javaproject.gui.admincomponents;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;

import admin.admin;
import exceptions.noData;
import GUI.AdminGUI;
import project.project;

public class projectInfo {

    JFrame frame = new JFrame("Modification Approval Menu");
    JPanel cliMenu = new JPanel();
    JLabel viewProject= new JLabel();
    String[] emptyArray = { "null" };
    JLabel id = new JLabel("Project ID: ");
    JLabel name = new JLabel("Project Name: ");
    JLabel dynamicName = new JLabel();
    JLabel status = new JLabel("Status of the Project");
    JLabel statusLabel = new JLabel();
    JButton approve = new JButton("APPROVE");
    JButton reject = new JButton("REJECT");
    JButton backButton = new JButton("BACK");
    JLabel descriptionLabel = new JLabel("Project Log");
    JTextArea descriptionTextField = new JTextArea(10, 50);
    JScrollPane scroll = new JScrollPane(descriptionTextField,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    String project_status;
    Font labelFont = new Font("Arial", Font.PLAIN, 20);

    JComboBox<String> allID = new JComboBox<String>(emptyArray);
    String[] projID = null;

    public projectInfo() throws noData {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);
        cliMenu.setBackground(Color.BLACK);
        admin ad = new admin();
        projID = ad.ProjectListChangesRequested() ;   
        if(projID == null)
            throw new noData();     
        viewProject.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        allID = new JComboBox<String>(projID);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        id.setForeground(Color.BLACK);
        id.setFont(labelFont);
        viewProject.add(id, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        allID.setForeground(Color.WHITE);
        allID.setBackground(Color.DARK_GRAY);
        allID.setBorder(new LineBorder(Color.DARK_GRAY));
        viewProject.add(allID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        name.setForeground(Color.BLACK);
        name.setFont(labelFont);
        viewProject.add(name, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dynamicName.setForeground(Color.WHITE);
        dynamicName.setBackground(Color.DARK_GRAY);
        dynamicName.setBorder(new LineBorder(Color.DARK_GRAY));
        dynamicName.setFont(labelFont);
        viewProject.add(dynamicName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        descriptionLabel.setFont(labelFont);
        descriptionLabel.setForeground(Color.BLACK);
        viewProject.add(descriptionLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        descriptionTextField.setLineWrap(true);
        descriptionTextField.setEditable(false);
        descriptionTextField.setForeground(Color.WHITE);
        descriptionTextField.setBackground(Color.DARK_GRAY);
        descriptionTextField.setBorder(new LineBorder(Color.DARK_GRAY));
        descriptionTextField.setCaretColor(Color.GREEN);
        viewProject.add(descriptionTextField, gbc);
        viewProject.add(scroll, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        status.setForeground(Color.BLACK);
        status.setFont(labelFont);
        viewProject.add(status, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBackground(Color.DARK_GRAY);
        statusLabel.setBorder(new LineBorder(Color.DARK_GRAY));
        statusLabel.setFont(labelFont);
        viewProject.add(statusLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        approve.setOpaque(false);
        approve.setContentAreaFilled(false);
        approve.setForeground(Color.BLUE);
        approve.setBorderPainted(false);
        viewProject.add(approve, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.CENTER;
        reject.setOpaque(false);
        reject.setForeground(Color.RED);
        reject.setContentAreaFilled(false);
        reject.setBorderPainted(false);
        viewProject.add(reject,gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        viewProject.add(backButton, gbc);

        approve.addActionListener(new handleApproveChanges());
        allID.addActionListener(new handleShowDetails());
        reject.addActionListener(new handleRejectChanges());
        frame.setSize(1280, 1024);
        frame.add(viewProject);
        frame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                frame.dispose();
                new AdminGUI();
            }
        });

    }

    private void updateviewProjectGUI(project P) {
        dynamicName.setText(P.getProjectName());
        statusLabel.setText(P.getProjectStatus());
        project_status = P.getProjectStatus();
        String full_log = P.getProjectLog();
        String description = full_log.replaceAll("\\\\n", System.getProperty("line.separator"));
        descriptionTextField.setText(description);
        //dynamicTotalOrder.setText((String.format("%d", C.getTotal_Orders())));
    }
    
    private int logPrompt(project P){
        admin adm = new admin();
        String message = JOptionPane.showInputDialog(frame, "Admin Log Message", "", JOptionPane.INFORMATION_MESSAGE);
        System.out.println(message);
        if(message!=null && message.length()>0){
            adm.addProjectLog(P, message);
            return 1;
        }else{
            return 0;
        }
    }

    class handleShowDetails implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            /*reports will be updated later*/

        }
    }

    class handleApproveChanges implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            project pro = new project();
            admin ad = new admin();
            pro.setProjectID((String)allID.getSelectedItem());
            int output = logPrompt(pro);
            if(output == 1){
                ad.ProjectChanges(pro,"CHANGES ACCEPTED");
                JOptionPane.showMessageDialog(frame, "Status Updated", "Info", JOptionPane.PLAIN_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(frame, "Status Not Updated: Provide Log to update", "Info", JOptionPane.PLAIN_MESSAGE);

            }
            frame.repaint();
        }
    }
    class handleRejectChanges implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            project pro = new project();
            admin ad = new admin();
            pro.setProjectID((String)allID.getSelectedItem());
            int output = logPrompt(pro);
            if(output == 1){
                ad.ProjectChanges(pro,"CHANGES REJECTED");
                JOptionPane.showMessageDialog(frame, "Status Updated", "Info", JOptionPane.PLAIN_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(frame, "Status Not Updated: Provide Log to update", "Info", JOptionPane.PLAIN_MESSAGE);

            }
            frame.repaint();
        }
    }

}

class Driver123 {
public static void main(String[] args) {
        
        try {
            new projectInfo();
        } catch (noData nd) {
            nd.displaYError(new JFrame());
        }
    }
}
