
package se.uu.it.todomanger.dao;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.tools.JavaFileManager.Location;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import se.uu.it.todomanger.model.*;

public class Savestate
{
    // Attributes
    Task []taskList;
    Category []categoryList;
    
    // Date format for our save state
    SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    public Savestate()
    {
    
    }
    // Constructor
    public Savestate(String fileName)
    {
        try
        {
            // Open the save file
            File saveFile = new File(fileName);
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(saveFile);
            
            // Normalize the document
            doc.getDocumentElement().normalize();
            
            // Create node lists for tasks and categories
            NodeList taskNodes     = doc.getElementsByTagName("task");
            NodeList categoryNodes = doc.getElementsByTagName("category");
            
            // Get the number of tasks and categories
            int numberOfTasks      = taskNodes.getLength();
            int numberOfCategories = categoryNodes.getLength();
            
            // Create the arrays for tasks and categories
            taskList     = new Task[numberOfTasks];
            categoryList = new Category[numberOfCategories];
            
            // Fill task list with tasks from the save file
            for(int i = 0; i < numberOfTasks; i++)
            {
                Node taskNode = taskNodes.item(i);
                
                // Create task in task list
                if(taskNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element taskElement = (Element) taskNode;
                    
                    // Gather temporary information about the task
                    int tmpId             = Integer.parseInt(
                            taskElement.getAttribute("id"));
                    String tmpTitle       = taskElement.
                            getElementsByTagName("title").item(0).
                            getTextContent();
                    Date tmpDate = simpleDateFormat.parse(taskElement.
                            getElementsByTagName("date").item(0).
                            getTextContent());
                    int tmpCategoryId     = Integer.parseInt(taskElement.
                            getElementsByTagName("categoryId").item(0).
                            getTextContent());
                    String tmpDescription = taskElement.
                            getElementsByTagName("description").item(0).
                            getTextContent();
                    int tmpPriority       = Integer.parseInt(taskElement.
                            getElementsByTagName("priority").item(0).
                            getTextContent());
                    boolean tmpCompleted  = Boolean.parseBoolean(taskElement.
                            getElementsByTagName("completed").item(0).
                            getTextContent());
                    
                    // Insert task into task list
                    taskList[i] = new Task(tmpId, tmpTitle, tmpDate,
                            tmpCategoryId, tmpDescription, tmpPriority,
                            tmpCompleted);
                }
            }
            
            // Fill the category list with categories from the save file
            for(int i = 0; i < numberOfCategories; i++)
            {
                Node categoryNode = categoryNodes.item(i);
                
                // Create category in cateory list
                if(categoryNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element categoryElement = (Element) categoryNode;
                    
                    // Gather temporary information about the category
                    int tmpId             = Integer.parseInt(
                            categoryElement.getAttribute("id"));
                    String tmpTitle       = categoryElement.
                            getElementsByTagName("title").item(0).
                            getTextContent();
                    
                    // Insert category into category list
                    categoryList[i] = new Category(tmpId, tmpTitle);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("There were errors reading the save file");
        }
    }
    
    /*
    // Method to save a save state
    public void save(String fileName, boolean quickSave)
    {
        try
        {
            // Just overwrite the existing file
            if(quickSave)
            {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.
                        newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(fileName);
                
                // Get the root element
                Node savestateNode = doc.getFirstChild();
                
                
            }
            
            // Save as new file
            else
            {

            }
        }
        catch (ParserConfigurationException pce){pce.printStackTrace();}
        //catch (TransformerException tfe){tfe.printStackTrace();}
        catch (IOException ioe){ioe.printStackTrace();}
        catch (SAXException sae){sae.printStackTrace();}
    }*/
    
    // Method to get a new task id
    public int getNewTaskId()
    {
        int newTaskId = 0;
        
        if(taskList.length != 0)
        {
            // Go through the task list to find the highest id
            for(int i = 0; i < taskList.length; i++)
            {
                int tmpTaskId = taskList[i].getId();
                
                if(newTaskId < tmpTaskId)
                {
                    newTaskId = tmpTaskId;
                }
            }
            
            // Increment by 1 to get a new highest id
            newTaskId++;
        }
        
        return newTaskId;
    }
    
    // Method to get a new category id
    public int getNewCategoryId()
    {
        int newCategoryId = 0;
        
        if(categoryList.length != 0)
        {
            // Go through the task list to find the highest id
            for(int i = 0; i < categoryList.length; i++)
            {
                int tmpCategoryId = categoryList[i].getCategoryId();
                
                if(newCategoryId < tmpCategoryId)
                {
                    newCategoryId = tmpCategoryId;
                }
            }
            
            // Increment by 1 to get a new highest id
            newCategoryId++;
        }
        
        return newCategoryId;
    }
    
    // Method to get the category title of a category id
    public String getCategoryTitle(int categoryId)
    {
        String categoryTitle = "";
        
        for(int i = 0; i < categoryList.length; i++)
        {
            if(categoryList[i].getCategoryId() == categoryId)
            {
                categoryTitle = categoryList[i].getCategoryTitle();
                
                break;
            }
        }
        
        return categoryTitle;
    }
    
    // Returns the task list
    public Task [] getTaskList()
    {
        return taskList;
    }
    
    // Returns the cateogyr list
    public Category [] getCategoryList()
    {
        return categoryList;
    }
    
    // Method to print all the unfinished tasks in the save state
    public void printTasks()
    {
        for(int i = 0; i<taskList.length; i++)
        {
            if(!taskList[i].isCompleted())
            {
                System.out.println(getCategoryTitle(taskList[i].
                        getCategory()) + " - " + taskList[i].
                        getTitle() + "\n" + simpleDateFormat.
                        format(taskList[i].getDueDate()) + "\n");
            }
        }
    }
    
    
    /** public void saveLocation({@link Dimension} size, {@link Point} location)
     * <br>Save the window's size and location when the window is closed
     * @param size
     * @param location
     */
    public void saveLocation(Dimension size, Point location ,String lang){
    	String userHome = System.getProperty("user.home");
    	try {
			OutputStream os = new FileOutputStream(userHome + "/TODOgroup12.properties");
			Properties prop = new Properties();
			prop.put("x", Integer.toString(location.x));
			prop.put("y", Integer.toString(location.y));
			prop.put("width", Integer.toString(size.width));
			prop.put("height", Integer.toString(size.height));
			prop.put("lang", lang.toString());
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
    public Properties loadLocation(String filename) {
    	
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
}