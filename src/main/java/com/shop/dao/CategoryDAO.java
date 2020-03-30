package com.shop.dao;



import com.shop.domain.Category;
import com.shop.domain.Province;
import com.shop.enums.RecordStatusEnum;
import com.shop.util.SessionUtill;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;



import java.util.List;
import static org.hibernate.criterion.Projections.id;


public class CategoryDAO {
	//Add new Category
	public static Category saveCategory(Category obj){
		try {
                        SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			session.save(obj);
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
                        
		} catch (Exception e) {
			System.out.println("save Category " + e.getCause());
		}
		return obj;
	}
	
	//edit Category
	public static Category updateCategory(Category obj){
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			//obj.setStatus(RecordStatusEnum.MODIFIED.getId());
			session.update(obj);
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("Edit Category:" + e.getCause());
		}
		return obj;
	}
	
	//delete Category
	public static Category deleteCategory(Category obj){
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			//obj.setActiveStatus(RecordStatusEnum.DELETED.getId());
			session.update(obj);
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("Edit Category:" + e.getCause());
		}
		return obj;
	}
	
	//get all Categorys
	public static List<Category> getAllCategorys(String status){
		List<Category> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Category.class);
			if(status=="a"){
				criteria.add(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()));
			}else if(status=="all"){
				criteria.add(Restrictions.or(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()), Restrictions.eq("status", RecordStatusEnum.INACTIVE.getId())));
			}
			
			dataList = criteria.list();
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get all Categorys:" + e.getCause());
		}
		return dataList;
	}
	
	//get Category by id
	public static Category getCategoryById(int id){
		Category obj = new Category();
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			                 
			Criteria criteria = session.createCriteria(Category.class);
			criteria.add(Restrictions.eq("id", id));
			List<Category> dataList = criteria.list();
			
			obj = dataList.get(0);
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get Category by id:" + e.getCause());
		}
		return obj;
	}
        
        //get districts of a province
	public static List<Category> getCategorysofProvince(Province province){
		List<Category> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Category.class);
			criteria.add(Restrictions.and(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()), Restrictions.eq("province", province)));
			
			dataList = criteria.list();
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get districts of province:" + e.getCause());
		}
		return dataList;
	}
}
