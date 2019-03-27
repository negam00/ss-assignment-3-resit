package nl.hva.ict.ss.textsearch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import nl.hva.ict.ss.textsearch.BackwardsSearch;



/* todo geuncomment en heb de bovenste test even aangepast naar hoe het zou moeten zijn,
 de anderen moeten nog aangepast worden*/
public class BackwardsSearchTest {

    @Test
    public void searchPattern(){
        BackwardsSearch backwardsSearch = new BackwardsSearch("potato");
        assertEquals("potototototota".length(), backwardsSearch.findLocation("potato","pototototototapotatotototo"));
    }

//
//    @Test
//    public void searchEndOfWordPattern(){
//        BackwardsSearch backwardsSearch = new BackwardsSearch("pattern");
//        assertEquals("whereisthehidden".length(),nl.hva.ict.ss.textsearch.backwardsSearch.search("whereisthehiddenpattern"));
//    }
//
//
//    @Test
//    public void searchMiddleOfWordPattern(){
//        BackwardsSearch backwardsSearch = new BackwardsSearch("pattern");
//        assertEquals("whereistn".length(),nl.hva.ict.ss.textsearch.backwardsSearch.search("whereistnpatternhehidden"));
//    }
//
//
//    @Test
//    public void searchStartOfWordPattern(){
//        BackwardsSearch backwardsSearch = new BackwardsSearch("pattern");
//        assertEquals("whereistn".length(),nl.hva.ict.ss.textsearch.backwardsSearch.search("whereistnpatternhehidden"));
//    }
//
//    @Test
//    public void searchHugeWordPattern(){
//        BackwardsSearch backwardsSearch = new BackwardsSearch("ditiseenlangpattern");
//        assertEquals("ditiseenlangditiseenlangpatditiseenlangpatditpatternlangpatternditpatterniseenpatter".length(),backwardsSearch.search("ditiseenlangditiseenlangpatditiseenlangpatditpatternlangpatternditpatterniseenpatterditiseenlangpatternditisditiseenlangditiseenlpattern"));
//    }

}
