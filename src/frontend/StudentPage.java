package frontend;
import backend.Dbconn;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class StudentPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel studentNameLabel;
	private JTextField studentNameTextField;
	private JTextField studentSemesterTextField;
	private JTextField studentLevelTextField;
	private String studentName = "";
	private String level = "";
	private String semester = "";
	private JButton studentAddBtn;
	
	/**
	 * @return
	 */
	public JTextField getStudentNameTextField() {
		return studentNameTextField;
	}

	public JTextField getStudentSemesterTextField() {
		return studentSemesterTextField;
	}

	public JTextField getStudentLevelTextField() {
		return studentLevelTextField;
	}
	

	public JButton getStudentAddBtn() {
		return studentAddBtn;
	}

	public static void main(String[] args) {
		try {
			StudentPage dialog = new StudentPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StudentPage() {
		setTitle("Add Student Page");
		setBounds(100, 100, 547, 319);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			studentNameLabel = new JLabel("Name");
		}
		JLabel semesterLabel = new JLabel("Semester");
		JLabel levelLabel = new JLabel("Level");
		
		studentNameTextField = new JTextField();
		studentNameTextField.setColumns(10);
		
		studentSemesterTextField = new JTextField();
		studentSemesterTextField.setColumns(10);
		
		studentLevelTextField = new JTextField();
		studentLevelTextField.setColumns(10);
		
		studentAddBtn = new JButton("Add");
		studentAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals("Add")) {
					studentName = studentNameTextField.getText().trim();
					semester = studentSemesterTextField.getText().trim();
					level = studentLevelTextField.getText().trim();
					
					if (studentName.equals("") || semester.equals("") ||  level.equals("")) {
						JOptionPane.showMessageDialog(studentNameTextField, "Some fields are empty!!", "Error", 1);
					} else {
						Statement statement = Dbconn.getStatement();
	 					
						String insertQuery = "INSERT INTO `student` (`Id`, `Student_Name`, `Semester`, `Level`) "
								+ "VALUES (NULL, '"+studentName+"', '"+semester+"', '"+level+"');";
						
						try {
							int insertSuccess = statement.executeUpdate(insertQuery);
							if(insertSuccess == 1) {
								JOptionPane.showMessageDialog(studentNameTextField, "Saved into database");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					AdminDashBoard.getStudentDataFromDb();
				}
		
			}
		});
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(semesterLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentSemesterTextField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(levelLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentLevelTextField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(studentNameLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(172, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(429, Short.MAX_VALUE)
					.addComponent(studentAddBtn, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addGap(43))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(studentNameLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(studentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(semesterLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(studentSemesterTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(levelLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(studentLevelTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
					.addComponent(studentAddBtn)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
