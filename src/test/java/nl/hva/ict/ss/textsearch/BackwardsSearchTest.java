//package nl.hva.ict.ss.textsearch;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//import nl.hva.ict.ss.textsearch.BackwardsSearch;
//
//

//todo deze klasse uncommenten, gecomment ivm error
//todo missen standaard tests van school nog copy pastten uit yacobs bestand.

//public class BackwardsSearchTest {
//
//    @Test
//    public void searchPattern(){
//        BackwardsSearch backwardsSearch = new BackwardsSearch("potato");
//        assertEquals("potototototota".length(), nl.hva.ict.ss.textsearch.backwardsSearch.search("pototototototapotatotototo"));
//    }s
//
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
//
//}
