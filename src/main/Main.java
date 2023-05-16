package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.exit;

public class Main {
    public static String PackZip;

    public static void main(String[] args) throws IOException, InterruptedException {
        String arguments = Arrays.toString(args);
        System.out.println(arguments);
        GUIV1.main(args);

        if (!arguments.contains("--ModFile"))

            {
            System.out.println("Please provide the filename of your downloaded zip file using --ModFile [Filename]");
            exit(0);
            }

        else

            {
            List<String> list = new ArrayList<>(Arrays.stream(args).toList());
            String FileName = list.get(1);
            System.out.println("Processing " + FileName + "...");
            PackZip = FileName;
            runShell.executeCommands();
            runShell.ReadFile();
            }
    }
}
//