import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

public class Ball implements Runnable {
	private int deepWindow = Balls.radiusOfBall;
	private double _speed;
	public int _angle;
	private Color _color;
	public double _x, _y;
	private Balls _balls;
	
	
	public void  SetSpeed(double speed) {
		_speed = speed;
	}
	public void  SetAngle(int angle) {
		set_angle(angle);
	}
	public Color GetColor()
	{
		return _color;
	}
	Ball(double speed, int angle, int x, int y, Color color, Balls balls) {
		set_angle(angle);
		_speed = speed;
		_x = x;
		_y = y;
		_color = color;
		_balls = balls;
	}
	
	public void run()
	{
		while(true){
			CalculatePos();
		}
	}
	
	private void CalculatePos() {
		double radians = Math.toRadians(get_angle());
		_y += Math.sin(radians) * _speed;
		_x += Math.cos(radians) * _speed;
		CheckInForm();
		//CheckAnotherBallDrop();
	}
	private void CheckInForm()
	{
		Dimension sizeWindow = _balls.getSize();
		if (_x < 0 || _y < 0 || _x > (sizeWindow.getWidth() - deepWindow) ||
				_y > (sizeWindow.getHeight() - deepWindow) ) {
			set_angle(NewAngle(get_angle()));
			if (_x < 0 || _y < 0) {
				_x += 3;
				_y += 3;
			}else {
				_x -= 3;
				_y -= 3;
			}
				
				
		}
		if (_x - Balls.radiusOfBall > sizeWindow.getWidth() || 
				_y - Balls.radiusOfBall > sizeWindow.getHeight()){
			_x = sizeWindow.getWidth() - Balls.radiusOfBall * 2;
			_y = sizeWindow.getHeight() - Balls.radiusOfBall * 2;
		}
		
	}
	
	public static int NewAngle(int angle){
		return (angle + 100) % 360;
	}
	public static double Distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt( (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) );
	}
	public int get_angle() {
		return _angle;
	}
	public void set_angle(int _angle) {
		this._angle = _angle;
	}
}

