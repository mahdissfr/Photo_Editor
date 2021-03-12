package aut.ir;

import Frame.MainFrame;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        // write your code here
        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 500); // set frame size
        mainFrame.setVisible(true); // display frame
    }
}
