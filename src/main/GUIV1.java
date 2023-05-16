package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.exit;

public class GUIV1 {
    public static String PackZip;
    public static void main(String[] args) throws IOException {
        FileFilter filter = new FileNameExtensionFilter(".zip", ".zip");
        JFrame frame = new JFrame("Disruption CurseRipper");
        JFrame files = new JFrame("Choose File...");
        JPanel panel = new JPanel();
        JFileChooser FileSelector = new JFileChooser();
        FileSelector.addChoosableFileFilter(filter);
        //Action Listeners for buttons
        ActionListener FileSelectButtonPressed = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            files.add(FileSelector);
            files.setSize(1000, 1000);
            files.setEnabled(true);
            files.setVisible(true);
            PackZip = FileSelector.getSelectedFile().getAbsolutePath();
            }
        };

        ActionListener NextButtonPressed = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!PackZip.contains(".zip"))

                {
                    System.out.println("Please select a valid .zip file");
                    exit(0);
                }

                else

                {
                    List<String> list = new ArrayList<>(Arrays.stream(args).toList());
                    String FileName = list.get(1);
                    System.out.println("Processing " + FileName + "...");
                    PackZip = FileName;
                    try {
                        runShell.executeCommands();
                    } catch (IOException | InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    runShell.ReadFile();
                }
            }
        };
        //Define buttons
        JButton FileSelectButton = new JButton("Select File...");
        JButton NextButton = new JButton("Next");
        //Add buttons to panel
        FileSelectButton.addActionListener(FileSelectButtonPressed);
        panel.add(FileSelectButton);

        NextButton.addActionListener(NextButtonPressed);
        panel.add(NextButton);

        //Textarea on Main Panel
        String newline = "\n";
        JTextArea Text = new JTextArea("Welcome to Disruption CurseRipper" + newline + "This script will extract the provided modpack.zip file and download the "+newline+"mods directly from Curseforge)");
        Text.setLineWrap(true);
        Text.setSize(500, 500);
        Color Lime = new Color(0, 255, 0);
        Text.setCaretColor(Lime);
        //Add the components to the panel
        panel.add(Text);
        panel.setVisible(true);
        //Set up the Frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(ImageIO.read(new File("src/main/resources/Icon.png")));
        frame.add(panel);
        frame.setSize(600, 150);
        frame.setVisible(true);

    }
}
