package pl.edu.agh.ki.mwo.persistence;

//////import java.awt.List;
//import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/////////import javax.websocket.Session;

/////////import org.hibernate.Criteria;

///import javax.persistence.*;
///import org.hibernate.Query;
///import org.hibernate.Session;
///import org.hibernate.Transaction;
///import pl.edu.agh.ki.mwo.persistence.HibernateUtil;
/*import javax.persistence.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;*/

import pl.edu.agh.ki.mwo.model.User;

import org.hibernate.Criteria;
import org.hibernate.Session;


///@Entity
///@Table
//@SuppressWarnings("serial")
public class UserDatabase{
	
	protected static UserDatabase userDatabase = null;
	
	public static UserDatabase getInstance()
	{
		if (userDatabase == null)
			userDatabase = new UserDatabase();
		return userDatabase;
	}

	protected User currentUser = null;
	
	protected boolean currentUserIsPrelegent = false;
	
	protected boolean currentUserIsRecenzent = false;
	
	protected boolean currentUserIsAdministrator = false;
	
	protected int userCount = 0;
	
	///	Session session = HibernateUtil.
	///getSessionFactory().openSession();
	
	///Criteria crit = session.createCriteria
	///(School.class);

	
	///////protected ArrayList<User> users = null;
	protected List<User> users = null;	//protected List<User> users = null;
	///protected List<User> users = crit.list();
	
	
	protected UserDatabase() {
		//users = new ArrayList<User>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria crit = session.createCriteria(User.class);
		List<User> users = crit.list();	//List<User> users = crit.list();
		///users = new List<User>();
	}

	public List<User> getUsers() {
		return this.users;
	}

	//public void addUser(User user) {
	public void addUser(String login, String firstName, String lastName, String sex, int year, int month, int day, String university, String domain, String email, String address, String telephone, boolean isPrelegent, boolean isRecenzent, boolean isAdmin, String password){
		//System.out.println("1 login='" + login + "'"); //String login = user.getLogin();
		int age = new GregorianCalendar().get(Calendar.YEAR) - year;
		if(!UserDatabase.getInstance().hasUser(login))
			if(!(login.length()<3))
				if(!(login.length()>20))
					if(!(firstName.length()<2))
						if(!(firstName.length()>40))
							if(!(lastName.length()<2))
								if(!(lastName.length()>60))
									if(!(age<18))
										if(!(age>100))
											if(isPrelegent==true || isPrelegent==false)
												if(isRecenzent==true || isRecenzent==false)
													if(isAdmin==true || isAdmin==false)
														if(!(password.length()<5))
															if(!(password.length()>20)) {
																
																/*Transaction trns = null;
																Session session = HibernateUtil.getSessionFactory().openSession();
																try {
																	trns = session.beginTransaction();*/
																	
																	
																	//System.out.println("UserDatabase 1 login='" + login + "' firstName='" + firstName + "' lastName='" + lastName + "' sex='" + sex + "' year=" + year + " month=" + month + " day=" + day + " university='" + university + "' domain='" + domain + "' email='" + email + "' isPrelegent=" + isPrelegent + " isRecenzent=" + isRecenzent + " isAdmin=" + isAdmin);
																	User nUser = new User(login, firstName, lastName, sex, year, month, day, university, domain, email, address, telephone, isPrelegent, isRecenzent, isAdmin, password);
																	users.add( nUser );
																	//System.out.println("UserDatabase 2 login='" + nUser.getLogin() + "' firstName='" + nUser.getFirstName() + "' lastname='" + nUser.getLastName() + "' sex='" + nUser.getSex() + "' year=" + nUser.getYear() + " month=" + nUser.getMonth() + " day=" + nUser.getDay() + " university='" + nUser.getUniversity() + "' domain='" + nUser.getDomain() + "' email='" + nUser.getEmail() + 
																	//		"' isPrelegent=" + nUser.getIsPrelegent() + " isRecenzent=" + nUser.getIsRecenzent() + " isAdmin=" + nUser.getIsAdmin());
																
																/*}
																catch (RuntimeException e) {
																	if (trns != null) 
																		trns.rollback();
																	e.printStackTrace();
																}
																finally {
																	session.flush();
																	session.close();
																}*/
																
															}
	}
	
	public void removeUser(int userId) {
		for(User user : users) {
			if (user.getId() == userId)	{
				users.remove(user);
				return;
			}
		}
	}
	
	public boolean hasUser(String login){
		for(User user: users){
			if(user.getLogin().equals(login)) return true;
		}
		return false;
	}
	
	public boolean getLoginCorrect(String login, String password){
		for(User user: users){
			if(user.getLoginPassCorrect(login, password)) {
					currentUser = user;
					currentUserIsPrelegent = currentUser.getIsPrelegent();
					currentUserIsRecenzent = currentUser.getIsRecenzent();
					currentUserIsAdministrator = currentUser.getIsAdmin();
					return true;
			}
		}
		return false;
	}
	
	public User getCurrentUser(){
		return currentUser;
	}
	
	public boolean getCurrentUserIsPrelegent(){
		return currentUser.getIsPrelegent();
	}
	
	public boolean getCurrentUserIsRecenzent(){
		return currentUser.getIsRecenzent();
	}
	                 
	public boolean getCurrentUserIsAdministrator(){
		return currentUserIsAdministrator;
	}
	
	public int getUserCount(){
		return users.size();
	}
}
