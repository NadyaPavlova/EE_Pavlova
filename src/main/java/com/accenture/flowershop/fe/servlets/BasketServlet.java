package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.fe.dto.BasketDTO;
import com.accenture.flowershop.fe.dto.FlowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/user/BasketServlet")
public class BasketServlet extends HttpServlet {

    @Autowired
    FlowerBusinessService fbs;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flower_id = Long.parseLong(req.getParameter("id_flower"));
        FlowerDTO flower= new FlowerDTO(fbs.getFlowerById(flower_id));
        HttpSession session = req.getSession(false);
        BasketDTO basket = (BasketDTO)session.getAttribute("basket");
        basket.addBasket(flower);
        session.setAttribute("basket",basket);
        req.setAttribute("flowers", fbs.getAllFlowers());
        req.getRequestDispatcher("/personalAccount.jsp").forward(req, resp);
    }


}