package seng.qualitytester.Metrics;
//Created By Sina Sabetfar
//Email: C3382615@uon.edu.au
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FogIndexCalculator {

    private static final Pattern SENTENCE_PATTERN = Pattern.compile("[^.!?]+");
    private static final Pattern WORD_PATTERN = Pattern.compile("\\w+");

    /**
     * Calculates the Fog Index metric for a given code.
     *
     * @param text the code to analyze
     * @return the calculated Fog Index
     */
    public static double calculateFogIndex(String text) {
        int sentenceCount = countSentences(text);
        int wordCount = countWords(text);
        int complexWordCount = countComplexWords(text);

        double averageWordsPerSentence = (double) wordCount / sentenceCount;
        double percentageOfComplexWords = (double) complexWordCount / wordCount * 100;

        return 0.4 * (averageWordsPerSentence + percentageOfComplexWords);
    }

    /**
     * Counts the number of sentences in the code.
     *
     * @param text the code to count sentences in
     * @return the number of sentences
     */
    private static int countSentences(String text) {
        Matcher sentenceMatcher = SENTENCE_PATTERN.matcher(text);
        return (int) sentenceMatcher.results().count();
    }

    /**
     * Counts the number of words in the code.
     *
     * @param text the code to count words in
     * @return the number of words
     */
    private static int countWords(String text) {
        Matcher wordMatcher = WORD_PATTERN.matcher(text);
        return (int) wordMatcher.results().count();
    }

    /**
     * Counts the number of complex words in the code.
     *
     * @param text the code to count complex words in
     * @return the number of complex words
     */
    private static int countComplexWords(String text) {
        return (int) Arrays.stream(text.split("\\s+"))
                .filter(FogIndexCalculator::isComplexWord)
                .count();
    }

    /**
     * Checks if a word is considered complex based on its syllable count.
     *
     * @param word the word to check
     * @return true if the word is complex, false otherwise
     */
    private static boolean isComplexWord(String word) {
        int syllableCount = countSyllables(word);
        return syllableCount > 2;
    }

    /**
     * Counts the number of syllables in a word.
     *
     * @param word the word to count syllables in
     * @return the number of syllables
     */
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
