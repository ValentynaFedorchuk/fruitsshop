package uaedunung.se.servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.hibernate.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uaedunung.se.config.FreeMarkerConfig;
import uaedunung.se.config.HibernateUtil;
import uaedunung.se.entity.Fruit;
import uaedunung.se.entity.User;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            logger.warn("Спроба доступу до /products без авторизації");
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        logger.info("Користувач {} заходить на /products", user.getEmail());

        //resp.getWriter().write("user: " + user);

        Configuration cfg = FreeMarkerConfig.getConfig(getServletContext());
        Map<String, Object> model = new HashMap<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Fruit> fruits = session.createQuery("FROM Fruit", Fruit.class).list();
            model.put("fruits", fruits);
        }

        model.put("contextPath", req.getContextPath());
        model.put("session", req.getSession());
        model.put("user", req.getSession().getAttribute("user"));

        Template template = cfg.getTemplate("products.ftl");
        resp.setContentType("text/html; charset=UTF-8");

        try (Writer out = new OutputStreamWriter(resp.getOutputStream(), "UTF-8")) {
            template.process(model, out);
        } catch (TemplateException e) {
            throw new ServletException(e);
        }
    }
}
