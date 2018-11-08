import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartPanel extends JPanel {
	
	private StartPanelListener listener;

	public StartPanel() {
		setBackground(SystemColor.inactiveCaption);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 35, 101 };

		setLayout(gridBagLayout);

		JLabel lblFirstgame = new JLabel("FirstGame 1.0");

		lblFirstgame.setFont(new Font("MV Boli", Font.PLAIN, 30));
		lblFirstgame.setForeground(new Color(255, 51, 0));
		GridBagConstraints gbc_lblFirstgame = new GridBagConstraints();
		gbc_lblFirstgame.insets = new Insets(0, 0, 5, 0);
		gbc_lblFirstgame.gridx = 0;
		gbc_lblFirstgame.gridy = 0;
		add(lblFirstgame, gbc_lblFirstgame);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			fireStartGame();
			}
		});
		btnNewGame.setFont(new Font("MV Boli", Font.PLAIN, 20));
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.gridx = 0;
		gbc_btnNewGame.gridy = 1;
		add(btnNewGame, gbc_btnNewGame);

	}
	
	public void setListener(StartPanelListener listener) {
		this.listener=listener;
	}
	
	private void fireStartGame() {
		if (listener!=null) {
			listener.startGame();
		}
	}

}
