package frontend;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import backend.Dbconn;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SubmitAssignmentPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel assignmentOneLabel;
	private JTextField assignmentOneTextField;
	private JTextField assignmentTwoTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SubmitAssignmentPage dialog = new SubmitAssignmentPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SubmitAssignmentPage() {
		setBounds(100, 100, 516, 314);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			assignmentOneLabel = new JLabel("Assingment One");
		}
		JLabel assignmentTwoLabel = new JLabel("Assignment Two");
		assignmentOneTextField = new JTextField();
		assignmentOneTextField.setColumns(10);
		assignmentTwoTextField = new JTextField();
		assignmentTwoTextField.setColumns(10);
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String assignmentOne = assignmentOneTextField.getText().trim();
				String assignmentTwo = assignmentTwoTextField.getText().trim();
				
				if(assignmentOne.equals("") || assignmentTwo.equals("")) {
					JOptionPane.showMessageDialog(assignmentOneTextField, "Some fields are empty!!", "Error", 1);
				} else {
					
					Statement statement = Dbconn.getStatement();
					
					String insertQuery = "INSERT INTO `submitted_assignments` "
							+ "(`Id`, `Assignment_One`, `Assignment_Two`) "
							+ "VALUES (NULL, '"+assignmentOne+"', '"+assignmentTwo+"');";
					
					try {
						int insertSuccess = statement.executeUpdate(insertQuery);
						if(insertSuccess == 1) {
							JOptionPane.showMessageDialog(assignmentOneTextField, "Successfully Submitted!!");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}

			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(assignmentOneLabel)
						.addComponent(assignmentTwoLabel)
						.addComponent(assignmentTwoTextField, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(291, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(369, Short.MAX_VALUE)
					.addComponent(submitBtn, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(assignmentOneTextField, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(291, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(assignmentOneLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(assignmentOneTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
					.addComponent(assignmentTwoLabel)
					.addGap(18)
					.addComponent(assignmentTwoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(submitBtn))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
