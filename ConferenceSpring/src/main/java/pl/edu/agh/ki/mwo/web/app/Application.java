package pl.edu.agh.ki.mwo.web.app;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import pl.edu.agh.ki.mwo.persistence.HibernateUtil;

@SpringBootApplication
@ComponentScan(basePackages = "pl.edu.agh.ki.mwo.web.controllers")
public class Application {

    public static void main(String[] args) {
        
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		/*Criteria crit = session.createCriteria(User.class);
		List<User> users = crit.list();
		
		for (School s : schools) {
			System.out.println(s.getName());
		}*/
		
		SpringApplication.run(Application.class, args);
		
		session.close();
		HibernateUtil.shutdown();
        
    }

}