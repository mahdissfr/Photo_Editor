package Frame;

import Add.AddText;
import Edit.ChangeColor;
import Edit.CropImage;
import Edit.Rotate;
import com.jhlabs.image.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainFrame extends JFrame {
    private JMenuItem newItem;
    private JMenuItem openItem;
    private JMenuItem closeItem;
    private JMenuItem saveItem;
    private JMenuItem addStickersItem;
    private JMenuItem addTextItem;
    private JPanel panel;
    private JButton buttons[];
    private JButton filterButtons[];
    private BufferedImage filterPic;


    public MainFrame() {
        super("photo editor");
        buttons = new JButton[4];
        filterButtons = new JButton[10];
        panel = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        newItem = new JMenuItem("New", KeyEvent.VK_N);
        openItem = new JMenuItem("Open", KeyEvent.VK_N);
        closeItem = new JMenuItem("Close", KeyEvent.VK_N);
        saveItem = new JMenuItem("Save", KeyEvent.VK_N);
        addStickersItem = new JMenuItem("Add Stickers", KeyEvent.VK_N);
        addTextItem = new JMenuItem("Add Text", KeyEvent.VK_N);
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(closeItem);
        fileMenu.add(saveItem);
        editMenu.add(addStickersItem);
        editMenu.add(addTextItem);
        setJMenuBar(menuBar);
        MainFrameHandler openHandler = new MainFrameHandler();
        openItem.addActionListener(openHandler);
        MainFrameHandler newHandler = new MainFrameHandler();
        newItem.addActionListener(newHandler);
        MainFrameHandler saveHandler = new MainFrameHandler();
        saveItem.addActionListener(saveHandler);
        MainFrameHandler closeItemHandler = new MainFrameHandler();
        closeItem.addActionListener(closeItemHandler);
        MainFrameHandler addTextHandler = new MainFrameHandler();
        addTextItem.addActionListener(addTextHandler);
        setButtons();
    }


    private class ButtonFrameHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttons[0]) {
                Rotate rotate = new Rotate(panel);
                panel.removeAll();
                revalidate();
                try {
                    panel.add(new JLabel(new ImageIcon(rotate.rotate())));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                pack();
                repaint();
                revalidate();
            } else if (e.getSource() == buttons[1]) {
                CropImage cropImage = new CropImage();
                try {
                    cropImage.start(panel);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                repaint();
                revalidate();

            } else if (e.getSource() == buttons[2]) {
                BufferedImage input = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                panel.paint(input.getGraphics());
                Icon filterInput = ((JLabel) panel.getComponent(0)).getIcon();
                filterPic = new BufferedImage(filterInput.getIconWidth(), filterInput.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics g = filterPic.createGraphics();
                filterInput.paintIcon(null, g, 0, 0);
                g.dispose();
                panel.removeAll();
                setfilterButtons();
                revalidate();
                panel.add(new JLabel(new ImageIcon(input)));
                panel.revalidate();

            } else if (e.getSource() == buttons[3]) {
                new ChangeColor(panel);
                panel.revalidate();
            }
        }
    }

    private class FilterButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == filterButtons[0]) {
                TwirlFilter twirlFilter = new TwirlFilter();
                panel.removeAll();
                panel.revalidate();
                panel.add(new JLabel(new ImageIcon(twirlFilter.filter(filterPic, null))));
                add(panel);
                pack();
                panel.repaint();
                panel.revalidate();
            } else if (e.getSource() == filterButtons[1]) {
                InvertFilter invertFilter = new InvertFilter();
                panel.removeAll();
                panel.revalidate();
                panel.add(new JLabel(new ImageIcon(invertFilter.filter(filterPic, null))));
                panel.revalidate();
            } else if (e.getSource() == filterButtons[2]) {
                CrystallizeFilter crystallizeFilter = new CrystallizeFilter();
                panel.removeAll();
                panel.revalidate();
                panel.add(new JLabel(new ImageIcon(crystallizeFilter.filter(filterPic, null))));
                add(panel);
                pack();
                panel.repaint();
                panel.revalidate();
            } else if (e.getSource() == filterButtons[3]) {
                ChromeFilter chromeFilter = new ChromeFilter();
                panel.removeAll();
                panel.revalidate();
                panel.add(new JLabel(new ImageIcon(chromeFilter.filter(filterPic, null))));
                add(panel);
                pack();
                panel.repaint();
                panel.revalidate();
            } else if (e.getSource() == filterButtons[4]) {
                EdgeFilter edgeFilter = new EdgeFilter();
                panel.removeAll();
                panel.revalidate();
                panel.add(new JLabel(new ImageIcon(edgeFilter.filter(filterPic, null))));
                add(panel);
                pack();
                panel.repaint();
                panel.revalidate();
            } else if (e.getSource() == filterButtons[5]) {
                ThresholdFilter thresholdFilter = new ThresholdFilter();
                panel.removeAll();
                panel.revalidate();
                panel.add(new JLabel(new ImageIcon(thresholdFilter.filter(filterPic, null))));
                add(panel);
                pack();
                panel.repaint();
                panel.revalidate();
            } else if (e.getSource() == filterButtons[6]) {
                MirrorFilter mirrorFilter = new MirrorFilter();
                panel.removeAll();
                panel.revalidate();
                panel.add(new JLabel(new ImageIcon(mirrorFilter.filter(filterPic, null))));
                add(panel);
                pack();
                panel.repaint();
                panel.revalidate();
            } else if (e.getSource() == filterButtons[7]) {
                DiffuseFilter diffuseFilter = new DiffuseFilter();
                panel.removeAll();
                revalidate();
                panel.add(new JLabel(new ImageIcon(diffuseFilter.filter(filterPic, null))));
                panel.revalidate();
            } else if (e.getSource() == filterButtons[8]) {
                MarbleFilter marbleFilter = new MarbleFilter();
                panel.removeAll();
                panel.revalidate();
                panel.add(new JLabel(new ImageIcon(marbleFilter.filter(filterPic, null))));
                add(panel);
                pack();
                panel.repaint();
                panel.revalidate();
            } else if (e.getSource() == filterButtons[9]) {
                SolarizeFilter solarizeFilter = new SolarizeFilter();
                panel.removeAll();
                revalidate();
                panel.add(new JLabel(new ImageIcon(solarizeFilter.filter(filterPic, null))));
                add(panel);
                pack();
                panel.repaint();
                panel.revalidate();
            }
        }
    }

    private class MainFrameHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == newItem) {
                panel.removeAll();
                NewImage newFrame = new NewImage();
                setButtons();
                panel = newFrame;
            } else if (e.getSource() == openItem) {
                panel.removeAll();
                OpenImage openImage = new OpenImage();
                setButtons();
                panel = openImage;
            } else if (e.getSource() == closeItem) {
                System.exit(0);

            } else if (e.getSource() == saveItem) {
                panel = new SaveImage();
                revalidate();

            } else if (e.getSource() == addStickersItem) {
                revalidate();
            } else if (e.getSource() == addTextItem) {
                new AddText(panel);
                panel.revalidate();
            }
            add(panel);
            revalidate();
        }
    }

    private void setButtons() {
        panel.setLayout(new GridLayout(1, buttons.length));
        buttons[0] = new JButton("Rotate");
        ButtonFrameHandler rotateHandler = new ButtonFrameHandler();
        buttons[0].addActionListener(rotateHandler);
        panel.add(buttons[0]);
        buttons[1] = new JButton("Crop");
        ButtonFrameHandler cropHandler = new ButtonFrameHandler();
        buttons[1].addActionListener(cropHandler);
        panel.add(buttons[1]);
        buttons[2] = new JButton("Filter");
        ButtonFrameHandler filterHandler = new ButtonFrameHandler();
        buttons[2].addActionListener(filterHandler);
        panel.add(buttons[2]);
        buttons[3] = new JButton("Color");
        ButtonFrameHandler colorHandler = new ButtonFrameHandler();
        buttons[3].addActionListener(colorHandler);
        panel.add(buttons[3]);
        add(panel, BorderLayout.SOUTH);
    }

    private void setfilterButtons() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(1, filterButtons.length));
        filterButtons[0] = new JButton("TwirlFilter");
        FilterButtonHandler TwirlFilterHandler = new FilterButtonHandler();
        filterButtons[0].addActionListener(TwirlFilterHandler);
        filterPanel.add(filterButtons[0]);
        filterButtons[1] = new JButton("InvertFilter");
        FilterButtonHandler InvertFilterHandler = new FilterButtonHandler();
        filterButtons[1].addActionListener(InvertFilterHandler);
        filterPanel.add(filterButtons[1]);
        filterButtons[2] = new JButton("CrystallizeFilter");
        FilterButtonHandler CrystallizeFilterHandler = new FilterButtonHandler();
        filterButtons[2].addActionListener(CrystallizeFilterHandler);
        filterPanel.add(filterButtons[2]);
        filterButtons[3] = new JButton("ChromeFilter");
        FilterButtonHandler ChromeFilterHandler = new FilterButtonHandler();
        filterButtons[3].addActionListener(ChromeFilterHandler);
        filterPanel.add(filterButtons[3]);
        filterButtons[4] = new JButton("EdgeFilter");
        FilterButtonHandler EdgeFilterHandler = new FilterButtonHandler();
        filterButtons[4].addActionListener(EdgeFilterHandler);
        filterPanel.add(filterButtons[4]);
        filterButtons[5] = new JButton("ThresholdFilter");
        FilterButtonHandler LookupFilterHandler = new FilterButtonHandler();
        filterButtons[5].addActionListener(LookupFilterHandler);
        filterPanel.add(filterButtons[5]);
        filterButtons[6] = new JButton("MirrorFilter");
        FilterButtonHandler MirrorFilterHandler = new FilterButtonHandler();
        filterButtons[6].addActionListener(MirrorFilterHandler);
        filterPanel.add(filterButtons[6]);
        filterButtons[7] = new JButton("DiffuseFilter");
        FilterButtonHandler CurvesFilterHandler = new FilterButtonHandler();
        filterButtons[7].addActionListener(CurvesFilterHandler);
        filterPanel.add(filterButtons[7]);
        filterButtons[8] = new JButton("MarbleFilter ");
        FilterButtonHandler MarbleFilterHandler = new FilterButtonHandler();
        filterButtons[8].addActionListener(MarbleFilterHandler);
        filterPanel.add(filterButtons[8]);
        filterButtons[9] = new JButton("SolarizeFilter");
        FilterButtonHandler solarizeFilter = new FilterButtonHandler();
        filterButtons[9].addActionListener(solarizeFilter);
        filterPanel.add(filterButtons[9]);
        add(filterPanel, BorderLayout.NORTH);

    }
}
