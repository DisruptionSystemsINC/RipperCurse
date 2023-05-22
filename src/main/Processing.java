package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.runShell.ModListContent;

public class Processing {
    public static ArrayList<String> SplitContent = new ArrayList<>();
    public static String FileID;
    public static String ProjectID;


    public static void Processor() throws IOException, InterruptedException {

        String newline = "\n";
        System.out.println(ModListContent);
        int startIdx = -1;
        for (int i = 0; i < ModListContent.size(); i++) {
            if (ModListContent.get(i).trim().equals("\"files\": [")) {
                SplitContent.add(ModListContent.get(i));
                startIdx = i + 1;
            }

            else if (ModListContent.toString().contains("\"downloadUrl\":")){
                SplitContent.add(ModListContent.get(i));
                startIdx = i + 1;
            }

            else  {
                SplitContent.add(Arrays.toString(ModListContent.get(i).split(",")));
                startIdx = i + 1;
            }
        }
        if (startIdx == -1) {
            System.out.println("Error: Could not find start of 'files' array");
            return;
        }


        // Combine the elements of the input list from the start of the "files" array to the end into a single string
        String input = String.join("", SplitContent.subList(0, ModListContent.size()));
        System.out.println(input);
        Pattern pattern = Pattern.compile("\"(?:projectID|fileID)\":\\s*(\\d+)");

        // Find all matches of the pattern in the input string and extract the digits
        Matcher matcher = pattern.matcher(input);
        List<Integer> projectIDs = new ArrayList<>();
        List<Integer> fileIDs = new ArrayList<>();
        while (matcher.find()) {
            int fieldValue = Integer.parseInt(matcher.group(1));
            if (matcher.group().startsWith("\"projectID\"")) {
                projectIDs.add(fieldValue);
            } else if (matcher.group().startsWith("\"fileID\"")) {
                fileIDs.add(fieldValue);
            }
        }

        // Download the mods
        for (int i = 0; i < projectIDs.size(); i++) {
            FileID = fileIDs.get(i).toString();
            ProjectID = projectIDs.get(i).toString();
            download.DownloadMod();
        }
        System.out.println("All done! Install forge and set the game directory to this directory, and you're ready to go!");
    }
}
