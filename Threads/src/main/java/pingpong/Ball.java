package pingpong;

import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Ball {
    /**
     * Направление движения, истина - направо, иначе - налево.
     */
    private boolean toRight;

    /**
     * Изображение мяча
     */
    private final Rectangle rect;

    /**
     * Угол отскока шара
     */
    private double k;

    /**
     * Генератор случайных углов оскока
     */
    private Random rnd;

    private double maxY;

    private boolean gameOver;

    public Ball(double maxY) {
        this.rect =  new Rectangle(0, 10, 10, 10);
        this.toRight = true;
        this.rnd = new Random();
        this.maxY = maxY;
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean isToRight() {
        return toRight;
    }

    public double getK() {
        return k;
    }

    public double getHeight() {
        return rect.getHeight();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Определяет угол отскока
     * @param right - направление движения шара
     */
    public void move(boolean right) {
        double destY = this.rnd.nextDouble() * this.maxY;
        int delta = 1;
        double toX = this.maxY;
        if (!right) {
            delta = -1;
            toX = 0;
        }
        this.k = (this.rect.getY() - destY) / (this.rect.getX() - toX);
        this.rect.setX(this.rect.getX() + delta);
        this.toRight = right;
    }
}
