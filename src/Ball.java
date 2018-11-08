import java.awt.geom.Ellipse2D;

public class Ball {

	private Ellipse2D.Double body= new Ellipse2D.Double(200, 300, 15, 15);
	private int xDirectionBall = 1;
	private int yDirectionBall = 1;
	private double speed = 4;
	
	public Ellipse2D.Double getBody() {
		return body;
	}
	public void setBody(Ellipse2D.Double body) {
		this.body = body;
	}
	public int getxDirectionBall() {
		return xDirectionBall;
	}
	public void setxDirectionBall(int xDirectionBall) {
		this.xDirectionBall = xDirectionBall;
	}
	public int getyDirectionBall() {
		return yDirectionBall;
	}
	public void setyDirectionBall(int yDirectionBall) {
		this.yDirectionBall = yDirectionBall;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	
}
