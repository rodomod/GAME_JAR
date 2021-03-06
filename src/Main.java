import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Main {
    static CursorPanel cursorPanel = new CursorPanel();
    static BufferedImage cursorImage = null;
    static JFrame frame = new JFrame("X/Y Mouse");
    static JLayeredPane overlay = new JLayeredPane();
    static JSlider horizontalSlider = null;
    static JSlider verticalSlider = null;
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
	 
public static void main(String args[]) {
        frame.setDefaultCloseOperation(3);
        frame.setExtendedState(6);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 1));
        frame.getContentPane().setBackground(new Color(0, 0, 0, 0));
        frame.setAlwaysOnTop(true);
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", Boolean.valueOf(false));
        frame.setLayout(new BorderLayout());
        overlay.setPreferredSize(screenSize);
        frame.add(overlay);
        horizontalSlider = new JSlider(0, 8, screenSize.width - 8, screenSize.width / 2);
        horizontalSlider.setBounds(0, 0, screenSize.width, 16);
        verticalSlider = new JSlider(1, 8, screenSize.height - 8, screenSize.height / 2);
        verticalSlider.setBounds(0, 0, 16, screenSize.height);
        verticalSlider.setInverted(true);
        cursorPanel.setBounds(0, 0, screenSize.width, screenSize.height);
        overlay.add(cursorPanel, new Integer(1));
        JButton clickButton = new JButton("Click!");
        clickButton.setMargin(new Insets(0, 0, 0, 0));
        clickButton.setBounds(screenSize.width - 50, screenSize.height - 50, 50, 50);
        overlay.add(clickButton, new Integer(2));//
        frame.pack();
        frame.setVisible(true);
        boolean hSliderShown = false;
        boolean vSliderShown = false;
clickButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                Robot clicker;
             try{clicker = new Robot();
                }catch(AWTException exeption){return;}
                int oldX = Main.mouseLocation.x;
                int oldY = Main.mouseLocation.y;
                Main.frame.setBackground(new Color(0, 0, 0, 0));
                clicker.mouseMove(Main.horizontalSlider.getValue(), Main.verticalSlider.getValue());
                clicker.mousePress(1024);
                clicker.mouseRelease(1024);
                Main.frame.setBackground(new Color(0, 0, 0, 1));
                clicker.mouseMove(oldX, oldY);
                     }
});
for(boolean isRunning = true; isRunning; cursorPanel.repaint()) {
            mouseLocation = MouseInfo.getPointerInfo().getLocation();
if(mouseLocation.y == 0 && !vSliderShown && !hSliderShown) {
                overlay.add(horizontalSlider, new Integer(2));
                hSliderShown = true;
 }else if(mouseLocation.y > 16 && hSliderShown) {
                overlay.remove(horizontalSlider);
                hSliderShown = false;
            }
if(mouseLocation.x == 0 && !hSliderShown && !vSliderShown) {
                overlay.add(verticalSlider, new Integer(2));
                vSliderShown = true;
                continue;
            }
if(mouseLocation.x > 16 && vSliderShown) {
                overlay.remove(verticalSlider);
                vSliderShown = false;
            }
        }

    }
static class CursorPanel extends JPanel {
CursorPanel() { 
            setOpaque(false);
		 try{  cursorImage = ImageIO.read(Main.getClassLoader().getResource("Crosshair.png"));  //this and Game ...
		   }catch(IOException e){return;}         
		         requestFocus();
		     }
protected void paintComponent(Graphics g) {
		         super.paintComponent(g);
		         Graphics2D pen = (Graphics2D)g.create();
		 		pen.drawImage(cursorImage, Main.horizontalSlider.getValue() - 8, Main.verticalSlider.getValue() - 8, this);
		         pen.dispose();
		     } 
		}
   }