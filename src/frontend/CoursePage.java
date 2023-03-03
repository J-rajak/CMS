package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import backend.Dbconn;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class CoursePage extends JDialog {

	private final JPanel contentPanel;
	private JTextField courseNameTextField;
	private JTextField noOfModulesTextField;
	private JTextField lengthTextField;
	private JButton addCourseBtn;
	private JCheckBox isActivatedCheckBox;
	
	private String courseName = "";
	private String noOfModules = "";
	private String isActivated = "NO";
	private String length = "";
	private JLabel lblNewLabel;

	
	public JCheckBox getIsActivatedCheckBox() {
		return isActivatedCheckBox;
	}

	public JTextField getCourseNameTextField() {
		return courseNameTextField;
	}

	public JTextField getNoOfModulesTextField() {
		return noOfModulesTextField;
	}

	public JTextField getLengthTextField() {
		return lengthTextField;
	}
	
	public JButton getAddCourseBtn() {
		return addCourseBtn;
	}


	public CoursePage() {
		setTitle("Add Course Page");
		setBounds(100, 100, 640, 471);
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(209, 209, 209));
		setContentPane(contentPanel);
		
		JLabel courseNameLabel = new JLabel("Course Name");
		courseNameLabel.setBounds(32, 114, 97, 29);
		
		JLabel noOfModulesLabel = new JLabel("No. Of Modules");
		noOfModulesLabel.setBounds(32, 178, 97, 29);
		
		JLabel lengthLabel = new JLabel("Length");
		lengthLabel.setBounds(32, 239, 97, 29);
		
		 isActivatedCheckBox = new JCheckBox("Activated");
		 isActivatedCheckBox.setBounds(32, 325, 121, 23);
		 isActivatedCheckBox.setBackground(new Color(209, 209, 209));
		
		courseNameTextField = new JTextField();
		courseNameTextField.setBounds(139, 118, 361, 20);
		courseNameTextField.setColumns(10);
		
		noOfModulesTextField = new JTextField();
		noOfModulesTextField.setBounds(139, 182, 361, 20);
		noOfModulesTextField.setColumns(10);
		
		lengthTextField = new JTextField();
		lengthTextField.setBounds(139, 243, 361, 20);
		lengthTextField.setColumns(10);
		
		addCourseBtn = new JButton("Add");
		addCourseBtn.setBounds(474, 388, 121, 23);
		addCourseBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("Add")) {
				courseName = courseNameTextField.getText().trim();
				noOfModules = noOfModulesTextField.getText().trim();
				length = lengthTextField.getText().trim();
				
				
				if(isActivatedCheckBox.isSelected()) {
					isActivated = "YES";
				} else {
					isActivated = "NO";
				}
				
				if(courseName.equals("") || noOfModules.equals("") || length.equals("")) {
					JOptionPane.showMessageDialog(courseNameTextField, "Some fields are empty!!", "Error", 1);
				} else {
					
					Statement statement = Dbconn.getStatement();
					
					String insertQuery = "INSERT INTO `course` (`Course_Id`, `Course_Name`, `No_Of_Modules`, `Active_Status`, `Length`)"
							+ " VALUES (NULL, '"+courseName+"', '"+noOfModules+"', '"+isActivated+"', '"+length+"');";
					
					try {
						int insertSuccess = statement.executeUpdate(insertQuery);
						if(insertSuccess == 1) {
							JOptionPane.showMessageDialog(courseNameTextField, "Saved into database");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				

				AdminDashBoard.getCourseDataFromDb();
				
			}
	
		}
	});
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		contentPanel.add(isActivatedCheckBox);
		contentPanel.add(lengthLabel);
		contentPanel.add(lengthTextField);
		contentPanel.add(noOfModulesLabel);
		contentPanel.add(noOfModulesTextField);
		contentPanel.add(courseNameLabel);
		contentPanel.add(courseNameTextField);
		contentPanel.add(addCourseBtn);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CoursePage.class.getResource("/icons/system.png")));
		lblNewLabel.setBounds(265, 11, 56, 58);
		contentPanel.add(lblNewLabel);


	}


}
