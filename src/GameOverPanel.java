import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Insets;

public class GameOverPanel extends JPanel {
	private JTextField txtGameOver;
	private JTextField textField;
	private JTextField txtGameOver_1;
	private JTextField txtTheFinalScore;
	
	

	
	public GameOverPanel() {
		setBackground(new Color(176, 224, 230));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{151, 129, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		txtGameOver_1 = new JTextField();
		txtGameOver_1.setEditable(false);
		txtGameOver_1.setBackground(new Color(176, 196, 222));
		txtGameOver_1.setForeground(new Color(255, 69, 0));
		txtGameOver_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtGameOver_1.setText("GAME OVER");
		txtGameOver_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		GridBagConstraints gbc_txtGameOver_1 = new GridBagConstraints();
		gbc_txtGameOver_1.anchor = GridBagConstraints.SOUTH;
		gbc_txtGameOver_1.insets = new Insets(0, 0, 5, 0);
		gbc_txtGameOver_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGameOver_1.gridx = 0;
		gbc_txtGameOver_1.gridy = 0;
		add(txtGameOver_1, gbc_txtGameOver_1);
		txtGameOver_1.setColumns(10);
		
		txtTheFinalScore = new JTextField();
		txtTheFinalScore.setEditable(false);
		txtTheFinalScore.setHorizontalAlignment(SwingConstants.CENTER);
		txtTheFinalScore.setForeground(new Color(255, 69, 0));
		txtTheFinalScore.setFont(new Font("Tahoma", Font.BOLD, 22));
		txtTheFinalScore.setBackground(new Color(176, 196, 222));
		GridBagConstraints gbc_txtTheFinalScore = new GridBagConstraints();
		gbc_txtTheFinalScore.anchor = GridBagConstraints.NORTH;
		gbc_txtTheFinalScore.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTheFinalScore.gridx = 0;
		gbc_txtTheFinalScore.gridy = 1;
		add(txtTheFinalScore, gbc_txtTheFinalScore);
		txtTheFinalScore.setColumns(10);
		

	}
	
	public void setScore(Integer score) {
		txtTheFinalScore.setText("THE FINAL SCORE IS: " + score.toString());
	}

}
