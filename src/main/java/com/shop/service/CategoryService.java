package com.shop.service;


import com.shop.dao.CategoryDAO;
import com.shop.domain.Category;
import com.shop.domain.Province;
import com.shop.enums.RecordStatusEnum;
import java.util.List;
import static org.hibernate.criterion.Projections.id;




public class CategoryService {
	
	//Add new obj
	public static Category saveCategory(Category obj){
		//validations here
		if(obj.getStatus()=="1"){
			obj.setStatus(RecordStatusEnum.ACTIVE.getId());
		}else if(obj.getStatus()=="0"){
			obj.setStatus(RecordStatusEnum.INACTIVE.getId());
		}
		
		return CategoryDAO.saveCategory(obj);
	}
	
	//update obj
	public static Category updateCategory(Category obj){
		//validations here
		
		return CategoryDAO.updateCategory(obj);
	}
	
	//delete obj
	public static Category deleteCategory(Category obj){
		//validations here
		obj.setStatus(RecordStatusEnum.DELETED.getId());
		return CategoryDAO.deleteCategory(obj);
	}
	
	//get active obj
	public static List<Category> getActiveCategorys(){
		return CategoryDAO.getAllCategorys("a");
	}
	
	//get all obj
	public static List<Category> getAllCategorys(){
		return CategoryDAO.getAllCategorys("all");
	}
	
	//get obj by id
	public static Category getCategoryById(int id){
		return CategoryDAO.getCategoryById(id);
	}
        
        //get districts of province
	public static List<Category> getCategorysofProvince(Province province){
		return CategoryDAO.getCategorysofProvince(province);
	}
}
