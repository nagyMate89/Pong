
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;

public class Pong extends JComponent {
	private List<Ball> balls = new ArrayList<Ball>();
	private RoundRectangle2D.Double bat = new RoundRectangle2D.Double(200, 400, 200, 20, 30, 20);
	private int blockX = 150;
	private int blockY = 50;
	private int blockW = 30;
	private int blockH = 30;

	private ArrayList<Rectangle2D.Double> blocks = new ArrayList<Rectangle2D.Double>();

	private int batSpeed = 15;
	private BufferedImage buffer;
	private boolean checkIntersection = true;
	private boolean blockIntersection = true;
	private boolean shouldIncrementLevel = false;
	private ArrayList<Boolean> shouldRenderBlock = new ArrayList<Boolean>();
	private UIBar uiBar;
	private Integer score = 0;
	private Integer level = 1;
	private EndGameListener listener;
	private Graphics2D g2;
	private boolean shouldSetupBlocks = true;
	private Clip clip;
	private AudioInputStream audio;

	public Pong() {

		setLayout(new BorderLayout());
		uiBar = new UIBar();

		addMouseMotionListener(new MouseMotionListener() {

			public void mouseMoved(MouseEvent e) {
				bat.x = e.getX();

			}

			public void mouseDragged(MouseEvent e) {

			}
		});

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

			public boolean dispatchKeyEvent(KeyEvent e) {

				int key = e.getKeyCode();

				switch (key) {

				case KeyEvent.VK_UP:

					break;
				case KeyEvent.VK_DOWN:

					break;
				case KeyEvent.VK_LEFT:

					bat.x -= batSpeed;

					break;
				case KeyEvent.VK_RIGHT:
					bat.x += batSpeed;
					break;
				}

				return false;
			}
		});

		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				buffer = null;
			}

		});

		Cursor hiddenCursor = getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB),
				new Point(1, 1), "");
		setCursor(hiddenCursor);
		add(uiBar, BorderLayout.NORTH);
	}

	protected void paintComponent(Graphics g) {

		if (buffer == null) {
			buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		}

		g2 = (Graphics2D) buffer.getGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());

		setBlocksUp();
		for (int i = 0; i < balls.size(); i++) {
			g2.setColor(Color.red);
			g2.fill(balls.get(i).getBody());
		}

		g2.setColor(Color.BLUE);
		g2.fill(bat);

		g.drawImage(buffer, 0, 0, null);
	}

	public void update() {
		if (balls.size() == 0) {
			balls.add(new Ball());
		}
		for (int i = 0; i < balls.size(); i++) {
			balls.get(i).getBody().x += balls.get(i).getxDirectionBall() * balls.get(i).getSpeed();
			balls.get(i).getBody().y += balls.get(i).getyDirectionBall() * balls.get(i).getSpeed();

			if (balls.get(i).getBody().x < 0) {
				balls.get(i).setxDirectionBall(1);
				balls.get(i).getBody().x = 0;
			} else if (balls.get(i).getBody().x > getWidth() - balls.get(i).getBody().getBounds().width) {
				balls.get(i).setxDirectionBall(-1);
				balls.get(i).getBody().x = getWidth() - balls.get(i).getBody().getBounds().width;
			}

			if (balls.get(i).getBody().y < 50) {
				balls.get(i).setyDirectionBall(1);
				balls.get(i).getBody().y = 50;
			} else if (balls.get(i).getBody().y > (getHeight() - balls.get(i).getBody().getBounds().height)) {
				balls.get(i).setyDirectionBall(-1);
				balls.get(i).getBody().y = (getHeight() - balls.get(i).getBody().getBounds().height);
			}

			if (bat.x >= getWidth() - 150) {
				bat.x = getWidth() - 150;
			} else if (bat.x <= 0) {
				bat.x = 0;
			}

			if (balls.get(i).getBody().intersects(bat.getBounds2D())) {
				if (checkIntersection) {
					balls.get(i).setyDirectionBall(-balls.get(i).getyDirectionBall());
					checkIntersection = false;

					for (int z = 0; z < shouldRenderBlock.size(); z++) {
						if (shouldRenderBlock.get(z) == true) {
							System.out.println("break at " + z + ".element");
							break;

						}
						if (z == shouldRenderBlock.size() - 1 && shouldRenderBlock.get(z) == false) {
							level++;
							
							uiBar.setLevel(level.toString());
							balls.get(i).setSpeed(balls.get(i).getSpeed() + 1);
							System.out.println("clearing...");
							shouldRenderBlock.clear();
							shouldSetupBlocks = true;
							blocks.clear();
							balls.add(new Ball());
						}
					}

				}
			} else {
				checkIntersection = true;
			}

			for (int z = 0; z < blocks.size(); z++) {
				if (balls.get(i).getBody().intersects(blocks.get(z).getBounds2D()) && shouldRenderBlock.get(z)) {

					if (blockIntersection) {

						File file = new File("C:\\Users\\nagym\\eclipse-workspace\\Pong\\music\\hit.wav");

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
						clip.loop(0);

						balls.get(i).setyDirectionBall(-balls.get(i).getyDirectionBall());
						balls.get(i).setxDirectionBall(-balls.get(i).getxDirectionBall());
						g2.clearRect(200, 50, 30, 30);
						blockIntersection = false;
						shouldRenderBlock.set(z, false);
						score++;
						uiBar.setScore(score.toString());
					}

				} else {
					blockIntersection = true;
				}

				// if ((shouldRenderBlock.stream().allMatch(shouldRenderBlock.get(0):: equals )
				// && score%16!=0)) {
				// shouldSetupBlocks=true;

				if (balls.get(i).getBody().y > 480) {
					fireGameOver(score);

				}

			}

			repaint();

		}
	}

	public void setListener(EndGameListener listener) {
		this.listener = listener;
	}

	public void fireGameOver(int score) {
		if (listener != null) {
			listener.gameOver(score);

		}
	}

	public void setBlocksUp() {

		if (shouldSetupBlocks) {
			for (int i = 0; i < 17; i++) {
				shouldRenderBlock.add(new Boolean(true));
				if (i < 8 || i == 8) {
					blocks.add(new Rectangle2D.Double((blockX + i * 40), blockY, blockW, blockH));
				}
				if (i > 8 && i < 14) {
					blocks.add(new Rectangle2D.Double(((blockX + (i - 7) * 40)), blockY + 40, blockW, blockH));
				}
				if (i > 14 && i < 17 || i == 14) {
					blocks.add(new Rectangle2D.Double(((blockX + (i - 14) * 40) + 120), blockY + 80, blockW, blockH));
				}
			}
			shouldSetupBlocks = false;
		}

		for (int i = 0; i < blocks.size(); i++) {
			if (shouldRenderBlock.get(i)) {

				if (i % 2 == 0) {
					g2.setColor(Color.GREEN);
					g2.fill(blocks.get(i));
				} else if (i % 2 > 0) {

					g2.setColor(Color.RED);
					g2.fill(blocks.get(i));
				}

			}

		}

	}
}
