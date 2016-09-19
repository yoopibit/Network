
public class Loop implements Runnable {
	private Balls _balls;
	
	Loop(Balls balls){
		_balls = balls;
	}
	public void run() {
		while (true){
			if (_balls.IfDraw())
				_balls.paint(_balls.getGraphics());
		}
	}
}
