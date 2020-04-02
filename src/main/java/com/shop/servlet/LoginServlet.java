/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.servlet;

import com.shop.domain.UserDetails;
import com.shop.service.UserDetailsService;
import com.shop.util.AeSimpleSHA1;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;

/**
 *
 * @author resha
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        
        try {
                password = AeSimpleSHA1.SHA1(password);
        } catch (NoSuchAlgorithmException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
        }
        
        UserDetails user = new UserDetails();
        List<UserDetails> userList = null;
        boolean status = false;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        
        if(action.trim().equals("auth")){
            user.setUsername(username);
            user.setPassword(password);

            user = UserDetailsService.authentication(user);
        }
        System.out.println("##############user : "+user);
        JSONArray jsonString = new JSONArray(userList);
        
        //Response	
        response.setCharacterEncoding("UTF-8"); 
        response.setContentType("application/json");
        if(!(user == null)){
            request.getSession();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            status = true;
            response.getWriter().print(status);
            
//            //user log
//            UserLog userlog = new UserLog();
//            userlog.setLogInTime(date);
//            userlog.setUserDetails(user);
//            userlog.setStatus(1);
//            userlog.setCreatedDate(date);
//            
//            userlog = UserLogService.saveUserLog(userlog);
            
        }else{
            status = false;
            response.getWriter().print(status); 
        } 
        response.getWriter().close();
        System.out.println("##JsonArraylogin"+status);
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
