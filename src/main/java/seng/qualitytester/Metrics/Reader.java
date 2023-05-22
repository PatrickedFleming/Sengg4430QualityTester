package seng.qualitytester.Metrics;
//Created By Patrick Fleming
//Email: C3253586@uon.edu.au
import java.io.*;
import java.util.*;

/*Reader class used to recurse through directory and get all files to be tested
And stores  a map with the name of the file and the code and
A map of the file name and directory */
public class Reader {
    private final Map<String, String> javaFiles;
    private final Map<String, String> filesDir;

    //constructor
    public Reader(String directory) throws IOException {
        javaFiles = new HashMap<>();
        filesDir = new HashMap<>();
        processDirectory(new File(directory));
    }

    //recurses through directory give and populates maps with java files, code and directory
    private void processDirectory(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file);
                } else if (file.getName().endsWith(".java")) {
                    String content = readContent(file);
                    javaFiles.put(file.getName(), content);
                    filesDir.put(file.getName(), file.getAbsolutePath());
                }
            }
        }
    }

    //gets all the code from the java file and returns it as a string
    private String readContent(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }

    //getters
    public Map<String, String> getJavaFiles() {
        return javaFiles;
    }
    public Map<String, String> getFilesDir() { return filesDir; }
}