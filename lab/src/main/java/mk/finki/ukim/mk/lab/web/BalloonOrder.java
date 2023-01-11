package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "ballon-order-servlet", urlPatterns = "/BalloonOrderServlet")
public class BalloonOrder extends HttpServlet {


    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;
    public BalloonOrder(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        springTemplateEngine.process("deliveryInfo.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String clientName = req.getParameter("clientName");
        String ballon =(String) session.getAttribute("color");
        session.setAttribute("clientName", clientName);
        LocalDateTime date = LocalDateTime.parse(req.getParameter("orderDate"));

        this.orderService.placeOrder(ballon, clientName, date);
        resp.sendRedirect("/ConfirmationInfo");
    }
}
