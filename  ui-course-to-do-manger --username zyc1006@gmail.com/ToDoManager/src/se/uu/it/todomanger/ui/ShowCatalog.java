package se.uu.it.todomanger.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 * 
 * @author Shiyu
 */
public class ShowCatalog
{
//private static ShowCatalog tree = null;
	
	/*private ShowCatalog() 
	{
	}*/
	public JTree initJTree()
	{
		//JButton j = new JButton("asd");
		//JPanel panel = new JPanel();
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("catalog");
		DefaultMutableTreeNode sub1 = new DefaultMutableTreeNode("ALL");
		DefaultMutableTreeNode sub2 = new DefaultMutableTreeNode("Today");
		root.add(sub1);
		root.add(sub2);
		DefaultMutableTreeNode sub1_1 = new DefaultMutableTreeNode("Need to do");
		DefaultMutableTreeNode sub1_2 = new DefaultMutableTreeNode("Have done");
		sub1.add(sub1_1);
		sub1.add(sub1_2);
		JTree tree = new JTree(root);
		//JScrollPane scroll = new JScrollPane(tree);
		//panel.add(scroll);
		
		return tree;
	}
	
	//static public ToDoManagerCatalog getInstance(){
		/*if(null == tree){
			tree = new ToDoManagerCatalog();
			init();
		}*/
		//return tree;
	//}
}
