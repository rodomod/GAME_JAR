import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JSlider;
     public class CursorPanel extends JPanel {
public CursorPanel() {
      setOpaque(false);
  try { cursorImage = ImageIO.read(Main.getClassLoader().getResource("Crosshair.png"));
    } catch(IOException e) { return; } requestFocus(); 
 }
protected void paintComponent(Graphics g)  {
        super.paintComponent(g);
        Graphics2D pen = (Graphics2D)g.create();
        pen.drawImage(cursorImage, Main.horizontalSlider.getValue() - 8, Main.verticalSlider.getValue() - 8, this);
        pen.dispose();
          }
static BufferedImage cursorImage = null;
     }
