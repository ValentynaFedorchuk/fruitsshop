package uaedunung.se.servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.query.Query;
import uaedunung.se.config.FreeMarkerConfig;
import uaedunung.se.config.HibernateUtil;
import uaedunung.se.entity.User;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Configuration cfg = FreeMarkerConfig.getConfig(getServletContext());
        Map<String, Object> model = new HashMap<>();
        model.put("contextPath", req.getContextPath());

        // Якщо є повідомлення про помилку з логіна — передаємо
        Object error = req.getSession().getAttribute("changePasswordError");
        if (error != null) {
            model.put("error", error.toString());
            req.getSession().removeAttribute("changePasswordError");
        }
        Object success = req.getSession().getAttribute("changePasswordSuccess");
        if (success != null) {
            model.put("changePasswordSuccess", success.toString());
            req.getSession().removeAttribute("changePasswordSuccess");
        }

        Template template = cfg.getTemplate("change-password.ftl");

        try (Writer out = new OutputStreamWriter(resp.getOutputStream(), StandardCharsets.UTF_8)) {
            template.process(model, out);
        } catch (TemplateException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery(
                    "FROM User WHERE email = :email AND password = :password", User.class);
            query.setParameter("email", email);
            query.setParameter("password", oldPassword); // в реальному проекті — хешувати!

            User user = query.uniqueResult();

            if (user != null) {
                user.setPassword(newPassword); // Тут також — хешування в реальних умовах
                session.update(user);
                session.getTransaction().commit();

                req.getSession().setAttribute("changePasswordSuccess", "Success in changing password!");
                resp.sendRedirect(req.getContextPath() + "/change-password");
            } else {
                session.getTransaction().rollback();
                req.getSession().setAttribute("changePasswordError", "You have entered an invalid email or password");
                resp.sendRedirect(req.getContextPath() + "/change-password");
            }
        } catch (Exception e) {
            throw new ServletException("Помилка під час зміни паролю", e);
        }
    }
}
