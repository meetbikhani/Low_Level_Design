package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class trieNode {

    private boolean isEndOfWord;
    Map<Character, trieNode> children;

    public trieNode() {
        this.isEndOfWord = false;
        this.children = new HashMap<>();
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public Map<Character, trieNode> getChildren() {
        return children;
    }

    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }

    public void insertWord(String word) {
        trieNode current = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            current.children.putIfAbsent(c, new trieNode());
            current = current.children.get(c);
        }
        current.isEndOfWord = true;     
    }
public trieNode checkPrefix(String prefix){
        trieNode current = this;
        for(int i = 0 ; i < prefix.length() ; i++){
            char c = prefix.charAt(i);
            if(current.children.containsKey(c)){
                current = current.children.get(c);
            }else{
                return null;
            }
        }
        return current;
    }

    private void collectWords(trieNode node, String currentWord, List<String> suggestions) {
        if (node.isEndOfWord()) {
            suggestions.add(currentWord);
        }

        for (Map.Entry<Character, trieNode> entry : node.getChildren().entrySet()) {
            collectWords(entry.getValue(), currentWord + entry.getKey(), suggestions);
        }
    }
    

    public List<String> getWords(String prefix){
        List<String>ans = new ArrayList<>();
        trieNode current = checkPrefix(prefix);
        if(current==null){
            System.out.println("Prefix not found");
            return ans;
        }
        collectWords(current, prefix, ans);
        return ans;
    }

}