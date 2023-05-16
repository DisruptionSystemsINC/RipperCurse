package main;

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.exit;
import static main.GUIV1.MinecraftGameFolderPath;
import static main.GUIV1.PackZipPath;

public class runShell {
    public static ArrayList<String> ModListContent = new ArrayList<>();
    public static void executeCommands() throws IOException, InterruptedException {
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
        printWriter.println("cd "+ MinecraftGameFolderPath);
        printWriter.println("unzip " + PackZipPath);
        printWriter.println("cd overrides");
        printWriter.println("cp -r * ..");
        printWriter.println("cd ..");
        printWriter.println("mkdir mods");
        printWriter.println("rm -rf overrides");




        printWriter.close();

        return tempScript;
    }

    public static void ReadFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("manifest.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                ModListContent.add(line);
            }
            Processing.Processor();

            } catch(FileNotFoundException e){
                System.out.println("manifest.json not Found, Is it present?");
                exit(404);
            } catch(IOException | InterruptedException e){
                throw new RuntimeException(e);
            }
    }
    }
