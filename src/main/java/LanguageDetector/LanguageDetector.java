package LanguageDetector;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageDetector {
    private String content; // Once an instance is created this will hold the complete content of the file.
    private String code;
    private String comments;
    private int letterCount[];

    private String javaDoc;
    private ArrayList<String> englishFreq = new ArrayList<>();
    private ArrayList<String> remove = new ArrayList<>();


    public LanguageDetector(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z"); // EOF marker
        content = sc.next();
        findJavaDoc();
        seperateCode();
        findNotMethodCall();
    }


    public void findJavaDoc() {
        code = content.toLowerCase();
        StringBuilder generateText = new StringBuilder();
        Pattern findJavaDoc = Pattern.compile("(?s)\\/\\*.*?\\*\\/", Pattern.MULTILINE);
        Matcher docMatcher = findJavaDoc.matcher(code);


        while (docMatcher.find() == true) {
            generateText.append(docMatcher.group());
        }

        comments = generateText.toString();
        System.out.println("------------------JAVADOC-------------------------");
        System.out.println(comments);
        System.out.println("");
    }



    public void seperateCode() {
        code = content.toLowerCase();
        StringBuilder generateText = new StringBuilder();
        Pattern findJavaDoc = Pattern.compile("(?s)\\/\\*.*?\\*\\/", Pattern.MULTILINE);
        Matcher docMatcher = findJavaDoc.matcher(code);
        Matcher codeMatcher = findJavaDoc.matcher(code);

        while (codeMatcher.find() == true) {
            code = docMatcher.replaceAll("");
        }

        System.out.println("--------------------CODE STARTS HERE---------------------------");
        System.out.println(code);
    }


    public void findNotMethodCall() {
        Pattern deleteNonCall = Pattern.compile("(void|public|static|while|private|for|if|else if).*?((\\()|(\\{))", Pattern.MULTILINE);
        Matcher matchNonCall = deleteNonCall.matcher(code);


        while (matchNonCall.find() == true) {
            code = matchNonCall.replaceAll("");
        }
    }


    public void methodCallFinder(){
        ArrayList<String> methodCallList = new ArrayList<>();
        StringBuilder methodFoundBuilder = new StringBuilder();
        ArrayList<String> secondaryFunc = new ArrayList<>();

        Pattern findFunctionCalls = Pattern.compile("(([a-z0-9<>.\\[\\]])+?)\\s*(\\()(([a-z0-9<>\\-.,+/\\[\\\\\\]()\\s\"\'])*)(\\))", Pattern.MULTILINE) ;
        Matcher matchFunctionCalls = findFunctionCalls.matcher(code);

        while (matchFunctionCalls.find() == true){
            methodCallList.add(matchFunctionCalls.group());
        }
        for (int i = 0; i < methodCallList.size(); i++) {
            methodFoundBuilder.append(methodCallList.get(i));
        }

        Pattern secondaryFunctionPat = Pattern.compile("(\\(([a-z0-9<>\\-.,+/\\s\\[\\\\\\]()\"'])*?)\\s*(\\()(([a-z0-9<>\\-.,+/\\s*\\[\\\\\\]()\"'])*?)(\\))", Pattern.MULTILINE );
        Matcher secondaryFuncMatcher = secondaryFunctionPat.matcher(code);


        while(secondaryFuncMatcher.find() == true){
            String secondaryFuncString =  secondaryFuncMatcher.group();
            Matcher secondFuncMatching = findFunctionCalls.matcher(secondaryFuncString);

            while(secondFuncMatching.find()==true){
                secondaryFunc.add(secondFuncMatching.group());
            }
        }
        System.out.println("found method list: \n");

        for (int i = 0; i < methodCallList.size(); i++) {
            System.out.println(methodCallList.get(i) );
        }

        for (int i = 0; i < secondaryFunc.size(); i++) {
            System.out.println(secondaryFunc.get(i) );
        }




    }

    public void alphabetCounter() {
        ArrayList language = new ArrayList<>();
        ArrayList chars = new ArrayList<>();

        int total = 0;

        // see freq the letter is used in total
        String lettersOrderedToCount = "";

        letterCount = new int[123]; // only 97 up to 122 will be used

        for (int k = 0; k < letterCount.length; k++) {
            letterCount[k] = 0;
        }

        for (int l = 'a'; l < 'z'; l++) {
            Pattern pattern = Pattern.compile("" + (char) l, Pattern.MULTILINE | Pattern.COMMENTS);
            Matcher matcher = pattern.matcher(javaDoc);

            while (matcher.find()) {
                letterCount[l]++;
            }
            System.out.print("\n" + letterCount[l]);

        }


        while (true) {
            int highestNumber = 0;
            int highestIndex = 0;

            for (int j = 0; j < 123; j++) {
                if (highestNumber < letterCount[j]) {
                    highestIndex = j;
                    highestNumber = letterCount[j];
                }
            }

            if (0 < highestNumber) {
                total += highestNumber;
                chars.add((char) highestIndex);
                lettersOrderedToCount += (char) highestIndex;

                letterCount[highestIndex] = 0;
            } else {
                break;
            }
        }

        System.out.println("------------------------------------");

        System.out.println("Total letters: " + total);



        englishFreq.add("e");
        englishFreq.add("t");
        englishFreq.add("a");
        englishFreq.add("o");
        englishFreq.add("i");
        englishFreq.add("n");
        englishFreq.add("s");
        englishFreq.add("h");
        englishFreq.add("r");
        englishFreq.add("d");
        englishFreq.add("l");
        englishFreq.add("c");
        englishFreq.add("u");
        englishFreq.add("m");
        englishFreq.add("w");
        englishFreq.add("f");
        englishFreq.add("g");
        englishFreq.add("y");
        englishFreq.add("p");
        englishFreq.add("b");
        englishFreq.add("v");
        englishFreq.add("k");
        englishFreq.add("j");
        englishFreq.add("x");
        englishFreq.add("q");
        englishFreq.add("z");
        englishFreq.add("ü");
        englishFreq.add("ä");
        englishFreq.add("ö");
        englishFreq.add("ß");
        englishFreq.add("à");
        englishFreq.add("â");
        englishFreq.add("á");
        englishFreq.add("å");
        englishFreq.add("ã");



        int listCount = 0;
        int langsize = englishFreq.size();


        while(remove.size() > langsize ){

            for (int j = 0;  j < langsize; j++) {
                if (englishFreq.get(listCount) != language.get(listCount)){
                    System.out.printf("Javadoc is not written in English. Too bad.");
                } else {
                    System.out.println("\n\n\n\n\n\n\n\n\nThe javadoc is written in english, luckily.");
                }
            }

            listCount++;

        }


    }


}
