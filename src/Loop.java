import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Loop extends Applet implements Runnable {
	Balls _balls;
	public Loop(Balls balls){
		_balls = balls;
	}
	
	public void init(){
	}
	
	public void run() {
		while (true){
			if (Balls.bDrawing)
				_balls.invalidate();
			}
		}
	
	public void paint(Graphics g) {	 //   метод  малює лінії
		Dimension dimAppWndDimension = getSize();
		setBackground(Color.pink);
		g.setColor(Color.black);
		g.drawRect(0, 0, dimAppWndDimension.width  - 1,dimAppWndDimension.height - 1);    
		for (int i=0; i < Balls.balls.size(); i++) 	 
		 {
			g.drawOval(	(int)Balls.balls.elementAt(i).getWidth(),
						(int)Balls.balls.elementAt(i).getHeight(),
						Balls.radiusOfBall, 
						Balls.radiusOfBall
					  );
					
//			Rectangle p = (Rectangle)lines.elementAt(i);	 
//			g.drawLine(p.width, p.height,  p.x, p.y);
//			g.drawString("<" + p.width+","+p.height+">",p.width,p.height);
//			g.drawString("<"+p.x+","+p.y+">",p.x,p.y);
		 }
		//bDrawing = false;
  	}
}
