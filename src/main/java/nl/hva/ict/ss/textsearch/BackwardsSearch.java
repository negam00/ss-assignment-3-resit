package nl.hva.ict.ss.textsearch;

public class BackwardsSearch {
    public static int comp;
    private final int radix;
    private int[] skipped;

    public BackwardsSearch(String pattern) {
        comp = 0;

        this.radix = 256; //todo kijken of dit getal veranderd kan worden

        skipped = new int[radix];
        for (int k = 0; k < radix; k++) {
            skipped[k] = -1;
        }

        int d = 0; // todo andere naam geven of niet
        for (int l = pattern.length() - 1; l >= 0; l--) {
            skipped[pattern.charAt(l)] = d;
            System.out.println("Character: "+  pattern.charAt(l) + " Value: " + skipped[pattern.charAt(l)]);
            d++;
        }
    }

    /**
     * Returns index of the right most location where <code>needle</code> occurs within <code>haystack</code>. Searching
     * starts at the right end side of the text (<code>haystack</code>) and proceeds to the first character (left side).
     *
     * @param needle   the text to search for.
     * @param haystack the text which might contain the <code>needle</code>.
     * @return -1 if the <code>needle</code> is not found and otherwise the left most index of the first
     * character of the <code>needle</code>.
     */
    int findLocation(String needle, String haystack) {
        //todo even nnamen aanpassen
        String pattern = needle;
        String text = haystack;

        int lengthText = text.length();
        int lengthPattern = pattern.length();
        int skip = 0;

        // Start at the end instead of the beginning
        for (int index = lengthText - lengthPattern; index >= 0; index -= skip) {
            skip = 0;

            for (int indexP = 0; indexP < lengthPattern; lengthPattern++) { // todo even souts weghalen of aanpassen
                System.out.println("Needle  " + pattern.charAt(patternIndex) + " Haystack   " + text.charAt(textIndex));
                System.out.println("Text index: " + textIndex + " Pattern index " + patternIndex);

                comp++;
                if (pattern.charAt(indexP) != text.charAt(index + indexP)) {
                    skip = (lengthPattern - 1) - skipped[text.charAt(index + indexP)];

                    if (skip < 1) {
                        skip = 1;
                    }

                } else {
                    System.out.println("A match was found!");
                }


            }

            System.out.println("Total comparisons: " + comp);
            if (skip == 0) {
                return index;
            }
        }


        return -1;
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
