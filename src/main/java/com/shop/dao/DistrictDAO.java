package com.shop.dao;



import com.shop.domain.District;
import com.shop.domain.Province;
import com.shop.enums.RecordStatusEnum;
import com.shop.util.SessionUtill;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;



import java.util.List;
import static org.hibernate.criterion.Projections.id;


public class DistrictDAO {
	//Add new District
	public static District saveDistrict(District obj){
		try {
                        SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			session.save(obj);
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
                        
		} catch (Exception e) {
			System.out.println("save District " + e.getCause());
		}
		return obj;
	}
	
	//edit District
	public static District updateDistrict(District obj){
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
			System.out.println("Edit District:" + e.getCause());
		}
		return obj;
	}
	
	//delete District
	public static District deleteDistrict(District obj){
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
			System.out.println("Edit District:" + e.getCause());
		}
		return obj;
	}
	
	//get all Districts
	public static List<District> getAllDistricts(String status){
		List<District> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(District.class);
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
			System.out.println("get all Districts:" + e.getCause());
		}
		return dataList;
	}
	
	//get District by id
	public static District getDistrictById(int id){
		District obj = new District();
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			                 
			Criteria criteria = session.createCriteria(District.class);
			criteria.add(Restrictions.eq("id", id));
			List<District> dataList = criteria.list();
			
			obj = dataList.get(0);
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get District by id:" + e.getCause());
		}
		return obj;
	}
        
        //get districts of a province
	public static List<District> getDistrictsofProvince(Province province){
		List<District> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(District.class);
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
