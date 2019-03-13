package nl.hva.ict.ss.textsearch;

public class BackwardsSearch {

    /**
     * Returns index of the right most location where <code>needle</code> occurs within <code>haystack</code>. Searching
     * starts at the right end side of the text (<code>haystack</code>) and proceeds to the first character (left side).
     *
     * @param needle the text to search for.
     * @param haystack the text which might contain the <code>needle</code>.
     * @return -1 if the <code>needle</code> is not found and otherwise the left most index of the first
     * character of the <code>needle</code>.
     */

    //todo change name of searchcomparisons

    private final int R;     // radix
    private int[] skippedArray;     // Array that saves the skipped characters
    public static int searchComparisons;


    public BackwardsSearch(String pat) {
        //initialize radix and searchcomparsions
        this.R = 256;
        searchComparisons = 0;

        skippedArray = new int[R];
        int i = 0;


        while (i < R) {
            skippedArray[i] = -1;
            i++;
        }

        int counter = 0;
        int t = pat.length()-1;
        while(0 <= t){
        skippedArray[pat.charAt(t)] = counter;
            System.out.println("Character:  " + pat.charAt(t) + " value: " + skippedArray[pat.charAt(t)]);
            counter++;
            t--;
        }



    }


        //todo vanaf hier
    int findLocation(String needle, String haystack) {
        String pattern = needle;
        String text = haystack;
        int textlength = text.length();
        int patternLength = pattern.length();
        int amountToSkip = 0;
        //Begin aan het einde ipv begin                                    Amount to skip is subtracted ipv added
        for (int textIndex = textlength - patternLength; textIndex >= 0; textIndex -= amountToSkip) {
            amountToSkip = 0;
            //Reverse here
//

            for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
                System.out.println("Needle  " + pattern.charAt(patternIndex) + " Haystack   " + text.charAt(textIndex));
                System.out.println("Text index: " + textIndex + " Pattern index " + patternIndex);
                searchComparisons++;

                if (pattern.charAt(patternIndex) != text.charAt(textIndex+patternIndex)) {
                    amountToSkip =  (patternLength-1) - skippedArray[text.charAt(textIndex + patternIndex)];
                    if (amountToSkip < 1) amountToSkip = 1;
                    System.out.println("Skip amount: "+ amountToSkip );
                    break;
                } else {
                    System.out.println("MATCH FOUND--------");

                }
            }
            System.out.println("amount of comparisons done is: "+searchComparisons);
            if (amountToSkip == 0) return textIndex;    // found
        }

        return -1;                       // not found
    }

    /**
     * Returns the number of character compares that where performed during the last search.
     *
     * @return the number of character comparisons during the last search.
     */
    int getComparisonsForLastSearch() {
        return 0;
    }
}


