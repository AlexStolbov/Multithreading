package pingpong;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class RectangleMoveTest {
    @Test
    public void whenBallOnBorderThenItIsNotMove() {
        int limitX = 50;
        int limitY = 50;
        Ball ball = new Ball(limitY);
        assertThat(ball.getRect().getX(), is(0.0));
        Thread move = new Thread(new RectangleMove(ball, limitX, limitY));
        move.start();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            return;
        }
        assertThat(ball.getRect().getX(), is(0.0));
        ball.setGameOver(true);
    }

    @Test
    public void whenBallNotOnBorderThenItIsMoveToOtherBorder() {
        int limitX = 30;
        int limitY = 30;
        Ball ball = new Ball(limitY);
        assertThat(ball.getRect().getX(), is(0.0));
        ball.getRect().setX(1);
        Thread move = new Thread(new RectangleMove(ball, limitX, limitY));
        move.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            return;
        }
        assertThat(ball.getRect().getX(), is((double) limitX - 10));
        ball.setGameOver(true);
    }

}