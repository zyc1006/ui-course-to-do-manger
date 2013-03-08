package se.uu.it.todomanger.dao;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import se.uu.it.todomanger.controller.ThemeManager;
import se.uu.it.todomanger.model.Task;
/**
 * A dao object in charge of saving and loading task data.
 * @author Yucheng
 *
 */
public class DataSource {
	
	/**A hashmap stores all the task*/
	public static HashMap<Integer, Task> taskHashMap = new HashMap<Integer, Task>();
	
	/**An arraylist stores all the task, used for sorting */
	public static ArrayList<Task> taskArrayList = new ArrayList<Task>();
	
//	/**
//	 * get the hashmap of tasks
//	 * @return a hashmap contanis all the tasks
//	 */
//	public HashMap<Integer, Task> getTaskHashMap() {
//		return taskHashMap;
//	}
	
//	/**
//	 * get the ArrayList of tasks
//	 * @return an ArrayList contanis all the tasks
//	 */
//	public ArrayList<Task> getTaskArrayList() {
//		return taskArrayList;
//	}
//	
//	/**
//	 * set the ArrayList of tasks
//	 */
//	public void setTaskArrayList(ArrayList<Task> taskArrayList) {
//		this.taskArrayList = taskArrayList;
//		toHashMap();
//	}
	
	/**
	 * public staitc toXmlFile({@link ArrayList} task)<br>
	 * Convert a arraylist of tasks into a xml file in default directory (user home directory)
	 * @param an arraylist of tasks
	 */
	public  static void toXmlFile(ArrayList<Task> tasks){
		toXmlFile(tasks, null);
	}
	
	
	
	/**
	 * public static toXmlFile({@link ArrayList} task, {@link String} fileName})<br>
	 * Convert a arraylist of tasks into a xml file in a specific directory
	 * @param tasks an arraylist of tasks
	 * @param fileName the file name of the xml file
	 */
	public  static void toXmlFile(ArrayList<Task> tasks, String fileName){
		Document doc = DocumentHelper.createDocument();
		
		//root element
		Element rootElement = doc.addElement("ToDoManager");
		
		Element tasksElement = rootElement.addElement("tasks");
		String userHome = System.getProperty("user.home");
		
		//add the nodes to root element
		for(int i = 0; i < tasks.size(); i++){
			Element task = tasksElement.addElement("task");
			task.addAttribute("id", Integer.toString(tasks.get(i).getId()));
			Element title = task.addElement("title");
			title.setText(tasks.get(i).getTitle());
			Element category = task.addElement("category");
			category.setText(Integer.toString(tasks.get(i).getCategory()));
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			Element duedate = task.addElement("duedate");
			duedate.setText(sdf.format(tasks.get(i).getDueDate()));
			Element priority = task.addElement("priority");
			priority.setText(Integer.toString(tasks.get(i).getPriority()));
			Element description = task.addElement("description");
			description.setText(tasks.get(i).getDescription());
			Element isComplete = task.addElement("isCompleted");
			isComplete.setText(tasks.get(i).isCompleted()? "true":"false");
		}
		
		//wirte file
		XMLWriter output;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			if (fileName == null) {
				output = new XMLWriter(new FileWriter(userHome
						+ "/TODOgroup12.xml"),format);
				output.write(doc);
				output.close();	
			}
			else{
				output = new XMLWriter(new FileWriter(fileName),format);
				output.write(doc);
				output.close();	
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
       
		
	}
	
	/**
	 * public public static {@link ArrayList} toTaskList()<br>
	 * Load the xml file from default directory(user home directory)
	 * 
	 */
	public static ArrayList<Task> toTaskList(){
		ArrayList<Task> tasks = toTaskList(null);
		return tasks;
	}
	
	/**
	 * public static {@link ArrayList} toTaskList({@link String} fileName)
	 * @param fileName the xml file name
	 * @return an arraylist contains all the tasks
	 */
	public static ArrayList<Task> toTaskList(String fileName){
		ArrayList<Task> tasks = new ArrayList<Task>();
		
		//default directory
		String userHome = System.getProperty("user.home");
		SAXReader reader = new SAXReader();
		
		try {
			Document doc = reader.read(userHome+ "/TODOgroup12.xml");
			Element rootElement = doc.getRootElement();
			Element tasksElement = rootElement.element("tasks");
			List taskList = tasksElement.elements("task");
			Iterator it = taskList.iterator();
			
			while(it.hasNext()){
				Element taskElement = (Element)it.next();
				Task task = new Task();
				task.setId(Integer.parseInt(taskElement.attributeValue("id")));
				task.setCategory(Integer.parseInt(taskElement.elementText("category")));
				task.setTitle(taskElement.elementText("title"));
				task.setPriority(Integer.parseInt(taskElement.elementText("priority")));
				task.setDescription(taskElement.elementText("description"));
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
				task.setDueDate(sdf.parse(taskElement.elementText("duedate")));
				task.setCompleted(taskElement.elementText("isCompleted").equals("true")? true:false);
				tasks.add(task);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
//		toHashMap();
		return tasks;
	}
	
	/** public void saveLocation({@link Dimension} size, {@link Point} location)
     * <br>Save the window's size, theme and location when the window is closed
     * @param size
     * @param location
     */
    public static void saveLocation(Dimension size, Point location ,String lang){
    	String userHome = System.getProperty("user.home");
    	try {
			OutputStream os = new FileOutputStream(userHome + "/TODOgroup12.properties");
			Properties prop = new Properties();
			prop.put("x", Integer.toString(location.x));
			prop.put("y", Integer.toString(location.y));
			prop.put("width", Integer.toString(size.width));
			prop.put("height", Integer.toString(size.height));
			prop.put("lang", lang.toString());
			prop.put("theme", Integer.toString(ThemeManager.getTheme()));
			prop.store(os, "Window size and location");
			os.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /** public void loadLocation({@link String} filename)
     * <br>load the previous window's size and location
     * @param filename
     * 
     */
    public static Properties loadLocation(String filename) {
    	
    	Properties prop = new Properties();
    	try {
			prop.load(new FileInputStream(new File(filename)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	return prop;
    }
    
    public static void updateTask(Task task){
    	for(Task taskupd: taskArrayList){
    		if(task.getId()== task.getId()){
    			taskupd = task;
    		}
    	}
    }
    
    public static void deleteTask(Task task){
    	for(int i = 0; i < taskArrayList.size(); i++){
    		Task taskdel = taskArrayList.get(i);
    		if(taskdel.getId() == task.getId()){
    			taskArrayList.remove(i);
    		}
    	}
    		
    }
	
	
}
