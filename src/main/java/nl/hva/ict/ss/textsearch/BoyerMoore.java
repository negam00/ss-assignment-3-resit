package nl.hva.ict.ss.textsearch;

public class BoyerMoore {
    private int[] right;
    private String pat;
    public static int count;


    //This method was taken from page 772 of the book : Algorithms 4th Edition by Robert Sedgewick, Kevin Wayne
    BoyerMoore(String pat) {
        // Compute skip table.
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;                // -1 for chars not in pattern
        for (int j = 0; j < M; j++) { // rightmost position for
            right[pat.charAt(j)] = j; //   chars in pattern
            System.out.println("letter " + pat.charAt(j) + " value = " + right[pat.charAt(j)]);
        }


    }

    public int search(String txt) {
        // Search for pattern in txt.
        int N = txt.length();
        int M = pat.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            // Does the pattern match the text at position i ?
            skip = 0;

            for (int j = M - 1; j >= 0; j--){
                System.out.println("amount skipped:  "+skip);
                System.out.println("text: " + pat.charAt(j) + " pattern: " + txt.charAt(i+j));
                System.out.println("index : " + i + " pattern index  " + j + "\n");
                count++;

                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) skip = 1;

                    System.out.println("amount skipped: "+ skip );
                    break;
            }

                }
            System.out.println("Amount of comparisons: " + count);
            if (skip == 0) return i; // found.
        }

        return N;
        // not found.
    }

    public static void main(String[] args) {

        String pat = "potato";
        String txt = "potattotapotatotopotat";

        BoyerMoore booyer = new BoyerMoore(pat);

        System.out.println("text:    " + txt);
        System.out.print("pattern: " + pat );
        System.out.print("\n");
        int offset = booyer.search(txt);
        System.out.print("\n");
        System.out.println("text:    " + txt);
        System.out.print("pattern: ");

        for (int i = 0; i < offset; i++)
            System.out.print(" ");
            System.out.println(pat);
    }


}




