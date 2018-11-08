import java.applet.AudioClip;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JApplet;
import javax.swing.Timer;

public class Main extends JApplet{
	
	private Pong game;
	private Timer timer;
	private int count;
	private StartPanel startPanel;
	private GameOverPanel gameOverPanel;
	private CardLayout cards;
	private boolean shouldStart=false;
	  private Clip clip;
	  private AudioInputStream audio;
	@Override
	public void destroy() {
	
	}
	
	

	@Override
	public void init() {
		
		cards = new CardLayout();
		gameOverPanel= new GameOverPanel();
		startPanel = new StartPanel();
		game = new Pong();
		setSize(600,500);
		setLayout(cards);
		
		add(startPanel,"start");
		add(game, "game");
		add(gameOverPanel,"gameOver");
		File file = new File("C:\\Users\\nagym\\eclipse-workspace\\Pong\\music\\music.wav");
	  
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			audio = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     try {
			clip.open(audio);
		} catch (LineUnavailableException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     clip.loop(Clip.LOOP_CONTINUOUSLY);
	
		
		game.setListener(new EndGameListener() {
			
			@Override
			public void gameOver(int score) {
				gameOverPanel.setScore(score);
				System.out.println("main: " + score);
				cards.show(Main.this.getContentPane(), "gameOver");
				timer.stop();
				
			}
		});
		
		startPanel.setListener(new StartPanelListener() {
			
			@Override
			public void startGame() {
				cards.show(Main.this.getContentPane(), "game");
				shouldStart=true;
				start();
				
				
				
				
			}
		});
		
		
		timer = new Timer(10, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				
			}
				
			});
		
		
		
	
	}

	@Override
	public void start() {
if (shouldStart) {
	timer.start();
}
	
	}

	@Override
	public void stop() {
	
		timer.stop();
	}

	
	}


