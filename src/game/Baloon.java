package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Baloon extends Thread implements VisibleObjects {

    Point location;
    MyCanvas canvas;

    public Baloon(Point location, MyCanvas c) {
        this.location = location;
        this.canvas = c;
        start();
    }

    @Override
    public void run() {
        while (location.y < Toolkit.getDefaultToolkit().getScreenSize().getHeight()) {
            location.y += 3;
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        canvas.removeObject(this);
    }

    @Override
    public void display(Graphics g) {
        int[] x = {location.x + 10, location.x - 25, location.x + 25, location.x - 10};
        int[] y = {location.y + 10, location.y - 50, location.y - 50, location.y + 10};
        g.setColor(Color.GREEN);
        g.fillArc(location.x - 25, location.y - 75, 50, 50, 0, 180);
        g.setColor(Color.RED);
        g.fillPolygon(new Polygon(x, y, 4));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(location.x - 25, location.y - 75, 50, 50); // Balloon bounds for collision
    }
}
