package nl.hva.ict.ss.textsearch;

public class BackwardsSearch {
    public static int comp;
    private final int radix;
    private int[] canBeSkipped;

    public BackwardsSearch(String pattern) {
        comp = 0;

        this.radix = 256;

        canBeSkipped = new int[radix];
        for (int k = 0; k < radix; k++) { // all characters equal to -1
            canBeSkipped[k] = -1;
        }

        int d = 0;
        for (int l = pattern.length() - 1; l >= 0; l--) { //
            canBeSkipped[pattern.charAt(l)] = d;
            System.out.println("Character: "+  pattern.charAt(l) + " Value: " + canBeSkipped[pattern.charAt(l)]);
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
        int lengthText = haystack.length();
        int lengthPattern = needle.length();
        int skip;

        // Start at the end instead of the beginning
        for (int index = lengthText - lengthPattern; 0 <= index; index -= skip) {
            skip = 0;

            for (int indexP = 0;  lengthPattern > indexP; indexP++) {
                System.out.println("Needle needs that needs to be found: " + needle.charAt(indexP) + " in haystack at "
                        + index+ ""+ haystack.charAt(index) + "\nText index: " + index + " Pattern index " + indexP);

                comp++;
                if (haystack.charAt(index + indexP) != needle.charAt(indexP)) {
                    skip = (lengthPattern - 1) - canBeSkipped[haystack.charAt(index + indexP)];

                    if (skip < 1) {
                        skip = 1;
                    }
                    break;
                } else {
                    System.out.println("\nA match was found!");
                }

            }

            System.out.println("Total comparisons " + comp);
            if (skip == 0) {
                // if found
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
