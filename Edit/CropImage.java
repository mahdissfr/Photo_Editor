package Edit;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;

public class CropImage extends JFrame implements MouseListener, MouseMotionListener {
    private JPanel panel;
    private int drag_status = 0, c1, c2, c3, c4;

    public void start(JPanel panel) throws IOException {
        this.panel = panel;
        Icon icon = ((JLabel) panel.getComponent(0)).getIcon();
        BufferedImage input = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = input.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);

    }

    private void draggedScreen() throws Exception {
        int w = c1 - c3;
        int h = c2 - c4;
        w = w * -1;
        h = h * -1;
        Robot robot = new Robot();
        BufferedImage img = robot.createScreenCapture(new Rectangle(c1, c2, w, h));
        panel.removeAll();
        panel.revalidate();
        panel.add(new JLabel(new ImageIcon(img)));
        panel.repaint();
        panel.revalidate();
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        panel.repaint();
        c1 = arg0.getX();
        c2 = arg0.getY();
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        panel.repaint();
        if (drag_status == 1) {
            c3 = arg0.getX();
            c4 = arg0.getY();
            try {

                draggedScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        panel.repaint();
        drag_status = 1;
        c3 = arg0.getX();
        c4 = arg0.getY();
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {

    }

    public void paint(Graphics g) {
        super.paint(g);
        int w = c1 - c3;
        int h = c2 - c4;
        w = w * -1;
        h = h * -1;
        if (w < 0)
            w = w * -1;
        g.drawRect(c1, c2, w, h);
    }

}




