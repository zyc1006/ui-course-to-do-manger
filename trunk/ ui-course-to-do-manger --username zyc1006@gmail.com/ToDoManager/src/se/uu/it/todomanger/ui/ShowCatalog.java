package se.uu.it.todomanger.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import se.uu.it.todomanger.controller.CategoryManager;
import se.uu.it.todomanger.controller.LanguageManager;
import se.uu.it.todomanger.controller.TaskManager;
import se.uu.it.todomanger.dao.DataSource;
import se.uu.it.todomanger.model.Category;
import se.uu.it.todomanger.model.CategoryTreeNode;

/**
 *  A class that display the categories
 * @author Sara
 */

public class ShowCatalog  {
	private static DefaultMutableTreeNode root;
	private static DefaultTreeModel model;
	private static JPanel panel;
	private static JTree tree;
	private static JButton addButton;
	private static JButton removeButton;
	
	// Size of buttons
	public static final int CATEGORY_BUTTON_WIDTH = 50;
	public static final int CATEGORY_BUTTON_HEIGHT = 36;

	
	/**
	 * Defines the GUI for categories
	 * 
	 * @author sara
	 */
	public JPanel init() {
		//Init GridBagLayout
		panel = new JPanel();
		GridBagLayout gridbagLayoutSettings = new GridBagLayout();
		panel.setLayout(gridbagLayoutSettings); 
		GridBagConstraints gc = new GridBagConstraints();	
	    gc.fill = GridBagConstraints.BOTH;
	    gc.anchor = GridBagConstraints.NORTHWEST;
	    
		//Init Add Button
		addButton = new JButton(LanguageManager.getString("MainWindow_AddCategory_Label"));
	    gc.weightx = 0.5;
	    gc.gridx = 0;
	    gc.gridy = 0;
		setAddButtonListener(addButton);
		gridbagLayoutSettings.setConstraints(addButton, gc);
		addButton.setMargin(new Insets(2,0,2,0));
	    panel.add(addButton);

		//Init Remove Button 
	    removeButton = new JButton(LanguageManager.getString("MainWindow_RemoveCategory_Label"));
	    gc.gridx = 2;
	    gc.gridy = 0;
		setRemoveButtonListener(removeButton);
		gridbagLayoutSettings.setConstraints(removeButton, gc);
		removeButton.setMargin(new Insets(2,0,2,0));
	    panel.add(removeButton);

		//Init tree
		root = new DefaultMutableTreeNode(LanguageManager.getString("MainWindow_AllCategories_Label"));
		model = new DefaultTreeModel(root);
	    tree = new JTree(model);
		tree.setEditable(true);
	    tree.setSelectionRow(0);
	    model.addTreeModelListener(new CategoryTreeModelListener());
	    setTreeNodeListener();
        gc.weightx = 0.0;
        gc.weighty = 1.0;
        gc.gridwidth = 3;
        gc.gridx = 0;
        gc.gridy = 1;
		gridbagLayoutSettings.setConstraints(tree, gc);
		
		CategoryManager cm = CategoryManager.getInstance();


		//Create nodes for all loaded categories from xml
		for (Entry<Integer, Category> entry : cm.getCategories().entrySet()) {
			CategoryTreeNode loadNode = new CategoryTreeNode(entry.getValue().getCategoryTitle());
	        model.insertNodeInto(loadNode, root, root.getChildCount());
	 
	        Category nc = new Category(entry.getKey(), entry.getValue().getCategoryTitle());
	        loadNode.setCategory(nc);
	        
	        cm.addCategory(nc);
		}
	
		tree.expandRow(0);
	    panel.add(tree);
		return panel;
	}
	
	
	
	/**
	 * Handles selecting of treenodes
	 * 
	 * @author sara
	 */
	
