package se.uu.it.todomanger.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.EtchedBorder;
import se.uu.it.todomanger.model.Task;

/**
 * 
 * A reminder window to remind user what to do now the reminder will not
 * disappear until you click it.
 * 
 * @author shiyu
 */
public class ShowRemainder extends JWindow implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer screenWidth; // the width of screen
	private Integer screenHeight; // the height of screen
	private Integer windowWidth = 300; // the width of reminder window
	private Integer windowHeight = 200; // the height of reminder window
	private Integer bottomToolKitHeight;
	private Integer sleepTime = 5000;
	private Integer x;
	private Integer y;
	private String Title = "Reminder" + "                                      ";
	private String message;
	private JPanel mainPanel;
	private JLabel titleLabel;
	private JPanel titlePanel;
	private JLabel messageLabel;
	private JPanel messagePanel;

	/**
	 * Initialize the show remainder public ShowRemainder ({@link Task} task)<br>
	 * 
	 * @param task
	 *            a task model
	 */
	public ShowRemainder(Task task) {
		initComponents(task);
		Thread thread = new Thread(this);
		thread.start();
	}

	private void initComponents(Task task) {
		message = "Hello! This is a reminder: " + task.getTitle().toString();
		bottomToolKitHeight = Toolkit.getDefaultToolkit().getScreenInsets(
				this.getGraphicsConfiguration()).bottom;
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = dimension.width;
		screenHeight = dimension.height;
		x = screenWidth - windowWidth;
		y = screenHeight;
		this.setLocation(x, y - bottomToolKitHeight - windowHeight);
		EtchedBorder etchedBorder = (EtchedBorder) BorderFactory
				.createEtchedBorder();
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(255, 255, 225));

		titleLabel = new JLabel(Title);
		// titleLabel.setForeground(new Color(255, 255, 225));
		titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		titlePanel.add(titleLabel);

		// titlePanel.setBackground(Color.RED);
		titlePanel.setBorder(etchedBorder);

		messageLabel = new JLabel(message);
		messagePanel = new JPanel();
		messagePanel.add(messageLabel);
		// messagePanel.setBackground(Color.YELLOW);
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		mainPanel.add(messagePanel, BorderLayout.CENTER);
		mainPanel.setBorder(etchedBorder);

		this.setSize(windowWidth, windowHeight);
		this.setAlwaysOnTop(true);
		this.getContentPane().add(mainPanel);
		Toolkit.getDefaultToolkit().beep(); // to notify
//		 try {
//		 AudioClip ac =
//		 Applet.newAudioClip(ShowRemainder.class.getResource("sound/" +
//		 "msg.wav"));
//		 ac.play();
//		 } //catch (MalformedURLException e1) {

//		 e1.printStackTrace();
//		 }
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		this.setVisible(true);

	}

	/**
	 * show reminder window
	 * 
	 * @Override
	 */
	public void run() {
		Integer delay = 10;
		Integer step = 1;
		Integer end = windowHeight + bottomToolKitHeight;
		while (true) {
			try {
				step++;
				y = y - 1;
				this.setLocation(x, y);
				if (step > end) {
					Thread.sleep(sleepTime);
					break;
				}
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
