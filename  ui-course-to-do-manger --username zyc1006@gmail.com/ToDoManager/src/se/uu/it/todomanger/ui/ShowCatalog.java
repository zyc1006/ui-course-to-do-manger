package se.uu.it.todomanger.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import se.uu.it.todomanger.controller.CategoryManager;
import se.uu.it.todomanger.model.Category;
import se.uu.it.todomanger.model.CategoryTreeNode;

/**
 *  A class that display the categories
 * @author Sara
 */

public class ShowCatalog  {
	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	private JPanel panel;
	private JTree tree;
	private JButton addButton;
	private JButton removeButton;
	
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
		addButton = new JButton("Add Cateogry");
	    gc.weightx = 0.5;
	    gc.gridx = 0;
	    gc.gridy = 0;
		setAddButtonListener(addButton);
		gridbagLayoutSettings.setConstraints(addButton, gc);
		addButton.setMargin(new Insets(2,0,2,0));
	    panel.add(addButton);

		//Init Remove Button 
	    removeButton = new JButton("Delete Category");
	    gc.gridx = 2;
	    gc.gridy = 0;
		setRemoveButtonListener(addButton);
		gridbagLayoutSettings.setConstraints(removeButton, gc);
		removeButton.setMargin(new Insets(2,0,2,0));
	    panel.add(removeButton);

		//Init tree
		root = new DefaultMutableTreeNode("All");
		model = new DefaultTreeModel(root);
	    tree = new JTree(model);
		tree.setEditable(true);
	    tree.setSelectionRow(0);
	    model.addTreeModelListener(new CategoryTreeModelListener());
        gc.weightx = 0.0;
        gc.weighty = 1.0;
        gc.gridwidth = 3;
        gc.gridx = 0;
        gc.gridy = 1;
		gridbagLayoutSettings.setConstraints(tree, gc);
	    panel.add(tree);
	
		return panel;
	}
	
	
	/**
	 * Handles the add button
	 * 
	 * @author sara
	 */
	
	private void setAddButtonListener(JButton addButton) {
		addButton.addActionListener(new ActionListener() {
    	
			public void actionPerformed(ActionEvent event) {
	    	  //Get the selected node
	    	  //DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) tree .getLastSelectedPathComponent();
	    	  
	    	  //Create the new category
	    	  //if (selNode != null) {
		          CategoryTreeNode newNode = new CategoryTreeNode("New Category");
		          model.insertNodeInto(newNode, root, root.getChildCount());
		          TreeNode[] nodes = model.getPathToRoot(newNode);
		          TreePath path = new TreePath(nodes);
		          tree.scrollPathToVisible(path);
		          tree.setSelectionPath(path);
		          tree.startEditingAtPath(path);
		         
		          Category nc = new Category(Category.nextCategoryId, newNode.getUserObject().toString());
		          newNode.setCategory(nc);
		          Category.nextCategoryId++;
		          
		          CategoryManager cm = CategoryManager.getInstance();
		          cm.addCategory(nc);
	    	  }
	      //}
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
				//Get the selected node
				DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
					
				System.out.print("selNode: " + selNode.getUserObject().toString());
			    	if (selNode != null && !selNode.equals(root)) {
			    		root.remove(selNode);
				          
				        //CategoryManager cm = CategoryManager.getInstance();
				        //cm.deleteCategory(selNode.getCategory());
			    	}
				 
			}
		});
	
	}
	
	
	/**
	 * Handles changes in treenodes
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
		    //Get current selected node
			DefaultMutableTreeNode node;
		    node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());

		    //Try if a node has been selected
		    try {
		        int index = e.getChildIndices()[0];
		        node = (CategoryTreeNode) (node.getChildAt(index));
		    } catch (NullPointerException exc) {}

	        CategoryManager cm = CategoryManager.getInstance();
	        cm.deleteCategory(((CategoryTreeNode) node).getCategory());
		}
		
		public void treeStructureChanged(TreeModelEvent e) {
		}

	}
}
