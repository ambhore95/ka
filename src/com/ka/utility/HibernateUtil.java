package com.ka.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static ThreadLocal <Session> threadLocal=new ThreadLocal<Session>();
	private static SessionFactory factory;
	
	static {
		Configuration cfg=null;
		//Create Session Factory
		cfg=new Configuration();
		cfg=cfg.configure("/com/ka/cfgs/hibernate.cfg.xml");
		//get Factory
		factory=cfg.buildSessionFactory();
	}
	
	public static Session getSession() {
		Session ses=null;
		if(threadLocal.get()==null) {
			ses=factory.openSession();
			threadLocal.set(ses);
		}
		ses=threadLocal.get();
		return ses;
	}
	public static void closeSession(){
		Session session=null;
		session=threadLocal.get();
		session.close();
		threadLocal.remove();
	}
	
	public static void closeSessionFactory(){
		factory.close();
	}

}
