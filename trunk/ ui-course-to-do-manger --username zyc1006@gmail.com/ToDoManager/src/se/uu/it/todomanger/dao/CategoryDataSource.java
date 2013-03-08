package se.uu.it.todomanger.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import se.uu.it.todomanger.model.Category;
/**
 * A dao object in charge of saving and loading category data.
 * @author Sara
 *
 */
public class CategoryDataSource {
	
	/**A hashmap stores all the categories*/
	public static HashMap<Integer, Category> categoryHashMap = new HashMap<Integer, Category>();
	
	
	/**
	 * public static toXmlFile({@link HashMap<Integer, Category>} categories)<br>
	 * Convert a HashMap of categories into a xml file in default directory (user home directory)
	 * @param a HashMap of categories
	 */
	public  static void toXmlFile(HashMap<Integer, Category> categories){
		toXmlFile(categories, null);
	}
	
	
	/**
	 * public static toXmlFile({@link HashMap<Integer, Category>} categories, {@link String} fileName})<br>
	 * Convert a HashMap of categories into a xml file in a specific directory
	 * @param categories - a HashMap of categories
	 * @param fileName - the file name of the xml file
	 */
	public  static void toXmlFile(HashMap<Integer,Category> categories, String fileName){
		Document doc = DocumentHelper.createDocument();
		
		//root element
		Element rootElement = doc.addElement("TODOmanager_categories");
		
		Element categoriesElement = rootElement.addElement("categories");
		String userHome = System.getProperty("user.home");
		
		for (Entry<Integer, Category> entry : categories.entrySet()) {
		    Integer key = entry.getKey();
		    Category nextCategory = entry.getValue();
			Element category = categoriesElement.addElement("category");
			category.addAttribute("id", Integer.toString(nextCategory.getCategoryId()));
			Element title = category.addElement("title");
			title.setText(nextCategory.getCategoryTitle());

		}

		Element index = rootElement.addElement("index");
		index.addAttribute("id", Integer.toString(Category.nextCategoryId));
		
		//write file
		XMLWriter output;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			if (fileName == null) {
				output = new XMLWriter(new FileWriter(userHome
						+ "/TODOgroup12_cataegories.xml"),format);
				output.write(doc);
				output.close();	
			} else {
				output = new XMLWriter(new FileWriter(fileName),format);
				output.write(doc);
				output.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * public static {@link HashMap<Integer, Category>} toCategoryList()<br>
	 * Load the xml file from default directory(user home directory)
	 * 
	 */
	public static HashMap<Integer, Category> toCategoryList(){
		HashMap<Integer,Category> categories = toCategoryList(null);
		return categories;
	}
	
	/**
	 * public static {@link HashMap<Integer, Category>} toCategoryList({@link String} fileName)
	 * @param fileName the xml file name
	 * @return an HashMap contains all the cateogories
	 */
	public static HashMap<Integer, Category> toCategoryList(String fileName){
		HashMap<Integer,Category> categories = new HashMap<Integer,Category>();
		
		//default directory
		String userHome = System.getProperty("user.home");
		SAXReader reader = new SAXReader();
		
		try {
			Document doc = reader.read(userHome + "/TODOgroup12_cataegories.xml");
			Element rootElement = doc.getRootElement();
			Element categoriesElement = rootElement.element("categories");
			List categoryList = categoriesElement.elements("category");
			Iterator it = categoryList.iterator();
			
			while(it.hasNext()){
				Element categoryElement = (Element)it.next();
				Integer id = Integer.parseInt(categoryElement.attributeValue("id"));
				String title = categoryElement.elementText("title");		
				Category category = new Category(id, title);
				categories.put(id,  category);
			}
			
			Element index = rootElement.element("index");
			Category.nextCategoryId = Integer.parseInt(index.attributeValue("id"));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 	
		return categories;
	}
	
	
}
