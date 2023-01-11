package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class BallonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;
    private final OrderService orderService;
    public BallonController(BalloonService balloonService, ManufacturerService manufacturerService, OrderService orderService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
        this.orderService = orderService;
    }

    @GetMapping(value = {"/balloons", "/"})
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("balloons", this.balloonService.listAll());

        return "listBalloons";
    }
    @PostMapping("/balloons/add")
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturerId){
        this.balloonService.addBalloon(name, description, manufacturerId);
        return "redirect:/balloons";
    }

    @PostMapping("/balloons/add/{id}")
    public String updateBalloon(@PathVariable Long id,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturerId){
        this.balloonService.updateBalloon(id, name, description, manufacturerId);
        return "redirect:/balloons";
    }
    @GetMapping("/balloons/add-form")
    public String getAddBalloonPage(Model model){
        model.addAttribute("manufactures", this.manufacturerService.findAll());
        return "add-balloon";
    }

    @GetMapping("/balloons/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model){
        Balloon balloon = this.balloonService.findById(id);
        if(balloon == null)
            return "redirect:/balloons?error=balloon not exists";
        model.addAttribute("balloon", balloon);
        model.addAttribute("manufactures", this.manufacturerService.findAll());
        return "add-balloon";
    }
    @GetMapping("/balloons/delete/{id}")
    public String deleteBalloon(@PathVariable Long id){
        this.balloonService.deleteBalloon(id);
        return "redirect:/balloons";
    }
    @PostMapping("/balloons/filter")
    public String filterBallons(@RequestParam String desc){
        this.balloonService.filterByDescription(desc);
        return "redirect:/balloons";
    }
    @GetMapping("/add/manu")
    public String showManuPage(Model model){
        model.addAttribute("manus", manufacturerService.findAll());
        return "manu";
    }
    @PostMapping("/add/manu")
    public String addManu(@RequestParam String address, @RequestParam String name, @RequestParam String country){
        this.manufacturerService.saveManu(address, name, country);
        return "redirect:/add/manu";
    }
    @GetMapping("/selectBalloon")
    public String getSelectBalloonPage(HttpServletRequest req, HttpServletResponse resp){
        return "SelectBalloonSize";
    }
    @PostMapping("/selectBalloon")
    public String selectBalloon(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        session.setAttribute("size", req.getParameter("size"));
        return "redirect:/BalloonOrder";

    }
    @GetMapping("/BalloonOrder")
    public String getBalloonOrderPage(HttpServletRequest req, HttpServletResponse resp){
        return "deliveryInfo";

    }

    @PostMapping("/BalloonOrder")
    public String balloonOrder(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        String clientName = req.getRemoteUser();
        String ballon =(String) session.getAttribute("color");
        session.setAttribute("clientName", clientName);
        LocalDateTime date = LocalDateTime.parse(req.getParameter("orderDate"));

        this.orderService.placeOrder(ballon, clientName, date);
        return "redirect:/ConfirmationInfo";
      //  resp.sendRedirect("/ConfirmationInfo");
    }
    @GetMapping("/ConfirmationInfo")
    public String getConfInfoPage(HttpServletRequest req, HttpServletResponse resp, Model model){
        HttpSession session = req.getSession();
        String color =(String)session.getAttribute("color");
        String size = (String)session.getAttribute("size");
        String address = req.getRemoteAddr();
        String userAgent = req.getHeader("User-Agent");
        String clientName = (String)session.getAttribute("clientName");
        String clientAddress = (String)session.getAttribute("clientAddress");

        model.addAttribute("color", color);
        model.addAttribute("size", size);
        model.addAttribute("address", address);
        model.addAttribute("userAgent", userAgent);
        model.addAttribute("clientName", clientName);
        model.addAttribute("clientAddress", clientAddress);
        return "confirmationInfo";
    }
}
