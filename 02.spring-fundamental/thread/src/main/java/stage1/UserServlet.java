package stage1;

import java.util.ArrayList;
import java.util.List;

public class UserServlet {

    private final List<User> users = new ArrayList<>();

    public void service(final User user) {
        join(user);
    }

    private synchronized void join(final User user) { //join() is capsulated //여러 쓰레드가 같은 UserServlet을 동시 참조하기 때문에, synchronized을 붙여주지 않으면 race condition 일어난다!
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public int size() {
        return users.size();
    }

    public List<User> getUsers() {
        return users;
    }
}
