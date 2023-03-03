package frontend;
import backend.Dbconn;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ModuleCRUDView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable moduleTable;
	private static DefaultTableModel moduleDefaultTableModel= new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"ModuleName", "Course Name", "Level", "Semester", "Credit Value", "OptionalModule"
			}
		);

	public static void main(String[] args) {
		try {
			ModuleCRUDView dialog = new ModuleCRUDView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getModuleDataFromDb() {
		Statement statement = Dbconn.getStatement();
		String selectQuery = "SELECT * FROM `module`";
		
		ResultSet resultSet;
		
		try {
			resultSet = statement.executeQuery(selectQuery);
			moduleDefaultTableModel.setRowCount(0);
			while(resultSet.next()) {
				String moduleNameFromDb = resultSet.getString("Module_Name");
				String courseNameFromDb = resultSet.getString("Course_Name");
				String levelFromDb = resultSet.getString("Level");
				String semesterFromDb = resultSet.getString("Semester");
				int creditValueFromDb = resultSet.getInt("Credit_Value");
				String optionalModuleFromDb = resultSet.getString("Optional_Module");
				
				moduleDefaultTableModel.addRow(new Object[] {
						moduleNameFromDb,
						courseNameFromDb,
						levelFromDb,
						semesterFromDb,
						creditValueFromDb,
						optionalModuleFromDb	
				});
			}
		} catch(SQLException ex) {
			
		}
	}
	
	public ModuleCRUDView() {
		setTitle("Module CRUD View");
		setBounds(100, 100, 628, 347);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane moduleScrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addGap(24)
					.addComponent(moduleScrollPane, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
					.addGap(30))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(moduleScrollPane, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		moduleTable = new JTable();
		moduleTable.setModel(moduleDefaultTableModel);
		moduleScrollPane.setViewportView(moduleTable);
		contentPanel.setLayout(gl_contentPanel);
		ModuleCRUDView.getModuleDataFromDb();
	}

}