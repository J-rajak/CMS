package frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import backend.Dbconn;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class TeacherDashBoard {

	JFrame frame;
	private JPanel cardPanel;
	private CardLayout cl_cardPanel = new CardLayout(0, 0);
	private static DefaultTableModel moduleAssignedDefaultTableModel = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Teacher Name", "Module_1", "Module_2", "Module_3", "Module_4"
			}
		);
	
	private static DefaultTableModel marksDefaultTableModel = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Id", "Student_Name", "Level", "Module_1", "Module_2", "Module_3", "Module_4", "Module_5", "Module_6", "Module_7", "Module_8"
			});
	
	private static DefaultTableModel assignmentDefaultTableModel = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Id", "Teacher_Name", "Module_Name", "Assignment_one", "Assignment_two"
			}
		);
	
	private static DefaultTableModel registeredStudentsDefaultTableModel = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"NMC", "ISA", "OODP", "ISP", "DSA", "AI"
			}
		);
	
	
	private JButton assignedModules;
	private JButton assignmentBtn;
	private JButton logoutBtn;
	private JTable ModuleAssignedTable;
	private JTable assignementTable;
	private JTable marksTable;
	private JTable table;
	private JLabel welcomeLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherDashBoard window = new TeacherDashBoard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void getAssignmentDataFromDb(){
		Statement statement = Dbconn.getStatement();
		String selectQuery = "SELECT * FROM `assignment`";
		
		ResultSet resultSet;
		try {
			resultSet = statement.executeQuery(selectQuery);
			assignmentDefaultTableModel.setRowCount(0);
			while(resultSet.next()) {
				int idFromDb = resultSet.getInt("Id");
				String teacherNameFromDb = resultSet.getString("Teacher_Name");
				String moduleNameFromDb = resultSet.getString("Module_Name");
				String assignmentOneFromDb = resultSet.getString("AssignmentOne");
				String assignmentTwoFromDb = resultSet.getString("AssignmentTwo");
				
				assignmentDefaultTableModel.addRow(new Object[] {
					idFromDb,
					teacherNameFromDb,
					moduleNameFromDb,
					assignmentOneFromDb,
					assignmentTwoFromDb
				});
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getMarksDataFromDb() {
		Statement statement = Dbconn.getStatement();
		String selectQuery = "SELECT * FROM `marks`";
		
		ResultSet resultSet;
		try {
			resultSet = statement.executeQuery(selectQuery);
			marksDefaultTableModel.setRowCount(0);
			while(resultSet.next()) {
				int idFromDb = resultSet.getInt("student_Id");
				String nameFromDb = resultSet.getString("Student_Name");
				int levelFromDb = resultSet.getInt("Level");
				int moduleOne = resultSet.getInt("Module_1");
				int moduleTwo = resultSet.getInt("Module_2");
				int moduleThree = resultSet.getInt("Module_3");
				int moduleFour = resultSet.getInt("Module_4");
				int moduleFive = resultSet.getInt("Module_5");
				int moduleSix = resultSet.getInt("Module_6");
				int moduleSeven = resultSet.getInt("Module_7");
				int moduleEight = resultSet.getInt("Module_8");
				
				marksDefaultTableModel.addRow(new Object[] {
					idFromDb,
					nameFromDb,
					levelFromDb,
					moduleOne,
					moduleTwo,
					moduleThree,
					moduleFour,
					moduleFive,
					moduleSix,
					moduleSeven,
					moduleEight
				});
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void getModuleAssignedDataFromDb() {
		Statement statement = Dbconn.getStatement();
		String selectQuery = "SELECT * FROM `assigned_modules`";
		
		ResultSet resultSet;
		try {
			resultSet = statement.executeQuery(selectQuery);
			moduleAssignedDefaultTableModel.setRowCount(0);
			while(resultSet.next()) {
				String teacherNameFromDb = resultSet.getString("Teacher_Name");
				String moduleOneFromDb = resultSet.getString("Module_1");
				String moduleTwoFromDb = resultSet.getString("Module_2");
				String moduleThreeFromDb = resultSet.getString("Module_3");
				String moduleFourFromDb = resultSet.getString("Module_4");
				
				moduleAssignedDefaultTableModel.addRow(new Object[] {
						teacherNameFromDb,
						moduleOneFromDb,
						moduleTwoFromDb,
						moduleThreeFromDb,
						moduleFourFromDb
				});
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getRegisteredStudentsDataFromDb() {
		Statement statement = Dbconn.getStatement();
		String selectQuery = "SELECT * FROM `registered_students`";
		
		ResultSet resultSet;
		try {
			resultSet = statement.executeQuery(selectQuery);
			registeredStudentsDefaultTableModel.setRowCount(0);
			while(resultSet.next()) {
				String moduleOneFromDb = resultSet.getString("NMC");
				String moduleTwoFromDb = resultSet.getString("ISA");
				String moduleThreeFromDb = resultSet.getString("OODP");
				String moduleFourFromDb = resultSet.getString("ISP");
				String moduleFiveFromDb = resultSet.getString("DSA");
				String moduleSixFromDb = resultSet.getString("AI");
				
				registeredStudentsDefaultTableModel.addRow(new Object[] {
						moduleOneFromDb,
						moduleTwoFromDb,
						moduleThreeFromDb,
						moduleFourFromDb,
						moduleFiveFromDb,
						moduleSixFromDb
				});
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public TeacherDashBoard() {
		initialize();
	}
	
	public TeacherDashBoard(String uname) {
		initialize();
		welcomeLabel.setText("Welcome " +uname);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(206, 206, 206));
		frame.setTitle("Teacher DashBoard");
		frame.setBounds(100, 100, 905, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(188, 188, 188));
		
		cardPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cardPanel, GroupLayout.PREFERRED_SIZE, 660, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(cardPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		welcomeLabel = new JLabel("Welcome Tutor");
		welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TeacherDashBoard.class.getResource("/icons/system.png")));
		
		assignedModules = new JButton("Assigned Modules");
		
		assignmentBtn = new JButton("Assignements");
		assignmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cardPanel.show(cardPanel, "name_21250577992100");
			}
		});
		
		logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Yes", "No"};
				int n = JOptionPane.showOptionDialog(null,
						"Are you sure you want to logout?",
						"Do you want to logout", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				if(n == 0) {
					UserLoginPage userLoginPage = new UserLoginPage();
					userLoginPage.frame.setVisible(true);
					frame.dispose();
				} else if (n == 1) {
					// stay logged in
				}
			}
		});
		
		JButton viewStudentBtn = new JButton("Assign Marks");
		viewStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cardPanel.show(cardPanel, "name_49760655066600");
			}
		});
		
		JButton viewRegisteredStudentsBtn = new JButton("View Students");
		viewRegisteredStudentsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cardPanel.show(cardPanel, "name_21145658931000");
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(45)
					.addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
					.addGap(50))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(77)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(72))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(viewRegisteredStudentsBtn, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
							.addGap(35))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(viewStudentBtn, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
								.addGap(35))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
									.addComponent(logoutBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
									.addComponent(assignmentBtn, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
									.addComponent(assignedModules, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
								.addGap(35)))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(28)
					.addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addGap(33)
					.addComponent(assignedModules, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(30)
					.addComponent(assignmentBtn, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(27)
					.addComponent(viewStudentBtn, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(viewRegisteredStudentsBtn, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(44)
					.addComponent(logoutBtn, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(75))
		);
		panel_1.setLayout(gl_panel_1);
		cardPanel.setLayout(cl_cardPanel);
		
		JPanel moduleAssignedPanel = new JPanel();
		moduleAssignedPanel.setBackground(new Color(188, 188, 188));
		cardPanel.add(moduleAssignedPanel, "name_21227050352500");
		
		JScrollPane moduleAssignedScrollPane = new JScrollPane();
		GroupLayout gl_moduleAssignedPanel = new GroupLayout(moduleAssignedPanel);
		gl_moduleAssignedPanel.setHorizontalGroup(
			gl_moduleAssignedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_moduleAssignedPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(moduleAssignedScrollPane, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_moduleAssignedPanel.setVerticalGroup(
			gl_moduleAssignedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_moduleAssignedPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(moduleAssignedScrollPane, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		ModuleAssignedTable = new JTable();
		ModuleAssignedTable.setModel(moduleAssignedDefaultTableModel);
		ModuleAssignedTable.getColumnModel().getColumn(0).setPreferredWidth(98);
		moduleAssignedScrollPane.setViewportView(ModuleAssignedTable);
		moduleAssignedPanel.setLayout(gl_moduleAssignedPanel);
		
		JPanel assignmentPanel = new JPanel();
		cardPanel.add(assignmentPanel, "name_21250577992100");
		
		JScrollPane assignmentScrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		GroupLayout gl_assignmentPanel = new GroupLayout(assignmentPanel);
		gl_assignmentPanel.setHorizontalGroup(
			gl_assignmentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_assignmentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_assignmentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
						.addComponent(assignmentScrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_assignmentPanel.setVerticalGroup(
			gl_assignmentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_assignmentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(assignmentScrollPane, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		
		JButton addAssignmentBtn = new JButton("Add");
		addAssignmentBtn.setIcon(new ImageIcon(TeacherDashBoard.class.getResource("/icons/books.png")));
		addAssignmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssignmentPage assignmentPage = new AssignmentPage();
				assignmentPage.setVisible(true);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(470, Short.MAX_VALUE)
					.addComponent(addAssignmentBtn, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(addAssignmentBtn, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		assignementTable = new JTable();
		assignementTable.setModel(assignmentDefaultTableModel);
		assignementTable.getColumnModel().getColumn(1).setPreferredWidth(90);
		assignementTable.getColumnModel().getColumn(2).setPreferredWidth(105);
		assignementTable.getColumnModel().getColumn(3).setPreferredWidth(98);
		assignementTable.getColumnModel().getColumn(4).setPreferredWidth(96);
		assignmentScrollPane.setViewportView(assignementTable);
		assignmentPanel.setLayout(gl_assignmentPanel);
		
		JPanel studentPanel = new JPanel();
		cardPanel.add(studentPanel, "name_49760655066600");
		
		JScrollPane studentScrollPane = new JScrollPane();
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_studentPanel = new GroupLayout(studentPanel);
		gl_studentPanel.setHorizontalGroup(
			gl_studentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_studentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_studentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
						.addComponent(studentScrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE))
					.addGap(7))
		);
		gl_studentPanel.setVerticalGroup(
			gl_studentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_studentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(studentScrollPane, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton addMarks = new JButton("Add Marks");
		addMarks.setIcon(new ImageIcon(TeacherDashBoard.class.getResource("/icons/university.png")));
		addMarks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MarksPage marksPage = new MarksPage();
				marksPage.setVisible(true);
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addGap(466)
					.addComponent(addMarks, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(addMarks, GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		marksTable = new JTable();
		marksTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String studentId = marksTable.getValueAt(marksTable.getSelectedRow(), 0).toString();
				Object[] options = { "Update Marks", "Delete Marks"};
				int n = JOptionPane.showOptionDialog(null,
						"Do you want to update delete marks?",
						" Update or Delete marks", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				
				if(n == 0) {
					MarksPage updateMarksPage = new MarksPage();
					updateMarksPage.setVisible(true);
					updateMarksPage.setTitle("Update MarksPage");
					
					JButton updateBtn = updateMarksPage.getAddMarksBtn();
					updateBtn.setText("Update");
					
					String studentName = "";
					String selectedLevelFromComboBox = "";
					String moduleOne = "";
					String moduleTwo = "";
					String moduleThree = "";
					String moduleFour = "";
					String moduleFive = "";
					String moduleSix = "";
					String moduleSeven = "";
					String moduleEight = "";
					
					for(int columnIndex = 1; columnIndex < marksTable.getColumnCount(); columnIndex++) {
						if(studentName.isEmpty()) {
							studentName = marksTable.getValueAt(marksTable.getSelectedRow(), columnIndex).toString();
						} else if(selectedLevelFromComboBox.isEmpty()){
							selectedLevelFromComboBox = marksTable.getValueAt(marksTable.getSelectedRow(), columnIndex).toString();
						} else if(moduleOne.isEmpty()){
							moduleOne = marksTable.getValueAt(marksTable.getSelectedRow(), columnIndex).toString();
						} else if(moduleTwo.isEmpty()) {
							moduleTwo = marksTable.getValueAt(marksTable.getSelectedRow(), columnIndex).toString();
						}else if(moduleThree.isEmpty()) {
							moduleThree = marksTable.getValueAt(marksTable.getSelectedRow(), columnIndex).toString();
						} else if(moduleFour.isEmpty()) {
							moduleFour = marksTable.getValueAt(marksTable.getSelectedRow(), columnIndex).toString();
						}else if(moduleFive.isEmpty()) {
							moduleFive = marksTable.getValueAt(marksTable.getSelectedRow(), columnIndex).toString();
						}else if(moduleSix.isEmpty()) {
							moduleSix = marksTable.getValueAt(marksTable.getSelectedRow(), columnIndex).toString();
						}else if(moduleSeven.isEmpty()) {
							moduleSeven = marksTable.getValueAt(marksTable.getSelectedRow(), columnIndex).toString();
						}else if(moduleEight.isEmpty()) {
							moduleEight = marksTable.getValueAt(marksTable.getSelectedRow(), columnIndex).toString();
						}
					}
					
					
					updateMarksPage.getStudentNameTextField().setText(studentName);
					updateMarksPage.getModuleOneTextField().setText(moduleOne);
					updateMarksPage.getModuleTwoTextField().setText(moduleTwo);
					updateMarksPage.getModuleThreeTextField().setText(moduleThree);
					updateMarksPage.getModuleFourTextField().setText(moduleFour);
					updateMarksPage.getModuleFiveTextField().setText(moduleFive);
					updateMarksPage.getModuleSixTextField().setText(moduleSix);
					updateMarksPage.getModuleSevenTextField().setText(moduleSeven);
					updateMarksPage.getModuleEightTextField().setText(moduleEight);
					
					JComboBox levelComboBox = updateMarksPage.getSelectedLevelFromComboBox();
					ComboBoxModel model = levelComboBox.getModel();
					
					for(int i = 0; i <= 2; i++) {
						if(model.getElementAt(i).equals(selectedLevelFromComboBox)) {
							model.setSelectedItem(model.getElementAt(i));
							break;
						}
					}
					
					updateBtn.setActionCommand("Update");
					updateBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							
							JTextField studentNameTextField = updateMarksPage.getStudentNameTextField();
							JTextField moduleOneField = updateMarksPage.getModuleOneTextField();
							JTextField moduleTwoField = updateMarksPage.getModuleTwoTextField();
							JTextField moduleThreeField = updateMarksPage.getModuleThreeTextField();
							JTextField moduleFourField = updateMarksPage.getModuleFourTextField();
							JTextField moduleFiveField = updateMarksPage.getModuleFiveTextField();
							JTextField moduleSixField = updateMarksPage.getModuleSixTextField();
							JTextField moduleSevenField = updateMarksPage.getModuleSevenTextField();
							JTextField moduleEightField = updateMarksPage.getModuleEightTextField();
							JComboBox levelTextField = updateMarksPage.getSelectedLevelFromComboBox();
							
					
							String updatedStudentName = studentNameTextField.getText().trim();
							String updatedModuleOneField = moduleOneField.getText().trim();
							String updatedModuleTwoField = moduleTwoField.getText().trim();
							String updatedModuleThreeField = moduleThreeField.getText().trim();
							String updatedModuleFourField = moduleFourField.getText().trim();
							String updatedModuleFiveField = moduleFiveField.getText().trim();
							String updatedModuleSixField = moduleSixField.getText().trim();
							String updatedModuleSevenField = moduleSevenField.getText().trim();
							String updatedModuleEightField = moduleEightField.getText().trim();
							String updatedSelectedLevel = "";
							ComboBoxModel comboBoxModel = levelComboBox.getModel();
							Object selectedItem = comboBoxModel.getSelectedItem();
							updatedSelectedLevel = selectedItem.toString();
							
							String updatedQuery = "UPDATE `marks` SET `Student_Name` = '"+updatedStudentName+"',"
									+ " `Level` = '"+updatedSelectedLevel+"', `Module_1` = '"+updatedModuleOneField+"', `Module_2` = '"+updatedModuleTwoField+"', `Module_3` = '"+updatedModuleThreeField+"', "
									+ "`Module_4` = '"+updatedModuleFourField+"', `Module_5` = '"+updatedModuleFiveField+"', `Module_6` = '"+updatedModuleSixField+"', `Module_7` = '"+updatedModuleSevenField+"', "
									+ "`Module_8` = '"+updatedModuleEightField+"' WHERE `marks`.`student_Id` = "+studentId+";";
							
							Statement statement = Dbconn.getStatement();
							try {
								int updateSuccess = statement.executeUpdate(updatedQuery);
								if(updateSuccess == 1) {
									JOptionPane.showMessageDialog(studentScrollPane, "Data is Updated");
								} else {
									JOptionPane.showMessageDialog(studentScrollPane, "Something went wrong while updating");
								}
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							TeacherDashBoard.getMarksDataFromDb();
							
							System.out.println("Update button is working");
						}
						});
				} else if(n == 1) {
					Statement statement = Dbconn.getStatement();
					
					String deleteQuery =  "DELETE FROM marks WHERE `marks`.`student_Id` = "+studentId+" ";
					try {
						int deleteSuccess = statement.executeUpdate(deleteQuery);
						if(deleteSuccess == 1) {
							JOptionPane.showMessageDialog(studentScrollPane, "Deleted!!!");
						} else {
							JOptionPane.showMessageDialog(studentScrollPane, "Something went wrong");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					TeacherDashBoard.getMarksDataFromDb();
				}
				
			}
		});
		marksTable.setModel(marksDefaultTableModel);
		marksTable.getColumnModel().getColumn(1).setPreferredWidth(90);
		studentScrollPane.setViewportView(marksTable);
		studentPanel.setLayout(gl_studentPanel);
		
		JPanel viewReisteredStudentsPanel = new JPanel();
		cardPanel.add(viewReisteredStudentsPanel, "name_21145658931000");
		
		JPanel registeredStudentsPanel = new JPanel();
		GroupLayout gl_viewReisteredStudentsPanel = new GroupLayout(viewReisteredStudentsPanel);
		gl_viewReisteredStudentsPanel.setHorizontalGroup(
			gl_viewReisteredStudentsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_viewReisteredStudentsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(registeredStudentsPanel, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_viewReisteredStudentsPanel.setVerticalGroup(
			gl_viewReisteredStudentsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_viewReisteredStudentsPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(registeredStudentsPanel, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JScrollPane registeredStudentsScrollPane = new JScrollPane();
		GroupLayout gl_registeredStudentsPanel = new GroupLayout(registeredStudentsPanel);
		gl_registeredStudentsPanel.setHorizontalGroup(
			gl_registeredStudentsPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(registeredStudentsScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
		);
		gl_registeredStudentsPanel.setVerticalGroup(
			gl_registeredStudentsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_registeredStudentsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(registeredStudentsScrollPane)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(registeredStudentsDefaultTableModel);
		registeredStudentsScrollPane.setViewportView(table);
		registeredStudentsPanel.setLayout(gl_registeredStudentsPanel);
		viewReisteredStudentsPanel.setLayout(gl_viewReisteredStudentsPanel);
		
		frame.getContentPane().setLayout(groupLayout);
		
		assignedModules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cardPanel.show(cardPanel, "name_21227050352500");
			}
		});
		
		TeacherDashBoard.getMarksDataFromDb();
		TeacherDashBoard.getAssignmentDataFromDb();
		TeacherDashBoard.getModuleAssignedDataFromDb();
		TeacherDashBoard.getRegisteredStudentsDataFromDb();
	}
}
