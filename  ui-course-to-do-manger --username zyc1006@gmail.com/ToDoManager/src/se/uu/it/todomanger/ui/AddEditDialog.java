/* TBD by Bjoern and Sara */

package se.uu.it.todomanger.ui;

import javax.swing.JFrame;

import se.uu.it.todomanger.model.Task;

/** 
 * @author bjorn
 * @author sara
 */
public class AddEditDialog extends JFrame {
	
	private Task task;
	
	// Constructor for adding new item.
	public AddEditDialog() {
		throw new UnsupportedOperationException("Not implemented.");
	}
	
	// Constructor for editing existing item.
	public AddEditDialog(Task editTask) {
		throw new UnsupportedOperationException("Not implemented.");
	}
	
	// or just a creator that creates the window, and then call with ShowEdit(Task)
	// and add by calling ShowAdd()? might be better... I don't know.
	
}