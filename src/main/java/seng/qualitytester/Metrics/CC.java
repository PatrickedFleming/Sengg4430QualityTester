package seng.qualitytester.Metrics;
//Created by Asad Ullah Awan
//Email: C3421072@uon.edu.au

//CC stands for Cyclomatic Complexity
public class CC {

	//Counts Cyclomatic Complexity by predicates
	public static int calculateCyclomaticComplexity(String code) {
		int complexity = 0;
		String[] lines = code.split("\n");
		for (String line : lines) {
			line = line.trim();
			if (line.startsWith("if") || line.startsWith("else if") ||
					line.startsWith("while") || line.startsWith("for") ||
					line.startsWith("case") || line.startsWith("catch")) {
				complexity++;
			}
		}
		return complexity;
	}
}
