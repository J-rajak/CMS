package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class BdsPage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel levelFourPanel;
	private String selectedOptionalOneFromComboBox = "";
	private String selectedOptionalTwoFromComboBox = "";
	private JComboBox optionalOneComboBox;
	private JComboBox optionalTwoComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BdsPage dialog = new BdsPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BdsPage() {
		setTitle("BIT Modules");
		setBounds(100, 100, 685, 520);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(206, 206, 206));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			levelFourPanel = new JPanel();
			levelFourPanel.setBackground(new Color(188, 188, 188));
		}
		
		JPanel levelFivePanel = new JPanel();
		levelFivePanel.setBackground(new Color(188, 188, 188));
		
		JPanel levelSixPanel = new JPanel();
		levelSixPanel.setBackground(new Color(188, 188, 188));
		
		optionalOneComboBox = new JComboBox();
		optionalOneComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					selectedOptionalOneFromComboBox = (String) e.getItem();
					System.out.println(e.getItem());
				}
			}
		});
		optionalOneComboBox.setModel(new DefaultComboBoxModel(new String[] {"BNA", "Model Designing"}));
		
		optionalTwoComboBox = new JComboBox();
		optionalTwoComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					selectedOptionalTwoFromComboBox = (String) e.getItem();
					System.out.println(e.getItem());
				}
			}
		});
		optionalTwoComboBox.setModel(new DefaultComboBoxModel(new String[] {"YJA", "Statistics"}));
		
		JButton confirmBtn = new JButton("Confirm");
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(levelFourPanel, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
							.addComponent(levelFivePanel, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(206)
							.addComponent(levelSixPanel, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
							.addComponent(confirmBtn)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(levelFivePanel, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
						.addComponent(levelFourPanel, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(levelSixPanel, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
						.addComponent(confirmBtn))
					.addContainerGap())
		);
		
		JLabel levelSixModulesLabel = new JLabel("Level 6");
		levelSixModulesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel level6Mod1 = new JLabel("LOA");
		
		JLabel level6Mod2 = new JLabel("JUA");
		
		JLabel oprionalModulesLabel = new JLabel("Optional Modules");
		
		JLabel level6Mod3 = new JLabel("Optional 1");
		
		JLabel level6Mod4 = new JLabel("Optional 2");
		
		GroupLayout gl_levelSixPanel = new GroupLayout(levelSixPanel);
		gl_levelSixPanel.setHorizontalGroup(
			gl_levelSixPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_levelSixPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_levelSixPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(levelSixModulesLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(level6Mod1)
						.addComponent(level6Mod2)
						.addComponent(oprionalModulesLabel)
						.addGroup(gl_levelSixPanel.createSequentialGroup()
							.addGroup(gl_levelSixPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(level6Mod3)
								.addComponent(level6Mod4))
							.addGap(33)
							.addGroup(gl_levelSixPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(optionalTwoComboBox, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
								.addComponent(optionalOneComboBox, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		gl_levelSixPanel.setVerticalGroup(
			gl_levelSixPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_levelSixPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(levelSixModulesLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(level6Mod1)
					.addGap(18)
					.addComponent(level6Mod2)
					.addGap(18)
					.addComponent(oprionalModulesLabel)
					.addGap(18)
					.addGroup(gl_levelSixPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(level6Mod3)
						.addComponent(optionalOneComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_levelSixPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(level6Mod4)
						.addComponent(optionalTwoComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		levelSixPanel.setLayout(gl_levelSixPanel);
		
		JLabel levelFiveModulesLabe1 = new JLabel("Level 5");
		levelFiveModulesLabe1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel level5Mod1 = new JLabel("AI");
		
		JLabel level5Mod2 = new JLabel("MHA");
		
		JLabel level5Mod3 = new JLabel("NBA");
		
		JLabel level5Mod4 = new JLabel("IUA");
		GroupLayout gl_levelFivePanel = new GroupLayout(levelFivePanel);
		gl_levelFivePanel.setHorizontalGroup(
			gl_levelFivePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_levelFivePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_levelFivePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(levelFiveModulesLabe1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(level5Mod1)
						.addComponent(level5Mod2)
						.addComponent(level5Mod3)
						.addComponent(level5Mod4))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		gl_levelFivePanel.setVerticalGroup(
			gl_levelFivePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_levelFivePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(levelFiveModulesLabe1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(level5Mod1)
					.addGap(18)
					.addComponent(level5Mod2)
					.addGap(18)
					.addComponent(level5Mod3)
					.addGap(18)
					.addComponent(level5Mod4)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		levelFivePanel.setLayout(gl_levelFivePanel);
		
		JLabel levelFourModulesLabel = new JLabel("Level 4");
		levelFourModulesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel level4Mod1 = new JLabel("OOPS");
		
		JLabel level4Mod2 = new JLabel("GGA");
		
		JLabel level4Mod3 = new JLabel("KKA");
		
		JLabel level4Mod4 = new JLabel("LOA");
		GroupLayout gl_levelFourPanel = new GroupLayout(levelFourPanel);
		gl_levelFourPanel.setHorizontalGroup(
			gl_levelFourPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_levelFourPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_levelFourPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(levelFourModulesLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addComponent(level4Mod1)
						.addComponent(level4Mod2)
						.addComponent(level4Mod3)
						.addComponent(level4Mod4))
					.addContainerGap(139, Short.MAX_VALUE))
		);
		gl_levelFourPanel.setVerticalGroup(
			gl_levelFourPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_levelFourPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(levelFourModulesLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(level4Mod1)
					.addGap(18)
					.addComponent(level4Mod2)
					.addGap(18)
					.addComponent(level4Mod3)
					.addGap(18)
					.addComponent(level4Mod4)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		levelFourPanel.setLayout(gl_levelFourPanel);
		contentPanel.setLayout(gl_contentPanel);
	}

}
