package frontend;
import backend.Dbconn;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.awt.event.ItemEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class UserRegistrationPage {

	JFrame frame2;
	private JTextField fullNameTextField;
	private JTextField passwordTextField;
	private JTextField userNameTextField;
	private JRadioButton maleRadioBtn;
	private JRadioButton femaleRadioBtn;
	private JComboBox selectUserTypeComboBox;
	private JComboBox selectCourseComboBox;
	private JButton addBtn;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String fullName = "";
	private String gender = "";
	private String userName = "";
	private String password = "";
	private String selectedCourseFromComboBox = "";
	private String SelectedUserTypeFromComboBox = "";
	private JLabel welcomeLabel;
	private JLabel iconLabel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegistrationPage window = new UserRegistrationPage();
					window.frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserRegistrationPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame2 = new JFrame();
		frame2.getContentPane().setBackground(new Color(206, 206, 206));
		frame2.setTitle("User Registration");
		frame2.setBounds(100, 100, 446, 548);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setResizable(false);
		
		JLabel NameLabel = new JLabel("Full Name");
		NameLabel.setBounds(18, 123, 66, 23);
		
		JLabel genderLabel = new JLabel("Gender");
		genderLabel.setBounds(18, 177, 60, 28);
		
		JLabel selectCourseLabel = new JLabel("Select Course");
		selectCourseLabel.setBounds(18, 362, 88, 28);
		
		fullNameTextField = new JTextField();
		fullNameTextField.setBounds(106, 124, 234, 20);
		fullNameTextField.setColumns(10);
		
		maleRadioBtn = new JRadioButton("Male");
		maleRadioBtn.setBackground(new Color(206, 206, 206));
		maleRadioBtn.setBounds(106, 180, 75, 23);
		buttonGroup.add(maleRadioBtn);
		
		femaleRadioBtn = new JRadioButton("Female");
		femaleRadioBtn.setBackground(new Color(206, 206, 206));
		femaleRadioBtn.setBounds(209, 180, 84, 23);
		buttonGroup.add(femaleRadioBtn);
		
		selectCourseComboBox = new JComboBox();
		selectCourseComboBox.setBounds(106, 366, 107, 20);
		selectCourseComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					selectedCourseFromComboBox = (String) e.getItem();
					System.out.println(e.getItem());
				}
			}
		});
		selectCourseComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "BIBM", "BIT", "BDS"}));
		
		JLabel userNameLabel = new JLabel("User Name");
		userNameLabel.setBounds(18, 242, 78, 28);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(18, 299, 78, 28);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(106, 303, 234, 20);
		passwordTextField.setColumns(10);
		
		userNameTextField = new JTextField();
		userNameTextField.setBounds(106, 246, 234, 20);
		userNameTextField.setColumns(10);
		
		JLabel selectUserTypeLabel = new JLabel("User Type");
		selectUserTypeLabel.setBounds(18, 417, 88, 28);
		
		selectUserTypeComboBox = new JComboBox();
		selectUserTypeComboBox.setBounds(106, 421, 107, 20);
		selectUserTypeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					SelectedUserTypeFromComboBox = (String) e.getItem();
					System.out.println(e.getItem());
				}
			}
		});
		selectUserTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "Student", "Teacher", "Admin"}));
		
		addBtn = new JButton("Add");
		addBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
		addBtn.setForeground(new Color(0, 0, 0));
		addBtn.setBackground(new Color(85, 85, 85));
		addBtn.setBounds(313, 475, 107, 23);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fullName = fullNameTextField.getText().trim();
				userName = userNameTextField.getText().trim();
				password = passwordTextField.getText().trim();
				
				for(Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();
					
					if(button.isSelected()) {
						gender = button.getText();
					}
				}
				
				if(fullName.equals("") || userName.equals("") || password.equals("") || selectedCourseFromComboBox.equals("Select") || SelectedUserTypeFromComboBox.equals("Select")){
					JOptionPane.showMessageDialog(fullNameTextField, "Some Fields are empty!!");
				} else {
					
					Statement statement = Dbconn.getStatement();
					String insertQuery = "INSERT INTO `users` (`User_Id`, `Full_Name`, `Gender`, `Username`, `Password`, `Course`, `User_Type`) "
							+ "VALUES (NULL, '"+fullName+"', '"+gender+"', '"+userName+"', '"+password+"', '"+selectedCourseFromComboBox+"', '"+SelectedUserTypeFromComboBox+"')";
					
					try {
						int insertSuccess = statement.executeUpdate(insertQuery);
						if (insertSuccess == 1) {
							JOptionPane.showMessageDialog(fullNameTextField, "Saved into database");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		frame2.getContentPane().setLayout(null);
		frame2.getContentPane().add(genderLabel);
		frame2.getContentPane().add(NameLabel);
		frame2.getContentPane().add(userNameLabel);
		frame2.getContentPane().add(passwordLabel);
		frame2.getContentPane().add(userNameTextField);
		frame2.getContentPane().add(passwordTextField);
		frame2.getContentPane().add(maleRadioBtn);
		frame2.getContentPane().add(femaleRadioBtn);
		frame2.getContentPane().add(fullNameTextField);
		frame2.getContentPane().add(selectCourseLabel);
		frame2.getContentPane().add(selectCourseComboBox);
		frame2.getContentPane().add(selectUserTypeLabel);
		frame2.getContentPane().add(selectUserTypeComboBox);
		frame2.getContentPane().add(addBtn);
		
		welcomeLabel = new JLabel("Registration Page");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		welcomeLabel.setBounds(117, 11, 155, 28);
		frame2.getContentPane().add(welcomeLabel);
		
		iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(UserRegistrationPage.class.getResource("/icons/system.png")));
		iconLabel.setBounds(171, 50, 50, 48);
		frame2.getContentPane().add(iconLabel);
	}
}
