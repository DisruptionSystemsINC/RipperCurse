package main;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIV1 {
    public static void main(String[] args){
        FileFilter filter = new FileNameExtensionFilter(".zip");
        JFrame frame = new JFrame();
        JFrame files = new JFrame();
        JPanel panel = new JPanel();
        JButton button = new JButton();
        JFileChooser FileSelector = new JFileChooser();
        FileSelector.addChoosableFileFilter(filter);
        ActionListener ButtonPress = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            files.add(FileSelector);
            files.setSize(1000, 1000);
            files.setName("Choose your Modpack.zip");
            files.setEnabled(true);
            files.setVisible(true);
            }
        };
        button.addActionListener(ButtonPress);
        panel.add(button);
        panel.setVisible(true);
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);

    }
}
