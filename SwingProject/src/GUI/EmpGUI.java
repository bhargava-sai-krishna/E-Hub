package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javaproject.gui.EmployeeComponents.requestProjGui;
import javaproject.gui.EmployeeComponents.viewProjGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class EmpGUI extends JFrame {
	Font labelFont = new Font("Arial", Font.PLAIN, 20);
    JFrame frame = new JFrame("Employee Dashboard");
    JPanel title = new JPanel();
    JPanel menu = new JPanel();
    JButton viewProjButton;
    JButton ProjStatusButton;
    JButton logout;
    JLabel background=new JLabel("Employee");
    
    public EmpGUI(String id) {
        frame.setSize(1280, 1024);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        GridLayout layout = new GridLayout(0, 3);
        layout.setHgap(75);
        background.setLayout(layout);
        frame.getContentPane().setBackground(Color.BLACK);
        

        background.setFont(labelFont);
        
        viewProjButton = new JButton("View Projects");
        viewProjButton.setOpaque(false);
        viewProjButton.setContentAreaFilled(false);
        viewProjButton.setBorderPainted(false);
        viewProjButton.setFont(labelFont);
        viewProjButton.setForeground(Color.GREEN);

        ProjStatusButton = new JButton("Project Status");
        ProjStatusButton.setOpaque(false);
        ProjStatusButton.setContentAreaFilled(false);
        ProjStatusButton.setBorderPainted(false);
        ProjStatusButton.setFont(labelFont);
        ProjStatusButton.setForeground(Color.GREEN);

        logout = new JButton("LOGOUT");
        logout.setOpaque(false);
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.setFont(labelFont);
        logout.setForeground(Color.RED);
        

        background.add(viewProjButton);
        background.add(ProjStatusButton);
        background.add(logout);
        background.setBackground(Color.BLACK);
        background.setForeground(Color.magenta);
        background.setBorder(BorderFactory.createEmptyBorder(250, 90, 250, 90));
        frame.add(background, BorderLayout.CENTER);
        setActions(this, id);
        frame.setVisible(true);
    
    }

    

    void setActions(EmpGUI empGUI, String id) {
        ProjStatusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                requestProjGui req = new requestProjGui(id);
                req.setVisible(true);
                req.getContentPane().setBackground(Color.BLACK);
                empGUI.setVisible(false);
            }
        });
        viewProjButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                new viewProjGui(id);
                //req.setVisible(true);
                frame.getContentPane().setBackground(Color.BLACK);
                empGUI.setVisible(false);
            }
        });
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a){
                frame.dispose();
                Login frame = new Login();
                frame.setSize(new Dimension(1280, 1024));
                frame.getContentPane().setBackground(Color.BLACK);
                frame.setTitle("Login Form");
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
    public static void main(String[] args) {
        new EmpGUI("EMP001");
    }
}


