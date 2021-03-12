package Edit;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ChangeColor extends JFrame {
    private JSlider r_slide, b_slide, g_slide;
    private JPanel panel;
    public BufferedImage input;
    public BufferedImage output;

    public ChangeColor(JPanel panel) {
        super("Using the Slider Control - Colors");
        this.panel = panel;
        input = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        panel.paint(input.getGraphics());
        output = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        JLabel r_label = new JLabel("R Value:");
        container.add(r_label);

        r_slide = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 205);
        r_slide.setMinorTickSpacing(5);
        r_slide.setMajorTickSpacing(50);
        r_slide.setPaintTicks(true);
        r_slide.setPaintLabels(true);
        container.add(r_slide);

        JLabel g_label = new JLabel("G Value:");
        container.add(g_label);

        g_slide = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 205);
        g_slide.setMinorTickSpacing(5);
        g_slide.setMajorTickSpacing(50);
        g_slide.setPaintTicks(true);
        g_slide.setPaintLabels(true);
        container.add(g_slide);

        JLabel b_label = new JLabel("B Value:");
        container.add(b_label);

        b_slide = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 205);
        b_slide.setMinorTickSpacing(5);
        b_slide.setMajorTickSpacing(50);
        b_slide.setPaintTicks(true);
        b_slide.setPaintLabels(true);
        container.add(b_slide);

        Color color = new Color(r_slide.getValue(), g_slide.getValue(), b_slide.getValue());

        container.setBackground(color);
        setSize(300, 200);
        setVisible(true);
        ChangeStateHandler handler = new ChangeStateHandler();
        r_slide.addChangeListener(handler);
        g_slide.addChangeListener(handler);
        b_slide.addChangeListener(handler);
    }


    private class ChangeStateHandler implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            for (int y = 0; y < panel.getHeight(); y++)
                for (int x = 0; x < input.getWidth(); x++) {
                    int pixel = input.getRGB(x , y);
                    int red = (pixel >> 16) & r_slide.getValue();
                    int alpha = (pixel >> 24);
                    int green = (pixel >> 8) & g_slide.getValue();
                    int blue = (pixel) & b_slide.getValue();
                    int newPixel = (alpha << 24) | (red << 16) | (green << 8) | (blue);
                    output.setRGB(x, y, newPixel );
                }
            panel.removeAll();
            panel.revalidate();
            panel.add(new JLabel(new ImageIcon(output)));
            panel.repaint();
            panel.revalidate();
        }
    }
}