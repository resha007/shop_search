package com.shop.service;


import com.shop.dao.UserLevelDAO;
import com.shop.domain.UserLevel;
import com.shop.enums.RecordStatusEnum;
import java.util.List;




public class UserLevelService {
	
	//Add new obj
	public static UserLevel saveUserLevel(UserLevel obj){
		//validations here
		if(obj.getStatus()=="1"){
			obj.setStatus(RecordStatusEnum.ACTIVE.getId());
		}else if(obj.getStatus()=="0"){
			obj.setStatus(RecordStatusEnum.INACTIVE.getId());
		}
		
		return UserLevelDAO.saveUserLevel(obj);
	}
	
	//update obj
	public static UserLevel updateUserLevel(UserLevel obj){
		//validations here
		
		return UserLevelDAO.updateUserLevel(obj);
	}
	
	//delete obj
	public static UserLevel deleteUserLevel(UserLevel obj){
		//validations here
		obj.setStatus(RecordStatusEnum.DELETED.getId());
		return UserLevelDAO.deleteUserLevel(obj);
	}
	
	//get active obj
	public static List<UserLevel> getActiveUserLevels(){
		return UserLevelDAO.getAllUserLevels("a");
	}
	
	//get all obj
	public static List<UserLevel> getAllUserLevels(){
		return UserLevelDAO.getAllUserLevels("all");
	}
	
	//get obj by id
	public static UserLevel getUserLevelById(int id){
		return UserLevelDAO.getUserLevelById(id);
	}
}
