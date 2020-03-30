package com.shop.dao;



import com.shop.domain.Province;
import com.shop.enums.RecordStatusEnum;
import com.shop.util.SessionUtill;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;



import java.util.List;


public class ProvinceDAO {
	//Add new Province
	public static Province saveProvince(Province obj){
		try {
                        SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			session.save(obj);
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
                        
		} catch (Exception e) {
			System.out.println("save Province " + e.getCause());
		}
		return obj;
	}
	
	//edit Province
	public static Province updateProvince(Province obj){
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
			System.out.println("Edit Province:" + e.getCause());
		}
		return obj;
	}
	
	//delete Province
	public static Province deleteProvince(Province obj){
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
			System.out.println("Edit Province:" + e.getCause());
		}
		return obj;
	}
	
	//get all Provinces
	public static List<Province> getAllProvinces(String status){System.out.println("##"+status);
		List<Province> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Province.class);
			if(status=="a"){
				criteria.add(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()));
			}else if(status=="all"){System.out.println("##allllll");
				criteria.add(Restrictions.or(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()), Restrictions.eq("status", RecordStatusEnum.INACTIVE.getId())));
			}
			System.out.println("##allllll2");
			dataList = criteria.list();
			System.out.println("##allllll3");
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get all Provinces:" + e.getCause());
		}
		return dataList;
	}
	
	//get Province by id
	public static Province getProvinceById(int id){
		Province obj = new Province();
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			                 
			Criteria criteria = session.createCriteria(Province.class);
			criteria.add(Restrictions.eq("id", id));
			List<Province> dataList = criteria.list();
			
			obj = dataList.get(0);
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get Province by id:" + e.getCause());
		}
		return obj;
	}
}
