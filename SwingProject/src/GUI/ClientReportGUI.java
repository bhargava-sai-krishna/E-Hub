package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import client.client;
import report.clientReport;
import admin.admin;

public class ClientReportGUI implements GUIInterface {
    JFrame frame = new JFrame();
    JLabel remClient = new JLabel();
    Font labelFont = new Font("Arial", Font.PLAIN, 20);
    JPanel display = new JPanel();
    JLabel id = new JLabel("ID");
    JLabel name = new JLabel("Name: ");
    JLabel dob = new JLabel("DOB ( Format: YYYY-MM-DD )");
    JLabel Company = new JLabel("Company");
    JLabel totalOrders = new JLabel("Total Orders");
    JLabel heading = new JLabel("something");
    JLabel priority = new JLabel("Client Priority");
    JTextField dynamicID = new JTextField();
    JTextField dynamicName = new JTextField();
    JTextField dynamicDOB = new JTextField();
    JTextField dynamicCompany = new JTextField();
    JTextField dynamicTotalOrder = new JTextField();
    JTextField dynamicPriority = new JTextField();
    JButton backSmallButton = new JButton("BACK");

    String[] emptyArray = { "null" };
    JComboBox<String> allID2 = new JComboBox<String>(emptyArray);

    CardLayout card;


    public ClientReportGUI() {
        card = new CardLayout();
        resetTextFields();
        display.setLayout(card);
        frame.setSize(1280, 1024);
        display.setBackground(Color.black);
        display.add(remClient, "removeClientMenu");
        frame.add(display);
     //   frame.add(remClient);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        admin ad = new admin();
        client cli = new client();
        String[] ID = ad.PersonList(cli);
        remClient.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        allID2.removeAllItems();
        for (String id : ID) {
            allID2.addItem(id);
        }
        gbc.ipadx = 100;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        id.setForeground(Color.GREEN);
        id.setFont(labelFont);
        remClient.add(id, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        allID2.setForeground(Color.GREEN);
        allID2.setBackground(Color.DARK_GRAY);
        allID2.setBorder(new LineBorder(Color.DARK_GRAY));
        allID2.setFont(labelFont);
        remClient.add(allID2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        name.setForeground(Color.GREEN);
        remClient.add(name, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dynamicName.setEditable(false);
        dynamicName.setForeground(Color.GREEN);
        dynamicName.setBackground(Color.DARK_GRAY);
        dynamicName.setBorder(new LineBorder(Color.DARK_GRAY));
        remClient.add(dynamicName, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dob.setForeground(Color.GREEN);
        remClient.add(dob, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dynamicDOB.setEditable(false);
        dynamicDOB.setForeground(Color.GREEN);
        dynamicDOB.setBackground(Color.DARK_GRAY);
        dynamicDOB.setBorder(new LineBorder(Color.DARK_GRAY));
        remClient.add(dynamicDOB, gbc);
        

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Company.setForeground(Color.GREEN);
        remClient.add(Company, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dynamicCompany.setEditable(false);
        dynamicCompany.setForeground(Color.GREEN);
        dynamicCompany.setBackground(Color.DARK_GRAY);
        dynamicCompany.setBorder(new LineBorder(Color.DARK_GRAY));
        remClient.add(dynamicCompany, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        totalOrders.setForeground(Color.GREEN);
        remClient.add(totalOrders, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dynamicTotalOrder.setForeground(Color.GREEN);
        dynamicTotalOrder.setEditable(false);
        dynamicTotalOrder.setBackground(Color.DARK_GRAY);
        dynamicTotalOrder.setBorder(new LineBorder(Color.DARK_GRAY));
        remClient.add(dynamicTotalOrder, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        priority.setForeground(Color.GREEN);
        remClient.add(priority, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dynamicPriority.setForeground(Color.GREEN);
        dynamicPriority.setEditable(false);
        dynamicPriority.setBackground(Color.DARK_GRAY);
        dynamicPriority.setBorder(new LineBorder(Color.DARK_GRAY));
        remClient.add(dynamicPriority, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        backSmallButton.setOpaque(false);
        backSmallButton.setContentAreaFilled(false);
        backSmallButton.setBorderPainted(false);
        backSmallButton.setForeground(Color.RED);
        remClient.add(backSmallButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //  remClient.add(remove1,gbc);
        allID2.addActionListener(new handleShowCliDetails());
        backSmallButton.addActionListener(new goToMainMenu());
        frame.setVisible(true);

    }

    public void resetTextFields(){
         dynamicID.setText("Select Data from Drop Down List");
         dynamicName.setText("Select Data from Drop Down List");
         dynamicDOB.setText("Select Data from Drop Down List");
         dynamicCompany.setText("Select Data from Drop Down List");
         dynamicTotalOrder.setText("Select Data from Drop Down List");
         dynamicPriority.setText("Select Data from Drop Down List");
    }
    class goToMainMenu implements ActionListener{
        public void actionPerformed(ActionEvent a){
            frame.dispose();
            new AdminGUI(); 
            //disposing and re-creating the frame fixed a lot of bugs in the back button part of the GUI such as the showProjectDetails 
        }
    }
    private void updateRemGUI(client C) {
        dynamicName.setText(C.getName());
        dynamicDOB.setText(C.getDOB());
        dynamicCompany.setText(C.getCompany());
        dynamicTotalOrder.setText((String.format("%d", C.getTotal_Orders())));
        C.ClientPriority();
        if (C.getPriority() == 0)
            dynamicPriority.setText("No paid projects yet");
        else
            dynamicPriority.setText((String.format("%d", C.getPriority())));
       

    }

    class handleShowCliDetails implements ActionListener {

        public void actionPerformed(ActionEvent a) {
            clientReport cliRep = new clientReport();
            client cli = new client();
            cli.setID((String) allID2.getSelectedItem());
            cli = cliRep.displayReport(cli);
            //System.out.println("\nClient GUI Details called\n");
            updateRemGUI(cli);
        }
    }
    
     
    class newDriverclass {
        public static void main(String[] args) {
            new ClientReportGUI();
            //adg.logPrompt();
        }
    }
}
