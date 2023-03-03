package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Dbconn;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AssignmentPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField assignmentOneTextField;
	private JTextField assignmentTwoTextField;
	private JTextField teacherNameTextField;
	private JComboBox moduleComboBox;
	private JButton postAssignmentBtn;
	
	
	private String selectedModuleFromComboBox = "";
	private String teacherName = "";
	private String assignmentOne = "";
	private String assignmentTwo = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AssignmentPage dialog = new AssignmentPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AssignmentPage() {
		setBounds(100, 100, 563, 365);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		{
			assignmentOneTextField = new JTextField();
			assignmentOneTextField.setColumns(10);
		}
		
		JLabel assignmentOneLabel = new JLabel("Assigment Ques 1");
		
		JLabel assignmentTwoLabel = new JLabel("Assignment Ques 2");
		
		assignmentTwoTextField = new JTextField();
		assignmentTwoTextField.setColumns(10);
		
		teacherNameTextField = new JTextField();
		teacherNameTextField.setColumns(10);
		
		postAssignmentBtn = new JButton("Post");

		moduleComboBox = new JComboBox();
		moduleComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					selectedModuleFromComboBox = (String) e.getItem();
					System.out.println(e.getItem());
				}
			}
		});
		moduleComboBox.setModel(new DefaultComboBoxModel(new String[] {"OODP", "NMC", "AI", "DataBase", "ISA", "C#"}));
		
		JLabel moduleComboBoxLabel = new JLabel("Module ");
		
		JLabel teacherNameLabel = new JLabel("Teacher Name");
		
		postAssignmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				teacherName = teacherNameTextField.getText().trim();
				assignmentOne = assignmentOneTextField.getText().trim();
				assignmentTwo = assignmentTwoTextField.getText().trim();
				
				if(teacherName.equals("") || assignmentOne.equals("") || assignmentTwo.equals("")) {
					JOptionPane.showMessageDialog(teacherNameTextField, "Some fields are empty!!", "Error", 1);
				} else {
					
				Statement statement = Dbconn.getStatement();
				
				String insertQuery = "INSERT INTO `assignment` (`Id`, `Teacher_Name`, `Module_Name`, `AssignmentOne`, `AssignmentTwo`) "
						+ "VALUES (NULL, '"+teacherName+"', '"+selectedModuleFromComboBox+"', '"+assignmentOne+"', '"+assignmentTwo+"');";
				try {
					int insertSuccess = statement.executeUpdate(insertQuery);
					if(insertSuccess == 1) {
						JOptionPane.showMessageDialog(teacherNameTextField, "Saved into database");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}

				TeacherDashBoard.getAssignmentDataFromDb();
				
			
			}
		});
		

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(assignmentTwoLabel, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
						.addComponent(assignmentTwoTextField))
					.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(teacherNameLabel)
						.addComponent(moduleComboBoxLabel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addComponent(moduleComboBox, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(assignmentOneLabel, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
						.addComponent(assignmentOneTextField, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
						.addComponent(teacherNameTextField, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(417, Short.MAX_VALUE)
					.addComponent(postAssignmentBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(teacherNameLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(teacherNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(moduleComboBoxLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(moduleComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(assignmentOneLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(assignmentOneTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(assignmentTwoLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(assignmentTwoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(postAssignmentBtn))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
