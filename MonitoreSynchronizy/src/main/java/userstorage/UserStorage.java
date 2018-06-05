package userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> storage;

    public UserStorage() {
        this.storage = new HashMap<>();
    }

    public synchronized boolean add(User user) {
        storage.put(user.getId(), user);
        return true;
    }

    public synchronized boolean update(User user) {
        storage.put(user.getId(), user);
        return true;
    }

    public synchronized boolean delete(User user) {
        User prev = storage.remove(user);
        boolean res = true;
        if (prev == null) {
            res = false;
        }
        return res;
    }

    public boolean transfer(int fromId, int toId, int amount) {
        synchronized (this) {
            boolean res = false;
            User from = storage.get(fromId);
            User to = storage.get(toId);
            if (from != null && to != null) {
                from.setAmount(from.getAmount() - amount);
                to.setAmount(to.getAmount() + amount);
                res = true;
            }
            return res;
        }
    }
}
