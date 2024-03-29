package main;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        // initiating folder
        File folder = null;
        File[] listOfFiles = null;

        try {
            folder = new File(args[0]);
            listOfFiles = folder.listFiles();
        } catch (Exception e) {
            System.out.println("Directory not given.");
        }

        // going through the folder. 
        for (File file: listOfFiles) {
            if (file.isFile()) {
                System.out.println("\n");
                System.out.println("-------------------------------------------------------------------------------------");
                System.out.println("File: " + file.getName());
                System.out.println("-------------------------------------------------------------------------------------");
                String filePath = args[0] + File.separator + file.getName();
                String text = getTextFromFile(filePath);
                Map<String, WordTree> wordTreeMap = parseString(text);
                for (WordTree wordTree: wordTreeMap.values()) {
                    System.out.println(wordTree.getWord());
                    for (WordTree childWord: wordTree.getChildrenNodes().values()) {
                        // make it print int as shown in the assessment
                        if (childWord.getCount()%wordTree.getFrequency() == 0) {
                            System.out.println("\t" + childWord.getWord() + " " + childWord.getCount()/wordTree.getFrequency());
                        } else {
                            System.out.println("\t" + childWord.getWord() + " " + ((float) childWord.getCount())/wordTree.getFrequency());
                        }
                    }
                }
            }
        }
    }

    public static String getTextFromFile(String filePath) throws IOException {
        TextHandler textHandler = new TextHandler(filePath);
        String text = textHandler.getReadFileAsString();
        text = textHandler.formatString(text);
        return text;
    }

    public static Map<String, WordTree> parseString(String str) {
        // This function parses a string by putting it in a while loop that breaks if it reaches end of string. 
        // Within the while loop, there's another while loop to continuously add 2 words into a queue.
        // If the queue reaches the required amount (i.e., 2), it will run the main logic of finding for the word in the hashmap etc, and then break once done.
        // If word exists, check for child word. If child word doesn't exist, create, if it exists, add count. If word doesn't exist, create word.
        // If not, it will continue looping until it has enough in the queue to run main logic. 
        // Exit criteria is the same as main while loop (i.e., end of string).

        Queue<String> wordQueue = new LinkedList<>(); 
        Scanner scan = new Scanner(str);
        Map<String, WordTree> wordTreeMap = new LinkedHashMap<>(); 
        String word = "";
        
        while (true) {
            WordTree wordTreeParent = null;
            WordTree wordTreeChild = null;
            while (true) {
                if (!scan.hasNext()) {
                    break;
                }
                if (wordQueue.size() == 2) {
                    String parentWord = wordQueue.remove();
                    String childWord = wordQueue.peek();


                    if (wordTreeMap.isEmpty() || !wordTreeMap.containsKey(parentWord)) {
                        wordTreeParent = new WordTree(true, parentWord);
                        wordTreeChild = new WordTree(false, childWord);
                        wordTreeParent.addChildrenNode(childWord, wordTreeChild);
                        wordTreeMap.put(parentWord, wordTreeParent);
                    } else {
                        wordTreeParent = wordTreeMap.get(parentWord);
                        if (wordTreeParent.containsChildNode(childWord)) {
                            wordTreeChild = wordTreeParent.getChildrenNode(childWord);
                            wordTreeChild.addCount();
                        } else {
                            wordTreeChild = new WordTree(false, childWord);
                            wordTreeParent.addChildrenNode(childWord, wordTreeChild);
                        }
                    }
                    wordTreeParent.setFrequency();
                    break;
                }
                word = scan.next();
                wordQueue.add(word);
            }
            if (!scan.hasNext()) {
                break;
            }
        }
        scan.close();

        return wordTreeMap;
    }
}
