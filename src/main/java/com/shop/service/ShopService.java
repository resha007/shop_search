package com.shop.service;


import com.shop.dao.ShopDAO;
import com.shop.domain.Area;
import com.shop.domain.Category;
import com.shop.domain.Shop;
import com.shop.domain.District;
import com.shop.domain.Province;
import com.shop.enums.RecordStatusEnum;
import java.util.List;




public class ShopService {
	
	//Add new obj
	public static Shop saveShop(Shop obj){
		//validations here
		if(obj.getStatus()=="1"){
			obj.setStatus(RecordStatusEnum.ACTIVE.getId());
		}else if(obj.getStatus()=="0"){
			obj.setStatus(RecordStatusEnum.INACTIVE.getId());
		}
		
		return ShopDAO.saveShop(obj);
	}
	
	//update obj
	public static Shop updateShop(Shop obj){
		//validations here
		
		return ShopDAO.updateShop(obj);
	}
	
	//delete obj
	public static Shop deleteShop(Shop obj){
		//validations here
		obj.setStatus(RecordStatusEnum.DELETED.getId());
		return ShopDAO.deleteShop(obj);
	}
	
	//get active obj
	public static List<Shop> getActiveShops(){
		return ShopDAO.getAllShops("a");
	}
	
	//get all obj
	public static List<Shop> getAllShops(){
		return ShopDAO.getAllShops("all");
	}
	
	//get obj by id
	public static Shop getShopById(int id){
		return ShopDAO.getShopById(id);
	}
        
        //get shop list by id
	public static List<Shop> getShopByIdList(int id){
		return ShopDAO.getShopByIdList(id);
	}
        
        //get cities of district
	public static List<Shop> getCitiesofDistrict(District district){
		return ShopDAO.getCitiesofDistrict(district);
	}
        
        //search
	public static List<Shop> searchShops(Province province,District district,Area area,Category category){
		return ShopDAO.searchShops(province,district,area,category);
	}
        
        //search
	public static List<Shop> searchShops(District district,Area area,Category category){
		return ShopDAO.searchShops(district,area,category);
	}
        
        //search
	public static List<Shop> searchShops(Area area,Category category){
		return ShopDAO.searchShops(area,category);
	}
        
        //search
	public static List<Shop> searchShops(Category category){
		return ShopDAO.searchShops(category);
	}
        
        //search
	public static List<Shop> searchShops(Province province,District district){
		return ShopDAO.searchShops(province,district);
	}
        
        //search
	public static List<Shop> searchShops(Province province){
		return ShopDAO.searchShops(province);
	}
        
        //search
	public static List<Shop> searchShops(Province province,District district,Category category){
		return ShopDAO.searchShops(province,district,category);
	}
        
        //search
	public static List<Shop> searchShops(Province province,District district,Area area){
		return ShopDAO.searchShops(province,district,area);
	}
}
