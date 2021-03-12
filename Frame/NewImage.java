package Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


 /** Created by mahdis on 5/23/2017.**/
 class NewImage extends JPanel {
    NewImage()
    {
        Color myWhite = new Color(255, 255, 255); // Color white
        int rgb = myWhite.getRGB();
        BufferedImage img=new BufferedImage(700,700,BufferedImage.TYPE_INT_ARGB);
        try {
            for (int i = 0; i < 700; i++) {
                for (int j = 0; j < 700; j++) {
                    img.setRGB(i, j, rgb);
                }
            }
            ImageIO.write(img,"JPG",new File("new.jpg"));
        }
        catch(Exception e)
            {
                System.out.println("cannot create the file");
                e.printStackTrace();
            }

        JLabel picLabel = new JLabel(new ImageIcon(img));
        add(picLabel);
    }
}
