package Frame;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class OpenImage extends JPanel {
    OpenImage(){
        BufferedImage image = null;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            try{
                image =ImageIO.read(file);

            }catch(IOException e){
                System.out.println("IOException : "+e);
            }
            assert image != null;
            JLabel picLabel = new JLabel(new ImageIcon(image));
            add(picLabel);

        }
    }

}
