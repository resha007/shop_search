package com.shop.service;


import com.shop.dao.ProvinceDAO;
import com.shop.domain.Province;
import com.shop.enums.RecordStatusEnum;
import java.util.List;




public class ProvinceService {
	
	//Add new obj
	public static Province saveProvince(Province obj){
		//validations here
		if(obj.getStatus()=="1"){
			obj.setStatus(RecordStatusEnum.ACTIVE.getId());
		}else if(obj.getStatus()=="0"){
			obj.setStatus(RecordStatusEnum.INACTIVE.getId());
		}
		System.out.println("##7");
		return ProvinceDAO.saveProvince(obj);
	}
	
	//update obj
	public static Province updateProvince(Province obj){
		//validations here
		
		return ProvinceDAO.updateProvince(obj);
	}
	
	//delete obj
	public static Province deleteProvince(Province obj){
		//validations here
		obj.setStatus(RecordStatusEnum.DELETED.getId());
		return ProvinceDAO.deleteProvince(obj);
	}
	
	//get active obj
	public static List<Province> getActiveProvinces(){
		return ProvinceDAO.getAllProvinces("a");
	}
	
	//get all obj
	public static List<Province> getAllProvinces(){
		return ProvinceDAO.getAllProvinces("all");
	}
	
	//get obj by id
	public static Province getProvinceById(int id){
		return ProvinceDAO.getProvinceById(id);
	}
}
