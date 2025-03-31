package uaedunung.se.servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.query.Query;
import uaedunung.se.config.FreeMarkerConfig;
import uaedunung.se.config.HibernateUtil;
import uaedunung.se.entity.Fruit;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        // ✅ Передаємо ServletContext у FreeMarkerConfig
        Configuration cfg = FreeMarkerConfig.getConfig(getServletContext());

        Map<String, Object> model = new HashMap<>();

        // Отримуємо список фруктів із бази
        List<Fruit> fruits;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Fruit> query = session.createQuery("FROM Fruit", Fruit.class);
            fruits = query.list();
        }
        model.put("fruits", fruits);
        model.put("contextPath", req.getContextPath());

        try {
            // Завантажуємо шаблон
            Template template = cfg.getTemplate("hello.ftl");

            // Дані для шаблону
            ////бо додала вищеMap<String, Object> model = new HashMap<>();
            model.put("message", "Hello from FreeMarker! My name is Valentyna)\n I am trying to understand how does it works... I start understand");

            // Рендеримо шаблон у відповідь
            try (Writer out = new OutputStreamWriter(resp.getOutputStream(), "UTF-8")) {
                template.process(model, out);
            }

        } catch (TemplateException e) {
            throw new ServletException("Помилка у FreeMarker", e);
        }
    }
}
