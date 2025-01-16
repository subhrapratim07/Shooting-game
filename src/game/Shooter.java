package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.ImageIcon;

public class Shooter implements VisibleObjects, KeyListener {

    Point location;
    MyCanvas canvas;
    Image shooter = null;
    Bullet bullet; // Reference to the bullet
    boolean isBulletFired = false; // Track whether the bullet is fired

    public Shooter(Point location, MyCanvas c) {
        this.location = location;
        this.canvas = c;

        // Load shooter image
        URL shooterURL = getClass().getResource("/res/backgrounds/shotter.png");
        if (shooterURL != null) {
            shooter = new ImageIcon(shooterURL).getImage();
        } else {
            System.err.println("Shooter image not found!");
        }

        bullet = new Bullet(new Point(location.x + 182, location.y + 59), c); // Position the bullet relative to the shooter
    }

    @Override
    public void display(Graphics g) {
        g.drawImage(shooter, location.x, location.y, 200, 200, null);
        bullet.display(g); // Always display the bullet
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No implementation needed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int screenHeight = canvas.getHeight();
        int shooterHeight = 200;

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (location.y - 5 >= 0) {
                location.y -= 5;
                if (!isBulletFired) {
                    bullet.location.y -= 5; // Adjust bullet if not fired
                }
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (location.y + 5 + shooterHeight <= screenHeight) {
                location.y += 5;
                if (!isBulletFired) {
                    bullet.location.y += 5; // Adjust bullet if not fired
                }
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!isBulletFired) {
                SoundUtils.playSound("/res/sounds/fire.wav"); // Play fire sound
                resetBulletPosition(); // Reset bullet to shooter's current position
                isBulletFired = true; // Mark the bullet as fired
                bullet.startMoving(() -> isBulletFired = false); // Reset flag when bullet stops (including when missed)
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No implementation needed
    }

    // Reset the bullet's position to align with the shooter
    private void resetBulletPosition() {
        bullet.location.x = location.x + 182; // Align bullet to shooter
        bullet.location.y = location.y + 59; // Align bullet vertically
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(location.x, location.y, 200, 200); // Adjust width and height as per shooter's size
    }
}
