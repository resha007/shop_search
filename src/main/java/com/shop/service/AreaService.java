package com.shop.service;


import com.shop.dao.AreaDAO;
import com.shop.dao.DistrictDAO;
import com.shop.domain.Area;
import com.shop.domain.District;
import com.shop.enums.RecordStatusEnum;
import java.util.List;




public class AreaService {
	
	//Add new obj
	public static Area saveArea(Area obj){
		//validations here
		if(obj.getStatus()=="1"){
			obj.setStatus(RecordStatusEnum.ACTIVE.getId());
		}else if(obj.getStatus()=="0"){
			obj.setStatus(RecordStatusEnum.INACTIVE.getId());
		}
		
		return AreaDAO.saveArea(obj);
	}
	
	//update obj
	public static Area updateArea(Area obj){
		//validations here
		
		return AreaDAO.updateArea(obj);
	}
	
	//delete obj
	public static Area deleteArea(Area obj){
		//validations here
		obj.setStatus(RecordStatusEnum.DELETED.getId());
		return AreaDAO.deleteArea(obj);
	}
	
	//get active obj
	public static List<Area> getActiveAreas(){
		return AreaDAO.getAllAreas("a");
	}
	
	//get all obj
	public static List<Area> getAllAreas(){
		return AreaDAO.getAllAreas("all");
	}
	
	//get obj by id
	public static Area getAreaById(int id){
		return AreaDAO.getAreaById(id);
	}
        
        //get cities of district
	public static List<Area> getCitiesofDistrict(District district){
		return AreaDAO.getCitiesofDistrict(district);
	}
}
