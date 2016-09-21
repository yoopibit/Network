
import java.applet.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;   			// імпортуються класи з  пакетів 

public class Balls extends Applet implements Runnable {
	public static Vector<Ball> balls;
	public static Vector<Thread> ballsThread;
	int MaxBalls = 5;
	public static int radiusOfBall;
	Thread animator;
	int delay;
	int frame;
	public static boolean bDrawing;
	public String getAppletInfo( ) {
			return "Draw Balls";
  	}
	
	public void init(){
		ballsThread = new Vector<Thread>();
		balls = new Vector<Ball>();
		radiusOfBall = 80;
		String str = getParameter("fps");
		int fps = (str != null) ? Integer.parseInt(str) : 10;
		delay = (fps > 0) ? (1000 / fps) : 100;
	}
	 public void start() {
			animator = new Thread(this);
			animator.start();
	 }
	 public void run() {
			// Remember the starting time
			long startTime = System.currentTimeMillis();
			//This is the animation loop.
			
			while (Thread.currentThread() == animator) {			
			    
				CheckAnotherBallDrop();
				//Advance the animation frame. 
			    frame++;
			    
			    //Display it.
			    repaint();
			    
			    //Delay depending on how far we are behind.
			    try { 
			    	startTime +=delay;
			    	Thread.sleep(Math.max(0, startTime-System.currentTimeMillis())); 
			    } catch (InterruptedException e) { 
			    	break; 
			    }
			}
	 }

	
	@Override
	public void paint(Graphics g) {	 //   метод  малює лінії
		Dimension dimAppWndDimension = getSize();
		setBackground(Color.pink);
		g.setColor(Color.black);
		g.drawRect(0, 0, dimAppWndDimension.width  - 1,dimAppWndDimension.height - 1);    
		for (int i=0; i < balls.size(); i++) 	 
		 {
			Ball currentBall = balls.elementAt(i);
			int x = (int)currentBall._x;
			int y = (int)currentBall._y;
			g.drawOval(	x,
						y,
						radiusOfBall, 
						radiusOfBall
					  );
			g.setColor(currentBall.GetColor());
			g.fillOval(x, y, radiusOfBall, radiusOfBall);
		 }
  	}
	
	public boolean mouseDown(Event evt,int x,int y)  { 
    	if(evt.clickCount > 1) { 
    	 	 //balls.removeAllElements();
    		for (Thread ballThread: ballsThread) {
				ballThread.stop();
			} 
    		ballsThread.clear();
    		balls.clear();
    	 	return true;
    	}
    	return true;
	}
	public boolean mouseUp(Event evt, int x, int y){
		if (balls.size() > MaxBalls)
			return false;
		
		Random rand = new Random();
		
		Runnable ball = new Ball(0.00003, rand.nextInt()%360,
						         x, y , new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)), this);
		
		balls.addElement((Ball)ball);
		
		Thread thread = new Thread(ball);
		ballsThread.addElement(thread);
		thread.start();
		bDrawing = true;

		return true;
		
	}
	private void CheckAnotherBallDrop() {
			
		for (int i = 0; i < balls.size(); i++) {
			for (int j = i + 1; j < balls.size(); j++) {
				Ball iPos = balls.elementAt(i);
				Ball jPos = balls.elementAt(j);
				double distance = Ball.Distance((int)jPos._x, (int)jPos._y, (int)iPos._x, (int)iPos._y); 
				if (distance < Balls.radiusOfBall * 1.5){
					
					iPos._x -= (jPos._x - iPos._x) / radiusOfBall * 2;
					jPos._x += (jPos._x - iPos._x) / radiusOfBall * 2;
										
					iPos._y -= (jPos._y - iPos._y) / radiusOfBall * 2;
					jPos._y += (jPos._y - iPos._y) / radiusOfBall * 2;
				}
				if (distance < Balls.radiusOfBall * 1.2){
					
					iPos._angle = (iPos._angle + 90) % 360;
					jPos._angle = (jPos._angle - 90) % 360;
					
				}
			}
		}
	}
}