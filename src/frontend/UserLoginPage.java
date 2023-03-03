package frontend;
import backend.Connections;
import backend.Dbconn;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel; 
import java.sql.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class UserLoginPage {

	JFrame frame;
	private JTextField userTextField;
	private JPasswordField passwordTextField;
	private JComboBox comboBox;
	private String selectUserTypeFromComboBox = "";
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					UserLoginPage window = new UserLoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserLoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(206, 206, 206));
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 453, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JLabel iconLabel1 = new JLabel("");
		iconLabel1.setBounds(91, 145, 50, 42);
		iconLabel1.setIcon(new ImageIcon(UserLoginPage.class.getResource("/icons/username.png")));
		
		JLabel welcomeLabel = new JLabel("Welcome to Herald");
		welcomeLabel.setBounds(134, 11, 151, 28);
		welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 17));
		
		JLabel usernameLabel = new JLabel("Enter Username");
		usernameLabel.setBounds(162, 145, 105, 14);
		
		JLabel passwordLabel = new JLabel("Enter Password");
		passwordLabel.setBounds(162, 221, 105, 14);
		
		JLabel loginAsLabel = new JLabel("Login as");
		loginAsLabel.setBounds(162, 312, 105, 14);
		
		userTextField = new JTextField();
		userTextField.setBounds(162, 160, 173, 28);
		userTextField.setFont(new Font("SansSerif", Font.PLAIN, 11));
		userTextField.setColumns(10);
		
		JLabel mainIcon = new JLabel("");
		mainIcon.setBounds(186, 50, 50, 55);
		mainIcon.setIcon(new ImageIcon(UserLoginPage.class.getResource("/icons/system.png")));
		
		JLabel iconLabel3 = new JLabel("");
		iconLabel3.setBounds(91, 221, 50, 42);
		iconLabel3.setIcon(new ImageIcon(UserLoginPage.class.getResource("/icons/password.png")));
		
		JLabel iconLabel5 = new JLabel("");
		iconLabel5.setBounds(107, 332, 34, 28);
		iconLabel5.setIcon(new ImageIcon(UserLoginPage.class.getResource("/icons/select-user.png")));
		
		comboBox = new JComboBox();
		comboBox.setBounds(162, 332, 167, 28);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "Student", "Teacher", "Admin"}));
		comboBox.setSelectedIndex(0);
		comboBox.setToolTipText("next\r\nnext");
		comboBox.setMaximumRowCount(3);
		comboBox.setBackground(new Color(192, 192, 192));
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 11));
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1) {
					selectUserTypeFromComboBox = (String) e.getItem();
					System.out.println(e.getItem());
				}
			}
		});

		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(162, 236, 173, 28);
		
		JCheckBox checkBox = new JCheckBox("show password");
		checkBox.setBackground(new Color(206, 206, 206));
		checkBox.setBounds(162, 271, 133, 23);
		checkBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == checkBox) {
					
					if(checkBox.isSelected()) {
						passwordTextField.setEchoChar((char)0); 
					}else {
						passwordTextField.setEchoChar('*');
					}
				}	
			}
		});
		
		JButton createBtn = new JButton("Create");
		createBtn.setBounds(69, 419, 99, 28);
		createBtn.setFont(new Font("SansSerif", Font.PLAIN, 11));
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegistrationPage userRegistrationPage = new UserRegistrationPage();
				userRegistrationPage.frame2.setVisible(true);
				
			}
		});
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = userTextField.getText().trim();
				String password = String.valueOf(passwordTextField.getPassword());
				String userType = comboBox.getSelectedItem().toString();

				String checkQuery = "SELECT * FROM `users` WHERE Username=? and Password=?";
				
				if(username.equals("") || password.equals("") || userType.equals("Select")) {
					JOptionPane.showMessageDialog(usernameLabel, "Some fields are empty!!", "Error", 1);
				} else {
					try {
						con = Connections.getConnection();
						pst = con.prepareStatement(checkQuery);
						pst.setString(1,  username);
						pst.setString(2, password);
						rs = pst.executeQuery();
						
						if(rs.next()) {
							String uname = rs.getString("Username");
							if(userType.equals("Admin")) {
								AdminDashBoard ad = new AdminDashBoard(uname);
								ad.frame.setVisible(true);
								frame.dispose();
							}
							if(userType.equals("Student")) {
								StudentDashBoard ad = new StudentDashBoard(uname);
								ad.frame.setVisible(true);
								frame.dispose();
							} if(userType.equals("Teacher")) {
								TeacherDashBoard ad = new TeacherDashBoard(uname);
								ad.frame.setVisible(true);
								frame.dispose();
							}
						}else {
								JOptionPane.showMessageDialog(usernameLabel, "username or password is incorrect!", "Login Error", 1);
							}
						
						
					} catch(SQLException e2) {
						e2.printStackTrace();
					}
				}
				
			}
		});
		loginBtn.setBounds(245, 420, 105, 27);
		loginBtn.setFont(new Font("SansSerif", Font.PLAIN, 11));
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(welcomeLabel);
		frame.getContentPane().add(mainIcon);
		frame.getContentPane().add(iconLabel1);
		frame.getContentPane().add(usernameLabel);
		frame.getContentPane().add(userTextField);
		frame.getContentPane().add(iconLabel3);
		frame.getContentPane().add(passwordLabel);
		frame.getContentPane().add(passwordTextField);
		frame.getContentPane().add(checkBox);
		frame.getContentPane().add(loginAsLabel);
		frame.getContentPane().add(iconLabel5);
		frame.getContentPane().add(comboBox);
		frame.getContentPane().add(createBtn);
		frame.getContentPane().add(loginBtn);
	}
}
