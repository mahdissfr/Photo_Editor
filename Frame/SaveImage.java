package Frame;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class SaveImage extends JPanel {
    SaveImage()
    {
        try
        {
            JFileChooser chooseDirec = new JFileChooser();
            chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooseDirec.showSaveDialog(SaveImage.this);
            File file = chooseDirec.getSelectedFile();
            file = new File(file+".png");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.close();
        }
        catch (IOException exception)
        {
            System.err.println("Error saving to new file.");
        }
        }
    }
