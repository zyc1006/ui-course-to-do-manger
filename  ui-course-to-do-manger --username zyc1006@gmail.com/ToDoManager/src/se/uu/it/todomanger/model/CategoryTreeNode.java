package se.uu.it.todomanger.model;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * The model for the Category tree
 * @author Sara
 *
 */

public class CategoryTreeNode extends DefaultMutableTreeNode{

	   public Category category;

	   //Constructor
	    public CategoryTreeNode(String title) {
	        setUserObject(title);
	    }
	    
		//Setters
	    public void setCategory(Category category) {
	        this.category = category;
	    }

		//Getters
	    public Category getCategory() {
	        return category;
	    }

	
	
}
