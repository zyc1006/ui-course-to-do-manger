package se.uu.it.todomanger.controller;

import java.util.HashMap;
import java.util.Map.Entry;

import se.uu.it.todomanger.dao.CategoryDataSource;
import se.uu.it.todomanger.model.Category;


/**
 * A singleton TaskManger used for creating and deleting categories.
 * @author Sara
 * 
 */


public class CategoryManager {
	/**A hashmap that stores all the categories*/
	private HashMap<Integer, Category> categoryHashMap = new HashMap<Integer, Category>();
	
	/**Singleton of TaskManager*/
	private static CategoryManager cm = null;
	
	private CategoryManager() {
		categoryHashMap = CategoryDataSource.categoryHashMap;
		int index = 0;
		
		if (categoryHashMap.size() == index) {
			categoryHashMap.put(index, new Category(index, LanguageManager.getString("MainWindow_NoCategoryName")));
		} else {
			for (Entry<Integer, Category> entry : categoryHashMap.entrySet()) {
				if (entry.getKey() > index ) { index = entry.getKey(); }
			}
		}
			
		Category.nextCategoryId = index+1;
			
	}
	
	
	
	/**
	 * Initialize the cateogry manager
	 * @return A singleton of Cateogry Manager
	 */
	public static CategoryManager getInstance() {
		if (cm == null) {
			cm = new CategoryManager();
		}
		return cm;
	}
	
	
	/**
	 * get the Categories
	 * returns category 
	 */
	public HashMap<Integer, Category> getCategories() {
		return categoryHashMap;
	}
	
	
	/**
	 * addCategory
	 * @param category
	 *            - The category that is going to be added
	 * Adds a category
	 */
	public void addCategory(Category category) {
		categoryHashMap.put(category.getCategoryId(), category);
	}

	
	/**
	 * deleteCategory
	 * @param category
	 *            - The category that is going to be deleted
	 * delete a category
	 */
	public void deleteCategory(Category category) {
		categoryHashMap.remove(category.getCategoryId());
	}
}

