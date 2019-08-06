package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/FilterServer")
public class FilterServer extends HttpServlet {

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("flowerStockFilter", flowerBusinessService.filterFlower(req.getParameter("nameFlower"), req.getParameter("minPrice"), req.getParameter("maxPrice")));
        req.getRequestDispatcher("/personalAccountServlet").forward(req, resp);
    }
}
