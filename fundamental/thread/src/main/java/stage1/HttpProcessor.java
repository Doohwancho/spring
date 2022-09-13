package stage1;

public class HttpProcessor implements Runnable { //thread

    private final User user;
    private final UserServlet userServlet;

    public HttpProcessor(final User user, final UserServlet userServlet) {
        this.user = user;
        this.userServlet = userServlet;
    }

    @Override
    public void run() {
        userServlet.service(user);
    } //servlet에 user를 집어넣는 동작하는 쓰레드구나
}
