package com.shop.dao;



import com.shop.domain.UserLevel;
import com.shop.enums.RecordStatusEnum;
import com.shop.util.SessionUtill;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;



import java.util.List;


public class UserLevelDAO {
	//Add new UserLevel
	public static UserLevel saveUserLevel(UserLevel obj){
		try {
                        SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			session.save(obj);
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
                        
		} catch (Exception e) {
			System.out.println("save UserLevel " + e.getCause());
		}
		return obj;
	}
	
	//edit UserLevel
	public static UserLevel updateUserLevel(UserLevel obj){
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
			System.out.println("Edit UserLevel:" + e.getCause());
		}
		return obj;
	}
	
	//delete UserLevel
	public static UserLevel deleteUserLevel(UserLevel obj){
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
			System.out.println("Edit UserLevel:" + e.getCause());
		}
		return obj;
	}
	
	//get all UserLevels
	public static List<UserLevel> getAllUserLevels(String status){
		List<UserLevel> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(UserLevel.class);
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
			System.out.println("get all UserLevels:" + e.getCause());
		}
		return dataList;
	}
	
	//get UserLevel by id
	public static UserLevel getUserLevelById(int id){
		UserLevel obj = new UserLevel();
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			                 
			Criteria criteria = session.createCriteria(UserLevel.class);
			criteria.add(Restrictions.eq("id", id));
			List<UserLevel> dataList = criteria.list();
			
			obj = dataList.get(0);
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get UserLevel by id:" + e.getCause());
		}
		return obj;
	}
}
