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


public class GUIV1 {
    public static String PackZipPath;
    public static String PackZip;
    public static String MinecraftGameFolderPath;

    public static void main(String[] args) throws IOException {
        String newline = "\n";
        JTextArea Text = new JTextArea("Welcome to Disruption CurseRipper" + newline + "This script will extract the provided modpack.zip file and download the "+newline+"mods directly from Curseforge");
        FileFilter filter = new FileNameExtensionFilter(".zip", "zip");
        JFrame frame = new JFrame("Disruption CurseRipper");
        JFrame files = new JFrame("Choose File...");
        JFrame MCDirect = new JFrame("Choose Game Directory...");
        JPanel panel = new JPanel();
        JFileChooser FileSelector = new JFileChooser();
        FileSelector.addChoosableFileFilter(filter);
        FileSelector.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileSelector.setFileHidingEnabled(false);
        JFileChooser MincraftGameFolderSelector = new JFileChooser();
        MincraftGameFolderSelector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        MincraftGameFolderSelector.setFileHidingEnabled(false);

        //Action Listeners for buttons
        ActionListener FileSelectButtonPressed = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            files.add(FileSelector);
            files.setSize(1000, 1000);
            files.setVisible(true);
            }
        };

        ActionListener FileMinecraftfolderSelectButtonPressed = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MCDirect.add(MincraftGameFolderSelector);
                MCDirect.setSize(1000, 1000);
                MCDirect.setVisible(true);
            }
        };

        ActionListener FileSelectAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)){
                    PackZip = FileSelector.getSelectedFile().getName();
                    PackZipPath = FileSelector.getSelectedFile().getAbsolutePath();
                    files.setVisible(false);
                    Text.setText(PackZip + " selected." + newline +"Press Next to start the installation");
                }
                else {
                    files.setVisible(false);
                    Text.setText("No Modpack selected.");
                }
            }
        };

        ActionListener FileSelectMinecraftFolderAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)){
                    MinecraftGameFolderPath = MincraftGameFolderSelector.getSelectedFile().getAbsolutePath();
                    MCDirect.setVisible(false);
                    Text.setText(MinecraftGameFolderPath + " selected." + newline +"select your Modpack");
                }
                else {
                    MCDirect.setVisible(false);
                    Text.setText("No Installation folder selected.");
                }
            }
        };

        ActionListener NextButtonPressed = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (PackZip == null)
                {
                    System.out.println("Please select a file first!");
                    Text.setText("Please select a file first!");
                    Text.setCaretColor(Color.RED);
                }
                else if (!PackZip.contains(".zip"))

                {
                    Text.setText("Please select a valid .zip file!");
                }

                else

                {
                    Text.setText("Processing" + PackZip + "...");
                    try {
                        runShell.executeCommands();
                    } catch (IOException | InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    runShell.ReadFile();
                }
            }
        };

        //Add the action listener to the JFileChooser

        FileSelector.addActionListener(FileSelectAction);
        MincraftGameFolderSelector.addActionListener(FileSelectMinecraftFolderAction);

        //Define buttons

        JButton FolderSelectButton = new JButton("Select Game folder...");
        JButton FileSelectButton = new JButton("Select File...");
        JButton NextButton = new JButton("Next");

        //Add buttons to panel

        FolderSelectButton.addActionListener(FileMinecraftfolderSelectButtonPressed);
        panel.add(FolderSelectButton);
        FileSelectButton.addActionListener(FileSelectButtonPressed);
        panel.add(FileSelectButton);

        NextButton.addActionListener(NextButtonPressed);
        panel.add(NextButton);

        //Textarea on Main Panel
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
