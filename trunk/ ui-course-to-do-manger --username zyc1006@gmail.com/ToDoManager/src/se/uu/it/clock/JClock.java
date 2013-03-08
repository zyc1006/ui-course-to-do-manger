/**
 * 
 */
package se.uu.it.clock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

import se.uu.it.todomanger.ui.CalendarPanel;


/**
 * @author bjorn
 *
 */
public class JClock extends JButton implements ActionListener {

	/**
	 * The UI class ID string.
	 */
	private static final String uiClassID = "JClock";

	private int drawWidth = 0;
	private int drawHeight = 0;
	private Color bgColor = Color.BLACK;
	private Color textColor = Color.WHITE;
	private SimpleDateFormat dateFormat;
	private Font clockFont;
	private FontMetrics metrics;
	private Timer redrawTimer;
	
	/**
	 * Creates a new clock with font font, date format sdf and redrawing interval redrawDelay milliseconds.
	 * @param font
	 * @param sdf
	 * @param redrawDelay
	 */
	public JClock(Font font, SimpleDateFormat sdf, int redrawDelay) {
		
		clockFont = font;
		metrics = this.getFontMetrics(clockFont);

		dateFormat = sdf;
		
		drawWidth = metrics.stringWidth(dateFormat.format(new Date())) + 8;
		drawHeight = metrics.getMaxAscent() + 8;

		setSize(new Dimension(drawWidth + 1, drawHeight + 1));
		setPreferredSize(new Dimension(drawWidth + 1, drawHeight + 1));
		

		redrawTimer = new Timer(redrawDelay, this);
		redrawTimer.start();
	}
	

	/**
	 * Repaints the clock on timer clicks.
	 */
	public void actionPerformed(ActionEvent arg0) {
		paint(this.getGraphics());
	}
	
	/**
	 * Sets the background color to c.
	 */
	public void setBackground(Color c) {
		bgColor = c;
	}
	
	/**
	 * Gets the background color.
	 */
	public Color getBackground() {
		return bgColor;
	}
	
	/**
	 * Sets the text color to c.
	 */
	public void setForeground(Color c) {
		textColor = c;
	}
	
	/**
	 * Gets the text color.
	 */
	public Color getForeground() {
		return textColor;
	}
	
	/**
	 * Sets the repainting interval to delay miliseconds.
	 * @param delay
	 */
	public void setRedrawDelay(int delay) {
		redrawTimer.setDelay(delay);
	}
	
	/**
	 * Sets the date format to sdf.
	 * @param sdf
	 */
	public void setDateFormat(SimpleDateFormat sdf) {
		dateFormat = sdf;
	}
	
	/**
	 * Gets the date format.
	 * @return The date format of the clock.
	 */
	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}
	
	/**
	 * Draw the component.
	 */
	public void paint(Graphics g) {
		
		g.setFont(clockFont);
		
		g.setColor(bgColor);
		g.fillRect(0, 0, drawWidth, drawHeight);
		
		g.setColor(textColor);
		g.drawString(dateFormat.format(new Date()), 4, metrics.getMaxAscent());
		
		System.out.println("paint");
		
	}
	
	
	
	
	
	
	
	
	private static JClock hej;
	private static JClock hej2;
	private static JClock hej3;
	private static JClock hej4;
	public static void main(String[] args) {
		
		
		hej = new JClock(new Font("Helvetica", 0, 72), new SimpleDateFormat("hh:mm"), 1000);
		hej2 = new JClock(new Font("Courier", 0, 72), new SimpleDateFormat("yyyy-MM-dd"), 1000);
		hej3 = new JClock(new Font("Symbol", 0, 72), new SimpleDateFormat("HH:mm:ss"), 1000);
		hej4 = new JClock(new Font("Times", 0, 72), new SimpleDateFormat("ss"), 1000);
		hej.setBackground(Color.BLACK);
		hej.setForeground(Color.GREEN);
		hej2.setBackground(Color.RED);
		hej2.setForeground(Color.YELLOW);
		hej3.setBackground(Color.ORANGE);
		hej3.setForeground(Color.BLUE);
		hej4.setBackground(Color.WHITE);
		hej4.setForeground(Color.PINK);
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.add(hej);
		panel.add(hej2);
		panel.add(hej3);
		panel.add(hej4);
		frame.add(panel);
		
		hej.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				hej.setBackground(Color.YELLOW);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				hej.setBackground(Color.BLACK);
			}
		});
		
		frame.pack();
		frame.setVisible(true);

		while(1 > 0) {
			
		}
		
	}

	
}
