package uaedunung.se.servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        // Отримуємо параметр "name"
        String name = req.getParameter("name");
        if (name == null) name = "Guest";

        // Додаємо значення в сесію
        HttpSession session = req.getSession();
        session.setAttribute("username", name);

        // Додаємо Cookie
        Cookie nameCookie = new Cookie("username", name);
        nameCookie.setMaxAge(60 * 60); // 1 година
        resp.addCookie(nameCookie);

        // HTML відповідь
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h2>Hello, " + name + "!</h2>");
        out.println("<p>Session ID: " + session.getId() + "</p>");
        out.println("</body></html>");
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // ✅ Додаємо підтримку CORS
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // ✅ Читаємо JSON-тіло запиту
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        try {
            // ✅ Перетворюємо JSON у Map
            Map<String, String> data = objectMapper.readValue(sb.toString(), new TypeReference<Map<String, String>>() {});
            String name = data.getOrDefault("name", "Unknown");

            // ✅ Відправляємо JSON-відповідь
            String jsonResponse = objectMapper.writeValueAsString(Map.of(
                    "message", "Received user data",
                    "name", name
            ));

            resp.getWriter().write(jsonResponse);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid JSON format\"}");
        }
    }
}
