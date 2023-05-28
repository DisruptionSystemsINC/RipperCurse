package main;


import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
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


        //Define buttons

        JButton FolderSelectButton = new JButton("Select Game folder...");
        JButton FileSelectButton = new JButton("Select File...");
        JButton NextButton = new JButton("Next");


        //Action Listeners for buttons
        ActionListener FileSelectButtonPressed = e -> {
        files.add(FileSelector);
        files.setSize(1000, 1000);
        files.setVisible(true);
        };

        ActionListener FileMinecraftfolderSelectButtonPressed = e -> {
            MCDirect.add(MincraftGameFolderSelector);
            MCDirect.setSize(1000, 1000);
            MCDirect.setVisible(true);
        };

        ActionListener FileSelectAction = e -> {
            if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)){
                PackZip = FileSelector.getSelectedFile().getName();
                PackZipPath = FileSelector.getSelectedFile().getAbsolutePath();
                files.setVisible(false);
                Text.setText(PackZip + " selected." + newline +"Press Next to start the installation" + newline + "Please wait, and do not close this Window." + newline + "If the program successfully finishes you will get a message in this Text box");
            }
            else {
                files.setVisible(false);
                Text.setText("No Modpack selected.");
            }
        };

        ActionListener FileSelectMinecraftFolderAction = e -> {
            if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)){
                MinecraftGameFolderPath = MincraftGameFolderSelector.getSelectedFile().getAbsolutePath();
                MCDirect.setVisible(false);
                Text.setText(MinecraftGameFolderPath + " selected." + newline +"select your Modpack");
            }
            else {
                MCDirect.setVisible(false);
                Text.setText("No Installation folder selected.");
            }
        };

        ActionListener NextButtonPressed = e -> {

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
                Text.setText("All Done! Open your minecraft launcher and create a new profile, selecting the game directory as your previously selected directory");
                NextButton.setEnabled(false);
                FileSelectButton.setEnabled(false);
                FolderSelectButton.setEnabled(false);
                try {
                    runShell.executeCommands();
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                runShell.ReadFile();
            }
        };

        //Add the action listener to the JFileChooser

        FileSelector.addActionListener(FileSelectAction);
        MincraftGameFolderSelector.addActionListener(FileSelectMinecraftFolderAction);


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

        //Add the components to the panel
        panel.add(Text);
        panel.setVisible(true);

        //Set up the Frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(600, 150);
        frame.setVisible(true);

    }
}
