package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Bullet implements VisibleObjects {

    Point location;
    MyCanvas canvas;
    Image bullet = null;
    boolean isMoving = false; // Track if the bullet is moving

    public Bullet(Point location, MyCanvas c) {
        this.location = location;
        this.canvas = c;
        bullet = new ImageIcon(getClass().getResource("/res/backgrounds/bullet.png")).getImage();
    }

    @Override
    public void display(Graphics g) {
        if (isMoving) {
            g.drawImage(bullet, location.x, location.y, 50, 50, null);
        }
    }

    public void startMoving(Runnable onComplete) {
        isMoving = true;

        // Start a new thread for bullet movement
        new Thread(() -> {
            while (isMoving) {
                location.x += 5; // Move the bullet along the X-axis

                // Check for collisions with objects except the shooter
                for (VisibleObjects object : new ArrayList<>(canvas.objects)) {
                    if (object != this && !(object instanceof Shooter) && object.getBounds().intersects(getBounds())) {
                        // Collision detected: Bullet hits an object
                        isMoving = false; // Stop bullet movement
                        canvas.removeObject(object); // Remove collided object
                        canvas.removeObject(this); // Remove bullet itself
                        canvas.incrementScore(); // Increment the score only when an object is hit
                        onComplete.run(); // Notify shooter that the bullet hit the object
                        canvas.repaint(); // Repaint canvas to reflect changes
                        return; // Exit if the bullet hits an object
                    }
                }

                // Stop bullet if it moves out of bounds (without incrementing score)
                if (location.x > canvas.getWidth()) {
                    isMoving = false;
                    canvas.removeObject(this); // Remove bullet
                    onComplete.run(); // Notify shooter that the bullet missed
                    canvas.repaint(); // Repaint the canvas to reflect changes
                }

                try {
                    Thread.sleep(1); // Adjust speed of movement
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                canvas.repaint(); // Repaint the canvas to reflect changes
            }
        }).start();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(location.x, location.y, 50, 50); // Bullet bounds for collision
    }
}
