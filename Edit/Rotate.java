package Edit;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Rotate extends JPanel {
    private BufferedImage input;
    private BufferedImage output;
    private String angelQuestion;
    public Rotate(JPanel panel){
        super();
        Icon icon = ((JLabel) panel.getComponent(0)).getIcon();
        input = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = input.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        angelQuestion = JOptionPane.showInputDialog("Enter a desirable angle");}

    public BufferedImage rotate() throws IOException {
        output = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = output.createGraphics();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Integer.parseInt(angelQuestion)*1.0*Math.PI/180 , input.getWidth() / 2, input.getHeight() / 2);
        g2d.setTransform(affineTransform);
        g2d.drawImage(input, 0, 0, this);
        g2d.dispose();
        return output;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(input.getWidth() * 2, input.getHeight());
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics.create();
        int x = (getWidth() - (input.getWidth() * 2)) / 2;
        int y = (getHeight() - input.getHeight()) / 2;
        g2d.drawImage(input, x, y, this);
        g2d.drawImage(output, x + input.getWidth(), y, this);
        g2d.dispose();
    }
}