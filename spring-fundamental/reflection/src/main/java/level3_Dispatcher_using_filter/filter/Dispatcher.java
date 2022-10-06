package level3_Dispatcher_using_filter.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import level3_Dispatcher_using_filter.anno.RequestMapping;
import level3_Dispatcher_using_filter.controller.UserController;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;

/*

원래 maven 프로젝트였으면,

web.xml에 <filter>에 /*에 대해 Dispatcher을 필터로 등록함.



 */

public class Dispatcher implements Filter { //ServletRequest, ServletResponse가 오갈 때 필터가 인터셉터함

    private boolean isMatching = false;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //http://localhost:8080/reflect/user
        System.out.println("컨텍스트패스 : " + request.getContextPath()); // Q. context path: /reflect
        System.out.println("식별자주소 : " + request.getRequestURI()); // Q. 식별자 주소: /reflect/user
        System.out.println("전체주소 : " + request.getRequestURL()); // Q. 전체주소: http://localhost:8080/reflect/user

        String endPoint = request.getRequestURI().replaceAll(request.getContextPath(), ""); //"/user/"
        System.out.println("엔드포인트 : " + endPoint);

        /*
        //Problem
        //만약 리플렉션 없었으면, 모든 incoming uri에 대해, 컨트롤러에 함수 하나 더 만들 때 마다 1대1 매핑 시켜줘야 함.
        //컨트롤러에 함수 하나 만들 때 마다 유지보수 해줘야한다? -> 미친짓

        UserController userController = new UserController();

        if(endPoint.equals("/join")){
            userController.join();
        } else if(endPoint.equals("/login")) {
            userController.login();
        } else if(endPoint.equals("/user")) {
            userController.user();
        }

         */

        //Solution
        //reflection을 써서, RequestMapper 필터 하나 만들고 땡 하고 싶어.
        //너가 "/user"하면, @RequestMapping(uri = "/user") 붙은 메서드 때려줄게!
        //너가 "/hello"하면, @RequestMapping(uri = "/hello") 붙은 메서드 때려줄게!
        //또한, 주소 매핑을 컨트롤러에 함수명 안쓰고 Annotation쓸 수 있다. 보기에 훨씬 좋다.

        UserController userController = new UserController();
        Method[] methods = userController.getClass().getDeclaredMethods();

        for (Method method : methods) { // 리플렉션한 메서드 개수만큼 순회함

            Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class); //@RequestMapping 어노테이션이 붙은 메서드만 뽑아냄.
            RequestMapping requestMapping = (RequestMapping) annotation;  //다운캐스팅 한 이유는, requestMapping.value()하면 @RequestMapping(uri = "/hello")의 값인 "/hello" 나온다.

            if (requestMapping.uri().equals(endPoint)) { //@RequestMapping(uri = "/user/join")
                isMatching = true;
                System.out.println("리플렉션 컨틀롤러 함수 어노테이션값 : " + requestMapping.uri());
                try {
                    Parameter[] params = method.getParameters(); ////@RequestMapping(uri = "/user/join") 의 파라미터니까 JoinDto dto
                    String path;
                    if (params.length != 0) {
                        Object dtoInstance = params[0].getType().newInstance(); //JoinDto의 인스턴스 생성. JoinDto가 될 수도있고 LoginDto가 될 수도 있으니 Object 타입으로 받는다
                        setData(dtoInstance, request); // JoinDto 인스턴스에 HttpRequest에서 값 떼서 주입
                        path = (String) method.invoke(userController, dtoInstance); //UserController에 join(JoinDto dto)을 실행 -> return "/";
                    } else {
                        path = (String) method.invoke(userController);
                    }

                    System.out.println("path : " + path);
                    RequestDispatcher dis = request.getRequestDispatcher(path); //RequestDispatcher는 필터를 안탐! 톰캣을 안타고 내부적으로 동작함. 이제 /resources/부터 찾음.
                    dis.forward(request, response); //"/"로 HttpRequest, HttpResponse를 담아 보냄
                    break; // 더 이상 메서드를 리플렉션 할 필요 없어서 빠져나감.
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (isMatching == false) {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println("잘못된 요청 주소입니다. 404 에러");
                out.flush();
            }
        }

    }

    private String keyToMethodKey(String key) {
        String firstkey = key.substring(0, 1);
        String remainKey = key.substring(1);
        return "set" + firstkey.toUpperCase() + remainKey;
    }

    //HttpRequest에 "/user"에 파라미터로 username이 오면, .setUsername(username); 하는 메서드.
    private <T> void setData(T instance, HttpServletRequest request) { //제네릭으로 받은 타입 그대로 반환.
        System.out.println("인스턴스 타입 : " + instance.getClass());
        Enumeration<String> params = request.getParameterNames(); //HttpRequest의 파라미터를 받아서

        while (params.hasMoreElements()) {
            String key = (String) params.nextElement();
            String methodKey = keyToMethodKey(key);
            System.out.println("실행할 setter메서드 :" + methodKey);
            Method[] methods = instance.getClass().getMethods();
            for (Method m : methods) {
                if (m.getName().equals(methodKey)) {
                    try {
                        m.invoke(instance, request.getParameter(key)); //HttpRequest의 파라미터들을 JoinDto에 setter method를 통한 주입.
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
