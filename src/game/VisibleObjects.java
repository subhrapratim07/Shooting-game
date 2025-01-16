package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface VisibleObjects {
    void display(Graphics g);
    Rectangle getBounds(); // Method for collision detection
}
