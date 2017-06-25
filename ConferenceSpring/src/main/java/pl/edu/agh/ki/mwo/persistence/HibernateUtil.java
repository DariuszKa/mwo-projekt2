package pl.edu.agh.ki.mwo.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	static{
		try{
			//sessionFactory = new Configuration().configure().buildSessionFactory();
			buildSessionFactory();
		}catch (Exception e) {
			System.err.println("SessionFactory creation failed." + e);
			e.printStackTrace();
		}
	}
	
	private static SessionFactory buildSessionFactory()
	   {
	      try
	      {
	         if (sessionFactory == null)
	         {
	             sessionFactory = new Configuration().configure().buildSessionFactory();
	         }
	         return sessionFactory;
	      } catch (Throwable ex)
	      {
	         System.err.println("Initial SessionFactory creation failed." + ex);
	         throw new ExceptionInInitializerError(ex);
	      }
	 }

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown(){
		getSessionFactory().close();
	}
}
