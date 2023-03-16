package GUI;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener{
	//required fields
	Image backgroundImage;
	Font labelFont = new Font("Arial", Font.PLAIN, 20);
	JFrame frame=new JFrame();
	JLabel ulbl=new JLabel("USERNAME");
	JLabel paslbl=new JLabel("PASSOWRD");//
	JButton login=new JButton("LOGIN");//
	JButton resetButton = new JButton("Reset");
	JButton signup=new JButton("Signup");//
	JCheckBox showPassword = new JCheckBox("Show Password");
	JTextField utxtfld=new JTextField("",50);//
	JPasswordField passtxtfld=new JPasswordField("",50);//
	JLabel panel = new JLabel();
	Login()
	{
		setLayoutManager();
		AddComponentsToContainer();
		addActionEvent();
		add(panel);
	}
	public void setLayoutManager() {
		//using grid bag layout
        panel.setLayout(new GridBagLayout());
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\bharg\\eclipse-workspace\\SwingPoject\\src\\GUI\\bgimg.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        getContentPane().setBackground(Color.BLACK);
    }
	public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
	public void AddComponentsToContainer()
	{
		//adding the components according to dimensions
		GridBagConstraints gdbc=new GridBagConstraints();
		gdbc.anchor=GridBagConstraints.WEST;
		gdbc.gridx=0;
		gdbc.gridy=0;
		gdbc.ipady=10;
		gdbc.insets=new Insets(10,10,10,10);
		ulbl.setFont(labelFont);
		ulbl.setForeground(Color.GREEN);
		panel.add(ulbl,gdbc);
		
		gdbc.gridx=0;
		gdbc.gridy=1;
		paslbl.setForeground(Color.GREEN);
		paslbl.setFont(labelFont);
		panel.add(paslbl,gdbc);
		
		gdbc.gridx=1;
		gdbc.gridy=0;
		utxtfld.setForeground(Color.GREEN);
		utxtfld.setBackground(Color.DARK_GRAY);
		utxtfld.setCaretColor(Color.GREEN);//used to set the color of blinking cursor
		panel.add(utxtfld,gdbc);
		
		gdbc.gridx=1;
		gdbc.gridy=1;
		passtxtfld.setForeground(Color.GREEN);
		passtxtfld.setBackground(Color.DARK_GRAY);
		passtxtfld.setCaretColor(Color.GREEN);
		panel.add(passtxtfld,gdbc);
		
		
		gdbc.gridx=0;
		gdbc.gridy=3;
		login.setForeground(Color.GREEN);
		login.setOpaque(false);
        login.setContentAreaFilled(false);
        login.setBorderPainted(true);
        login.setFont(labelFont);
        panel.add(login,gdbc);
        
        gdbc.gridx=1;
		gdbc.gridy=3;
		signup.setForeground(Color.GREEN);
		signup.setOpaque(false);
        signup.setContentAreaFilled(false);
        signup.setBorderPainted(true);
        signup.setFont(labelFont);
        panel.add(signup,gdbc);
        
        gdbc.gridx = 0;
        gdbc.gridy = 2;
        showPassword.setForeground(Color.white);
        showPassword.setOpaque(false);
        panel.add(showPassword,gdbc);
        
        gdbc.gridx = 2;
        gdbc.gridy = 3;
        resetButton.setOpaque(false);
        resetButton.setForeground(Color.GREEN);
        resetButton.setContentAreaFilled(false);
        resetButton.setBorderPainted(true);
        resetButton.setFont(labelFont);
        panel.add(resetButton,gdbc);
	}
	
	
	public void addActionEvent() {
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                Connection c = null;
                Statement stmt = null;
                try 
        		{
        			Class.forName("org.postgresql.Driver");
        		} 
        		catch (ClassNotFoundException e) {
        			e.printStackTrace();
        		}
                try {
                    secret obj = new secret();
                    c = DriverManager.getConnection(obj.url,obj.uname, obj.password); // We have used objects here as
                                                                                      // the URL, username and psswd are
                                                                                      // different for
                } catch (Exception e) { // each one of us. So all our necessary info is stored in secret.java
                    e.printStackTrace();
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
                try {
                    System.out.println("Opened database successfully");
                    stmt = c.createStatement();
                    String userText;
                    String pwdText;
                    userText = utxtfld.getText();
                    pwdText = String.valueOf(passtxtfld.getPassword());
                    String sql = String.format(
                            "select Substring(ID,1,3) as id,id as full_id, Password from Login where username='%s';",
                            userText);
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        if (rs.getString("password").equals(pwdText)) { // This line verifies the password
                            if (rs.getString("id").equals("ADM")) {
                                // redirect to admin dashboard
                                System.out.println("Welcome admin");
                                new AdminGUI();
                                dispose();
                            }
                            if (rs.getString("id").equals("CLI")) {
                                System.out.println("Welcome Client");
                                new clientgui(rs.getString("full_id"));
                                dispose(); // this will close the login frame completely
                            }
                        }
                        rs.close();
                        c.close();
                        stmt.close();
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        });
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        passtxtfld.setEchoChar('*'); // replace whatever the user types with a character.
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            utxtfld.setText("");
            passtxtfld.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passtxtfld.setEchoChar((char) 0); // setting the character to 0 indicates that we want the password
                                                     // field to behave as a normal TextField
            } else {
                passtxtfld.setEchoChar('*');
            }

        }
    }
	public static void main(String[] args)
	{
		Login frame=new Login();
		frame.setSize(1080,1400);
		frame.setVisible(true);
		frame.setTitle("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
