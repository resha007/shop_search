package com.shop.dao;



import com.shop.domain.Shop;
import com.shop.domain.District;
import com.shop.enums.RecordStatusEnum;
import com.shop.util.SessionUtill;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;



import java.util.List;


public class ShopDAO {
	//Add new Shop
	public static Shop saveShop(Shop obj){
		try {
                        SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			session.save(obj);
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
                        
		} catch (Exception e) {
			System.out.println("save Shop " + e.getCause());
		}
		return obj;
	}
	
	//edit Shop
	public static Shop updateShop(Shop obj){
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
			System.out.println("Edit Shop:" + e.getCause());
		}
		return obj;
	}
	
	//delete Shop
	public static Shop deleteShop(Shop obj){
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
			System.out.println("Edit Shop:" + e.getCause());
		}
		return obj;
	}
	
	//get all Shops
	public static List<Shop> getAllShops(String status){
		List<Shop> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Shop.class);
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
			System.out.println("get all Shops:" + e.getCause());
		}
		return dataList;
	}
	
	//get Shop by id
	public static Shop getShopById(int id){
		Shop obj = new Shop();
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			                 
			Criteria criteria = session.createCriteria(Shop.class);
			criteria.add(Restrictions.eq("id", id));
			List<Shop> dataList = criteria.list();
			
			obj = dataList.get(0);
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get Shop by id:" + e.getCause());
		}
		return obj;
	}
        
        //get cities of a district
	public static List<Shop> getCitiesofDistrict(District district){
		List<Shop> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Shop.class);
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
