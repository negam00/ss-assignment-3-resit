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





//
//    public void findMethodCalls() {
//        ArrayList<String> methodCalls = new ArrayList<>();
//        Pattern getFunctionCalls = Pattern.compile("(([a-z0-9<>\\.\\[\\]])+?)\\s*(\\()(([a-z0-9<>\\-\\.\\,\\+\\/\\[\\\\\\]()\\s\"\'])*)(\\))", Pattern.MULTILINE | Pattern.COMMENTS);
//        Matcher functionCallMatcher = getFunctionCalls.matcher(code);
//        while (functionCallMatcher.find()) {
//            methodCalls.add(functionCallMatcher.group());
//        }
//        StringBuilder methodsFound = new StringBuilder();
//        for (String method : methodCalls) {
////            System.out.println(method);
//            methodsFound.append(method + "\n");
//        }
//        String allMethods = methodsFound.toString();
////        System.out.println("ALLMETHODS");
////        System.out.println(allMethods); (([a-z0-9<>\.\[\]])*?)\s*(\()
//
////        System.out.println("aaa");
//        System.out.println("First method call list" + allMethods);
//        Pattern getInnerFunctions = Pattern.compile("(\\(([a-z0-9<>\\-\\.\\,\\+\\/\\s\\[\\\\\\]()\"'])*?)\\s*(\\()(([a-z0-9<>\\-\\.\\,\\+\\/\\s*\\[\\\\\\]()\"'])*?)(\\))", Pattern.MULTILINE | Pattern.COMMENTS);
//        Matcher innerFunctionMatcher = getInnerFunctions.matcher(code);
//        ArrayList<String> secondCalls = new ArrayList<>();
//        System.out.println("------------------------------------");
//
//        while (innerFunctionMatcher.find()) {
//            String innerCall = innerFunctionMatcher.group();
//            Matcher getFunc = getFunctionCalls.matcher(innerCall);
//            while (getFunc.find()) {
//
//                secondCalls.add(getFunc.group());
//            }
//
//        }
//        System.out.println("Inner method calls list");
//        for (String secondCall : secondCalls) {
//            System.out.println(secondCall);
//
//        }
//        System.out.println("Total amount of methods found: " + (methodCalls.size() + secondCalls.size()));
//        System.out.println("------------------------------------");
//
//
//    }
//}
//

    public void alphabetCounter() {
        ArrayList counter = new ArrayList<>();
        ArrayList chars = new ArrayList<>();

        int total = 0;

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
            System.out.print("\n" + letterCount[l]); //todo weghalen?

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

        System.out.println("Totalletters: " + total); //todo weghalen?

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
//                if (englishFreq){
//
//                }
            }

            listCount++;

        }


//        System.out.println("Total amount of letters: "+totalLetters);
//        LanguageList langTemp = new LanguageList();
//        LanguageList langFinal = new LanguageList();
//
//      System.out.println("------------------------------------");
//
//        ArrayList<LanguageLetters> toRemove = new ArrayList<>();
//        //while there are languageList left
//        int listCounter = 0;
//        int langsize = languageList.size();
//        while (toRemove.size() < langsize) {
//            for (LanguageLetters language : languageList) {
//                if (language.getLetters().charAt(listCounter) != lettersInCountingOrder.charAt(listCounter)) {
//                    System.out.println("Next letter is: " + lettersInCountingOrder.charAt(listCounter));
//                    System.out.println("removing " + language.getLanguage());
//                    toRemove.add(language);
//                }
//            }
//            //Copies language list with entries inside of it into the final list
//            finalList.clear();
//            finalList.addAll(languageList);
//
//            languageList.removeAll(toRemove);
//
//            listCounter++;
//            if (languageList.size() < 1) {
//                System.out.println("------------------------------------");
//
//                System.out.println("Languages left: ");
//                for (LanguageLetters language : finalList) {
//                    System.out.println(language.getLanguage());
//                }
//                break;
//            }
//        }
//        System.out.println("------------------------------------");
//
//        System.out.println("Now printing all characters with their percentage of occurance");
//        for (int i = 0; i < characters.size(); i++) {
//
//            Double c = Double.valueOf(count.get(i));
//            Double total = Double.valueOf(totalLetters);
//            System.out.println(characters.get(i) + " occurred " + (c / total) * 100 + "%");
//        }
//        System.out.println("------------------------------------");


    }


}
//a = 97
//z = 122
