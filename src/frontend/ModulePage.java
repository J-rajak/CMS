package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Dbconn;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ModulePage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField creditValueTextField;
	private JComboBox courseComboBox;
	private JTextField moduleNameTextField;
	private JComboBox levelComboBox;
	private JComboBox semesterComboBox;
	private JCheckBox optionalModuleCheckBox;
	private JButton addBtn;
	private Statement statement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModulePage dialog = new ModulePage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModulePage() {
		setTitle("Module Page");
		setBounds(100, 100, 652, 454);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel moduleNameLabel = new JLabel("Module Name");
		
		JLabel courseNameLabel = new JLabel("Course Name");
		
		JLabel levelLabel = new JLabel("Level");
		
		JLabel semesterLabel = new JLabel("Semester");
		
		JLabel creditValueLabel = new JLabel("Credit Value");
		
		optionalModuleCheckBox = new JCheckBox("Optional Module");
		

		
		moduleNameTextField = new JTextField();
		moduleNameTextField.setColumns(10);
		
		creditValueTextField = new JTextField();
		creditValueTextField.setColumns(10);
		
		courseComboBox = new JComboBox();
		
		statement = Dbconn.getStatement();
		
		String selectQuery = "SELECT Course_Name FROM `course`";
		
		String courseNames [] = new String[3];
		int i = 0;
		try {
			ResultSet resultSet = statement.executeQuery(selectQuery);
			while(resultSet.next()) {
				String courseNameFromDB = resultSet.getString("Course_Name");
				courseNames[i] = courseNameFromDB;
				i++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		courseComboBox.setModel(new DefaultComboBoxModel(new String[] {courseNames[0], courseNames[1], courseNames[2]}));
		
		levelComboBox = new JComboBox();
		levelComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Level", "Level 4", "Level 5", "Level 6"}));
		
		semesterComboBox = new JComboBox();
		semesterComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Semester", "Semester 1", "Semester 2", "Semester 3"}));
		
		addBtn = new JButton("Add ");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String moduleName = moduleNameTextField.getText().trim();
				String selectedCourseName = courseComboBox.getSelectedItem().toString();
				String selectedLevel = levelComboBox.getSelectedItem().toString();
				String creditValue = creditValueTextField.getText().trim();
				String selectedSemester = semesterComboBox.getSelectedItem().toString();
				String isOptionalModule = "";
				
				if(optionalModuleCheckBox.isSelected()) {
					isOptionalModule ="YES";
				}else {
					isOptionalModule ="NO";
				}
			
				Statement statement1 = Dbconn.getStatement();
				
				String insertQuery = "INSERT INTO `module` (`Module_Name`, `Course_Name`, `Level`, `Semester`, `Credit_Value`, `Optional_Module`) "
						+ "VALUES ('"+moduleName +"', '"+selectedCourseName +"', '"+selectedLevel +"', '"+selectedSemester +"', '"+ creditValue+"', '"+isOptionalModule +"')";
						
						
						try {
							int insertSuccess = statement1.executeUpdate(insertQuery);
							if(insertSuccess == 1) {
								JOptionPane.showMessageDialog(contentPanel, "Saved into database!");

							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
				
				
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(creditValueLabel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(creditValueTextField, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(semesterLabel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(semesterComboBox, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(levelLabel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(levelComboBox, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(courseNameLabel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(moduleNameLabel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(moduleNameTextField, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(176, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(501, Short.MAX_VALUE)
					.addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(52)
					.addComponent(optionalModuleCheckBox)
					.addContainerGap(471, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(moduleNameLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(moduleNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(courseNameLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(levelLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(levelComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(semesterLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(semesterComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(creditValueLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(creditValueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(optionalModuleCheckBox)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(addBtn))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
