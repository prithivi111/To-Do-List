package taskDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import taskmodel.Task;
import util_hibernate.HibernateUtil;

public class TaskDAO {
	public static SessionFactory sf = HibernateUtil.initializeSession();
		
	public static void addTask() {
		Scanner sc = new Scanner(System.in);
		Session session1 = null;
		Transaction tx1 = null;
			
		Date date = null;
			do {
				System.out.println("Enter date (yyyy-mm-dd): For eg: 1988-06-29 ");			
				String inputDate = sc.nextLine();	
				date = parseDateMethod(inputDate);   //this will call parseDateMethod below
			} while (date == null);	
			
			System.out.println("Enter task description: ");
			String inputDescription = sc.nextLine();
			
			System.out.println("Enter task duration (in minutes): ");
			int inputDuration = sc.nextInt();
			
			Task task = new Task();
			task.setTaskDate(date);
			task.setDescription(inputDescription);
			task.setDuration(inputDuration);
			
			try {
				session1 = sf.openSession();
				tx1 = session1.beginTransaction();
			
				session1.save(task);
				System.out.println("Task added successfully!!");
				tx1.commit();
			} catch(Exception e) {
					if(tx1!= null) {
						tx1.rollback();
				}
					e.printStackTrace();
			}finally {
				if (session1 != null) {
					session1.close();
				}
			}
		sc.close();				
	}

	private static Date parseDateMethod(String inputDate) {
		try {
			SimpleDateFormat dd = new SimpleDateFormat("yyyy-mm-dd");
			return dd.parse(inputDate);
	
		}catch(ParseException e) {
			System.err.println("Invalid Date Format. Please use yyyy-mm-dd.");
			return null;
		}	
	}
	
	public static void searchTask() {
		Scanner sc = new Scanner(System.in);
		Session session2 = null;
		Transaction tx2 = null;
		
		Date date = null;		
		do {
			System.out.println("Enter the search date (eg. 1992-06-26)");
			String inputSearchDate = sc.nextLine();	
			date = parseDateMethod(inputSearchDate);   //this will again call parseDateMethod above
		} while (date == null);	
		
		try {
			session2 = sf.openSession();
			tx2 = session2.beginTransaction();
			
			Query query = session2.createQuery("from Task where taskDate =: value");
			query.setParameter("value", date);
			List<Task> tasks = query.list();
			
			if (!tasks.isEmpty()) 
				{
					System.out.println("The task details fetched on the basis of " + date + " is/are: ");
						for(Task tt: tasks) {
						System.out.println(tt.getTaskId() + " | " + tt.getTaskDate() + " | " + tt.getDuration() +" | " +tt.getDescription());
						System.out.println();
						}
			} else {
					
					System.out.println("No tasks found for " + date);
			}	
			
			tx2.commit();
		} catch(Exception e) {
				if(tx2!= null) {
					tx2.rollback();
			}
				e.printStackTrace();
		}finally {
			if (session2 != null) {
				session2.close();
			}
		}
		sc.close();
	}
	
	
}
