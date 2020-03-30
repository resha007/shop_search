/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.servlet;

import com.shop.domain.Area;
import com.shop.service.AreaService;
import com.shop.service.DistrictService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author resha
 */
public class AreaServlet extends HttpServlet {

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
            out.println("<title>Servlet AreaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AreaServlet at " + request.getContextPath() + "</h1>");
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
        String type = request.getParameter("type");
        List<Area> list = null;

        if(type.trim().equals("tbl")){
            //get data to the table
            list = AreaService.getAllAreas();
        }else if(type.trim().equals("cmb")){
            //get active list to load combo box
            list = AreaService.getActiveAreas();
        }else if(type.trim().equals("byDisId")){
            //get city list according to district id
            String disId = request.getParameter("id");
            list = AreaService.getCitiesofDistrict(DistrictService.getDistrictById(Integer.parseInt(disId)));
        }
        
        request.setAttribute("list", list);
        JSONArray jsonString = new JSONArray(list);

        //Response	
        response.setCharacterEncoding("UTF-8"); 
        response.setContentType("application/json");
        response.getWriter().print(jsonString); 
        response.getWriter().close();
        System.out.println("##JsonArraycity"+jsonString);
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
        processRequest(request, response);
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
