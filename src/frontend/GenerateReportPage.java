package frontend;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import backend.Dbconn;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;
import java.awt.Color;

public class GenerateReportPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
	
	
	private void printPanel(JPanel panel) {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		printerJob.setJobName("Print Panel");
		
		printerJob.setPrintable(new Printable() {

			@Override
			public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
				// TODO Auto-generated method stub
				
				if (pageIndex > 0) {
					return Printable.NO_SUCH_PAGE;
				}
				
				Graphics2D graphics2D = (Graphics2D)graphics;
				graphics2D.translate(pageFormat.getImageableX()*2, pageFormat.getImageableY()*2);
				graphics2D.scale(0.5,  0.5);
				panel.paint(graphics2D);
				return Printable.PAGE_EXISTS;
			}
			
		});
		boolean returnResult = printerJob.printDialog();
		
		if(returnResult) {
			try {
				printerJob.print();
				} catch(PrinterException printerException) {
					JOptionPane.showMessageDialog(this, "print error" + printerException.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			GenerateReportPage dialog = new GenerateReportPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GenerateReportPage() {
		setBounds(100, 100, 929, 618);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(168, 168, 168));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		

		studentIdTextField = new JTextField();
		studentIdTextField.setColumns(10);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(202, 202, 202));
		
		JLabel studentIdLabel = new JLabel("Student Id");
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(GenerateReportPage.class.getResource("/icons/result111.png")));
		iconLabel.setBounds(393, 34, 80, 70);
		panel.add(iconLabel);
		
		JLabel studentNameLabel = new JLabel("Student Name");
		studentNameLabel.setBounds(41, 121, 80, 14);
		panel.add(studentNameLabel);
		
		JLabel studentLevelLabel = new JLabel("Level");
		studentLevelLabel.setBounds(41, 165, 80, 14);
		panel.add(studentLevelLabel);
		
		JLabel moduleOneLabel = new JLabel("NMC");
		moduleOneLabel.setBounds(28, 217, 46, 14);
		panel.add(moduleOneLabel);
		
		JLabel moduletwoLabel = new JLabel("OODP");
		moduletwoLabel.setBounds(28, 281, 46, 14);
		panel.add(moduletwoLabel);
		
		JLabel moduleThreeLabel = new JLabel("AI");
		moduleThreeLabel.setBounds(28, 345, 46, 14);
		panel.add(moduleThreeLabel);
		
		JLabel moduleFourLabel = new JLabel("PCA");
		moduleFourLabel.setBounds(28, 406, 46, 14);
		panel.add(moduleFourLabel);
		
		JLabel moduleFiveLabel = new JLabel("Database");
		moduleFiveLabel.setBounds(551, 217, 73, 14);
		panel.add(moduleFiveLabel);
		
		JLabel moduleSixLabel = new JLabel("UI/UX");
		moduleSixLabel.setBounds(551, 281, 46, 14);
		panel.add(moduleSixLabel);
		
		JLabel moduleSevenLabel = new JLabel("C#");
		moduleSevenLabel.setBounds(551, 345, 46, 14);
		panel.add(moduleSevenLabel);
		
		JLabel moduleEightLabel = new JLabel("DSA");
		moduleEightLabel.setBounds(551, 406, 46, 14);
		panel.add(moduleEightLabel);
		
		JLabel overAllMarksLabel = new JLabel("Avg.");
		overAllMarksLabel.setBounds(612, 34, 46, 14);
		panel.add(overAllMarksLabel);
		
		JLabel gradeOverAllLabel = new JLabel("Grade");
		gradeOverAllLabel.setBounds(612, 78, 46, 14);
		panel.add(gradeOverAllLabel);
		
		JLabel nameLabel = new JLabel("Jessy Pinkman");
		nameLabel.setBounds(162, 121, 177, 14);
		panel.add(nameLabel);
		
		JLabel reportHeaderLabel = new JLabel("University Of Wolverhampton");
		reportHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reportHeaderLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		reportHeaderLabel.setBounds(313, 9, 247, 14);
		panel.add(reportHeaderLabel);
		
		JLabel levelLabel = new JLabel("5");
		levelLabel.setBounds(162, 165, 46, 14);
		panel.add(levelLabel);
		
		JLabel moduleOneMarksLabel = new JLabel("23");
		moduleOneMarksLabel.setBounds(100, 217, 46, 14);
		panel.add(moduleOneMarksLabel);
		
		JLabel moduleTwoMarksLabel = new JLabel("44");
		moduleTwoMarksLabel.setBounds(100, 281, 46, 14);
		panel.add(moduleTwoMarksLabel);
		
		JLabel moduleThreeMarksLabel = new JLabel("56");
		moduleThreeMarksLabel.setBounds(100, 345, 46, 14);
		panel.add(moduleThreeMarksLabel);
		
		JLabel moduleFourMarksLabel = new JLabel("43");
		moduleFourMarksLabel.setBounds(100, 406, 46, 14);
		panel.add(moduleFourMarksLabel);
		
		JLabel moduleFiveMarksLabel = new JLabel("33");
		moduleFiveMarksLabel.setBounds(662, 217, 46, 14);
		panel.add(moduleFiveMarksLabel);
		
		JLabel moduleSixMarksLabel = new JLabel("76");
		moduleSixMarksLabel.setBounds(662, 281, 46, 14);
		panel.add(moduleSixMarksLabel);
		
		JLabel moduleSevenMarksLabel = new JLabel("67");
		moduleSevenMarksLabel.setBounds(662, 345, 46, 14);
		panel.add(moduleSevenMarksLabel);
		
		JLabel moduleEightMarksLabel = new JLabel("87");
		moduleEightMarksLabel.setBounds(662, 406, 46, 14);
		panel.add(moduleEightMarksLabel);
		
		JLabel averageMarksLabel = new JLabel("41");
		averageMarksLabel.setBounds(704, 34, 46, 14);
		panel.add(averageMarksLabel);
		
		JLabel gradeMarksLabel = new JLabel("D");
		gradeMarksLabel.setBounds(704, 78, 46, 14);
		panel.add(gradeMarksLabel);
		
		JButton genearteReportBtn = new JButton("Generate");
		genearteReportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				String studentId = studentIdTextField.getText().trim();
				
				if(studentId.equals("")) {
					JOptionPane.showMessageDialog(studentIdTextField, "Some fields are empty!!", "Error", 1);
				} else {
	 				Statement statement = Dbconn.getStatement();
					String selectQuery = "SELECT Student_Name, Level, Module_1, Module_2, Module_3, Module_4, Module_5, Module_6, Module_7, Module_8 FROM `marks`"
							+ " WHERE student_Id = "+studentId+" ";
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
				String gradeRemarks = "";
				
				
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
				averageMarksLabel.setText(avgValue);
				
				if(avg < 40) {
					gradeRemarks = "Fail";
				} else if(avg >= 40 && avg < 50) {
					gradeRemarks = "D";
				} else if(avg >= 50 && avg < 60) {
					gradeRemarks = "C";
				} else if(avg >= 60 && avg < 70) {
					gradeRemarks = "B+";
				} else if (avg >= 70 && avg < 90) {
					gradeRemarks = "A";
				} else if(avg >= 90) {
					gradeRemarks = "A+";
				}
				
				gradeMarksLabel.setText(gradeRemarks);
				
			}
		});
		
		JButton printBtn = new JButton("Print");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printPanel(panel);
			}
		});
		

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(printBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(genearteReportBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(37)
							.addComponent(studentIdLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(studentIdTextField, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(studentIdLabel)
						.addComponent(studentIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(genearteReportBtn)
						.addComponent(printBtn)))
		);
		panel.setLayout(null);
		
		contentPanel.setLayout(gl_contentPanel);

	}

}
