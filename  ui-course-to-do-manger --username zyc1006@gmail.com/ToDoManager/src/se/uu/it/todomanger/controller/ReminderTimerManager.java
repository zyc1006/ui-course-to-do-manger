package se.uu.it.todomanger.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import se.uu.it.todomanger.dao.DataSource;
import se.uu.it.todomanger.model.Task;
import se.uu.it.todomanger.ui.ShowRemainder;
import se.uu.it.todomanger.ui.ToDoManagerTaskTable;

public class ReminderTimerManager 
{
//	Task task = new Task();
//	task.
	private ArrayList<Task> taskArrayList;	
	
	/**A task table instance to handle the display operation on the task table */
	//private ToDoManagerTaskTable taskTable = ToDoManagerTaskTable.getInstance();
	
	/**Singleton of TaskManager*/
	private static ReminderTimerManager rmg = null;

	private ReminderTimerManager() {

		taskArrayList = DataSource.taskArrayList;
	}
	/**
	 * Initialize the task manager
	 * @return A singleton of Task Manager
	 * @see Task
	 */
	public static ReminderTimerManager getInstance() {
		synchronized (ReminderTimerManager.class) {
			if (rmg == null) {
				synchronized(TaskManager.class){
					rmg = new ReminderTimerManager();
				}
			}
		}
		return rmg;
		
	}
	
	
	//public ReminderTimerManager()
	//public static void main(String[] args)
	public void TimeMonitor()
	{
		Task task = new Task();
		Timer timetask = new Timer(); 
		//ArrayList<Task> al = new ArrayList<Task>();

		ReminderTask[] rt = new ReminderTask[taskArrayList.size()];
		Date [] t = new Date[taskArrayList.size()];
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i = 0;
		
		
		
		
		Iterator<Task> it = taskArrayList.iterator();
		while(it.hasNext())
		{
			task = it.next();
			//rt[i] = new ReminderTask(task);
			if(task.getDueDate().compareTo(new Date()) > 0)
			{
				rt[i] = new ReminderTask(task);
				t[i] = task.getDueDate();
				timetask.schedule(rt[i] , t[i] );
//				//break;
				i++;
			}
			//System.out.println(task.getId() + ":" + task.getTitle() + ":" + task.getDueDate());
			//timetask.schedule(rt[i] , new Date() );
		}
//		Date time1 = new Date();
//		Date time2 = new Date();
//		Date time3 = new Date();
//		Date time4 = new Date();
//			try {
//				time1 = time.parse("2013-03-02 18:16:30");
//				time2 = time.parse("2013-03-04 9:44:40");
//				time3 = time.parse("2013-03-04 9:44:42");
//				time4 = time.parse("2013-03-04 9:44:44");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		System.out.println(time1.getYear() + ":" + time1.getMonth() + ":" + time1.getDate() + ":" + time1.getDay() + ":" + time1.getHours() + ":" 
//				+ time1.getMinutes() + ":" + time1.getSeconds());
//		Task task1 = new Task(1, "first", time1 , 1, "hello1", 2, true);
//		Task task2 = new Task(2, "first", time2 , 1, "hello2", 2, true);
//		Task task3 = new Task(3, "first", time3 , 1, "hello3", 2, true);
//		Task task4 = new Task(4, "first", time4 , 1, "hello4", 2, true);
		//Task task3 = new Task(3, "first", time3 , 1, "hello3", 2, true);
		//Task task1 = new Task();
		
//	
//		al.add(task1);
//		al.add(task2);
//		al.add(task3);
//		al.add(task4);
		
		
		//ReminderTask rt = null;
//		Iterator<Task> it = al.iterator();
//		while(it.hasNext())
//		{
//			task = it.next();
//			//rt[i] = new ReminderTask(task);
//			if(task.getDueDate().compareTo(new Date()) > 0)
//			{
//				rt[i] = new ReminderTask(task);
//				t[i] = task.getDueDate();
//				timetask.schedule(rt[i] , t[i] );
////				//break;
//				i++;
//			}
//			System.out.println(task.getId() + ":" + task.getTitle() + ":" + task.getDueDate());
//			//timetask.schedule(rt[i] , new Date() );
//		}
//		for(i = i-1; i >= 0 ; i-- )
//		{
////			timetask.schedule(rt[i] , t[i] );
//		}
//		System.out.println(task.getId() + ":" + task.getTitle() + ":" + task.getDueDate());
//		timetask.schedule(rt , task.getDueDate() );
	}
//		if(task1.getDueDate().compareTo(new Date()) > 0)
//		{
//			System.out.println("还没发生");
//			ShowRemainder sr = new ShowRemainder(task1);
//		}
//		else if(task1.getDueDate().compareTo(new Date()) == 0)
//		{
//			System.out.println("正好发生");
//			ShowRemainder sr = new ShowRemainder(task1);
//		}
//		else
//		{
//			System.out.println("已发生");
//			ShowRemainder sr = new ShowRemainder(task1);
//		}
//		
		//System.out.println(task1.getDueDate() - new Date());
	
}
class ReminderTask extends TimerTask
{
	private Task task;
	
	public ReminderTask(Task task)
	{
		super();
		this.task = task;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ShowRemainder sr = new ShowRemainder(task);
		System.out.println(task.getId()+ ":" + task.getDueDate() +"::" + new Date() +"正好发生");
		//System.out.println(new Date());
		
	}
}

