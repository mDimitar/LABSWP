package mk.finki.ukim.mk.lab.web;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "order-servlet", urlPatterns = "/servlet/orders")
public class OrderServlet extends HttpServlet {
   private  SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public OrderServlet(OrderService orderService, SpringTemplateEngine springTemplateEngine) {
        this.orderService = orderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList = orderService.findAll();
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("orders", orderList);
        springTemplateEngine.process("orders.html", context, resp.getWriter());

    }
}
