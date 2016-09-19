
import java.applet.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;   			// імпортуються класи з  пакетів 

public class Balls extends Applet  {
	
	public static Vector<Dimension> balls;
	public static int radiusOfBall;
//	Dimension dmDown;   //  поля для збереження  координат лінії (початок )
//	Dimension dmUp;   //  поля для збереження  координат лінії (кінець лінії)
//	Dimension dmPrev; //  Координати кінця старої лінії  
	boolean bDrawing; //  стан аплета:  малювання лінії - true, інший- false
	//Vector lines;    //  об’єкт  для зберігання координат ліній
	public String getAppletInfo( ) {  // метод для повернення назви аплету
			return "Draw Balls";
  	}
	
	public void init(){
		balls = new Vector<Dimension >();
		radiusOfBall = 20;
		Runnable runnable = new Loop(this);	
		Thread thread = new Thread(runnable);
		thread.start();
//		try {
//			thread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	public boolean IfDraw()
	{
		return bDrawing;
	}
	
	public void paint(Graphics g) {	 //   метод  малює лінії
		Dimension dimAppWndDimension = getSize();
		setBackground(Color.pink);
		g.setColor(Color.black);
		g.drawRect(0, 0, dimAppWndDimension.width  - 1,dimAppWndDimension.height - 1);    
		for (int i=0; i < balls.size(); i++) 	 
		 {
			g.drawOval(	(int)balls.elementAt(i).getWidth(),
						(int)balls.elementAt(i).getHeight(),
						radiusOfBall, 
						radiusOfBall
					  );
					
//			Rectangle p = (Rectangle)lines.elementAt(i);	 
//			g.drawLine(p.width, p.height,  p.x, p.y);
//			g.drawString("<" + p.width+","+p.height+">",p.width,p.height);
//			g.drawString("<"+p.x+","+p.y+">",p.x,p.y);
		 }
		//bDrawing = false;
  	}
	
//	public boolean mouseDown(Event evt,int x,int y)  { 
//    	if(evt.clickCount > 1)    { 
//    	 	 lines.removeAllElements(); 
//    	 	 repaint();
//    	 	 return true;
//    	}
//    	dmDown = new Dimension(x, y); //зберігання поточних координат курсору 
//    	dmPrev = new Dimension(x, y);
//    	bDrawing = false;
//    	return true;
//	}
	public boolean mouseUp(Event evt, int x, int y){
		Random rand = new Random();
		Dimension pos = new Dimension(x, y);
		
		balls.addElement(pos);
		Runnable ball = new Ball(balls.size() - 1, 0.0001, rand.nextInt()%360, pos, Color.blue);
		Thread thread = new Thread(ball);
		thread.start();
		bDrawing = true;
		
//		try {
//			thread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return true;
		
		//		if(bDrawing) {
//			dmUp = new Dimension(x, y);
//			lines.addElement(   
//					new Rectangle(dmDown.width, 
//							dmDown.height, x, y)); //  додавання нового елементу в масив lines
//			repaint();
//			bDrawing = false; //скидання ознаки малювання
//		}
//		return true;
	}
//	public boolean mouseDrag(Event evt, int x, int y)  {
//		Graphics g = getGraphics();
//		bDrawing = true;
//		g.setColor(Color.yellow); // стирання лінії - малювання  кольором фону
//		g.drawLine(dmDown.width,dmDown.height,dmPrev.width,dmPrev.height);
//		g.setColor(Color.black); 	//  малювання  лінії чорним кольором кольором  
//		g.drawLine(dmDown.width, dmDown.height, x, y);
//		dmPrev = new Dimension(x, y); 	// зберігання  координат лінії 
//		return true;
//	}
//	 	public boolean mouseMove(Event evt, int x, int y)  {
//	 		bDrawing = false; 	//відключає режим малювання
//	 		return true;
//	 	}	
}