package task01;

import java.util.Properties;
import java.util.TimeZone;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionFactory {
	private static SessionFactory hibernateSessionFactory;

	public static SessionFactory getSessionFactory() {
		if (hibernateSessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				
				Properties properties = new Properties();
				properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				properties.put(Environment.URL, "jdbc:mysql://localhost:3306/post_and_comments?serverTimezone=" + TimeZone.getDefault().getID());
				properties.put(Environment.USER, "root");
				properties.put(Environment.PASS, "root");
				properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				properties.put(Environment.SHOW_SQL, "true");
				properties.put(Environment.HBM2DDL_AUTO, "create");
				configuration.setProperties(properties);
				
				configuration.addAnnotatedClass(Comment.class);
				configuration.addAnnotatedClass(Post.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

				hibernateSessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hibernateSessionFactory;
	}
	
}