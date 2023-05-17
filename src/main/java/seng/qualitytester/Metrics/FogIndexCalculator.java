package seng.qualitytester.Metrics;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FogIndexCalculator {

    private static final Pattern SENTENCE_PATTERN = Pattern.compile("[^.!?]+");
    private static final Pattern WORD_PATTERN = Pattern.compile("\\w+");

    public static double calculateFogIndex(String text) {
        int sentenceCount = countSentences(text);
        int wordCount = countWords(text);
        int complexWordCount = countComplexWords(text);

        double averageWordsPerSentence = (double) wordCount / sentenceCount;
        double percentageOfComplexWords = (double) complexWordCount / wordCount * 100;

        return 0.4 * (averageWordsPerSentence + percentageOfComplexWords);
    }

    private static int countSentences(String text) {
        Matcher sentenceMatcher = SENTENCE_PATTERN.matcher(text);
        return (int) sentenceMatcher.results().count();
    }

    private static int countWords(String text) {
        Matcher wordMatcher = WORD_PATTERN.matcher(text);
        return (int) wordMatcher.results().count();
    }

    private static int countComplexWords(String text) {
        return (int) Arrays.stream(text.split("\\s+"))
                .filter(FogIndexCalculator::isComplexWord)
                .count();
    }

    private static boolean isComplexWord(String word) {
        int syllableCount = countSyllables(word);
        return syllableCount > 2;
    }

    private static int countSyllables(String word) {
        String lowerCaseWord = word.toLowerCase();
        String[] vowels = {"a", "e", "i", "o", "u", "y"};
        int syllableCount = 0;

        for (String vowel : vowels) {
            Pattern pattern = Pattern.compile(vowel);
            Matcher matcher = pattern.matcher(lowerCaseWord);
            syllableCount += (int) matcher.results().count();
        }

        if (lowerCaseWord.endsWith("e")) {
            syllableCount--;
        }

        if (syllableCount == 0) {
            syllableCount = 1;
        }

        return syllableCount;
    }
}
