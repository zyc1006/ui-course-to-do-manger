package se.uu.it.todomanger.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import se.uu.it.todomanger.controller.CategoryManager;
import se.uu.it.todomanger.model.Category;
/**
 * 
 * @author Shiyu
 */
public class ShowCatalog  {
	
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("All");
	
	DefaultTreeModel model = new DefaultTreeModel(root);
	
	BorderLayout bl = new BorderLayout();
	JPanel panel = new JPanel(new BorderLayout());
	JTree tree = new JTree(model);
	JButton addButton = new JButton("Add Cateogry");;
	
	
	public JPanel init() {
	    addButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent event) {
		        DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) tree
		            .getLastSelectedPathComponent();
		        if (selNode != null) {
		          DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("New Node");
		          model.insertNodeInto(newNode, selNode, selNode.getChildCount());
		          TreeNode[] nodes = model.getPathToRoot(newNode);
		          TreePath path = new TreePath(nodes);
		          tree.scrollPathToVisible(path);
		          tree.setSelectionPath(path);
		          tree.startEditingAtPath(path);
		          
		          Category nc = new Category(Category.nextcategoryid, newNode.getUserObject().toString());
		          Category.nextcategoryid++;
		          
		          CategoryManager cm = CategoryManager.getInstance();
		          cm.addCategory(nc);
		        }
		      }
		    });
		    
		
	    
		tree.setEditable(true);
	    tree.setSelectionRow(0);
	    
	    
		panel.add(BorderLayout.NORTH, addButton);
		panel.add(BorderLayout.CENTER, tree);
		
		
		return panel;
	}

}
