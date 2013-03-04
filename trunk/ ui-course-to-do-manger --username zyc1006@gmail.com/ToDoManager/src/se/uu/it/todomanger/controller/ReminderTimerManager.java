package se.uu.it.todomanger.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import se.uu.it.todomanger.dao.DataSource;
import se.uu.it.todomanger.model.Task;
import se.uu.it.todomanger.ui.ShowRemainder;

public class ReminderTimerManager 
{

	private ArrayList<Task> taskArrayList;	
	
	
	/**Singleton of ReminderTimerManager*/
	private static ReminderTimerManager rmg = null;

	private ReminderTimerManager() {

		taskArrayList = DataSource.taskArrayList;
	}
	/**
	 * Initialize the ReminderTimerManager
	 * @return A singleton of ReminderTimerManager
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
	
	/**
	 * Add Timer to each task
	 * 
	 */
	public void TimeMonitor()
	{
		Task task = new Task();
		Timer timetask = new Timer(); 

		ReminderTask[] rt = new ReminderTask[taskArrayList.size()];
		Date [] t = new Date[taskArrayList.size()];
		//SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i = 0;
	
		Iterator<Task> it = taskArrayList.iterator();
		while(it.hasNext())
		{
			task = it.next();
			if(task.getDueDate().compareTo(new Date()) > 0)
			{
				rt[i] = new ReminderTask(task);
				t[i] = task.getDueDate();
				timetask.schedule(rt[i] , t[i] );
				i++;
			}
			//System.out.println(task.getId() + ":" + task.getTitle() + ":" + task.getDueDate());
		}
	
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
		//System.out.println(task.getId()+ ":" + task.getDueDate() +"::" + new Date() +"need to do");
		//System.out.println(new Date());
		
	}
}
}

