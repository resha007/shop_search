/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.servlet;

import com.shop.domain.UserDetails;
import com.shop.enums.MessageEnum;
import com.shop.service.UserDetailsService;
import com.shop.service.UserLevelService;
import com.shop.util.AeSimpleSHA1;
import com.shop.util.PasswordGen;
import com.shop.util.SendMail;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author resha
 */
public class UserDetailsServlet extends HttpServlet {

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
            out.println("<title>Servlet UserDetailsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserDetailsServlet at " + request.getContextPath() + "</h1>");
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
        List<UserDetails> list = null;

        if(type.trim().equals("tbl")){
            //get data to the table
            list = UserDetailsService.getAllUserDetailss();
        }else if(type.trim().equals("cmb")){
            //get active list to load combo box
            list = UserDetailsService.getActiveUserDetailss();
        }else if(type.trim().equals("nicNo")){
            //get client no from nic
            list = UserDetailsService.getUserDetailsByNIC(request.getParameter("nic"));
        }else if(type.trim().equals("username")){
            //get client no from nic
            list = UserDetailsService.getUserDetailsByUsername(request.getParameter("username"));
        }
        
        request.setAttribute("list", list);
        JSONArray jsonString = new JSONArray(list);

        //Response	
        response.setCharacterEncoding("UTF-8"); 
        response.setContentType("application/json");
        response.getWriter().print(jsonString); 
        response.getWriter().close();
        System.out.println("##JsonArrayUserDetails"+jsonString);
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
        String type = request.getParameter("type");
        
//        int status = 1;
//        if(request.getParameter("cmbStatus").equals("Active")){
//            status = 1;
//        }else{
//            status = 2;
//        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        
        if(action.trim().equals("add")){
            try{
                    UserDetails obj = new UserDetails();
        
                    obj.setFisrtName(request.getParameter("txtFName"));
                    obj.setLastName(request.getParameter("txtLName"));
                    obj.setEmail(request.getParameter("txtEmail"));
                    obj.setUserDetailscol(request.getParameter("txtNIC"));
                    obj.setUsername(request.getParameter("txtUsername"));
                    //obj.setDesignation(DesignationService.getDesignationById(Integer.parseInt(request.getParameter("cmbDesig"))));
                    obj.setUserLevel(UserLevelService.getUserLevelById(2));
                    obj.setStatus("1");
                    obj.setCreatedDate(date);
                    
                    //password
                    String password = request.getParameter("txtPassword");
                    System.out.println("###############servlet3333");
                    try {System.out.println("###############servlet333311111");
                            obj.setPassword(AeSimpleSHA1.SHA1(password));System.out.println("###############servlet33332222222");
                    } catch (NoSuchAlgorithmException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                    }
                    System.out.println("###############servlet4");
                    obj = UserDetailsService.saveUserDetails(obj);
System.out.println("###############servlet5");
                    //notify with an email to the user
                    //email content
//                    String to = obj.getEmail();
//                    String subject = "User Credentials | eLoan";
//                    String signature = "<br><br>Thank you.<br><br>Credit Dept.,<br><span style='color:blue;font-size:18px;font-weight:bold'>SANASA BANK</span><br>Kalubowila.<br><br><i>Contat No : <b>011 245 245 | 011 245 246</b><br>Email : <b>sanasa.bank.noreply@gmail.com</b></i><br><small>(PS: This is an auto generated email message via eLoan)</small>";
//                    String msg = "Dear "+obj.getFisrtName()+" "+obj.getLastName()+",<br>You are a user of the eLoan Management System fo SANASA Bank from now as a <b>"+obj.getUserLevel().getName()+"</b><br><br>username : "+obj.getUsername()+"<br>password : "+password+"<br><br><b>Please make sure to change your password after you logged in to the system.</b><br><br>"+signature;
//
//                    //sending email
//                    SendMail.sendGMail(to, subject, msg);
                    
                    JSONObject res = new JSONObject();
                    
                    res.put("success", MessageEnum.CREATE_SUCCESS_MESSAGE.getDescription());
                    response.setCharacterEncoding("UTF-8"); 
                    response.setContentType("application/json");
                    response.setStatus(200);
                    response.getWriter().write(res.toString());
                    response.getWriter().close();
            }catch(Exception e){
                    JSONObject res = new JSONObject();
                    res.put("success", MessageEnum.CREATE_FAILED_MESSAGE.getDescription());
                    response.setCharacterEncoding("UTF-8"); 
                    response.setContentType("application/json");
                    response.setStatus(200);
                    response.getWriter().write(res.toString());
                    response.getWriter().close();
            }
        }//else if(action.trim().equals("update")){
//            try{
//                    UserDetails obj = UserDetailsService.getUserDetailsById(Integer.parseInt(request.getParameter("txtId")));
//        
//                    obj.setFirstName(request.getParameter("txtFName"));
//                    obj.setLastName(request.getParameter("txtLName"));
//                    obj.setEmail(request.getParameter("txtEmail"));
//                    obj.setNic(request.getParameter("txtNIC"));
//                    obj.setUsername(request.getParameter("txtUsername"));
//                    obj.setDesignation(DesignationService.getDesignationById(Integer.parseInt(request.getParameter("cmbDesig"))));
//                    obj.setUserLevel(UserLevelService.getUserLevelById(Integer.parseInt(request.getParameter("cmbUserLevel"))));
//                    obj.setStatus(status);
//                    obj.setCreatedDate(date);
//                    obj.setModifiedDate(date);
//                    
//                    obj = UserDetailsService.updateUserDetails(obj);
//
//                    JSONObject res = new JSONObject();
//                    
//                    res.put("success", MessageEnum.UPDATE_SUCCESS_MESSAGE.getDescription());
//                    response.setCharacterEncoding("UTF-8"); 
//                    response.setContentType("application/json");
//                    response.setStatus(200);
//                    response.getWriter().write(res.toString());
//                    response.getWriter().close();
//            }catch(Exception e){
//                    JSONObject res = new JSONObject();
//                    res.put("success", MessageEnum.UPDATE_FAILED_MESSAGE.getDescription());
//                    response.setCharacterEncoding("UTF-8"); 
//                    response.setContentType("application/json");
//                    response.setStatus(200);
//                    response.getWriter().write(res.toString());
//                    response.getWriter().close();
//            }
//        }else if(action.trim().equals("delete")){System.out.println("##5");
//            try{
//                    UserDetails obj = UserDetailsService.getUserDetailsById(Integer.parseInt(request.getParameter("txtId")));
//                    obj.setModifiedDate(date);
//                    
//                    obj = UserDetailsService.deleteUserDetails(obj);
//
//                    JSONObject res = new JSONObject();
//                    
//                    res.put("success", MessageEnum.DELETE_SUCCESS_MESSAGE.getDescription());
//                    response.setCharacterEncoding("UTF-8"); 
//                    response.setContentType("application/json");
//                    response.setStatus(200);
//                    response.getWriter().write(res.toString());
//                    response.getWriter().close();
//            }catch(Exception e){
//                    JSONObject res = new JSONObject();
//                    res.put("success", MessageEnum.DELETE_FAILED_MESSAGE.getDescription());
//                    response.setCharacterEncoding("UTF-8"); 
//                    response.setContentType("application/json");
//                    response.setStatus(200);
//                    response.getWriter().write(res.toString());
//                    response.getWriter().close();
//            }
//        }else if(action.trim().equals("resetPassword")){
//            try{
//                    UserDetails obj = UserDetailsService.getUserDetailsById(Integer.parseInt(request.getParameter("txtId")));
//                    obj.setModifiedDate(date);
//                    
//                    //password
//                    String password = PasswordGen.generateRandomPassword(8);
//                    
//                    try {
//                            obj.setPassword(AeSimpleSHA1.SHA1(password));
//                    } catch (NoSuchAlgorithmException e1) {
//                            // TODO Auto-generated catch block
//                            e1.printStackTrace();
//                    }
//                    
//                    obj = UserDetailsService.updateUserDetails(obj);
//                    
//                    //notify with an email to the user
//                    //email content
//                    String to = obj.getEmail();
//                    String subject = "Password Rest | eLoan";
//                    String signature = "<br><br>Thank you.<br><br>Credit Dept.,<br><span style='color:blue;font-size:18px;font-weight:bold'>SANASA BANK</span><br>Kalubowila.<br><br><i>Contat No : <b>011 245 245 | 011 245 246</b><br>Email : <b>sanasa.bank.noreply@gmail.com</b></i><br><small>(PS: This is an auto generated email message via eLoan)</small>";
//                    String msg = "Dear "+obj.getFirstName()+" "+obj.getLastName()+",<br>Your password has been reset. Your new credentials are given below.<br><br>username : "+obj.getUsername()+"<br>password : "+password+"<br><br><b>Please make sure to change your password after you logged in to the system.</b><br><br>"+signature;
//
//                    //sending email
//                    SendMail.sendGMail(to, subject, msg);
//
//                    JSONObject res = new JSONObject();
//                    
//                    res.put("success", MessageEnum.DELETE_SUCCESS_MESSAGE.getDescription());
//                    response.setCharacterEncoding("UTF-8"); 
//                    response.setContentType("application/json");
//                    response.setStatus(200);
//                    response.getWriter().write(res.toString());
//                    response.getWriter().close();
//            }catch(Exception e){
//                    JSONObject res = new JSONObject();
//                    res.put("success", MessageEnum.DELETE_FAILED_MESSAGE.getDescription());
//                    response.setCharacterEncoding("UTF-8"); 
//                    response.setContentType("application/json");
//                    response.setStatus(200);
//                    response.getWriter().write(res.toString());
//                    response.getWriter().close();
//            }
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
