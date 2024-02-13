package main;

import java.util.HashMap;
import java.util.Map;

public class WordTree {
  private boolean isParent;
  private String word;
  private int count;
  private int frequency;
  private Map<String, WordTree> childrenNodes;

  public WordTree(boolean isParent, String word) {
    this.isParent = isParent;
    this.word = word;
    this.count = 1;
    if (this.isParent) {
      this.childrenNodes = new HashMap<>();
      this.frequency = 0;
    }
  }

  public boolean isParent() {
    return isParent;
  }

  public String getWord() {
    return word;
  }

  public int getCount() {
    return count;
  }

  public void addCount() {
    this.count++;
  }

  public Map<String, WordTree> getChildrenNodes() {
    return this.childrenNodes;
  }

  public WordTree getChildrenNode(String word) {
    return this.childrenNodes.get(word);
  }

  public void addChildrenNodes(String word, WordTree newWordTree) {
    this.childrenNodes.put(word, newWordTree);
  }

  public boolean containsChildNode (String word) {
    return this.childrenNodes.containsKey(word);
  }

  

  public int getFrequency() {
    return this.frequency;
  }

  public void setFrequency() {
    int sum = 0;
    for (WordTree child: childrenNodes.values()) {
      sum += child.getCount();
    }
    this.frequency = sum;
  }

  @Override
  public String toString() {
    return "WordTree [isParent=" + this.isParent + ", word=" + this.word + ", count=" + this.count + ", childrenNodes=" + this.childrenNodes
        + "]";
  } 
}
