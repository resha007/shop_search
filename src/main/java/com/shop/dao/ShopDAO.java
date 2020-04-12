package com.shop.dao;



import com.shop.domain.Area;
import com.shop.domain.Category;
import com.shop.domain.Shop;
import com.shop.domain.District;
import com.shop.domain.Province;
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
	public static Shop updateShop(Shop obj){System.out.println("####### update dao");
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
			}else if(status=="all"){System.out.println("#########"+status);
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
        
        //get shop by id list
	public static List<Shop> getShopByIdList(int id){
		List<Shop> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Shop.class);
			
			criteria.add(Restrictions.eq("id", id));
			
			dataList = criteria.list();
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get all Shops:" + e.getCause());
		}
		return dataList;
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
        
        //shop search
	public static List<Shop> searchShops(Province province,District district,Area area,Category category){System.out.println("##### distrct : "+category.getName());
		List<Shop> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Shop.class);
                        
                        criteria.add(Restrictions.and( Restrictions.eq("category", category), Restrictions.eq("area", area)));
                        criteria.add(Restrictions.or(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()),Restrictions.eq("status", RecordStatusEnum.INACTIVE.getId())));
			//criteria.add(Restrictions.and(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()), Restrictions.eq("category", category), Restrictions.eq("area", area)));
			
			dataList = criteria.list();
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get search :" + e.getCause());
		}
		return dataList;
	}
        
        //shop search
	public static List<Shop> searchShops(District district,Area area,Category category){System.out.println("##### distrct : "+category.getName());
		List<Shop> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Shop.class);
                        
                        //criteria.add(Restrictions.and(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId())));
                        criteria.add(Restrictions.or(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()),Restrictions.eq("status", RecordStatusEnum.INACTIVE.getId())));
			//criteria.add(Restrictions.and(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()), Restrictions.eq("category", category), Restrictions.eq("area", area)));
			
			dataList = criteria.list();
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get search :" + e.getCause());
		}
		return dataList;
	}
        
        //shop search
	public static List<Shop> searchShops(Area area,Category category){//System.out.println("##### distrct : "+category.getName());
		List<Shop> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Shop.class);
                        
                        //criteria.add(Restrictions.and(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId())));
                        
			criteria.add(Restrictions.and( Restrictions.eq("category", category), Restrictions.eq("area", area)));
			criteria.add(Restrictions.or(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()),Restrictions.eq("status", RecordStatusEnum.INACTIVE.getId())));
			dataList = criteria.list();
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get search :" + e.getCause());
		}
		return dataList;
	}
        
        //shop search
	public static List<Shop> searchShops(Category category){//System.out.println("##### distrct : "+category.getName());
		List<Shop> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Shop.class);
                        
                        criteria.add(Restrictions.and(Restrictions.eq("category", category)));
                        criteria.add(Restrictions.or(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()),Restrictions.eq("status", RecordStatusEnum.INACTIVE.getId())));
			//criteria.add(Restrictions.and(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()), Restrictions.eq("category", category), Restrictions.eq("area", area)));
			
			dataList = criteria.list();
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get search :" + e.getCause());
		}
		return dataList;
	}
        
        //shop search
	public static List<Shop> searchShops(Province province,District district){//System.out.println("##### distrct : "+category.getName());
		List<Shop> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Shop.class);
                        
                        criteria.add(Restrictions.and( Restrictions.eq("area.district", district)));
                        criteria.add(Restrictions.or(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()),Restrictions.eq("status", RecordStatusEnum.INACTIVE.getId())));
			//criteria.add(Restrictions.and(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()), Restrictions.eq("category", category), Restrictions.eq("area", area)));
			
			dataList = criteria.list();
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get search :" + e.getCause());
		}
		return dataList;
	}
        
        //shop search
	public static List<Shop> searchShops(Province province){//System.out.println("##### distrct : "+category.getName());
		List<Shop> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Shop.class);
                        
                        criteria.add(Restrictions.and(Restrictions.eq("area.district.province", province)));
                        criteria.add(Restrictions.or(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()),Restrictions.eq("status", RecordStatusEnum.INACTIVE.getId())));
			//criteria.add(Restrictions.and(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()), Restrictions.eq("category", category), Restrictions.eq("area", area)));
			
			dataList = criteria.list();
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get search :" + e.getCause());
		}
		return dataList;
	}
        
        //shop search
	public static List<Shop> searchShops(Province province,District district,Category category){//System.out.println("##### distrct : "+category.getName());
		List<Shop> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Shop.class);
                        
                        criteria.add(Restrictions.and(Restrictions.eq("category", category)));
                        criteria.add(Restrictions.or(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()),Restrictions.eq("status", RecordStatusEnum.INACTIVE.getId())));
			//criteria.add(Restrictions.and(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()), Restrictions.eq("category", category), Restrictions.eq("area", area)));
			
			dataList = criteria.list();
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get search :" + e.getCause());
		}
		return dataList;
	}
        
        //shop search
	public static List<Shop> searchShops(Province province,District district,Area area){//System.out.println("##### distrct : "+category.getName());
		List<Shop> dataList = null;
		try {
			SessionUtill sessionUtill = new SessionUtill();
			Session session =  sessionUtill.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Shop.class);
                        
                        criteria.add(Restrictions.and(Restrictions.eq("area", area)));
                        criteria.add(Restrictions.or(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()),Restrictions.eq("status", RecordStatusEnum.INACTIVE.getId())));
			//criteria.add(Restrictions.and(Restrictions.eq("status", RecordStatusEnum.ACTIVE.getId()), Restrictions.eq("category", category), Restrictions.eq("area", area)));
			
			dataList = criteria.list();
			
			session.flush();
			session.getTransaction().commit();
			sessionUtill.closeSession();
			
		} catch (Exception e) {
			System.out.println("get search :" + e.getCause());
		}
		return dataList;
	}
}
