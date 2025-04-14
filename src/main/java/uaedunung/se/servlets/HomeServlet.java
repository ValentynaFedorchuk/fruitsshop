package uaedunung.se.servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uaedunung.se.config.FreeMarkerConfig;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Configuration cfg = FreeMarkerConfig.getConfig(getServletContext());
        Map<String, Object> model = new HashMap<>();
        model.put("contextPath", req.getContextPath());
        model.put("session", req.getSession());

        // Якщо є повідомлення про помилку з логіна — передаємо
        Object error = req.getSession().getAttribute("loginError");
        if (error != null) {
            model.put("error", error.toString());
            req.getSession().removeAttribute("loginError");
        }

        Template template = cfg.getTemplate("index.ftl");

        resp.setContentType("text/html; charset=UTF-8");
        try (Writer out = new OutputStreamWriter(resp.getOutputStream(), "UTF-8")) {
            template.process(model, out);
        } catch (TemplateException e) {
            throw new ServletException(e);
        }
    }
}
