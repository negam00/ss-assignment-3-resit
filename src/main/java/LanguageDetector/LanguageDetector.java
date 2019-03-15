package LanguageDetector;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.MULTILINE;

public class LanguageDetector {
    private String content; // Once an instance is created this will hold the complete content of the file.
    private String code;
    private String docs;
    private int letterCount[];

    private String javaDoc;


    public LanguageDetector(InputStream input) {
        Scanner sc = new Scanner(input);
        sc.useDelimiter("\\Z"); // EOF marker
        content = sc.next();
    }


/*

 */
    public void findJavaDoc() {
        content = content.toLowerCase();
        StringBuilder holdJavaDoc = new StringBuilder();
//        Pattern findJavaDoc = Pattern.compile("^\s*   ");

        //can start with blankspace (needs to be optional?)
        //can start with /*, can be unlimited amount of *** following the /
        //If the rule starts with *, its still comment but this can be ignored
        //because the commenent only ends anyway untill it reaches the */
        //therefore there needs to be unlimited text inbetween option.
        //the end can be ****************************/ but only meeting the */ requirmenet should be enough
        //because theres a */ at the end only anyway.
        //Thus ends with */

    }


        public void separateDocsFromCode() {

        code = content.toLowerCase();
        StringBuilder textHolder = new StringBuilder();
        Pattern findComments = Pattern.compile("(?s)/\\*(.)*?\\*/", MULTILINE | Pattern.COMMENTS);


        Matcher matcher = findComments.matcher(code);
        Matcher matcher2 = findComments.matcher(code);
        while (matcher.find()) {
            textHolder.append(matcher.group());
        }
        while (matcher2.find()) {
            code = matcher.replaceAll("");
        }
        docs = textHolder.toString();
        System.out.println("DOCS:");
        System.out.println(docs);
        System.out.println("-----------------------------");
        System.out.println("CODE:");
        System.out.println(code);
        System.out.println("-----------------------------");

    }



    // Put your own code here and integrate it with the test class.
}
