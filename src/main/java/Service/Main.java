package Service;
import java.text.SimpleDateFormat;

import taskDAO.TaskDAO;

public class Main {
	public static void main(String[] args) {
		
		//Insert task into the DB
		TaskDAO.addTask();	
		
		//Search task based on the particular date
		//Since we can have multiple tasks on the particular date, we need to use List<> here.
		TaskDAO.searchTask();
		
	}
}
