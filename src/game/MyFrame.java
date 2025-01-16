package game;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MyFrame extends JFrame implements ActionListener{

	MyCanvas canvas = new MyCanvas();
	

	public MyFrame() {
		setTitle("My Game");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(canvas);
		Baloon b = new Baloon(new Point(1300,100),canvas);
		canvas.addObject(b);
		Shooter s = new Shooter(new Point(0,500),canvas);
		canvas.addObject(s);
		addKeyListener(s);
		Enemy e = new Enemy(new Point(1000,100),canvas);
		canvas.addObject(e);
		Enemy2 ee = new Enemy2(new Point(1100,0),canvas);
		canvas.addObject(ee);
		Enemy3 eee = new Enemy3(new Point(900,0),canvas);
		canvas.addObject(eee);
		Enemy4 eeee = new Enemy4(new Point(800,0),canvas);
		canvas.addObject(eeee);
		Bomb bb = new Bomb(new Point(700,0),canvas);
		canvas.addObject(bb);
		Timer timer = new Timer(100, this);
		timer.start();
	}
	
	public static void main(String[] args) {
		MyFrame f = new MyFrame();
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		canvas.repaint();
		
	}

}