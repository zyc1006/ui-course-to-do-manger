package se.uu.it.todomanger.model;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * The model for the Category tree
 * @author Sara
 *
 */

public class CategoryTreeNode extends DefaultMutableTreeNode{

	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Category category;
	   /**
	    * Constructor
	    * @param title
	    */
	    public CategoryTreeNode(String title) {
	        setUserObject(title);
	    }
	    
		/**
		 * sets the category
		 * @param category
		 */
	    public void setCategory(Category category) {
	        this.category = category;
	    }

		/**
		 * returns the category
		 * @return category
		 */
	    public Category getCategory() {
	        return category;
	    }

	
	
}
