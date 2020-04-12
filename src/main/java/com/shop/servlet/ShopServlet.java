/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.servlet;

import com.shop.domain.Area;
import com.shop.domain.Category;
import com.shop.domain.District;
import com.shop.domain.Province;
import com.shop.domain.Shop;
import com.shop.enums.MessageEnum;
import com.shop.enums.RecordStatusEnum;
import com.shop.service.AreaService;
import com.shop.service.CategoryService;
import com.shop.service.DistrictService;
import com.shop.service.ProvinceService;
import com.shop.service.ShopService;
import com.shop.service.UserDetailsService;
import java.io.IOException;
import java.io.PrintWriter;
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
        String type = request.getParameter("type");
        
        List<Shop> list = null;

        if(type.trim().equals("tbl")){
            //get data to the table
            list = ShopService.getAllShops();
        }else if(type.trim().equals("cmb")){
            //get active list to load combo box
            list = ShopService.getActiveShops();
        }else if(type.trim().equals("byId")){
            //get active list to load combo box
            String id = request.getParameter("id");
            list = ShopService.getShopByIdList(Integer.parseInt(id));
        }
        
        if(type.trim().equals("search")){
            //get data to the table
            String proId = request.getParameter("province");
            String disId = request.getParameter("district");
            String areaId = request.getParameter("area");
            String catId = request.getParameter("category");
            
            if(proId.equals("0") && disId.equals("0") && areaId.equals("0") && catId.equals("0")){System.out.println("########## alll");
                list = ShopService.getAllShops();
            }
            else if(disId.equals("0") && areaId.equals("0") && catId.equals("0")){
                Province pro = ProvinceService.getProvinceById(Integer.parseInt(proId));
                list = ShopService.searchShops(pro);
            }
            else if(areaId.equals("0") && catId.equals("0")){
                Province pro = ProvinceService.getProvinceById(Integer.parseInt(proId));
                District dis = DistrictService.getDistrictById(Integer.parseInt(disId));
                list = ShopService.searchShops(pro, dis);
            }
            else if(catId.equals("0")){
                Province pro = ProvinceService.getProvinceById(Integer.parseInt(proId));
                District dis = DistrictService.getDistrictById(Integer.parseInt(disId));
                Area area = AreaService.getAreaById(Integer.parseInt(areaId));
                list = ShopService.searchShops(pro, dis, area);
            }
            else if(proId.equals("0") && disId.equals("0") && areaId.equals("0")){
                Category cat = CategoryService.getCategoryById(Integer.parseInt(catId));
                list = ShopService.searchShops(cat);
            }
            else{
                Province pro = ProvinceService.getProvinceById(Integer.parseInt(proId));
                District dis = DistrictService.getDistrictById(Integer.parseInt(disId));
                Area area = AreaService.getAreaById(Integer.parseInt(areaId));
                Category cat = CategoryService.getCategoryById(Integer.parseInt(catId));
                list = ShopService.searchShops(pro, dis, area, cat);
            }
            
//            Province pro = ProvinceService.getProvinceById(Integer.parseInt(proId));
//            District dis = DistrictService.getDistrictById(Integer.parseInt(disId));
//            Area area = AreaService.getAreaById(Integer.parseInt(areaId));
//            Category cat = CategoryService.getCategoryById(Integer.parseInt(catId));
//            
//            list = ShopService.searchShops(pro, dis, area, cat);
        }
        
        request.setAttribute("list", list);
        JSONArray jsonString = new JSONArray(list);

        //Response	
        response.setCharacterEncoding("UTF-8"); 
        response.setContentType("application/json");
        response.getWriter().print(jsonString); 
        response.getWriter().close();
        System.out.println("##JsonArraysearch"+jsonString);
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
        
//        if(request.getParameter("cmbStatus").equals("Active")){
//            status = 1;
//        }else{
//            status = 2;
//        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        
        Shop shop = new Shop();
        
        shop.setName(request.getParameter("txtShopName"));
        shop.setOtherName(request.getParameter("txtOtherName"));
        shop.setBrNo(request.getParameter("txtBR").toUpperCase());
        shop.setCategory(CategoryService.getCategoryById(Integer.parseInt(request.getParameter("cmbCategory"))));
        shop.setArea(AreaService.getAreaById(Integer.parseInt(request.getParameter("cmbArea"))));
        shop.setAddress(request.getParameter("txtAddress"));
        //shop.setIs24Hours(request.getParameter("chk24"));
        
