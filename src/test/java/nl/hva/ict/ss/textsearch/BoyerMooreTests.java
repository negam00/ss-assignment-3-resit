package nl.hva.ict.ss.textsearch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class BoyerMooreTests {

    @Test
    public void searchPattern(){
        BoyerMoore boyerMoore = new BoyerMoore("potato");
        assertEquals("potototototota".length(), boyerMoore.search("pototototototapotatotototo"));
    }


    @Test
    public void searchEndOfWordPattern(){
        BoyerMoore boyerMoore = new BoyerMoore("pattern");
        assertEquals("whereisthehidden".length(),boyerMoore.search("whereisthehiddenpattern"));
    }


    @Test
    public void searchMiddleOfWordPattern(){
        BoyerMoore boyerMoore = new BoyerMoore("pattern");
        assertEquals("whereistn".length(),boyerMoore.search("whereistnpatternhehidden"));
    }


    @Test
    public void searchStartOfWordPattern(){
        BoyerMoore boyerMoore = new BoyerMoore("pattern");
        assertEquals("whereistn".length(),boyerMoore.search("whereistnpatternhehidden"));
    }

    @Test
    public void searchHugeWordPattern(){
        BoyerMoore boyerMoore = new BoyerMoore("ditiseenlangpattern");
        assertEquals("ditiseenlangditiseenlangpatditiseenlangpatditpatternlangpatternditpatterniseenpatter".length(),boyerMoore.search("ditiseenlangditiseenlangpatditiseenlangpatditpatternlangpatternditpatterniseenpatterditiseenlangpatternditisditiseenlangditiseenlpattern"));
    }

}
