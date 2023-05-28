package main;

import java.io.*;

import static main.Processing.*;

public class download {
    private static Process process;

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
        printWriter.println("cd mods");
        printWriter.println("wget --content-disposition " + "https://www.curseforge.com/api/v1/mods/" + ProjectID + "/files/" + FileID + "/download");

        printWriter.close();

        return tempScript;
    }

    public static void DownloadForge() throws IOException, InterruptedException {
        File tempScript2 = createTempScript2();

        try {
            ProcessBuilder pb2 = new ProcessBuilder("bash", tempScript2.toString());
            pb2.inheritIO();
            process = pb2.start();
            process.waitFor();

        } finally {
            String error = process.getErrorStream().toString();
            if (error == null) {
                tempScript2.delete();
            }
            else {
                System.out.println("WARNING, MINECRAFT VERSION NOT FOUND IN JSON.\n Manual forge install Required");
                tempScript2.delete();
            }
            }
    }

    public static File createTempScript2() throws IOException {
        File tempScript2 = File.createTempFile("tmp", "Disrupt");
        Writer streamWriter = new OutputStreamWriter(new FileOutputStream(tempScript2));
        PrintWriter printWriter = new PrintWriter(streamWriter);
        String ForgeIDsrt = ForgeID.replace("forge-", "");
        System.out.println(ForgeID);

            printWriter.println("#!/bin/bash");
            printWriter.println("wget --content-disposition " + "//https://maven.minecraftforge.net/net/minecraftforge/forge/" + ForgeIDsrt + "/forge-" + ForgeIDsrt + "-installer.jar");
            printWriter.println("java -jar " + ForgeIDsrt + "-installer.jar");

            printWriter.close();

            return tempScript2;
        }
    }





//https://maven.minecraftforge.net/net/minecraftforge/forge/1.19.3-44.1.23/forge-1.19.3-44.1.23-installer.jar