package main;

import java.io.*;

import static main.Processing.FileID;
import static main.Processing.ProjectID;

public class download {

    public static void DownloadMod() throws IOException, InterruptedException {
        File tempScript = createTempScript();

        try {
            ProcessBuilder pb = new ProcessBuilder("bash", tempScript.toString());
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
        } finally {
            tempScript.delete();
        }
    }

    public static File createTempScript() throws IOException {
        File tempScript = File.createTempFile("script", null);

        Writer streamWriter = new OutputStreamWriter(new FileOutputStream(tempScript));
        PrintWriter printWriter = new PrintWriter(streamWriter);

        printWriter.println("#!/bin/bash");
        printWriter.println("cd "+GUIV1.MinecraftGameFolderPath);
        printWriter.println("cd mods");
        printWriter.println("wget --content-disposition " + "https://www.curseforge.com/api/v1/mods/" + ProjectID + "/files/" + FileID + "/download");
        printWriter.close();

        return tempScript;
    }
}
