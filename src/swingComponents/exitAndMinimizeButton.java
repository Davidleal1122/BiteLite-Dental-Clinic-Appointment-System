package swingComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Main;

public class exitAndMinimizeButton extends JPanel{

	int x, y;
	Color minColor, effectColorExit, exitColor, effectColorMin;
	boolean changeColor1, changeColor2, showExit, showMin;
	JFrame frame;
	
	public exitAndMinimizeButton(int x, int y, Color exitColor, Color minColor, Color effectColorExit, Color effectColorMin) {
		
		this.x = x;
		this.y = y;
		this.exitColor = exitColor;
		this.effectColorExit = effectColorExit;
		this.effectColorMin = effectColorMin;
		this.minColor = minColor;
		
		
		setBounds(x, y, 100, 40);
		setLayout(null);
		setOpaque(false);
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				int width = getWidth();
				int height = getHeight();
				
				Ellipse2D minimize = new Ellipse2D.Double(width /2 - 20, height/2 - 5, 12, 12);
				Ellipse2D exit = new Ellipse2D.Double(width / 2, height/2 - 5, 12, 12);
				
				if(minimize.contains(e.getPoint())) {
					changeColor1 = true;
					showMin = true;
					repaint();
				}
				else if(exit.contains(e.getPoint())) {
					changeColor2 = true;
					showExit = true;
					repaint();
				}
				else {
					changeColor1 = false;
					changeColor2 = false;
					showMin = false;
					showExit = false;
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int width = getWidth();
				int height = getHeight();
				
				Ellipse2D circle1 = new Ellipse2D.Double(width /2 - 20, height/2 - 5, 12, 12);
				Ellipse2D circle2 = new Ellipse2D.Double(width / 2, height/2 - 5, 12, 12);
				
				if(circle1.contains(e.getPoint())) {
					
				}
				else if(circle2.contains(e.getPoint())) {
					Main main = new Main();
//					JOptionPaneExit JExit = new JOptionPaneExit(main);
//					JExit.setVisible(true);
				}
				else {
				}
			}
		});
		
		
	}
	protected void paintComponent(Graphics g) {
		
		int width = getWidth();
		int height = getHeight();

		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(changeColor1 ? effectColorMin : minColor);
		g2.fillOval(width /2 - 20, height/2 - 5, 12, 12);
		g2.setColor(changeColor2 ? effectColorExit : exitColor);
		g2.fillOval(width /2, height/2 - 5, 12, 12);
		g2.setColor(showMin ? new Color(82, 82, 79) : new Color(0, 0, 0, 0));
		g2.setFont(new Font("Arial", Font.PLAIN, 20));
		g2.drawString("-", width /2 - 17, height/2 + 6);
		g2.setFont(new Font("Arial", Font.BOLD, 12));
		g2.setColor(showExit ? new Color(82, 82, 79) : new Color(0, 0, 0, 0));
		g2.drawString("x", width /2 + 3, height/2 + 4.5f);
		repaint();
		
		
		super.paintComponent(g);

	}
}
