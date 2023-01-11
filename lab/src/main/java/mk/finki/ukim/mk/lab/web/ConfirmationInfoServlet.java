package mk.finki.ukim.mk.lab.web;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "confirmation-info-servler", urlPatterns = "/ConfirmationInfoServlet")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String color =(String)session.getAttribute("color");
        String size = (String)session.getAttribute("size");
        String address = req.getRemoteAddr();
        String userAgent = req.getHeader("User-Agent");
        String clientName = (String)session.getAttribute("clientName");
        String clientAddress = (String)session.getAttribute("clientAddress");
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("color", color);
        webContext.setVariable("size", size);
        webContext.setVariable("address", address);
        webContext.setVariable("userAgent", userAgent);
        webContext.setVariable("clientName", clientName);
        webContext.setVariable("clientAddress", clientAddress);
        this.springTemplateEngine.process("confirmationInfo.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
