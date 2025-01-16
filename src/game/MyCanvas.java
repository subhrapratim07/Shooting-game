package game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyCanvas extends JPanel {

    List<VisibleObjects> objects = new ArrayList<VisibleObjects>();
    Image background = null;
    int score = 0; // Variable to keep track of the score

    // Track the types of objects that need to be removed to trigger Game Over
    boolean balloonRemoved = false;
    boolean bombRemoved = false;
    boolean enemyRemoved = false;
    boolean enemy2Removed = false;
    boolean enemy3Removed = false;
    boolean enemy4Removed = false;

    public MyCanvas() {
        background = new ImageIcon(getClass().getResource("/res/backgrounds/bg.jpg")).getImage();
    }

    public void addObject(VisibleObjects o) {
        objects.add(o);
    }

    public void removeObject(VisibleObjects o) {
        // Only remove the object from the canvas list
        objects.remove(o);

        // Check if the object is of the specific type and mark it as removed
        if (o instanceof Baloon) {
            balloonRemoved = true;
        } else if (o instanceof Bomb) {
            bombRemoved = true;
        } else if (o instanceof Enemy) {
            enemyRemoved = true;
        } else if (o instanceof Enemy2) {
            enemy2Removed = true;
        } else if (o instanceof Enemy3) {
            enemy3Removed = true;
        } else if (o instanceof Enemy4) {
            enemy4Removed = true;
        }
    }

    // Increment score when an object is hit (only called by Bullet when collision occurs)
    public void incrementScore() {
        if (score < 6) {
            score++; // Only increment if the score is below 6
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);

        // Display all objects
        for (VisibleObjects o : objects) {
            o.display(g);
        }

        // Set the color to red for the score
        g.setColor(java.awt.Color.BLACK);
        // Display score in the top-left corner
        g.drawString("Score: " + score, 10, 20);

        // Check if all specific objects are removed and show game over
        if (balloonRemoved && bombRemoved && enemyRemoved && enemy2Removed && enemy3Removed && enemy4Removed) {
            // Set color to black for the Game Over message
            g.setColor(java.awt.Color.RED);

            // Set a larger, bold font for the Game Over message
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 48));

            // Calculate the position to center the text
            String gameOverText = "Game Over";
            String finalScoreText = "Final Score: " + score;

            // Get the width of the text to center it
            int gameOverWidth = g.getFontMetrics().stringWidth(gameOverText);
            int scoreWidth = g.getFontMetrics().stringWidth(finalScoreText);
            int screenWidth = getWidth(); // Assuming this is a method that returns the width of your screen/window
            int screenHeight = getHeight(); // Assuming this is a method that returns the height of your screen/window

            // Draw "Game Over" centered in the middle of the screen
            g.drawString(gameOverText, (screenWidth - gameOverWidth) / 2, screenHeight / 2 - 20);
            // Draw the "Final Score" centered below "Game Over"
            g.drawString(finalScoreText, (screenWidth - scoreWidth) / 2, screenHeight / 2 + 20);
        }

    }

    // Getter for score
    public int getScore() {
        return score;
    }
}
