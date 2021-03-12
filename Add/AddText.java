package Add;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AddText extends JFrame {
    public AddText(JPanel panel) {
        String textStr = JOptionPane.showInputDialog("Enter a desirable text");
        Font f = new Font("Serif", Font.BOLD, 12);
        JLabel text = new JLabel(textStr);
        text.setFont(f);

        MediaTracker mt = new MediaTracker(this);
        Image image = Toolkit.getDefaultToolkit().createImage(textStr + ".jpg");
        mt.addImage(image, 0);
        try {
            mt.waitForID(0);
        } catch (InterruptedException ie) {
            System.out.println("InterruptedException"+ie);
        }
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bimg.createGraphics().drawImage(image, 0, 0, this);
        bimg.getGraphics().setFont(f);
        bimg.getGraphics().drawString(textStr, 250, 100);
        panel.add(new JLabel(new ImageIcon(bimg)));

    }
}
