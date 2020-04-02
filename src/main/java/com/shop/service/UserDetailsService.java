package com.shop.service;


import com.shop.dao.UserDetailsDAO;
import com.shop.domain.UserDetails;
import com.shop.enums.RecordStatusEnum;
import java.util.List;




public class UserDetailsService {
	
	//Add new obj
	public static UserDetails saveUserDetails(UserDetails obj){
		//validations here
		//if(obj.getStatus()=="1"){
			obj.setStatus(RecordStatusEnum.ACTIVE.getId());
		//}else if(obj.getStatus()=="0"){
			//obj.setStatus(RecordStatusEnum.INACTIVE.getId());
		//}
		System.out.println("##7");
		return UserDetailsDAO.saveUserDetails(obj);
	}
	
	//update obj
	public static UserDetails updateUserDetails(UserDetails obj){
		//validations here
		
		return UserDetailsDAO.updateUserDetails(obj);
	}
	
	//delete obj
	public static UserDetails deleteUserDetails(UserDetails obj){
		//validations here
		obj.setStatus(RecordStatusEnum.DELETED.getId());
		return UserDetailsDAO.deleteUserDetails(obj);
	}
	
	//get active obj
	public static List<UserDetails> getActiveUserDetailss(){
		return UserDetailsDAO.getAllUserDetailss("a");
	}
	
	//get all obj
	public static List<UserDetails> getAllUserDetailss(){
		return UserDetailsDAO.getAllUserDetailss("all");
	}
	
	//get obj by id
	public static UserDetails getUserDetailsById(int id){
		return UserDetailsDAO.getUserDetailsById(id);
	}
        
        //get obj by nic
	public static List<UserDetails> getUserDetailsByNIC(String nic){
		return UserDetailsDAO.getUserDetailsByNIC(nic);
	}
        
        //get obj by username
	public static List<UserDetails> getUserDetailsByUsername(String username){
		return UserDetailsDAO.getUserDetailsByUsername(username);
	}
        
        //auth for login
	public static UserDetails authentication(UserDetails user){
		return UserDetailsDAO.authentication(user);
	}
}
