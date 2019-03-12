package nl.hva.ict.ss.textsearch;

import LanguageDetector.LanguageDetector;
import org.junit.Before;

public class LanguageDetectorTest {
    private LanguageDetector detector;

    @Before
    public void setup() {
        detector = new LanguageDetector(getClass().getResourceAsStream("/.idea/Huffman.java"));
    }

    // Add your tests here. They are allowed to NOT use assertXxxx... :-)
}