	private void setTreeNodeListener() {
		tree.addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) { 
			    try {
				    //Try if a node has been selected
		    		DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		    		
		    		//Display tasks in the selected category. If root is selected all tasks is dispayed.
		    		if (selNode.equals(root)) {
		    			TaskManager.getInstance().showTaskInCategory(-1);
		    			System.out.print("Selected category: All \n");
		    			System.out.print("Change this in ShowCatalog, row 130-138\n\n");
		    		} else {
				    	CategoryTreeNode selCategoryNode = (CategoryTreeNode) selNode;
			    		System.out.print("Selected Category: "+ selCategoryNode.getCategory().getCategoryTitle() + " with id: " + selCategoryNode.getCategory().getCategoryId() + "\n");
		    			System.out.print("Change this in ShowCatalog, row 130-138\n\n");
		    			TaskManager.getInstance().showTaskInCategory(selCategoryNode.getCategory().getCategoryId());
		    		}
			    	

			    } catch (NullPointerException exc) {}

		    }
		});
	}
	
	
	
	/**
	 * Handles the add button
	 * 
	 * @author sara
	 * @author bjorn
	 */
	
	private void setAddButtonListener(JButton addButton) {
		addButton.addActionListener(new ActionListener() {
    	
			public void actionPerformed(ActionEvent event) {
			    //Create new tree node
		        CategoryTreeNode newNode = new CategoryTreeNode(LanguageManager.getString("MainWindow_NewCategoryName"));
		        model.insertNodeInto(newNode, root, root.getChildCount());
		        TreeNode[] nodes = model.getPathToRoot(newNode);
		        TreePath path = new TreePath(nodes);
		        tree.scrollPathToVisible(path);
		        tree.setSelectionPath(path);
		        tree.startEditingAtPath(path);
		         
		        //Add category to tree node
		        Category nc = new Category(Category.nextCategoryId, newNode.getUserObject().toString());
		        newNode.setCategory(nc);
		          
		        //Add category to category list
		        CategoryManager cm = CategoryManager.getInstance();
		        cm.addCategory(nc);
		  	 }
	    });
	}

	/**
	 * Handles the remove button
	 * 
	 * @author sara
	 */
	
	private void setRemoveButtonListener(JButton addButton) {
		addButton.addActionListener(new ActionListener() {
    	
			public void actionPerformed(ActionEvent event) {
			    try {
				    //Try if a node has been selected
			    	DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			    	
			    	//Show error if root is marked since that category is not allowed to delete
			    	if (selNode.equals(root)) {
			    		JOptionPane.showMessageDialog(panel, LanguageManager.getString("Category_Delete_Error"), LanguageManager
			    				.getString("AddEditDialog_Error_Dialog_Title"),
			    				JOptionPane.ERROR_MESSAGE);
			    	} else {
				    
					    //Remove category from category list
					    CategoryTreeNode selCategoryNode = (CategoryTreeNode) selNode;
					    
				    	//Show error if "No Category" is marked since that category is not allowed to delete
					    if (selCategoryNode.getCategory().getCategoryId() == 0) {
				    		JOptionPane.showMessageDialog(panel, LanguageManager.getString("Category_Delete_Error"), LanguageManager
				    				.getString("AddEditDialog_Error_Dialog_Title"),
				    				JOptionPane.ERROR_MESSAGE);
					    } else {
						    CategoryManager cm = CategoryManager.getInstance();
						    cm.deleteCategory(selCategoryNode.getCategory());
						    
					    	//Remove node from tree
						    model.removeNodeFromParent(selNode);
					    }
			    	}
			    
			        
			    } catch (NullPointerException exc) {} 
			}
		});
	
	}
	
	/**
	 * Handles the language for add and remove buttons
	 * 
	 * @author bjorn
	 */
	public static void UpdateLanguageText() {
		Integer width, height;
		removeButton.setText(LanguageManager.getString("MainWindow_RemoveCategory_Label"));
		addButton.setText(LanguageManager.getString("MainWindow_AddCategory_Label"));
		root.setUserObject(new String(LanguageManager.getString("MainWindow_AllCategories_Label")));
		tree.updateUI();
	}
	
	
	/**
	 * Handles changes of category titles
	 * 
	 * @author sara
	 */
	
	//Handles changes in nodes
	private class CategoryTreeModelListener implements TreeModelListener {
	
		public void treeNodesChanged(TreeModelEvent e) {
		    //Get current selected node
			DefaultMutableTreeNode node;
		    node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());

		    //Try if a node has been selected
		    try {
		        int index = e.getChildIndices()[0];
		        node = (CategoryTreeNode) (node.getChildAt(index));
		    } catch (NullPointerException exc) {}

		    //Change the title of the current category to the new value
            ((CategoryTreeNode) node).getCategory().setCategoryTitle(node.getUserObject().toString());
		}
		
		public void treeNodesInserted(TreeModelEvent e) {
		}
		
		public void treeNodesRemoved(TreeModelEvent e) {

		}
		
		public void treeStructureChanged(TreeModelEvent e) {
		}

	}
}
