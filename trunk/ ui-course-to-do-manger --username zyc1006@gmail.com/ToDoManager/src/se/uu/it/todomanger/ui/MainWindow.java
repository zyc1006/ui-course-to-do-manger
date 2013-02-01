/**
 * A test main window, will be modified later
 */
package se.uu.it.todomanger.ui;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Edward
 *
 */
public class MainWindow extends JFrame {

	//ContentPane
	Container container = null;
	
	//Default constructor
	public MainWindow(){
		container = this.getContentPane();
		container.setLayout(new GridLayout());
		JLabel label = new JLabel("This is a JLabel",JLabel.CENTER);
		label.setFont(new Font("SANS", Font.PLAIN, 22));
		//Add label to container
		container.add(label);
		setSize(500, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	public static void main(String[] args){
		MainWindow window = new MainWindow();
                // Bjšrn was here!
		
	}
	

}
