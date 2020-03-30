/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.servlet;

import com.shop.domain.Shop;
import com.shop.service.AreaService;
import com.shop.service.CategoryService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author resha
 */
public class ShopServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShopServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //action of data
        String action = request.getParameter("action");
        
        int status = 1;
        
        if(request.getParameter("cmbStatus").equals("Active")){
            status = 1;
        }else{
            status = 2;
        }
        
        Shop shop = new Shop();
        
        shop.setName(request.getParameter("txtShopName"));
        shop.setOtherName(request.getParameter("txtOtherName"));
        shop.setBrNo(request.getParameter("txtBR"));
        shop.setCategory(CategoryService.getCategoryById(Integer.parseInt(request.getParameter("cmbCategory"))));
        shop.setArea(AreaService.getAreaById(Integer.parseInt(request.getParameter("cmbArea"))));
        shop.setAddress(request.getParameter("txtAddress"));
        //shop.setIs24Hours(request.getParameter("chk24"));
        
        shop.setContactNo1(request.getParameter("txtCon1"));
        shop.setContactNo2(request.getParameter("txtCon2"));
        shop.setWhatsappViber(request.getParameter("txtWhatsapp"));
        shop.setEmail(request.getParameter("txtEmail"));
        shop.setDeliveryTimeMin(Integer.parseInt(request.getParameter("txtDelMin")));
        shop.setDeliveryTimeMax(Integer.parseInt(request.getParameter("txtDelMax")));
        shop.setDeliveryKm(Double.parseDouble(request.getParameter("txtDelArea")));
        shop.setWebsite(request.getParameter("txtWeb"));
        shop.setFbLink(request.getParameter("txtFb"));
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
