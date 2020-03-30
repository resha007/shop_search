package com.shop.dao;



import com.shop.domain.Area;
import com.shop.domain.District;
import com.shop.enums.RecordStatusEnum;
import com.shop.util.SessionUtill;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;



import java.util.List;


public class AreaDAO {
	//Add new Area
	public static Area saveArea(Area obj){
		try {
                        SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			session.save(obj);
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
                        
		} catch (Exception e) {
			System.out.println("save Area " + e.getCause());
		}
		return obj;
	}
	
	//edit Area
	public static Area updateArea(Area obj){
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
			System.out.println("Edit Area:" + e.getCause());
		}
		return obj;
	}
	
	//delete Area
	public static Area deleteArea(Area obj){
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
			System.out.println("Edit Area:" + e.getCause());
		}
		return obj;
	}
	
	//get all Areas
	public static List<Area> getAllAreas(String status){
		List<Area> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Area.class);
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
			System.out.println("get all Areas:" + e.getCause());
		}
		return dataList;
	}
	
	//get Area by id
	public static Area getAreaById(int id){
		Area obj = new Area();
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			                 
			Criteria criteria = session.createCriteria(Area.class);
			criteria.add(Restrictions.eq("id", id));
			List<Area> dataList = criteria.list();
			
			obj = dataList.get(0);
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get Area by id:" + e.getCause());
		}
		return obj;
	}
        
        //get cities of a district
	public static List<Area> getCitiesofDistrict(District district){
		List<Area> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Area.class);
			criteria.add(Restrictions.and(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()), Restrictions.eq("district", district)));
			
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
