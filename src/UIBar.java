import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class UIBar extends JPanel {
	private JTextField txtScore;
	private JTextField txtLevel;

	/**
	 * Create the panel.
	 */
	public UIBar() {
		setBorder(new LineBorder(new Color(176, 224, 230), 3, true));
		setBackground(Color.BLACK);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtScore = new JTextField();
		txtScore.setEditable(false);
		txtScore.setForeground(new Color(176, 224, 230));
		txtScore.setFont(new Font("MV Boli", Font.BOLD, 18));
		txtScore.setText("SCORE: 0");
		txtScore.setBackground(Color.GRAY);
		add(txtScore);
		txtScore.setColumns(10);
		
		txtLevel = new JTextField();
		txtLevel.setEditable(false);
		txtLevel.setForeground(new Color(176, 224, 230));
		txtLevel.setText("LEVEL: 1");
		txtLevel.setFont(new Font("MV Boli", Font.BOLD, 18));
		txtLevel.setBackground(Color.GRAY);
		add(txtLevel);
		txtLevel.setColumns(10);

	}
	
	public void setScore(String score) {
		txtScore.setText("SCORE: " + score);
	}
	
	public void setLevel(String level) {
		txtLevel.setText("LEVEL: " + level);
	}

}
