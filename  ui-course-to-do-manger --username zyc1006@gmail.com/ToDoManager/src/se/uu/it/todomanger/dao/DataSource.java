package se.uu.it.todomanger.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

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
				//task.setCompleted(taskElement.elementText("isCompleted").equals("true")? true:false);
				tasks.add(task);
			}
			
		} catch (DocumentException e) {
			
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		toHashMap();
		
		return tasks;
	}
	
	
}
