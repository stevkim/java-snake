import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
  private class Tile {
    int x;
    int y;

    Tile(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  int boardWidth;
  int boardHeight;
  int tileSize = 25;

  Tile snakeHead;

  Tile apple;
  Random random;

  // game logic
  Timer gameLoop;
  int velocityX;
  int velocityY;

  SnakeGame(int boardWidth, int boardHeight) {
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
    setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
    setBackground(Color.black);
    addKeyListener(this);
    setFocusable(true);

    snakeHead = new Tile(5, 5);

    apple = new Tile(10, 10);
    random = new Random();
    placeApple();

    velocityX = 0;
    velocityY = 1;

    gameLoop = new Timer(100, this);
    gameLoop.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }

  public void draw(Graphics g) {
    for (int i = 0; i < boardWidth / tileSize; i++) {
      g.drawLine(i * tileSize, 0, i * tileSize, boardHeight);
      g.drawLine(0, i * tileSize, boardWidth, i * tileSize);
    }

    // Apple
    g.setColor(Color.red);
    g.fillRect(apple.x * tileSize, apple.y * tileSize, tileSize, tileSize);

    // Snake
    g.setColor(Color.green);
    g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
  }

  public void placeApple() {
    apple.x = random.nextInt(boardWidth / tileSize);
    apple.y = random.nextInt(boardHeight / tileSize);
  }

  public void move() {
    snakeHead.x += velocityX;
    snakeHead.y += velocityY;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    move();
    repaint();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      velocityX = 0;
      velocityY = -1;
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      velocityX = 0;
      velocityY = 1;
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      velocityX = -1;
      velocityY = 0;
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      velocityX = 1;
      velocityY = 0;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }
}
