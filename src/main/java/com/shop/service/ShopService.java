package com.shop.service;


import com.shop.dao.ShopDAO;
import com.shop.dao.DistrictDAO;
import com.shop.domain.Shop;
import com.shop.domain.District;
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
        
        //get cities of district
	public static List<Shop> getCitiesofDistrict(District district){
		return ShopDAO.getCitiesofDistrict(district);
	}
}
