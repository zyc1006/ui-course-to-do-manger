package se.uu.it.todomanger.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	
	/**
	 * public staitc toXmlFile({@link ArrayList<Task>} task)<br>
	 * Convert a arraylist of tasks into a xml file in default directory (user home directory)
	 * @param an arraylist of tasks
	 */
	public  static void toXmlFile(ArrayList<Task> tasks){
		toXmlFile(tasks, null);
	}
	
	/**
	 * public staitc toXmlFile({@link ArrayList<Task>} task, {@link String} fileName})<br>
	 * Convert a arraylist of tasks into a xml file in a specific directory
	 * @param tasks an arraylist of tasks
	 * @param filename the file name of the xml file
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
	 * public public static {@link ArrayList<Task>} toTaskList()<br>
	 * Load the xml file from default directory(user home directory)
	 * @param an arraylist of tasks
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
				tasks.add(task);
			}
			
		} catch (DocumentException e) {
			
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return tasks;
	}
}
