package frontend;
import backend.Dbconn;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TutorPage extends JDialog {
	private JPanel formPanel;
	private JTextField nameTextField;
	private JTextField numberTextField;
	private JTextField addressTextField;
	private JTextField dobTextField;
	private JButton addBtn;
	private JCheckBox isFullTimeCheckBox;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox moduleAssignedComboBox;
 	private String name = "";
	private String mobileNumber = "";
	private String address = "";
	private String gender = "";
	private String isFullTime = "NO";
	private String selectedModuleFromComboBox = "";
	private String dateOfBirth = "";
	
	
	
	public JComboBox getModuleAssignedComboBox() {
		return moduleAssignedComboBox;
	}

	public JCheckBox getIsFullTimeCheckBox() {
		return isFullTimeCheckBox;
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public JTextField getNumberTextField() {
		return numberTextField;
	}
	
	public JTextField getAddressTextField() {
		return addressTextField;
	}

	public JTextField getDobTextField() {
		return dobTextField;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}
	
	public JButton getAddBtn() {
		return addBtn;
	}

	public TutorPage() {
		
		setTitle("Add Teacher Form");
		setBounds(100, 100, 660, 518);
		formPanel = new JPanel();
		setContentPane(formPanel);
		
		JLabel nameLabel = new JLabel("Full Name");
		nameLabel.setBounds(41, 37, 99, 24);
		JLabel numberLabel = new JLabel("Phone Number");
		numberLabel.setBounds(41, 90, 99, 24);
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setBounds(41, 147, 99, 24);
		JLabel sexLabel = new JLabel("Sex");
		sexLabel.setBounds(41, 213, 99, 24);
		JLabel dateOfBirthLabel = new JLabel("Date Of Birth");
		dateOfBirthLabel.setBounds(41, 274, 99, 24);
		isFullTimeCheckBox = new JCheckBox("Full Time");
		isFullTimeCheckBox.setBounds(41, 330, 124, 23);
		JLabel modukeAssignedLabel = new JLabel("Module Assigned");
		modukeAssignedLabel.setBounds(41, 391, 124, 24);
		
		
		addBtn = new JButton("Add");
		addBtn.setBounds(498, 445, 113, 23);
		addBtn.setActionCommand("Add");
		moduleAssignedComboBox = new JComboBox();
		moduleAssignedComboBox.setBounds(238, 393, 170, 20);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(146, 38, 350, 22);
		nameTextField.setColumns(10);
		
		numberTextField = new JTextField();
		numberTextField.setBounds(144, 91, 350, 22);
		numberTextField.setColumns(10);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(144, 148, 350, 22);
		addressTextField.setColumns(10);
		
		dobTextField = new JTextField();
		dobTextField.setBounds(144, 275, 350, 22);
		dobTextField.setColumns(10);
		
		moduleAssignedComboBox.setModel(new DefaultComboBoxModel(new String[] {"NMC", "OODP", "Concepts AI", "ISA"}));
		moduleAssignedComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					selectedModuleFromComboBox = (String) e.getItem();
					System.out.println(e.getItem());
				}
			}
		});
		
		
		JRadioButton maleRadioBtn = new JRadioButton("Male ");
		maleRadioBtn.setBounds(170, 214, 98, 23);
		buttonGroup.add(maleRadioBtn);
		
		JRadioButton femaleRadioBtn = new JRadioButton("Female");
		femaleRadioBtn.setBounds(296, 214, 120, 23);
		buttonGroup.add(femaleRadioBtn);
		
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Add")) {
					
				name = nameTextField.getText().trim();
				mobileNumber = numberTextField.getText().trim();
				dateOfBirth = dobTextField.getText().trim();
				address = addressTextField.getText().trim();
				
				for(Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();
					
					if(button.isSelected()) {
						gender = button.getText();
					}
		 		}
				
				if(isFullTimeCheckBox.isSelected()) {
					isFullTime = "YES";
				} else {
					isFullTime = "NO";
				}
				
				if(name.equals("") || mobileNumber.equals("") || dateOfBirth.equals("") || address.equals("")) {
					JOptionPane.showMessageDialog(nameTextField, "Some fields are empty!!", "Error", 1);
				} else {
					
					Statement statement = Dbconn.getStatement();

					String insertQuery = "INSERT INTO `tutor` (`Id`, `Name`, `Phone_Number`, `Address`, `Sex`, `Module_Assigned`, `Date_Of_Birth`, `Full_Time`)"
							+ " VALUES (NULL, '" + name + "', '" + mobileNumber + "', '" + address + "', '" + gender
							+ "', '" + selectedModuleFromComboBox + "', '" + dateOfBirth + "', '" + isFullTime + "')";
					try {
						int insertSuccess = statement.executeUpdate(insertQuery);
						if (insertSuccess == 1) {
							JOptionPane.showMessageDialog(nameTextField, "Saved into database");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				AdminDashBoard.getTutorDataInTableFromDb();
				}
			}

		});
		
		formPanel.setLayout(null);
		formPanel.add(modukeAssignedLabel);
		formPanel.add(moduleAssignedComboBox);
		formPanel.add(sexLabel);
		formPanel.add(maleRadioBtn);
		formPanel.add(femaleRadioBtn);
		formPanel.add(addressLabel);
		formPanel.add(addressTextField);
		formPanel.add(numberLabel);
		formPanel.add(numberTextField);
		formPanel.add(nameLabel);
		formPanel.add(nameTextField);
		formPanel.add(isFullTimeCheckBox);
		formPanel.add(dateOfBirthLabel);
		formPanel.add(dobTextField);
		formPanel.add(addBtn);
		

	}
}
