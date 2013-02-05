/* TBD by Bjoern and Sara */

package se.uu.it.todomanger.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import se.uu.it.todomanger.controller.TaskManager;
import se.uu.it.todomanger.model.Task;

/** 
 * @author bjorn
 * @author sara
 */
public class AddEditDialog extends JDialog {
	
	private Task task;
	private JTextField txtTitle;

	//ContentPane
	Container container = null;
	
	//Default constructor
	public AddEditDialog() {
		
		// Set default title, should be changed when we know which of the
		// dialogs to be shown...
		this.setTitle("Add/Edit task");
		
		// Use the GridLayout for the dialog.
		container = this.getContentPane();
		container.setLayout(new GridBagLayout());
		//container.

		
		// A GridBagConstraints for placing the controls in the dialog.
		GridBagConstraints constraint = new GridBagConstraints();
		
		// Labels
		JLabel lblTitle = new JLabel("Title:");
		JLabel lblDueDate = new JLabel("Due date:");
		JLabel lblCategory = new JLabel("Category:");
		JLabel lblPriority = new JLabel("Priority:");
		
		// Text field for title
		txtTitle = new JTextField("Test task");
		txtTitle.setPreferredSize(new Dimension(300, 24));
		
		// Combo box for categories
		JComboBox cmbCategory = new JComboBox();
		cmbCategory.setPreferredSize(new Dimension(300, 24));
		
		// Text field for due date
		JTextField txtDueDate = new JTextField("Not a good component for dates...");
		txtDueDate.setPreferredSize(new Dimension(300, 24));
		
		// JSlider for Priority
		JSlider sliPriority = new JSlider();
		sliPriority.setPreferredSize(new Dimension(300, 48));
		sliPriority.setMinimum(0);
		sliPriority.setMaximum(2);
		sliPriority.setMajorTickSpacing(1);
		
		Dictionary<Integer, JLabel> test = new Hashtable<Integer, JLabel>();
		test.put(0, new JLabel("Low"));
		test.put(1, new JLabel("Normal"));
		test.put(2, new JLabel("High"));
		
		sliPriority.setLabelTable(test);
		sliPriority.setPaintLabels(true);
		sliPriority.setPaintTicks(true);
		
		// JButtons for OK and Cancel
		JButton btnCancel = new JButton("Cancel");
		JButton btnOK = new JButton("OK");
		
		btnCancel.setSize(50, 22);
		btnOK.setSize(50, 22);
		
		// Create the dialog
		constraint.ipadx = 5;
		constraint.ipady = 5;
		
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.anchor = constraint.EAST;
		container.add(lblTitle, constraint);
		constraint.gridx = 1;
		constraint.gridy = 0;
		constraint.anchor = constraint.WEST;
		container.add(txtTitle, constraint);

		constraint.gridx = 0;
		constraint.gridy = 1;
		constraint.anchor = constraint.EAST;
		container.add(lblCategory, constraint);
		constraint.gridx = 1;
		constraint.gridy = 1;
		constraint.anchor = constraint.WEST;
		container.add(cmbCategory, constraint);

		constraint.gridx = 0;
		constraint.gridy = 2;
		constraint.anchor = constraint.EAST;
		container.add(lblDueDate, constraint);
		constraint.gridx = 1;
		constraint.gridy = 2;
		constraint.anchor = constraint.WEST;
		container.add(txtDueDate, constraint);

		constraint.gridx = 0;
		constraint.gridy = 3;
		constraint.anchor = constraint.EAST;
		container.add(lblPriority, constraint);
		constraint.gridx = 1;
		constraint.gridy = 3;
		constraint.anchor = constraint.WEST;
		container.add(sliPriority, constraint);

		JPanel pnlButtonPanel = new JPanel();
		
		constraint.gridx = 1;
		constraint.gridy = 4;
		constraint.anchor = constraint.EAST;
		pnlButtonPanel.add(btnCancel);
		pnlButtonPanel.add(btnOK);
		container.add(pnlButtonPanel, constraint);
		
		this.setModal(true);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);	
	}
	
	public void ShowAddDialog() {
		
		this.setTitle("Add new task");
		txtTitle.setText("This is an Add dialog");
		this.setVisible(true);
		
	}
	
	public void ShowEditDialog() {
		
		this.setTitle("Edit task");
		txtTitle.setText("This is an Edit dialog");
		this.setVisible(true);
		
	}
	
	public static void main(String[] args){
		
		AddEditDialog addEdit = new AddEditDialog();
		addEdit.ShowAddDialog();
		addEdit.ShowEditDialog();
	}

	
}