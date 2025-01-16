package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Bomb extends Thread implements VisibleObjects {

    Point location;
    MyCanvas canvas;
    Image bomb = null;

    public Bomb(Point location, MyCanvas c) {
        this.location = location;
        this.canvas = c;
        bomb = new ImageIcon(getClass().getResource("/res/backgrounds/bomb.png")).getImage();
        start();
    }

    @Override
    public void run() {
        while (location.y < Toolkit.getDefaultToolkit().getScreenSize().getHeight()) {
            location.y += 3; // Move bomb down
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        canvas.removeObject(this); // Remove bomb when it exits the screen
    }

    @Override
    public void display(Graphics g) {
        g.drawImage(bomb, location.x, location.y, 80, 80, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(location.x, location.y, 80, 80); // Define bomb bounds
    }
}
