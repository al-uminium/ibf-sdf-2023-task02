package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextHandler {
  private String filePath;

  public TextHandler(String filePath) {
    this.filePath = filePath;
  }

  public String getReadFileAsString() throws IOException {
    String readFile = "";
    String lines = null;
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      
      while (true) {
        lines = reader.readLine();
        if (lines != null) {
          readFile += " " + lines;
        } else {
          break;
        }
      }

      reader.close();

    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }

    return readFile;
  }

  public String formatString(String inputStr) {
    String formattedString = inputStr.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase();

    return formattedString;
  }
}
