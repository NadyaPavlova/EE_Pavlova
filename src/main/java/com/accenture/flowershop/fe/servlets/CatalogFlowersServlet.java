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



@WebServlet(urlPatterns = "/catalog")
public class CatalogFlowersServlet extends HttpServlet {

    @Autowired
    private FlowerBusinessService fbs;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("flowers", fbs.getAllFlowers());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("flowers", fbs.getAllFlowers());
        String id_flower = req.getParameter("button");
        String quantity = req.getParameter("quantity" + id_flower);
    }
}