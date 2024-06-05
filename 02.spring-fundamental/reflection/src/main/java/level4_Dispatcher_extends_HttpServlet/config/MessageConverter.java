package level4_Dispatcher_extends_HttpServlet.config;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class MessageConverter {

    public static void resolve(Object data, HttpServletResponse response) {
        try {
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String responseBody = gson.toJson(data);
            out.println(responseBody);
            out.flush();
        } catch (Exception e) {
            writeError(response);
        }

    }

    private static void writeError(HttpServletResponse response) {
        try {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<h1>JSON 변환에 실패하였습니다.</h1>");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}