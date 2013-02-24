package se.uu.it.todomanger.model;


/**
 * The model for the Category
 * @author Sara
 *
 */

public class Category {

	//CategoryID
	public static int nextCategoryId = 0;
		
		
    // Attributes
    private int id;
    private String title;
    private Category parent;
    
    
    // Constructor
    public Category(int id, String title) {
        this.id    = id;
        this.title = title;
    }
    
    // Constructor with parent
    public Category(int id, String title, Category parent) {
        this.id     = id;
        this.title  = title;
        this.parent = parent;
    }
    
    // Getters
    public int getCategoryId() {
        return id;
    }
    public String getCategoryTitle() {
        return title;
    }
    public Category getCategoryParent() {
        return parent;
    }
    
    // Setters
    public void setCategoryTitle(String title) {
        this.title = title;
    }
    public void setCategoryParent(Category parent) {
        this.parent = parent;
    }
}