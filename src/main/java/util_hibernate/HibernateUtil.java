package util_hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import taskmodel.Task;

public class HibernateUtil {
	public static SessionFactory initializeSession() {
		try {
		Configuration con = new Configuration().configure().addAnnotatedClass(Task.class);
		SessionFactory sf = con.buildSessionFactory();
		return sf;
		} catch (Exception e) {
			throw new ExceptionInInitializerError();
		}
	}


}
