package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Enemy3 extends Thread implements VisibleObjects {

    Point location;
    MyCanvas canvas;
    Image enemy = null;

    public Enemy3(Point location, MyCanvas c) {
        this.location = location;
        this.canvas = c;
        enemy = new ImageIcon(getClass().getResource("/res/backgrounds/enemy3.png")).getImage();
        start();
    }

    @Override
    public void run() {
        while (location.y < Toolkit.getDefaultToolkit().getScreenSize().getHeight()) {
            location.y += 3;
            try {
                Thread.sleep(85);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        canvas.removeObject(this);
    }

    @Override
    public void display(Graphics g) {
        g.drawImage(enemy, location.x, location.y, 100, 100, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(location.x, location.y, 100, 100); // Enemy3 bounds for collision
    }
}