//        try{System.out.println("####################from to11111 : "+request.getParameter("txtFrom")+" "+request.getParameter("txtTo"));
//            Date from = new SimpleDateFormat("HH:mm:ss").parse(request.getParameter("txtFrom"));  
//            Date to = new SimpleDateFormat("HH:mm:ss").parse(request.getParameter("txtTo"));  
//            System.out.println("####################from to22222 : "+request.getParameter("txtFrom")+" "+request.getParameter("txtTo"));
//            shop.setFromTime(from);
//            shop.setToTime(to);
//        }catch(Exception e){
//                    System.out.println("############date error : "+e.getMessage()+e.getCause());
//        }
//        System.out.println("#################from to123 : "+shop.getFromTime()+" "+shop.getToTime());
        
        shop.setContactNo1(request.getParameter("txtCon1"));
        shop.setContactNo2(request.getParameter("txtCon2"));
        shop.setWhatsappViber(request.getParameter("txtWhatsapp"));
        shop.setEmail(request.getParameter("txtEmail"));
        shop.setDeliveryTimeMin(Integer.parseInt(request.getParameter("txtDelMin")));
        shop.setDeliveryTimeMax(Integer.parseInt(request.getParameter("txtDelMax")));
        shop.setDeliveryKm(Double.parseDouble(request.getParameter("txtDelArea")));
        shop.setWebsite(request.getParameter("txtWeb"));
        shop.setFbLink(request.getParameter("txtFb"));
        shop.setUserDetails(UserDetailsService.getUserDetailsById(Integer.parseInt(request.getParameter("txtUser"))));
        
        
        if(action.trim().equals("add")){System.out.println("##4");
            try{
                    shop.setCreatedDate(date);
                    shop.setStatus(RecordStatusEnum.INACTIVE.getId());
                    
                    shop = ShopService.saveShop(shop);

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
        }else if(action.trim().equals("update")){
            try{
                    shop.setId(Integer.parseInt(request.getParameter("txtId")));
                    
                    //shop.setStatus(RecordStatusEnum.INACTIVE.getId());
                    
                    if(request.getParameter("cmbStatus").equals("1")){
                        shop.setStatus(RecordStatusEnum.ACTIVE.getId());
                    }else if(request.getParameter("cmbStatus").equals("2")){
                        shop.setStatus(RecordStatusEnum.INACTIVE.getId());
                    }
                    
                    //shop.setModifiedDate(date);
                    shop = ShopService.updateShop(shop);

                    JSONObject res = new JSONObject();
                    
                    res.put("success", MessageEnum.UPDATE_SUCCESS_MESSAGE.getDescription());
                    response.setCharacterEncoding("UTF-8"); 
                    response.setContentType("application/json");
                    response.setStatus(200);
                    response.getWriter().write(res.toString());
                    response.getWriter().close();
            }catch(Exception e){
                    JSONObject res = new JSONObject();
                    res.put("success", MessageEnum.UPDATE_FAILED_MESSAGE.getDescription());
                    response.setCharacterEncoding("UTF-8"); 
                    response.setContentType("application/json");
                    response.setStatus(200);
                    response.getWriter().write(res.toString());
                    response.getWriter().close();
            }
        }else if(action.trim().equals("delete")){System.out.println("##6");
            try{
                    shop.setId(Integer.parseInt(request.getParameter("txtId")));
                    //obj.setModifiedDate(date);
                    
                    shop = ShopService.deleteShop(shop);

                    JSONObject res = new JSONObject();
                    
                    res.put("success", MessageEnum.DELETE_SUCCESS_MESSAGE.getDescription());
                    response.setCharacterEncoding("UTF-8"); 
                    response.setContentType("application/json");
                    response.setStatus(200);
                    response.getWriter().write(res.toString());
                    response.getWriter().close();
            }catch(Exception e){
                    JSONObject res = new JSONObject();
                    res.put("success", MessageEnum.DELETE_FAILED_MESSAGE.getDescription());
                    response.setCharacterEncoding("UTF-8"); 
                    response.setContentType("application/json");
                    response.setStatus(200);
                    response.getWriter().write(res.toString());
                    response.getWriter().close();
            }
        }
        
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
