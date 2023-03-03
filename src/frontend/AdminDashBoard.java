package frontend;
import java.awt.EventQueue;
import backend.Dbconn;
import javax.swing.JFrame;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class AdminDashBoard{

	JFrame frame;
	private CardLayout cl_cardPanel = new CardLayout(0, 0);
	private JPanel cardPanel;
	private JTable teacherTable;
	private static DefaultTableModel tutorDefaultTableModel = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Name", "Phone Number", "Address", "Sex", "Module Assigned", "Date Of Birth", "Full Time"
			});
	
	private static DefaultTableModel courseDefaultTableModel = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Course ID", "Course Name", "No. Of Modules", "Active Status", "Length"
			});
	
	private static DefaultTableModel studentDefaultTableModel = new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Id", "Student_Name", "Semester", "Level"
			});
	
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
	
	private JTable table;
	private JTable courseTable;
	private JTable studentTable;
	private JTable marksTable;
	private JLabel welcomeLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashBoard window = new AdminDashBoard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public AdminDashBoard(){
		initialize();
	}
	
	public AdminDashBoard(String uname){
		initialize();
		welcomeLabel.setText("Welcome " +uname);
	}
	
	public static void getTutorDataInTableFromDb(){
		Statement statement = Dbconn.getStatement();
		String selectQuery = "SELECT * FROM `tutor`";
		
		ResultSet resultSet;
		try {
			resultSet = statement.executeQuery(selectQuery);
			tutorDefaultTableModel.setRowCount(0);
			while(resultSet.next()) {
				int idFromDb = resultSet.getInt("Id");
				String nameFromDb = resultSet.getString("Name");
				BigDecimal numberFromDb = resultSet.getBigDecimal("Phone_Number");
				String addressFromDb = resultSet.getString("Address");
				String sexFromDb = resultSet.getString("Sex");
				String moduleAssignedFromDb = resultSet.getString("Module_Assigned");
				Date dateOfBirthFromDb = resultSet.getDate("Date_Of_Birth");
				String fullTimeFromDb = resultSet.getString("Full_Time");
				
				tutorDefaultTableModel.addRow(new Object[] {
					idFromDb,
					nameFromDb,
					numberFromDb,
					addressFromDb,
					sexFromDb,
					moduleAssignedFromDb,
					dateOfBirthFromDb,
					fullTimeFromDb
				});
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getCourseDataFromDb() {
		Statement statement = Dbconn.getStatement();
		String selectQuery = "SELECT * FROM `course`";
		
		ResultSet resultSet;
		try {
			resultSet = statement.executeQuery(selectQuery);
			courseDefaultTableModel.setRowCount(0);
			while(resultSet.next()) {
				int courseIdFromDb = resultSet.getInt("Course_Id");
				String courseNameFromDb = resultSet.getString("Course_Name");
				int noOfModulesFromDb = resultSet.getInt("No_Of_Modules");
				String activeStatusFromDb = resultSet.getString("Active_Status");
				int lengthFromDb = resultSet.getInt("Length");
				
				courseDefaultTableModel.addRow(new Object[] {
					courseIdFromDb,
					courseNameFromDb,
					noOfModulesFromDb,
					activeStatusFromDb,
					lengthFromDb
				});
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void getStudentDataFromDb() {
		Statement statement = Dbconn.getStatement();
		String selectQuery = "SELECT * FROM `student`";
		
		ResultSet resultSet;
		try {
			resultSet = statement.executeQuery(selectQuery);
			studentDefaultTableModel.setRowCount(0);
			while(resultSet.next()) {
				int studentIdFromDb = resultSet.getInt("Id");
				String studentNameFromDb = resultSet.getString("Student_Name");
				String semesterFromDb = resultSet.getString("Semester");
				String levelFromDb = resultSet.getString("Level");
				
				studentDefaultTableModel.addRow(new Object[] {
					studentIdFromDb,
					studentNameFromDb,
					semesterFromDb,
					levelFromDb
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
	

	private void initialize(){
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(206, 206, 206));
		frame.setBounds(100, 100, 903, 579);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(188, 188, 188));
		
		cardPanel = new JPanel();
		cardPanel.setBackground(new Color(188, 188, 188));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cardPanel, GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
							.addGap(35))
						.addComponent(cardPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(0))
		);
		cardPanel.setLayout(cl_cardPanel);
		
		JPanel coursePanel = new JPanel();
		coursePanel.setBackground(new Color(188, 188, 188));
		cardPanel.add(coursePanel, "name_3194150471600");
		
		JPanel courseHeaderPanel = new JPanel();
		
		JScrollPane courseScrollPane = new JScrollPane();
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_coursePanel = new GroupLayout(coursePanel);
		gl_coursePanel.setHorizontalGroup(
			gl_coursePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_coursePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_coursePanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_coursePanel.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(courseHeaderPanel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
						.addComponent(courseScrollPane, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_coursePanel.setVerticalGroup(
			gl_coursePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_coursePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_coursePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 122, Short.MAX_VALUE)
						.addComponent(courseHeaderPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(27)
					.addComponent(courseScrollPane, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel universityIconLabel = new JLabel("By studying at the University of Wolverhampton \r\nstudents can gain valuable knowledge and skills \r\nwhich will equip them for further study or employment \r\nin their chosen field. Graduates have gone on to pursue\r\n successful careers in fields such as teaching, medicine \r\nand law. The University also has strong links with \r\nemployers which gives its graduates access to\r\n internships and work placements throughout the UK.");
		universityIconLabel.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/icons/university.png")));
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textArea.setText("By studying at the University of Wolverhampton students can gain valuable knowledge and skills which will equip them for further study or employment in their chosen field. ");
		textArea.setLineWrap(true);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(universityIconLabel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
						.addComponent(universityIconLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		courseTable = new JTable();
		courseTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				String courseId = courseTable.getValueAt(courseTable.getSelectedRow(), 0).toString();
				String courseName = courseTable.getValueAt(courseTable.getSelectedRow(), 1).toString();

				Object[] options = { "Update Course", "Deactivate Course", "Delete Course", "Add/View Modules" };
				int n = JOptionPane.showOptionDialog(null,
						"Do you want to update or deactivate or delete course or View Modules?",
						" Update ot Deactivate or Delete Course", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				
				if(n == 0) { //update
					
					CoursePage updateCoursePage = new CoursePage();
					updateCoursePage.setVisible(true);
					updateCoursePage.setTitle("Update Course Page");
					
					JButton updateBtn = updateCoursePage.getAddCourseBtn();
					updateBtn.setText("Update");
					
					String name = "";
					String noOfModules = "";
					String isActive = "";
					String length = "";
					
					for(int columnIndex = 1; columnIndex < courseTable.getColumnCount(); columnIndex++) {
						if(name.isEmpty()) {
							name = courseTable.getValueAt(courseTable.getSelectedRow(), columnIndex).toString();
						} else if(noOfModules.isEmpty()){
							noOfModules = courseTable.getValueAt(courseTable.getSelectedRow(), columnIndex).toString();
						} else if(isActive.isEmpty()){
							isActive = courseTable.getValueAt(courseTable.getSelectedRow(), columnIndex).toString();
						} else if(length.isEmpty()) {
							length = courseTable.getValueAt(courseTable.getSelectedRow(), columnIndex).toString();
						}
					}
							
					updateCoursePage.getCourseNameTextField().setText(name);
					updateCoursePage.getNoOfModulesTextField().setText(noOfModules);
					updateCoursePage.getLengthTextField().setText(length);
					
					JCheckBox isActiveCheckBox = updateCoursePage.getIsActivatedCheckBox();
					if(isActive.equals("YES")) {
						isActiveCheckBox.setSelected(true);
					} else if(isActive.equals("NO")) {
						isActiveCheckBox.setSelected(false);
					}
					
					updateBtn.setActionCommand("Update");
					updateBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if(e.getActionCommand().equals("Update")) {
								JTextField courseNameTextField = updateCoursePage.getCourseNameTextField();
								JTextField noOfModulesTextField = updateCoursePage.getNoOfModulesTextField();
								JTextField lengthTextField = updateCoursePage.getLengthTextField();
								JCheckBox isActiveCheckBox1 = updateCoursePage.getIsActivatedCheckBox();
								String oldCourseName = (String)courseTable.getValueAt(courseTable.getSelectedRow(), 1);
								
								String updatedCourseName = courseNameTextField.getText().trim();
								String updatedNoOfModules = noOfModulesTextField.getText().trim();
								String updatedLength = lengthTextField.getText().trim();
								String updatedIsActive = "NO";
								
								
								if(isActiveCheckBox.isSelected()) {
									updatedIsActive = "YES";
								} else {
									updatedIsActive = "NO";
								}
								
								String updateQuery = "UPDATE `course` SET "
										+ "`Course_Name` = '"+updatedCourseName+"', `No_Of_Modules` = '"+updatedNoOfModules+"', "
										+ "`Active_Status` = '"+updatedIsActive+"', `Length` = '"+updatedLength+"'"
										+ " WHERE `course`.`Course_Id` = "+courseId+"";
								
								
								Statement statement = Dbconn.getStatement();
								try {
									int updateSuccess = statement.executeUpdate(updateQuery);
									if(updateSuccess == 1) {
										JOptionPane.showMessageDialog(courseNameTextField, "Data is Updated");
									} else {
										JOptionPane.showMessageDialog(courseNameTextField, "Something went wrong while updating");
									}
									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								AdminDashBoard.getCourseDataFromDb(); 
								
								System.out.println("Update button is working");
							}
						}
						
					}
							);
					
					
					  
				}else if(n == 1) { // deactivate course

					int yesOrNo = JOptionPane.showConfirmDialog(null, "Are sure you want to deactivate this course?",
							"Deactivate the course?", JOptionPane.YES_NO_OPTION);
					if (yesOrNo == 0) {
						// Update query for particular course
						String updateQuery = "UPDATE `course` SET `Active_Status` = 'NO' "
								+ "WHERE `course`.`Course_Id` = '" + courseId + "'";

						Statement statement = Dbconn.getStatement();

						try {
							int updateSuccess = statement.executeUpdate(updateQuery);
							if (updateSuccess == 1) {
								JOptionPane.showMessageDialog(courseScrollPane, "Successfully deactivated the course into database!");
							} else {
								JOptionPane.showMessageDialog(courseScrollPane, "Something went wrong in deactivation operation ");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						AdminDashBoard.getCourseDataFromDb();
					}
					
				}else if(n == 2) { // delete course
					
					String deleteModuleQuery = "DELETE FROM module "
								+ "WHERE `module`.`Course_Name` = '"+courseName +"'";
					String deleteCourseQuery = "DELETE FROM course "
							+ "WHERE `course`.`Course_Id` = '"+courseId+"'";
					
					Statement statement = Dbconn.getStatement();
					try {
						statement.executeUpdate(deleteModuleQuery);
						statement.executeUpdate(deleteCourseQuery);
						JOptionPane.showMessageDialog(courseScrollPane, "Successfully deleted course and its realted modules as well from Database!");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					AdminDashBoard.getCourseDataFromDb();

					
				} else if(n == 3) {
					
					Object[] moduleOptions = { "Add Modules", "View Modules" };
					int selectedOption = JOptionPane.showOptionDialog(null, "Do you want to view or add Modules?",
							" Add or View Modules", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
							moduleOptions, moduleOptions[0]);
					if (selectedOption == 0) { // open add Module form
						ModulePage modulePage = new ModulePage();
						modulePage.setVisible(true);

					} else if (selectedOption == 1) {
						ModuleCRUDView moduleCRUDView = new ModuleCRUDView();
						moduleCRUDView.setVisible(true);
						ModuleCRUDView.getModuleDataFromDb();
					}
					
				}
				
			}
		});
		
		courseTable.setModel(courseDefaultTableModel);
		courseTable.getColumnModel().getColumn(0).setPreferredWidth(64);
		courseTable.getColumnModel().getColumn(1).setPreferredWidth(96);
		courseTable.getColumnModel().getColumn(2).setPreferredWidth(88);
		courseTable.getColumnModel().getColumn(3).setPreferredWidth(80);
		courseScrollPane.setViewportView(courseTable);
		

		
		JButton addCourseBtn = new JButton("Add Course");
		addCourseBtn.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/icons/course.png")));
		addCourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CoursePage addNewCourse = new CoursePage();
				addNewCourse.setVisible(true);
			}
		});
		GroupLayout gl_courseHeaderPanel = new GroupLayout(courseHeaderPanel);
		gl_courseHeaderPanel.setHorizontalGroup(
			gl_courseHeaderPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_courseHeaderPanel.createSequentialGroup()
					.addContainerGap(398, Short.MAX_VALUE)
					.addComponent(addCourseBtn, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_courseHeaderPanel.setVerticalGroup(
			gl_courseHeaderPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_courseHeaderPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(addCourseBtn, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		courseHeaderPanel.setLayout(gl_courseHeaderPanel);
		coursePanel.setLayout(gl_coursePanel);
		
		JPanel studentPanel = new JPanel();
		studentPanel.setBackground(new Color(188, 188, 188));
		cardPanel.add(studentPanel, "name_6771723028700");
		
		JPanel studentHeaderPanel = new JPanel();
		
		JButton addStudentBtn = new JButton("Add Student");
		addStudentBtn.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/icons/student.png")));
		
		addStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentPage studentPage = new StudentPage();
				studentPage.setVisible(true);
			}
		});
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/icons/random.png")));
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textArea_1.setLineWrap(true);
		textArea_1.setEditable(false);
		textArea_1.setText("We are one of the largest universities in the West Midlands, invested in helping students and graduates thrive both locally and beyond. We are proud to work closely with businesses across the West Midlands region and further afield, supporting innovation and development with forward-thinking graduate talent. ");
		GroupLayout gl_studentHeaderPanel = new GroupLayout(studentHeaderPanel);
		gl_studentHeaderPanel.setHorizontalGroup(
			gl_studentHeaderPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_studentHeaderPanel.createSequentialGroup()
					.addGap(18)
					.addComponent(iconLabel)
					.addGap(14)
					.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(addStudentBtn, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_studentHeaderPanel.setVerticalGroup(
			gl_studentHeaderPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_studentHeaderPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_studentHeaderPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(addStudentBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textArea_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
						.addComponent(iconLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		studentHeaderPanel.setLayout(gl_studentHeaderPanel);
		
		JScrollPane studentScrollPane = new JScrollPane();
		GroupLayout gl_studentPanel = new GroupLayout(studentPanel);
		gl_studentPanel.setHorizontalGroup(
			gl_studentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_studentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_studentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(studentHeaderPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(studentScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_studentPanel.setVerticalGroup(
			gl_studentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_studentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(studentHeaderPanel, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(studentScrollPane, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		studentTable = new JTable();
		studentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String studentId = studentTable.getValueAt(studentTable.getSelectedRow(), 0).toString();

				Object[] options = { "Update Student", "Delete Student" };
				int n = JOptionPane.showOptionDialog(null,
						"Do you want to update student?",
						"Delete Student", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				
				if(n == 0) { //update
					
					StudentPage updateStudentPage = new StudentPage();
					updateStudentPage.setVisible(true);
					updateStudentPage.setTitle("Update Student Page");
					
					JButton updateBtn = updateStudentPage.getStudentAddBtn();
					updateBtn.setText("Update");
					
					String name = "";
					String semester = "";
					String level = "";
					
					for(int columnIndex = 1; columnIndex < studentTable.getColumnCount(); columnIndex++) {
						if(name.isEmpty()) {
							name = studentTable.getValueAt(studentTable.getSelectedRow(), columnIndex).toString();
						} else if(semester.isEmpty()){
							semester = studentTable.getValueAt(studentTable.getSelectedRow(), columnIndex).toString();
						} else if(level.isEmpty()){
							level = studentTable.getValueAt(studentTable.getSelectedRow(), columnIndex).toString();
						} 
					}
					
//					System.out.println("name" +name);
//					System.out.println("modules" +noOfModules);
//					System.out.println("active" +isActive);
//					System.out.println("length" +length);
					
					updateStudentPage.getStudentNameTextField().setText(name);
					updateStudentPage.getStudentSemesterTextField().setText(semester);
					updateStudentPage.getStudentLevelTextField().setText(level);
					
					updateBtn.setActionCommand("Update");
					updateBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if(e.getActionCommand().equals("Update")) {
								JTextField studentNameTextField = updateStudentPage.getStudentNameTextField();
								JTextField studentSemesterTextField = updateStudentPage.getStudentSemesterTextField();
								JTextField studentLevelTextField = updateStudentPage.getStudentLevelTextField();
								
								String updatedStudentName = studentNameTextField.getText().trim();
								String updatedSemester = studentSemesterTextField.getText().trim();
								String updatedLevel = studentLevelTextField.getText().trim();
								
								String updateQuery = "UPDATE `student` SET "
										+ "`Student_Name` = '"+updatedStudentName+"', "
										+ "`Semester` = '"+updatedSemester+"', `Level` = '"+updatedLevel+"'"
										+ " WHERE `student`.`Id` = "+studentId+"";
								
								
								Statement statement = Dbconn.getStatement();
								try {
									int updateSuccess = statement.executeUpdate(updateQuery);
									if(updateSuccess == 1) {
										JOptionPane.showMessageDialog(studentNameTextField, "Data is Updated");
									} else {
										JOptionPane.showMessageDialog(studentNameTextField, "Something went wrong while updating");
									}
									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								AdminDashBoard.getStudentDataFromDb(); 
								
								System.out.println("Update button is working");
							}
						}
						
					}
							);

					
				} else if(n == 1) {
					Statement statement = Dbconn.getStatement();
					
					String deleteQuery =  "DELETE FROM student WHERE `student`.`Id` = "+studentId+" ";
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
					
					AdminDashBoard.getStudentDataFromDb();
					
				}
				
			}
		});
		studentTable.setModel(studentDefaultTableModel);
		studentTable.getColumnModel().getColumn(1).setPreferredWidth(101);
		studentScrollPane.setViewportView(studentTable);
		studentPanel.setLayout(gl_studentPanel);
		
		JPanel teacherPanel = new JPanel();
		teacherPanel.setBackground(new Color(188, 188, 188));
		cardPanel.add(teacherPanel, "name_245905159712600");
		
		JPanel teacherHeaderPanel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_teacherPanel = new GroupLayout(teacherPanel);
		gl_teacherPanel.setHorizontalGroup(
			gl_teacherPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_teacherPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_teacherPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(teacherHeaderPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_teacherPanel.setVerticalGroup(
			gl_teacherPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_teacherPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(teacherHeaderPanel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		teacherTable = new JTable();
		teacherTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("row selected");
				System.out.println(teacherTable.getSelectedRow());
				
				Object[] options = {"Update", "Delete"};
				int n = JOptionPane.showOptionDialog(null, "Do you want to Update or Delete?", "Update or Delete teacher"
						,JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]); // showOptionDialog returns 0 if update is selected and returns 1 if delete is selected
				if(n == 0) { //update
					TutorPage updateTutorPage = new TutorPage();
					updateTutorPage.setVisible(true);
					updateTutorPage.setTitle("Update Tutor Form");
					
					JButton updateBtn = updateTutorPage.getAddBtn();
					updateBtn.setText("Update");
					
					String name = "";
					String phoneNumber = "";
					String address = "";
					String sex = "";
					String isFullTime = "NO";
					String selectedModuleFromComboBox = "";
					String dateOfBirth = "";
					int selectedRowNo = teacherTable.getSelectedRow();
					
					for(int columnIndex = 1; columnIndex < teacherTable.getColumnCount(); columnIndex++) {
						if(name.isEmpty()) {
							name = teacherTable.getValueAt(teacherTable.getSelectedRow(), columnIndex).toString();
						} else if(phoneNumber.isEmpty()){
							phoneNumber = teacherTable.getValueAt(teacherTable.getSelectedRow(), columnIndex).toString();
						} else if(address.isEmpty()){
							address = teacherTable.getValueAt(teacherTable.getSelectedRow(), columnIndex).toString();
						} else if(sex.isEmpty()){
							sex = (String)teacherTable.getValueAt(teacherTable.getSelectedRow(), columnIndex).toString();
						} else if(selectedModuleFromComboBox.isEmpty()){
							selectedModuleFromComboBox = teacherTable.getValueAt(teacherTable.getSelectedRow(), columnIndex).toString();
						} else if(dateOfBirth.isEmpty()){
							dateOfBirth = teacherTable.getValueAt(teacherTable.getSelectedRow(), columnIndex).toString();
						} else {
							isFullTime = teacherTable.getValueAt(teacherTable.getSelectedRow(), columnIndex).toString();
						} 
					}
					
					updateTutorPage.getNameTextField().setText(name);
					updateTutorPage.getNumberTextField().setText(phoneNumber.toString());
					updateTutorPage.getAddressTextField().setText(address);
					ButtonGroup buttonGroup = updateTutorPage.getButtonGroup();
					
					
					for(Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
						AbstractButton button = buttons.nextElement();
						
						if(sex.equals(button.getText())) {
							button.setSelected(true);
						}
			 		}
					updateTutorPage.getDobTextField().setText(dateOfBirth);
					
					JCheckBox isFullTimeCheckBox = updateTutorPage.getIsFullTimeCheckBox();
					
					if(isFullTime.equals("YES")) {
						isFullTimeCheckBox.setSelected(true);
					} else if(isFullTime.equals("NO")){
						isFullTimeCheckBox.setSelected(false);
					}
					
					JComboBox assignedModuleComboBox = updateTutorPage.getModuleAssignedComboBox();
					ComboBoxModel model = assignedModuleComboBox.getModel();
					
					for(int i = 0; i <= 2; i++) {
						if(model.getElementAt(i).equals(selectedModuleFromComboBox)) {
							model.setSelectedItem(model.getElementAt(i));
							break;
						}
					}
					updateBtn.setActionCommand("Update");
					updateBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if(e.getActionCommand().equals("Update")) {
									
									JTextField fullNameTextField = updateTutorPage.getNameTextField();
									JTextField addressTextField = updateTutorPage.getAddressTextField();
									JTextField numberTextField = updateTutorPage.getNumberTextField();
									JComboBox moduleAssignedTextField1 = updateTutorPage.getModuleAssignedComboBox();
									JCheckBox isFullTimeCheckBox1 = updateTutorPage.getIsFullTimeCheckBox();
									
									BigDecimal oldMobileNumber = (BigDecimal) teacherTable.getValueAt(teacherTable.getSelectedRow(), 2);
									
									String updatedName = fullNameTextField.getText().trim();
									String updatedNumber = numberTextField.getText().trim();
									String updatedAddress = addressTextField.getText().trim();
									String updatedSelectedModule = "";
									String updatedIsFullTime = "NO";
									
									ComboBoxModel comboBoxModel = assignedModuleComboBox.getModel();
									Object selectedItem = comboBoxModel.getSelectedItem();
									updatedSelectedModule = selectedItem.toString();
									
									if(isFullTimeCheckBox.isSelected()) {
										updatedIsFullTime = "YES";
									} else {
										updatedIsFullTime = "NO";
									}
									
									int updatedRowId = selectedRowNo + 1;
									
									String updatedQuery = "UPDATE `tutor` SET "
											+ "`Name` = '"+updatedName+"', "
											+ "`Phone_Number` = '"+updatedNumber+"',"
											+ " `Address` = '"+updatedAddress+"', "
											+ " `Module_Assigned` = '"+updatedSelectedModule+"', "
											+ "`Full_Time` = '"+updatedIsFullTime+"' "
											+ "WHERE `tutor`.`Phone_Number` = "+oldMobileNumber+" ";
									
									Statement statement = Dbconn.getStatement();
									try {
										int updateSuccess = statement.executeUpdate(updatedQuery);
										if(updateSuccess == 1) {
											JOptionPane.showMessageDialog(scrollPane, "Data is Updated");
										} else {
											JOptionPane.showMessageDialog(scrollPane, "Something went wrong while updating");
										}
										
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									AdminDashBoard.getTutorDataInTableFromDb(); // refersh JTable
									
									System.out.println("Update button is working");
								}
								
							}
					});
//					JOptionPane.showMessageDialog(scrollPane, "row is updated");
				} else if(n == 1){ // delete
					BigDecimal deleteMobileNumber = (BigDecimal) teacherTable.getValueAt(teacherTable.getSelectedRow(), 2);
					Statement statement = Dbconn.getStatement();
					
					String deleteQuery =  "DELETE FROM tutor WHERE `tutor`.`Phone_Number` = "+deleteMobileNumber+" ";
					try {
						int deleteSuccess = statement.executeUpdate(deleteQuery);
						if(deleteSuccess == 1) {
							JOptionPane.showMessageDialog(scrollPane, "Deleted!!!");
						} else {
							JOptionPane.showMessageDialog(scrollPane, "Something went wrong");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					AdminDashBoard.getTutorDataInTableFromDb(); // refersh JTable
				}
						
			}
		});
		teacherTable.setDefaultEditor(Object.class, null);
		
		teacherTable.setModel(tutorDefaultTableModel);
		teacherTable.getColumnModel().getColumn(2).setPreferredWidth(86);
		teacherTable.getColumnModel().getColumn(5).setPreferredWidth(90);
		scrollPane.setViewportView(teacherTable);
		
		JButton addTutorBtn = new JButton("Add Tutor");
		addTutorBtn.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/icons/teacher.png")));
		addTutorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TutorPage addTutorPage = new TutorPage();
				addTutorPage.setVisible(true);
			}
		});
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textArea_2.setText("Teaching is one of the most rewarding careers available. You’ll get the chance to educate future generations, inspire knowledge and shape lives. Or, you may have the satisfaction of upskilling older learners, allowing them to improve their lives and achieve their ambitions.\r\n");
		textArea_2.setLineWrap(true);
		textArea_2.setEditable(false);
		GroupLayout gl_teacherHeaderPanel = new GroupLayout(teacherHeaderPanel);
		gl_teacherHeaderPanel.setHorizontalGroup(
			gl_teacherHeaderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_teacherHeaderPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea_2, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(addTutorBtn, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_teacherHeaderPanel.setVerticalGroup(
			gl_teacherHeaderPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_teacherHeaderPanel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_teacherHeaderPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(addTutorBtn, GroupLayout.PREFERRED_SIZE, 87, Short.MAX_VALUE)
						.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		teacherHeaderPanel.setLayout(gl_teacherHeaderPanel);
		teacherPanel.setLayout(gl_teacherPanel);
		
		JPanel reportPanel = new JPanel();
		cardPanel.add(reportPanel, "name_22209029601000");
		
		JPanel panel = new JPanel();
		
		JButton generateReportBtn = new JButton("Generate Report");
		generateReportBtn.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/icons/report.png")));
		generateReportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerateReportPage generateReportPage = new GenerateReportPage();
				generateReportPage.setVisible(true);
			}
		});
		
		JTextArea txtrTheSchoolOf = new JTextArea();
		txtrTheSchoolOf.setText("The School of Education at the University of Wolverhampton offers a comprehensive range of undergraduate and postgraduate courses for future and current professionals, including teaching qualifications, continuing professional development, and research degrees.");
		txtrTheSchoolOf.setLineWrap(true);
		txtrTheSchoolOf.setEditable(false);
		GroupLayout gl_reportPanel = new GroupLayout(reportPanel);
		gl_reportPanel.setHorizontalGroup(
			gl_reportPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_reportPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_reportPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
						.addGroup(gl_reportPanel.createSequentialGroup()
							.addComponent(txtrTheSchoolOf, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(generateReportBtn, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_reportPanel.setVerticalGroup(
			gl_reportPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_reportPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_reportPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(generateReportBtn, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
						.addComponent(txtrTheSchoolOf))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JScrollPane reportScrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(reportScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(reportScrollPane, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
					.addGap(24))
		);
		
		marksTable = new JTable();
		marksTable.setModel(marksDefaultTableModel);
		marksTable.getColumnModel().getColumn(1).setPreferredWidth(91);
		reportScrollPane.setViewportView(marksTable);
		panel.setLayout(gl_panel);
		reportPanel.setLayout(gl_reportPanel);
		
		
		JButton courseBtn = new JButton("Courses");
		courseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cardPanel.show(cardPanel, "name_3194150471600");
			}
		});
		
		JButton studentsBtn = new JButton("Students");
		studentsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cardPanel.show(cardPanel, "name_6771723028700");
			}
		});
		
		JButton tutorBtn = new JButton("Tutor");
		tutorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cardPanel.show(cardPanel, "name_245905159712600");
			}
		});
		
		JButton reportBtn = new JButton("Report");
		reportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cardPanel.show(cardPanel, "name_22209029601000");
			}
		});
		
		welcomeLabel = new JLabel("Welcome Admin");
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(AdminDashBoard.class.getResource("/icons/system.png")));
		
		table = new JTable();
		
		JButton logoutBtn = new JButton("Logout");
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
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(10)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGap(22)
							.addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(courseBtn, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
								.addComponent(studentsBtn, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
								.addComponent(tutorBtn, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGap(22)
							.addComponent(reportBtn, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGap(16)
							.addComponent(logoutBtn, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(courseBtn, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
							.addGap(32)
							.addComponent(studentsBtn, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
							.addGap(26)
							.addComponent(tutorBtn, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(reportBtn, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
							.addGap(29)
							.addComponent(logoutBtn, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
							.addGap(103))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGap(158))))
		);
		panel_1.setLayout(gl_panel_1);
		frame.getContentPane().setLayout(groupLayout);
		
		AdminDashBoard.getTutorDataInTableFromDb();
		AdminDashBoard.getCourseDataFromDb();
		AdminDashBoard.getStudentDataFromDb();
		AdminDashBoard.getMarksDataFromDb();
	}
}
