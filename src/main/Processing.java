package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.runShell.ModListContent;

public class Processing {
    public static String FileID;
    public static String ProjectID;

    public static void Processor() throws IOException, InterruptedException {


        System.out.println(ModListContent);
        int startIdx = -1;
        for (int i = 0; i < ModListContent.size(); i++) {
            if (ModListContent.get(i).trim().equals("\"files\": [")) {
                startIdx = i + 1;
                break;
            }
        }
        if (startIdx == -1) {
            System.out.println("Error: Could not find start of 'files' array");
            return;
        }

        // Combine the elements of the input list from the start of the "files" array to the end into a single string
        String input = String.join("", ModListContent.subList(startIdx, ModListContent.size()));

        // Regex pattern to match the "projectID" and "fileID" fields and capture the digits
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
    }
}
