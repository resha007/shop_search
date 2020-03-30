package com.shop.service;


import com.shop.dao.DistrictDAO;
import com.shop.domain.District;
import com.shop.domain.Province;
import com.shop.enums.RecordStatusEnum;
import java.util.List;
import static org.hibernate.criterion.Projections.id;




public class DistrictService {
	
	//Add new obj
	public static District saveDistrict(District obj){
		//validations here
		if(obj.getStatus()=="1"){
			obj.setStatus(RecordStatusEnum.ACTIVE.getId());
		}else if(obj.getStatus()=="0"){
			obj.setStatus(RecordStatusEnum.INACTIVE.getId());
		}
		
		return DistrictDAO.saveDistrict(obj);
	}
	
	//update obj
	public static District updateDistrict(District obj){
		//validations here
		
		return DistrictDAO.updateDistrict(obj);
	}
	
	//delete obj
	public static District deleteDistrict(District obj){
		//validations here
		obj.setStatus(RecordStatusEnum.DELETED.getId());
		return DistrictDAO.deleteDistrict(obj);
	}
	
	//get active obj
	public static List<District> getActiveDistricts(){
		return DistrictDAO.getAllDistricts("a");
	}
	
	//get all obj
	public static List<District> getAllDistricts(){
		return DistrictDAO.getAllDistricts("all");
	}
	
	//get obj by id
	public static District getDistrictById(int id){
		return DistrictDAO.getDistrictById(id);
	}
        
        //get districts of province
	public static List<District> getDistrictsofProvince(Province province){
		return DistrictDAO.getDistrictsofProvince(province);
	}
}
