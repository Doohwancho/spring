package level3_metaCoding;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import level3_metaCoding.anno.Controller;
import level3_metaCoding.anno.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class DispatcherTest implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        System.out.println("컨텍스트패스 : " + request.getContextPath()); // 프로젝트 시작주소
        System.out.println("식별자주소 : " + request.getRequestURI()); // 끝주소
        System.out.println("전체주소 : " + request.getRequestURL()); // 전체주소

        String endPoint = request.getRequestURI().replaceAll(request.getContextPath(), "");
        System.out.println("엔드포인트 : " + endPoint);

        List<Class> controllerList = componentScan(); //현재 JVM에 런타임으로 돌고있는 모든 쓰레드의 클래스 이름 까서, 특정 패키지 안에 controller면 reflection으로 객체 생성해서 한 List에 담아 모든 컨트롤러 관리함

        for (Class controller : controllerList) {
            Annotation[] controllerAnnotations = controller.getAnnotations();

            for (Annotation controllerAnnotation : controllerAnnotations) {

                if(controllerAnnotation instanceof Controller) {
                    try {
                        Object controllerInstance = controller.newInstance();
                        Method[] methods = controller.getDeclaredMethods();

                        for (Method method : methods) { // 리플렉션한 메서드 개수만큼 순회함

                            Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
                            RequestMapping requestMapping = (RequestMapping) annotation;

                            if (requestMapping.uri().equals(endPoint)) {
                                System.out.println("리플렉션 컨틀롤러 함수 어노테이션값 : " + requestMapping.uri());
                                try {
                                    Parameter[] params = method.getParameters();
                                    String path;
                                    if (params.length != 0) {
                                        Object dtoInstance = params[0].getType().newInstance();
                                        setData(dtoInstance, request); // 인스턴스에 파라메터 값 추가하기 (레퍼런스를 넘겨서 리턴 안받아도 됨)
                                        path = (String) method.invoke(controllerInstance, dtoInstance);
                                    } else {
                                        path = (String) method.invoke(controllerInstance);
                                    }

                                    System.out.println("path : " + path); //UserController에서 public String join(JoinDto dto) {} 처리 후, "/" 반환
                                    RequestDispatcher dis = request.getRequestDispatcher(path); //해당 주소로 RequestDispatcher가 resources/static/에 view에 요청 보냄.
                                    dis.forward(request, response); //view 반환
                                    break; // 더 이상 메서드를 리플렉션 할 필요 없어서 빠져나감.
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } // controllerAnnotations end

        } // controllerList end

    }

    private String keyToMethodKey(String key) {
        String firstkey = key.substring(0, 1);
        String remainKey = key.substring(1);
        return "set" + firstkey.toUpperCase() + remainKey;
    }

    private <T> void setData(T instance, HttpServletRequest request) {
        System.out.println("인스턴스 타입 : " + instance.getClass());
        Enumeration<String> params = request.getParameterNames();

        while (params.hasMoreElements()) {
            String key = (String) params.nextElement();
            String methodKey = keyToMethodKey(key);
            System.out.println("실행할 setter메서드 :" + methodKey);
            Method[] methods = instance.getClass().getMethods();
            for (Method m : methods) {
                if (m.getName().equals(methodKey)) {
                    try {
                        m.invoke(instance, request.getParameter(key));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 참고 : https://programmerhoiit.tistory.com/7
    private List<Class> componentScan() {
        List<Class> controllerList = new ArrayList<>();
        String packageName = "com.cos.reflect.controller";
        String packageNameSlash = "./" + packageName.replace(".", "/");
        URL directoryURL = Thread.currentThread().getContextClassLoader().getResource(packageNameSlash);

        if (directoryURL == null) {
            System.err.println("Could not retrive URL resource : "+ packageNameSlash);
        }

        String directoryString = directoryURL.getFile();

        if (directoryString == null) {
            System.err.println("Could not find directory for URL resource : "+ packageNameSlash);
        }

        File directory = new File(directoryString);
        if (directory.exists()) {

            String[] files = directory.list();
            for (String fileName : files) {
                if (fileName.endsWith(".class")) {
                    fileName = fileName.replace(".class", "");
                    try {
                        Class temp = Class.forName(packageName + '.' + fileName);
                        controllerList.add(temp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return controllerList;
    }

}
