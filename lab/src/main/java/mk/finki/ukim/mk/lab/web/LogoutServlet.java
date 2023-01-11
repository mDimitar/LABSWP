package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.web.servlet.tags.HtmlEscapeTag;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "log-out", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private final UserService userService;

    public LogoutServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        userService.setCurrentShoppingCartToFinished((String)session.getAttribute("clientName"));
        session.invalidate();
        resp.sendRedirect("/balloons");
    }
}
