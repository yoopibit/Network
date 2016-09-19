import java.awt.Color;
import java.awt.Dimension;

public class Ball implements Runnable {
	private int _numBall;
	private double _speed;
	private int _angle;
	private Dimension _pos;
	private Color _color;
	private double _x, _y;
	private double _startX, _startY;
	
	public void  SetNumBall(int numBall) {
		_numBall = numBall;
	}
	public void  SetSpeed(double speed) {
		_speed = speed;
	}
	public void  SetAngle(int angle) {
		_angle = angle;
	}
	public void  SetPosition(Dimension pos) {
		_pos = pos;
	}
	
	Ball(int numBall, double speed, int angle, Dimension pos, Color color) {
		_x = _y = 0.0;
		_numBall = numBall;
		_angle = angle;
		_speed = speed;
		_pos = pos;
		_color = color;
		_startX = Balls.balls.elementAt(_numBall).width;
		_startY = Balls.balls.elementAt(_numBall).height;
	}
	
	public void run()
	{
		while(true){
			CalculatePos();
		}
	}
	
	private void CalculatePos() {
		double radians = Math.toRadians(_angle);
		_y += Math.sin(radians) * _speed;
		_x += Math.cos(radians) * _speed;
		
		Balls.balls.elementAt(_numBall).height = (int)(_startY + _y);
		Balls.balls.elementAt(_numBall).width = (int)(_startX + _x);
	}
}
