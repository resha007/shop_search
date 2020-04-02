package com.shop.dao;



import com.shop.domain.UserDetails;
import com.shop.enums.RecordStatusEnum;
import com.shop.util.SessionUtill;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;



import java.util.List;
import org.hibernate.criterion.Order;


public class UserDetailsDAO {
	//Add new UserDetails
	public static UserDetails saveUserDetails(UserDetails obj){
		try {
                        SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			session.save(obj);
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
                        
		} catch (Exception e) {
			System.out.println("save UserDetails " + e.getCause());
		}
		return obj;
	}
	
	//edit UserDetails
	public static UserDetails updateUserDetails(UserDetails obj){
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
			System.out.println("Edit UserDetails:" + e.getCause());
		}
		return obj;
	}
	
	//delete UserDetails
	public static UserDetails deleteUserDetails(UserDetails obj){
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
			System.out.println("Edit UserDetails:" + e.getCause());
		}
		return obj;
	}
	
	//get all UserDetailss
	public static List<UserDetails> getAllUserDetailss(String status){System.out.println("##"+status);
		List<UserDetails> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(UserDetails.class);
			if(status=="a"){
				criteria.add(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()));
			}else if(status=="all"){System.out.println("##allllll");
				criteria.add(Restrictions.or(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()), Restrictions.eq("status", RecordStatusEnum.INACTIVE.getId())));
			}
			//System.out.println("##allllll2");
			dataList = criteria.list();
			//System.out.println("##allllll3");
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get all UserDetailss:" + e.getCause());
		}
		return dataList;
	}
	
	//get UserDetails by id
	public static UserDetails getUserDetailsById(int id){ 
		UserDetails obj = new UserDetails();
                
		try {
			SessionUtill sessionUtill = new SessionUtill();
                        Session session =  sessionUtill.openSession();
                        session.beginTransaction();
			                
			Criteria criteria = session.createCriteria(UserDetails.class);
			criteria.add(Restrictions.eq("id", id));
			List<UserDetails> dataList = criteria.list();
			
			obj = dataList.get(0);
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get UserDetails by id:" + e.getCause()+e.getMessage());
                        //session.getTransaction().rollback();
		}
		return obj;
	}
        
        //get UserDetails by nic
	public static List<UserDetails> getUserDetailsByNIC(String nic){
		List<UserDetails> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			                 
			Criteria criteria = session.createCriteria(UserDetails.class);
			criteria.add(Restrictions.eq("nic", nic));
                        criteria.addOrder(Order.desc("createdDate"));
			dataList = criteria.list();
			
			//obj = dataList.get(0);
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get UserDetails by id:" + e.getCause());
		}
		return dataList;
	}
        
        //get UserDetails by username
	public static List<UserDetails> getUserDetailsByUsername(String username){
		List<UserDetails> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			                 
			Criteria criteria = session.createCriteria(UserDetails.class);
			criteria.add(Restrictions.eq("username", username));
                        criteria.addOrder(Order.desc("createdDate"));
			dataList = criteria.list();
			
			//obj = dataList.get(0);
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get UserDetails by id:" + e.getCause());
		}
		return dataList;
	}
        
        //authentication for login
	public static UserDetails authentication(UserDetails user){
            UserDetails userDetails = new UserDetails();
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			//System.out.println(user.getUserDetailsname()+user.getPassword());
			Criteria criteria = session.createCriteria(UserDetails.class);
			criteria.add(Restrictions.eq("username", user.getUsername()));
			criteria.add(Restrictions.eq("password", user.getPassword()));
			criteria.add(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()));
                        
                        List<UserDetails> dataList = criteria.list();
			
			userDetails = dataList.get(0);
			                 
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get UserDetails by id:" + e.getCause());
                        userDetails = null;
		}
		System.out.println("#####user dao : "+userDetails);
		return userDetails;
	}
}
