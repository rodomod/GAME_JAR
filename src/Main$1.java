import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JSlider;
       static class Main$1 implements ActionListener {
            public void actionPerformed(ActionEvent event) {
        Robot clicker;
  try{ clicker = new Robot();
      } catch(AWTException exeption) { return;
            }
        int oldX = Main.mouseLocation.x;
        int oldY = Main.mouseLocation.y;
        Main.frame.setBackground(new Color(0, 0, 0, 0));
        clicker.mouseMove(Main.horizontalSlider.getValue(), Main.verticalSlider.getValue());
        clicker.mousePress(1024);
        clicker.mouseRelease(1024);
        Main.frame.setBackground(new Color(0, 0, 0, 1));
        clicker.mouseMove(oldX, oldY);
              }
Main$1() {
	}
 }
