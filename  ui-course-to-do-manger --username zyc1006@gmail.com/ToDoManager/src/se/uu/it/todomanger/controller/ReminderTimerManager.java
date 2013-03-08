package se.uu.it.todomanger.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import se.uu.it.todomanger.dao.DataSource;
import se.uu.it.todomanger.model.Task;
import se.uu.it.todomanger.ui.ShowRemainder;

/**
 * 
 * A timer manager that adds undone tasks to a timer list for reminding
 * @author shiyu
 */
public class ReminderTimerManager {

	private ArrayList<Task> taskArrayList;
	private HashMap< Integer, Timer> timerHashMapList;

	/** Singleton of ReminderTimerManager */
	private static ReminderTimerManager rmg = null;

	private ReminderTimerManager() {

		taskArrayList = DataSource.taskArrayList;
		timerHashMapList = new HashMap<Integer, Timer>();
	}

	/**
	 * Initialize the ReminderTimerManager
	 * 
	 * @return A singleton of ReminderTimerManager
	 */
	public static ReminderTimerManager getInstance() {
		synchronized (ReminderTimerManager.class) {
			if (rmg == null) {
				synchronized (TaskManager.class) {
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
		//ReminderTask[] rt = new ReminderTask[taskArrayList.size()];
		//Date[] t = new Date[taskArrayList.size()];
		//int i = 0;
		
		// SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Iterator<Task> it = taskArrayList.iterator();
		while (it.hasNext()) {
			task = it.next();
			//rt[i] = new ReminderTask(task);
			//t[i] = task.getDueDate();
			TimeMonitorTask(task);
			//i++;
		}
	}
	/**
	 *  public TimeMonitorTask({@link Task} task)<br>
	 * @param task a task model
	 */
	public void TimeMonitorTask(Task task)
	{
		Timer timetask = new Timer(); 
		if (task.getDueDate().compareTo(new Date()) > 0 && task.isCompleted() == false) 
		{
			timetask.schedule(new ReminderTask(task) , task.getDueDate());
			timerHashMapList.put(task.getId(), timetask);
		}
		// System.out.println(task.getId() + ":" + task.getTitle() + ":" +
		// task.getDueDate());
	}
	public void resetTaskTimer(Task task)
	{
		Integer i = task.getId();
		if(task.isCompleted() == false)
		{
			TimeMonitorTask(task);
		}
		else
		{
			Timer timetask = timerHashMapList.get(i); 
			if(timetask != null)
			{
				timetask.cancel();
			}
		}
	}
	
	
	class ReminderTask extends TimerTask {
		private Task task;

		public ReminderTask(Task task) {
			super();
			this.task = task;
		}

		@SuppressWarnings("unused")
		@Override
		public void run() {
			// TODO Auto-generated method stub
			ShowRemainder sr = new ShowRemainder(task);
			// System.out.println(task.getId()+ ":" + task.getDueDate() +"::" +
			// new Date() +"need to do");
			// System.out.println(new Date());

		}
	}
}
