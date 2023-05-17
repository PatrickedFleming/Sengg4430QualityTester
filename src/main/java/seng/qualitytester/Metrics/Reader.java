package seng.qualitytester.Metrics;

import java.io.*;
import java.util.*;

public class Reader {
    private final String directory;
    private final Map<String, String> javaFiles;
    private final Map<String, String> filesDir;

    public Reader(String directory) throws IOException {
        this.directory = directory;
        javaFiles = new HashMap<>();
        filesDir = new HashMap<>();
        processDirectory(new File(directory));
    }

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

    public Map<String, String> getJavaFiles() {
        return javaFiles;
    }
    public Map<String, String> getFilesDir() { return filesDir; }
}