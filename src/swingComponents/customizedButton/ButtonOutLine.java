package swingComponents.customizedButton;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder; 
 
public class ButtonOutLine extends JButton { 
 
 protected Timer timer; 
 protected boolean mouseOver; 
 private float alpha = 0.1f; 
  
    public ButtonOutLine() { 
        setContentAreaFilled(false); 
        setBorder(new EmptyBorder(5, 0, 5, 0)); 
        setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        setFocusable(false); 
        setForeground(Color.white); 
        timer = new Timer(40, new ActionListener() { 
 
   @Override 
   public void actionPerformed(ActionEvent e) { 
    if(mouseOver) { 
     if(alpha < 0.6f) { 
      alpha += 0.05f; 
      repaint(); 
     } 
     else { 
      alpha = 0.6f; 
      timer.stop(); 
      repaint(); 
     } 
    }else { 
     if(alpha > 0.3f) { 
      alpha -= 0.05f; 
      repaint(); 
     }else { 
      alpha = 0.3f; 
      timer.stop(); 
      repaint(); 
     } 
    } 
     
   } 
          
          
        }); 
    } 
 
    @Override 
    protected void paintComponent(Graphics grphcs) { 
        int width = getWidth(); 
        int height = getHeight(); 
        Graphics2D g2 = (Graphics2D) grphcs; 
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        g2.setColor(Color.white); 
        g2.drawRoundRect(0, 0, width - 1, height - 1, height, height); 
        super.paintComponent(grphcs); 
        createStyles(g2); 
    } 
    protected void createStyles(Graphics2D grphcs) { 
     grphcs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha)); 
        int width = getWidth(); 
        int height = getHeight(); 
        grphcs.setPaint(new Color(255, 255, 255, 10)); 
        grphcs.fillRoundRect(0, 0, width - 1, height - 1, height, height); 
        
         
        super.paintComponent(grphcs); 
    } 
}
