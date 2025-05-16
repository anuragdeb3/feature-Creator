package utility;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

public class descriptionParser {
    private final SentenceDetectorME sentenceDetector;
    private final TokenizerME tokenizer;

    public descriptionParser() throws Exception {
        try (InputStream sentenceModelIn = getClass().getResourceAsStream("/models/opennlp-en-ud-ewt-sentence-1.2-2.5.0.bin");
             InputStream tokenizerModelIn = getClass().getResourceAsStream("/models/opennlp-en-ud-ewt-tokens-1.2-2.5.0.bin")) {
            sentenceDetector = new SentenceDetectorME(new SentenceModel(sentenceModelIn));
            tokenizer = new TokenizerME(new TokenizerModel(tokenizerModelIn));
        }
    }

    public List<String> parseToSteps(String description) {
        List<String> steps = new ArrayList<>();
        if (description == null || description.isBlank()) return steps;

        String[] sentences = sentenceDetector.sentDetect(description);
        boolean hasGiven = false;

        for (String sentence : sentences) {
            String s = sentence.trim().toLowerCase();
            if ((s.startsWith("as a") || s.contains("logged in") || s.contains("starting")) && !hasGiven) {
                steps.add("Given " + sentence.trim());
                hasGiven = true;
            } else if (s.startsWith("when") || s.contains("click") || s.contains("submit")) {
                steps.add("When " + sentence.trim());
            } else if (s.startsWith("then") || s.contains("should") || s.contains("expect")) {
                steps.add("Then " + sentence.trim());
            }
        }

        if (!hasGiven && !sentences[0].isBlank()) {
            steps.add(0, "Given " + sentences[0].trim());
        }

        if (steps.isEmpty()) {
            steps.add("Given some initial condition");
            steps.add("When an action occurs");
            steps.add("Then expect a result");
        }

        return steps;
    }
}
