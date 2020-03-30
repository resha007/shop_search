package com.shop.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class SessionUtill {
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static final SessionFactory  sessionFactory= createSessionFactory();
			
	private static 	SessionFactory createSessionFactory(){
		return new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	public static Session openSession(){
		Session session = threadLocal.get();
		if(session==null){
			session =  sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}
	
	public static void closeSession(){
		Session session = threadLocal.get();
		if(session.isOpen()){
			session.close();
			threadLocal.remove();
		}
	}
}
