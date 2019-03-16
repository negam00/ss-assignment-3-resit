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
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println();
        Pattern deleteNonCall = Pattern.compile("(void|public|static|while|private|for|if|else if).*?((\\()|(\\{))", Pattern.MULTILINE);
        Matcher matchNonCall = deleteNonCall.matcher(code);


        System.out.println("test 1:  " + code);
        while (matchNonCall.find() == true) {
            code = matchNonCall.replaceAll("");
        }
        System.out.println("test 2---------------------------: " + code);

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


//        }
//        System.out.println("------------------------------------");
//        System.out.println("Total amount of letters: "+totalLetters);
//        LanguageList langTemp = new LanguageList();
//        LanguageList langFinal = new LanguageList();
//
//        System.out.println("Total amount of languages to compare: "+langTemp.languageLettersList.size());
//        ArrayList<LanguageLetters> languageList = langTemp.languageLettersList;
//        ArrayList<LanguageLetters> finalList = langFinal.languageLettersList;
//        System.out.println("------------------------------------");
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
//
//    }
    }


}
//a = 97
//z = 122
