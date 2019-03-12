package nl.hva.ict.ss.textsearch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import nl.hva.ict.ss.textsearch.BoyerMoore;


public class BoyerMooreTests {

    @Test
    public void searchPattern(){
        BoyerMoore boyerMoore = new BoyerMoore("potato");
        assertEquals("potototototota".length(), boyerMoore.search("pototototototapotatotototo"));
    }


    @Test
    public void searchEndOfWordPattern(){
        BoyerMoore boy = new BoyerMoore("pattern");
        assertEquals("whereisthehidden".length(),boy.search("whereisthehiddenpattern"));
    }


    @Test
    public void searchMiddleOfWordPattern(){
        BoyerMoore boy = new BoyerMoore("pattern");
        assertEquals("whereistn".length(),boy.search("whereistnpatternhehidden"));
    }


    @Test
    public void searchStartOfWordPattern(){
        BoyerMoore boy = new BoyerMoore("pattern");
        assertEquals("whereistn".length(),boy.search("whereistnpatternhehidden"));
    }

    @Test
    public void searchHugeWordPattern(){
        BoyerMoore boy = new BoyerMoore("ditiseenlangpattern");
        assertEquals("ditiseenlangditiseenlangpatditiseenlangpatditpatternlangpatternditpatterniseenpatter".length(),boy.search("ditiseenlangditiseenlangpatditiseenlangpatditpatternlangpatternditpatterniseenpatterditiseenlangpatternditisditiseenlangditiseenlpattern"));
    }

}
