package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;

import admin.admin;
import employee.employee;
import report.employeeReport;


public class EmpReportGui implements GUIInterface {
    JFrame frame = new JFrame();
    JPanel display = new JPanel();
    JLabel remEmp = new JLabel();
    String[] emptyArray = { "null" };
    JComboBox<String> allID1 = new JComboBox<String>(emptyArray);
    JLabel id = new JLabel("ID");
    JLabel name = new JLabel("Name: ");
    JTextField dynamicName = new JTextField();
    JLabel dob = new JLabel("DOB ( Format: YYYY-MM-DD )");
    JTextField dynamicDOB = new JTextField();
    JLabel experience = new JLabel("Experience (In Years)");
    JTextField dynamicExperience = new JTextField();
    JLabel domain = new JLabel("Specialisation Domain");
    JTextField dynamicDomain = new JTextField();
    JLabel projectsWorked = new JLabel("Total Projects Worked");
    JLabel projectsLed = new JLabel("Total Projects Led");
    JTextField dynamicProjectsWorked = new JTextField();
    JTextField dynamicProjectsLed = new JTextField();
    JButton backSmallButton = new JButton();
    CardLayout card;

    public EmpReportGui() {
        card = new CardLayout();
        resetTextFields();
        display.setLayout(card);
        display.setBackground(Color.BLACK);
        frame.setSize(1280, 1024);
        display.add(remEmp, "removeClientMenu");
        frame.add(display);
        //frame.setBackground(Color.BLACK);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        admin ad = new admin();
        employee emp = new employee();
        remEmp.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String[] ID = ad.PersonList(emp);
        allID1.setBackground(Color.GREEN);
        allID1.removeAllItems();

        for (String id : ID) {
            allID1.addItem(id);
        }
        gbc.ipadx = 100;
        gbc.ipady = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        id.setForeground(Color.GREEN);
        remEmp.add(id, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        allID1.setForeground(Color.GREEN);
        allID1.setBackground(Color.DARK_GRAY);
        allID1.setBorder(new LineBorder(Color.DARK_GRAY));
        remEmp.add(allID1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        name.setForeground(Color.GREEN);
        remEmp.add(name, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        remEmp.add(dynamicName, gbc);
        dynamicName.setForeground(Color.GREEN);
        dynamicName.setBackground(Color.DARK_GRAY);
        dynamicName.setBorder(new LineBorder(Color.DARK_GRAY));
        dynamicName.setCaretColor(Color.GREEN);
        dynamicName.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dob.setForeground(Color.GREEN);
        remEmp.add(dob, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dynamicDOB.setForeground(Color.GREEN);
        dynamicDOB.setBackground(Color.DARK_GRAY);
        dynamicDOB.setBorder(new LineBorder(Color.DARK_GRAY));
        dynamicDOB.setCaretColor(Color.GREEN);
        remEmp.add(dynamicDOB, gbc);
        dynamicDOB.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        experience.setForeground(Color.GREEN);
        remEmp.add(experience, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dynamicExperience.setForeground(Color.GREEN);
        dynamicExperience.setBackground(Color.DARK_GRAY);
        dynamicExperience.setBorder(new LineBorder(Color.DARK_GRAY));
        dynamicExperience.setCaretColor(Color.GREEN);
        remEmp.add(dynamicExperience, gbc);
        dynamicExperience.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        domain.setForeground(Color.GREEN);
        remEmp.add(domain, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dynamicDomain.setForeground(Color.GREEN);
        dynamicDomain.setBackground(Color.DARK_GRAY);
        dynamicDomain.setBorder(new LineBorder(Color.DARK_GRAY));
        dynamicDomain.setCaretColor(Color.GREEN);
        remEmp.add(dynamicDomain, gbc);
        dynamicDomain.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        projectsWorked.setForeground(Color.GREEN);
        remEmp.add(projectsWorked, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
       dynamicProjectsWorked.setForeground(Color.GREEN);
       dynamicProjectsWorked.setBackground(Color.DARK_GRAY);
       dynamicProjectsWorked.setBorder(new LineBorder(Color.DARK_GRAY));
       dynamicProjectsWorked.setCaretColor(Color.GREEN);
        remEmp.add(dynamicProjectsWorked, gbc);
        dynamicProjectsWorked.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        projectsLed.setForeground(Color.GREEN);
        remEmp.add(projectsLed, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
         dynamicProjectsLed.setForeground(Color.GREEN);
       dynamicProjectsLed.setBackground(Color.DARK_GRAY);
       dynamicProjectsLed.setBorder(new LineBorder(Color.DARK_GRAY));
       dynamicProjectsLed.setCaretColor(Color.GREEN);
        remEmp.add(dynamicProjectsLed, gbc);
        dynamicProjectsLed.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 7;
        backSmallButton.setOpaque(false);
        backSmallButton.setContentAreaFilled(false);
        backSmallButton.setBorderPainted(false);
        remEmp.add(backSmallButton, gbc);
        allID1.addActionListener(new handleShowEmpDetails());
        frame.setVisible(true);
        backSmallButton.addActionListener(new goToMainMenu());
    }

    public void resetTextFields(){
        dynamicName.setText("Select Data from the Drop Down List");
        dynamicDOB.setText("Select Data from the Drop Down List");
        dynamicExperience.setText("Select Data from the Drop Down List");
        dynamicDomain.setText("Select Data from the Drop Down List");
        dynamicProjectsWorked.setText("Select Data from the Drop Down List");
        dynamicProjectsLed.setText("Select Data from the Drop Down List");
    
    }

    private void updateEmployeGui(employee E) {
        dynamicName.setText(E.getName());
        dynamicDOB.setText(E.getDOB());
        //System.out.println("UpdateRemEmpGUI: " + E.getName());
        dynamicExperience.setText(String.format("%d", E.getExperience()));
        dynamicDomain.setText(E.getDomain());
        dynamicProjectsWorked.setText(Integer.toString(E.getProjectsWorked()));
        dynamicProjectsLed.setText(Integer.toString(E.getProjectsLed()));
    }

    class handleShowEmpDetails implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            employeeReport empRep = new employeeReport();
            employee emp = new employee();
            emp.setID((String) allID1.getSelectedItem());
            emp = empRep.displayReport(emp);
            //System.out.println("\nEmployee GUI Details called\n");
            updateEmployeGui(emp);
        }
    }
    class goToMainMenu implements ActionListener{
        public void actionPerformed(ActionEvent a){
            frame.dispose();
            new AdminGUI(); 
            //disposing and re-creating the frame fixed a lot of bugs in the back button part of the GUI such as the showProjectDetails 
        }
    }
}
class newDriverclassEMP {
        
    }
