package level4_Dispatcher_extends_HttpServlet.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import level4_Dispatcher_extends_HttpServlet.config.web.RequestMapping;
import level4_Dispatcher_extends_HttpServlet.controller.MemberController;
import level4_Dispatcher_extends_HttpServlet.util.UtilsLog;



import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String TAG = "DispatcherServlet : ";

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UtilsLog.getInstance().info(TAG, "doDelete()");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberController memberController = new MemberController();

        UtilsLog.getInstance().info(TAG, "doGet()");
        UtilsLog.getInstance().info(TAG, req.getRequestURI());

        String identifier = req.getRequestURI();

        // 리플렉션 발동 /login
        Method[] methods = memberController.getClass().getDeclaredMethods();
        for(Method method : methods) {
            Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
            RequestMapping requestMapping = (RequestMapping) annotation;

            if(identifier.equals(requestMapping.value())) {

                try {
                    Parameter[] params = method.getParameters();
                    Object[] queue = new Object[params.length];

                    for(int i=0; i< params.length; i++) {

                        Class<?> cls = params[i].getType();

                        if(cls == HttpServletRequest.class) {
                            System.out.println("Request 찾음");
                            queue[i] = req;
                        }else if(cls == HttpServletResponse.class) {
                            System.out.println("Response 찾음");
                            queue[i] = resp;
                        }else {
                            Constructor<?> constructor = cls.getConstructor();
                            queue[i] = constructor.newInstance();

//							Field f = queue[i].getClass().getField("username");
//							f.setAccessible(true);
//							f.set(queue[i], "안녕");

                            for (Method m : queue[i].getClass().getDeclaredMethods()) {
                                if(m.getName().startsWith("set")) {
                                    String key = m.getName().replace("set", "").toLowerCase();

                                    String param = req.getParameter(key);
                                    if(param != null) {
                                        m.invoke(queue[i], param);
                                    }
                                }
                            }
                        }

                        System.out.println("size : "+queue.length);
                    }
                    // 전 (인터셉터)
                    method.invoke(memberController, queue);
                    // 후 (인터셉터)

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UtilsLog.getInstance().info(TAG, "doPost()");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UtilsLog.getInstance().info(TAG, "doPut()");
    }

}