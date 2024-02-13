package main;

import java.util.HashMap;
import java.util.Map;

public class WordTree {
  private boolean isParent;
  private String word;
  private int count;
  private int frequency;
  private Map<String, WordTree> childrenNode;

  public WordTree(boolean isParent, String word) {
    this.isParent = isParent;
    this.word = word;
    this.count = 1;
    if (this.isParent) {
      this.childrenNode = new HashMap<>();
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

  public Map<String, WordTree> getchildrenNode() {
    return this.childrenNode;
  }

  public WordTree getChildrenNode(String word) {
    return this.childrenNode.get(word);
  }

  public void addChildrenNode(String word, WordTree newWordTree) {
    this.childrenNode.put(word, newWordTree);
  }

  public boolean containsChildNode (String word) {
    return this.childrenNode.containsKey(word);
  }

  public int getFrequency() {
    return this.frequency;
  }

  public void setFrequency() {
    int sum = 0;
    for (WordTree child: childrenNode.values()) {
      sum += child.getCount();
    }
    this.frequency = sum;
  }

  @Override
  public String toString() {
    return "WordTree [isParent=" + this.isParent + ", word=" + this.word + ", count=" + this.count + ", childrenNode=" + this.childrenNode
        + "]";
  } 
}
