package se.uu.it.todomanger.ui;

import java.awt.BorderLayout;
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
	
	//Create tree
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("All");
		
	DefaultTreeModel model = new DefaultTreeModel(root);
	
	//Init layout
	BorderLayout bl = new BorderLayout();
	JPanel panel = new JPanel(new BorderLayout());
	JTree tree = new JTree(model);
	JButton addButton = new JButton("Add Cateogry");

	//Add category button
	public JPanel init() {		
	    addButton.addActionListener(new ActionListener() {
	    	
		      public void actionPerformed(ActionEvent event) {
		    	  //Get the selected node
		    	  DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) tree .getLastSelectedPathComponent();
		    	  
	    		  //Create the new category
		    	  if (selNode != null) {
			          CategoryTreeNode newNode = new CategoryTreeNode("New Category");
			          model.insertNodeInto(newNode, selNode, selNode.getChildCount());
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
		      }
		    });
		    
		
	    //Making the nodes editable
		tree.setEditable(true);
		
		//Default select is root node
	    tree.setSelectionRow(0);
	    
	    //Keep track of changes in nodes
	    model.addTreeModelListener(new CategoryTreeModelListener());
	    
	    //Display the objects in the UI
		panel.add(BorderLayout.NORTH, addButton);
		panel.add(BorderLayout.CENTER, tree);
		
		
		return panel;
	}

	
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
