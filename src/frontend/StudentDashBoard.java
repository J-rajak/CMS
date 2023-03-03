package frontend;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import backend.Dbconn;

import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class StudentDashBoard {

	JFrame frame;
	private JPanel cardPanel;
	private CardLayout cl_cardPanel = new CardLayout(0, 0);
	private JButton viewModulesBtn;
	private JButton logoutBtn;
	private JTable assignmentTable;
	
	
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
	private JTable viewTeachersTable;
	private JLabel welcomeLabel;
	private JTextField studentIdTextField;
	private String studentName = "";
	private String level = "";
	private double avg;

	private int module_1;
	private int module_2;
	private int module_3;
	private int module_4;
	private int module_5;
	private int module_6;
	private int module_7;
	private int module_8;
	
	public JTextField getStudentIdTextField() {
		return studentIdTextField;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDashBoard window = new StudentDashBoard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	/**
	 * @wbp.parser.entryPoint
	 */
	public StudentDashBoard() {
		initialize();
	}
	
	public StudentDashBoard(String uname) {
		initialize();
		welcomeLabel.setText("Welcome "+uname);
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

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(206, 206, 206));
		frame.setTitle("Student DashBoard");
		frame.setBounds(100, 100, 905, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(188, 188, 188));
		
		cardPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
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
						.addComponent(mainPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		welcomeLabel = new JLabel("Welcome Student");
		welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TeacherDashBoard.class.getResource("/icons/system.png")));
		
		viewModulesBtn = new JButton("Modules");
		
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
		
		JButton Assignment = new JButton("Assignment");
		Assignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cardPanel.show(cardPanel, "name_21250577992100");
			}
		});
		
		JButton viewTeachers = new JButton("View Teachers");
		viewTeachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cardPanel.show(cardPanel, "name_20403460143500");
			}
		});
		
		JButton viewMarksBtn = new JButton("View Marks");
		viewMarksBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cardPanel.show(cardPanel, "name_79444052955200");
			}
		});
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGap(77)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(72))
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_mainPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addGroup(gl_mainPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
								.addComponent(viewModulesBtn, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
							.addGap(35))
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addGroup(gl_mainPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(viewMarksBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(logoutBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
							.addGap(27))
						.addGroup(gl_mainPanel.createSequentialGroup()
							.addGroup(gl_mainPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(viewTeachers, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(Assignment, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
							.addGap(35)))
					.addGap(0))
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addGap(28)
					.addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addGap(33)
					.addComponent(viewModulesBtn, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(Assignment, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(30)
					.addComponent(viewTeachers, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(35)
					.addComponent(viewMarksBtn, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(54)
					.addComponent(logoutBtn, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(75))
		);
		mainPanel.setLayout(gl_mainPanel);
		cardPanel.setLayout(cl_cardPanel);
		
		JPanel modulesPanel = new JPanel();
		modulesPanel.setBackground(new Color(188, 188, 188));
		cardPanel.add(modulesPanel, "name_21227050352500");
		
		JButton bitBtn = new JButton("BIT");
		bitBtn.setIcon(new ImageIcon(StudentDashBoard.class.getResource("/icons/bookss.png")));
		bitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BitPage bitPage = new BitPage();
				bitPage.setVisible(true);
			}
		});
		
		JButton bibmBtn = new JButton("BIBM");
		bibmBtn.setIcon(new ImageIcon(StudentDashBoard.class.getResource("/icons/study.png")));
		bibmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BibmPage bibmPage = new BibmPage();
				bibmPage.setVisible(true);
			}
		});
		
		JButton bdsBtn = new JButton("BDS");
		bdsBtn.setIcon(new ImageIcon(StudentDashBoard.class.getResource("/icons/course.png")));
		bdsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BdsPage bdsPage = new BdsPage();
				bdsPage.setVisible(true);
			}
		});
		GroupLayout gl_modulesPanel = new GroupLayout(modulesPanel);
		gl_modulesPanel.setHorizontalGroup(
			gl_modulesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_modulesPanel.createSequentialGroup()
					.addContainerGap(271, Short.MAX_VALUE)
					.addGroup(gl_modulesPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(bibmBtn, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addComponent(bitBtn, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_modulesPanel.createSequentialGroup()
							.addComponent(bdsBtn, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(251))
		);
		gl_modulesPanel.setVerticalGroup(
			gl_modulesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modulesPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(bitBtn)
					.addGap(79)
					.addComponent(bdsBtn)
					.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
					.addComponent(bibmBtn)
					.addGap(36))
		);
		modulesPanel.setLayout(gl_modulesPanel);
		
		JPanel assignmentPanel = new JPanel();
		assignmentPanel.setBackground(new Color(188, 188, 188));
		cardPanel.add(assignmentPanel, "name_21250577992100");
		
		JPanel panel = new JPanel();
		
		JButton submitAssignementBtn = new JButton("Submit Assignment");
		submitAssignementBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubmitAssignmentPage submitAssignment = new SubmitAssignmentPage();
				submitAssignment.setVisible(true);
			}
		});
		GroupLayout gl_assignmentPanel = new GroupLayout(assignmentPanel);
		gl_assignmentPanel.setHorizontalGroup(
			gl_assignmentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_assignmentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_assignmentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
						.addComponent(submitAssignementBtn, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_assignmentPanel.setVerticalGroup(
			gl_assignmentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_assignmentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(submitAssignementBtn)
					.addContainerGap())
		);
		
		JScrollPane assignmentScrollpane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(assignmentScrollpane, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(assignmentScrollpane, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		assignmentTable = new JTable();
		assignmentTable.setModel(assignmentDefaultTableModel);
		assignmentTable.getColumnModel().getColumn(1).setPreferredWidth(96);
		assignmentTable.getColumnModel().getColumn(2).setPreferredWidth(106);
		assignmentTable.getColumnModel().getColumn(3).setPreferredWidth(104);
		assignmentTable.getColumnModel().getColumn(4).setPreferredWidth(127);
		assignmentScrollpane.setViewportView(assignmentTable);
		panel.setLayout(gl_panel);
		assignmentPanel.setLayout(gl_assignmentPanel);
		
		JPanel viewTeachersPanel = new JPanel();
		cardPanel.add(viewTeachersPanel, "name_20403460143500");
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_viewTeachersPanel = new GroupLayout(viewTeachersPanel);
		gl_viewTeachersPanel.setHorizontalGroup(
			gl_viewTeachersPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_viewTeachersPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_viewTeachersPanel.setVerticalGroup(
			gl_viewTeachersPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_viewTeachersPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JScrollPane viewTeachersScrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(viewTeachersScrollPane, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(viewTeachersScrollPane, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		viewTeachersTable = new JTable();
		viewTeachersTable.setModel(moduleAssignedDefaultTableModel);
		viewTeachersTable.getColumnModel().getColumn(0).setPreferredWidth(113);
		viewTeachersScrollPane.setViewportView(viewTeachersTable);
		panel_1.setLayout(gl_panel_1);
		viewTeachersPanel.setLayout(gl_viewTeachersPanel);
		
		JPanel viewMarksPanel = new JPanel();
		viewMarksPanel.setBackground(new Color(188, 188, 188));
		cardPanel.add(viewMarksPanel, "name_79444052955200");
		
		JLabel studentIdLabel = new JLabel("Student Id");
		
		studentIdTextField = new JTextField();
		studentIdTextField.setColumns(10);
		
		JButton checkBtn = new JButton("check");
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(209, 209, 209));
		GroupLayout gl_viewMarksPanel = new GroupLayout(viewMarksPanel);
		gl_viewMarksPanel.setHorizontalGroup(
			gl_viewMarksPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_viewMarksPanel.createSequentialGroup()
					.addGap(521)
					.addComponent(checkBtn, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_viewMarksPanel.createSequentialGroup()
					.addGap(19)
					.addComponent(studentIdLabel)
					.addGap(18)
					.addComponent(studentIdTextField, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
					.addGap(351))
				.addGroup(gl_viewMarksPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
					.addGap(9))
		);
		gl_viewMarksPanel.setVerticalGroup(
			gl_viewMarksPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_viewMarksPanel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_viewMarksPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(studentIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(studentIdLabel))
					.addGap(18)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBtn, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
					.addGap(6))
		);
		
		JLabel studentNameLabel = new JLabel("Student Name");
		studentNameLabel.setBounds(10, 119, 86, 14);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(273, 11, 80, 80);
		lblNewLabel_2.setIcon(new ImageIcon(StudentDashBoard.class.getResource("/icons/result111.png")));
		
		JLabel studentLevelLabel = new JLabel("Level");
		studentLevelLabel.setBounds(10, 151, 86, 14);
		
		JLabel moduleOneLabel = new JLabel("NMC");
		moduleOneLabel.setBounds(10, 198, 86, 14);
		
		JLabel moduleTwoLabel = new JLabel("OODP");
		moduleTwoLabel.setBounds(10, 239, 86, 14);
		
		JLabel moduleThreeLabel = new JLabel("DSA");
		moduleThreeLabel.setBounds(10, 286, 86, 14);
		
		JLabel moduleFourLabel = new JLabel("AI");
		moduleFourLabel.setBounds(10, 339, 86, 14);
		
		JLabel moduleFiveLabel = new JLabel("Concepts AI");
		moduleFiveLabel.setBounds(373, 198, 86, 14);
		
		JLabel moduleSixLabel = new JLabel("PCA");
		moduleSixLabel.setBounds(373, 239, 86, 14);
		panel_2.setLayout(null);
		panel_2.add(moduleOneLabel);
		panel_2.add(lblNewLabel_2);
		panel_2.add(studentNameLabel);
		panel_2.add(studentLevelLabel);
		panel_2.add(moduleTwoLabel);
		panel_2.add(moduleThreeLabel);
		panel_2.add(moduleFourLabel);
		panel_2.add(moduleSixLabel);
		panel_2.add(moduleFiveLabel);
		
		JLabel moduleSevenLabel = new JLabel("C#");
		moduleSevenLabel.setBounds(373, 286, 86, 14);
		panel_2.add(moduleSevenLabel);
		
		JLabel moduleEightLabel = new JLabel("Database");
		moduleEightLabel.setBounds(373, 339, 86, 14);
		panel_2.add(moduleEightLabel);
		
		JLabel nameLabel = new JLabel("Jessy Pinkman");
		nameLabel.setBounds(106, 119, 165, 14);
		panel_2.add(nameLabel);
		
		JLabel levelLabel = new JLabel("5");
		levelLabel.setBounds(104, 151, 86, 14);
		panel_2.add(levelLabel);
		
		JLabel moduleOneMarksLabel = new JLabel("23");
		moduleOneMarksLabel.setBounds(106, 198, 86, 14);
		panel_2.add(moduleOneMarksLabel);
		
		JLabel moduleTwoMarksLabel = new JLabel("45");
		moduleTwoMarksLabel.setBounds(106, 239, 86, 14);
		panel_2.add(moduleTwoMarksLabel);
		
		JLabel moduleThreeMarksLabel = new JLabel("65");
		moduleThreeMarksLabel.setBounds(106, 286, 86, 14);
		panel_2.add(moduleThreeMarksLabel);
		
		JLabel moduleFourMarksLabel = new JLabel("54");
		moduleFourMarksLabel.setBounds(106, 339, 86, 14);
		panel_2.add(moduleFourMarksLabel);
		
		JLabel moduleFiveMarksLabel = new JLabel("76");
		moduleFiveMarksLabel.setBounds(469, 198, 86, 14);
		panel_2.add(moduleFiveMarksLabel);
		
		JLabel moduleSixMarksLabel = new JLabel("54");
		moduleSixMarksLabel.setBounds(469, 239, 86, 14);
		panel_2.add(moduleSixMarksLabel);
		
		JLabel moduleSevenMarksLabel = new JLabel("98");
		moduleSevenMarksLabel.setBounds(469, 286, 86, 14);
		panel_2.add(moduleSevenMarksLabel);
		
		JLabel moduleEightMarksLabel = new JLabel("34");
		moduleEightMarksLabel.setBounds(469, 339, 86, 14);
		panel_2.add(moduleEightMarksLabel);
		
		JLabel randomImageLabel = new JLabel("");
		randomImageLabel.setIcon(new ImageIcon(StudentDashBoard.class.getResource("/icons/random.png")));
		randomImageLabel.setBounds(10, 11, 91, 80);
		panel_2.add(randomImageLabel);
		
		JLabel randomImagelabel_2 = new JLabel("");
		randomImagelabel_2.setIcon(new ImageIcon(StudentDashBoard.class.getResource("/icons/study.png")));
		randomImagelabel_2.setBounds(540, 11, 70, 80);
		panel_2.add(randomImagelabel_2);
		viewMarksPanel.setLayout(gl_viewMarksPanel);
		
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String studentId = studentIdTextField.getText().trim();
				String selectQuery = "SELECT Student_Name, Level, Module_1, Module_2, Module_3, Module_4, Module_5, Module_6, Module_7, Module_8 FROM `marks`"
						+ " WHERE student_Id = "+studentId+" ";
				
				Statement statement = Dbconn.getStatement();
				try {
					ResultSet resultSet = statement.executeQuery(selectQuery);
					while(resultSet.next()) {
						studentName = resultSet.getString("Student_Name");
						level = resultSet.getString("Level");
						module_1 = resultSet.getInt("Module_1");
						module_2 = resultSet.getInt("Module_2");
						module_3 = resultSet.getInt("Module_3");
						module_4 = resultSet.getInt("Module_4");
						module_5 = resultSet.getInt("Module_5");
						module_6 = resultSet.getInt("Module_6");
						module_7 = resultSet.getInt("Module_7");
						module_8 = resultSet.getInt("Module_8");
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				avg = (module_1 + module_2 + module_3 + module_4 + module_5 + module_6 + module_7 + module_8)/ 8;
				
				String studentName_1 = studentName;
				String level_1 = level;
				String moduleOneMarks = String.valueOf(module_1);
				String moduleTwoMarks = String.valueOf(module_2);
				String moduleThreeMarks = String.valueOf(module_3);
				String moduleFourMarks = String.valueOf(module_4);
				String moduleFiveMarks = String.valueOf(module_5);
				String moduleSixMarks = String.valueOf(module_6);
				String moduleSevenMarks = String.valueOf(module_7);
				String moduleEightMarks = String.valueOf(module_8);
				String avgValue = String.valueOf(avg);
				
				
				nameLabel.setText(studentName_1);
				levelLabel.setText(level_1);
				moduleOneMarksLabel.setText(moduleOneMarks);
				moduleTwoMarksLabel.setText(moduleTwoMarks);
				moduleThreeMarksLabel.setText(moduleThreeMarks);
				moduleFourMarksLabel.setText(moduleFourMarks);
				moduleFiveMarksLabel.setText(moduleFiveMarks);
				moduleSixMarksLabel.setText(moduleSixMarks);
				moduleSevenMarksLabel.setText(moduleSevenMarks);
				moduleEightMarksLabel.setText(moduleEightMarks);
				
				
			}
		});
		
		
		frame.getContentPane().setLayout(groupLayout);
		
		viewModulesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_cardPanel.show(cardPanel, "name_21227050352500");
			}
		});
		
		StudentDashBoard.getAssignmentDataFromDb();
		StudentDashBoard.getModuleAssignedDataFromDb();
	}
}
