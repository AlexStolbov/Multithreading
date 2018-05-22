package pingpong;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GamerRightTest {
    @Test
    public void whenBallOnRightBorderThenMoveItOnDelta() {
        int limitX = 30;
        int limitY = 30;
        double delta = 1;
        Ball ball = new Ball(limitY);
        double start = limitX - ball.getHeight();
        ball.getRect().setX(start);
        GamerRight gamer = new GamerRight(ball, limitX, limitY);
        Thread go = new Thread(gamer);
        go.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            return;
        }
        assertThat(ball.getRect().getX(), is(start - delta));
        ball.setGameOver(true);
    }
}