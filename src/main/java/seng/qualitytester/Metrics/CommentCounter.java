package seng.qualitytester.Metrics;
//Created By Sina Sabetfar
//Email: C3382615@uon.edu.au

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;

import java.io.File;
import java.io.IOException;


//Counts the comments
public class CommentCounter {

    public static int countComments(String fileName) {
        try {
            File file = new File(fileName);
            CompilationUnit compilationUnit = StaticJavaParser.parse(file);

            int commentCount = 0;
            for (Comment ignored : compilationUnit.getAllComments()) {
                commentCount++;
            }
            return commentCount;
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
            e.printStackTrace();
            return -1;
        }
    }
}
