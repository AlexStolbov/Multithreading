package userstorage;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenTransferAmountThenUserFromIsIncreaseAndUserToIsDecrease() {
        UserStorage userStorage = new UserStorage();
        User from = new User(1, 100);
        User to = new User(2, 50);
        userStorage.add(from);
        userStorage.add(to);
        boolean res = userStorage.transfer(1, 2, 50);
        assertTrue(res);
        assertThat(from.getAmount(), is(50));
        assertThat(to.getAmount(), is(100));
    }

    @Test
    public void multi() throws InterruptedException {
        int max = 10000;
        UserStorage storage = new UserStorage();
        User from = new User(1, max);
        User to = new User(2, 0);
        storage.add(from);
        storage.add(to);
        Thread one = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < max; i++) {
                    storage.transfer(1, 2, 1);
                }
            }
        };
        Thread two = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < max; i++) {
                    storage.transfer(2, 1, 1);
                }
            }
        };
        one.start();
        two.start();
        one.join();
        two.join();
        assertThat(from.getAmount(), is(max));
        assertThat(to.getAmount(), is(0));
    }

}