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
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;

public class MarksPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel studentNameLabel;
	private JTextField studentNameTextField;
	private JTextField moduleOneTextField;
	private JTextField moduleTwoTextField;
	private JTextField moduleThreeTextField;
	private JTextField moduleFourTextField;
	private JTextField moduleFiveTextField;
	private JTextField moduleSixTextField;
	private JTextField moduleSevenTextField;
	private JTextField moduleEightTextField;
	private JComboBox getLevelComboBox;
	
	public JTextField getStudentNameTextField() {
		return studentNameTextField;
	}

	public JTextField getModuleOneTextField() {
		return moduleOneTextField;
	}

	public JTextField getModuleTwoTextField() {
		return moduleTwoTextField;
	}

	public JTextField getModuleThreeTextField() {
		return moduleThreeTextField;
	}

	public JTextField getModuleFourTextField() {
		return moduleFourTextField;
	}

	public JTextField getModuleFiveTextField() {
		return moduleFiveTextField;
	}

	public JTextField getModuleSixTextField() {
		return moduleSixTextField;
	}

	public JTextField getModuleSevenTextField() {
		return moduleSevenTextField;
	}

	public JTextField getModuleEightTextField() {
		return moduleEightTextField;
	}

	public JButton getAddMarksBtn() {
		return addMarksBtn;
	}

	public JComboBox getSelectedLevelFromComboBox() {
		return getLevelComboBox;
	}


	private JButton addMarksBtn;
	private String selectedLevelFromComboBox = "";
	private String studentName = "";
	private String moduleOne = "";
	private String moduleTwo = "";
	private String moduleThree = "";
	private String moduleFour = "";
	private String moduleFive = "";
	private String moduleSix = "";
	private String moduleSeven = "";
	private String moduleEight = "";
	private JPanel yearOnePanel;
	private JPanel yearOnePanel_1;
	private JLabel moduleFourLabel;
	private JLabel moduleThreeLabel;
	private JLabel moduleTwoLabel;
	private JLabel moduleOneLabel;
	

	public static void main(String[] args) {
		try {
			MarksPage dialog = new MarksPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MarksPage() {
		setTitle("Marks Page");
		setBounds(100, 100, 386, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(168, 168, 168));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			studentNameLabel = new JLabel("Student Name");
			studentNameLabel.setBounds(15, 16, 96, 25);
		}
		JLabel levelLabel = new JLabel("Level");
		levelLabel.setBounds(15, 70, 96, 25);
		studentNameTextField = new JTextField();
		studentNameTextField.setBounds(121, 18, 234, 20);
		studentNameTextField.setColumns(10);
		
		getLevelComboBox = new JComboBox();
		getLevelComboBox.setBounds(121, 72, 138, 20);
		getLevelComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					selectedLevelFromComboBox = (String) e.getItem();
					System.out.println(e.getItem());
				}
			}
		});
		getLevelComboBox.setModel(new DefaultComboBoxModel(new String[] {"4", "5", "6"}));
		
		addMarksBtn = new JButton("Add");
		addMarksBtn.setBounds(240, 578, 115, 23);
		addMarksBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Add")) {
					studentName = studentNameTextField.getText().trim();
					moduleOne = moduleOneTextField.getText().trim();
					moduleTwo = moduleTwoTextField.getText().trim();
					moduleThree = moduleThreeTextField.getText().trim();
					moduleFour = moduleFourTextField.getText().trim();
					moduleFive = moduleFiveTextField.getText().trim();
					moduleSix = moduleSixTextField.getText().trim();
					moduleSeven = moduleSevenTextField.getText().trim();
					moduleEight = moduleEightTextField.getText().trim();
					
					if(studentName.equals("") || moduleOne.equals("") || moduleTwo.equals("") || moduleThree.equals("") || moduleFour.equals("") || moduleFive.equals("") || moduleSix.equals("") || moduleSeven.equals("") ||  moduleEight.equals("")) {
						JOptionPane.showMessageDialog(studentNameTextField, "Some fields are empty!!", "Error", 1);
					} else {
						Statement statement = Dbconn.getStatement();
						String insertQuery = "INSERT INTO `marks` (`student_Id`, `Student_Name`, `Level`, `Module_1`, `Module_2`, `Module_3`, `Module_4`, `Module_5`, `Module_6`, `Module_7`, `Module_8`) "
								+ "VALUES (NULL, '"+studentName+"', '"+selectedLevelFromComboBox+"', '"+moduleOne+"', '"+moduleTwo+"', '"+moduleThree+"', '"+moduleFour+"', '"+moduleFive+"', '"+moduleSix+"', '"+moduleSeven+"', '"+moduleEight+"');";
						
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
					 
					TeacherDashBoard.getMarksDataFromDb();
				}
			}
		});
		{
			yearOnePanel = new JPanel();
			yearOnePanel.setBounds(16, 118, 339, 220);
			yearOnePanel.setBackground(new Color(209, 209, 209));
			
			moduleOneLabel = new JLabel("Module One");
			moduleOneLabel.setBounds(10, 11, 79, 23);
			
			moduleTwoLabel = new JLabel("Module Two");
			moduleTwoLabel.setBounds(10, 62, 79, 23);
			
			moduleThreeLabel = new JLabel("Module Three");
			moduleThreeLabel.setBounds(10, 112, 79, 23);
			
			moduleFourLabel = new JLabel("Module Four");
			moduleFourLabel.setBounds(10, 170, 79, 23);
			
			moduleOneTextField = new JTextField();
			moduleOneTextField.setBounds(99, 12, 191, 20);
			moduleOneTextField.setColumns(10);
			
			moduleTwoTextField = new JTextField();
			moduleTwoTextField.setBounds(99, 63, 191, 20);
			moduleTwoTextField.setColumns(10);
			
			moduleThreeTextField = new JTextField();
			moduleThreeTextField.setBounds(99, 113, 191, 20);
			moduleThreeTextField.setColumns(10);
			
			moduleFourTextField = new JTextField();
			moduleFourTextField.setBounds(99, 171, 191, 20);
			moduleFourTextField.setColumns(10);
		}
		
		yearOnePanel_1 = new JPanel();
		yearOnePanel_1.setBounds(16, 344, 339, 220);
		yearOnePanel_1.setBackground(new Color(209, 209, 209));
		
		JLabel moduleFiveLabel = new JLabel("Module Five");
		moduleFiveLabel.setBounds(19, 11, 76, 23);
		
		moduleFiveTextField = new JTextField();
		moduleFiveTextField.setBounds(99, 12, 191, 20);
		moduleFiveTextField.setColumns(10);
		
		JLabel moduleSixLabel = new JLabel("Module Six");
		moduleSixLabel.setBounds(19, 52, 67, 23);
		
		moduleSixTextField = new JTextField();
		moduleSixTextField.setBounds(98, 53, 191, 20);
		moduleSixTextField.setColumns(10);
		
		JLabel moduleSevenLabel = new JLabel("Module Seven");
		moduleSevenLabel.setBounds(19, 102, 67, 23);
		
		moduleSevenTextField = new JTextField();
		moduleSevenTextField.setBounds(96, 103, 191, 20);
		moduleSevenTextField.setColumns(10);
		
		JLabel moduleEightLabel = new JLabel("Module eight");
		moduleEightLabel.setBounds(19, 156, 61, 23);
		
		moduleEightTextField = new JTextField();
		moduleEightTextField.setBounds(98, 157, 191, 20);
		moduleEightTextField.setColumns(10);
		contentPanel.setLayout(null);
		contentPanel.add(levelLabel);
		contentPanel.add(studentNameLabel);
		contentPanel.add(studentNameTextField);
		contentPanel.add(getLevelComboBox);
		contentPanel.add(addMarksBtn);
		contentPanel.add(yearOnePanel_1);
		yearOnePanel_1.setLayout(null);
		yearOnePanel_1.add(moduleEightLabel);
		yearOnePanel_1.add(moduleEightTextField);
		yearOnePanel_1.add(moduleSevenLabel);
		yearOnePanel_1.add(moduleSevenTextField);
		yearOnePanel_1.add(moduleSixLabel);
		yearOnePanel_1.add(moduleSixTextField);
		yearOnePanel_1.add(moduleFiveLabel);
		yearOnePanel_1.add(moduleFiveTextField);
		contentPanel.add(yearOnePanel);
		yearOnePanel.setLayout(null);
		yearOnePanel.add(moduleFourLabel);
		yearOnePanel.add(moduleThreeLabel);
		yearOnePanel.add(moduleTwoLabel);
		yearOnePanel.add(moduleOneLabel);
		yearOnePanel.add(moduleOneTextField);
		yearOnePanel.add(moduleTwoTextField);
		yearOnePanel.add(moduleThreeTextField);
		yearOnePanel.add(moduleFourTextField);
	}
}
