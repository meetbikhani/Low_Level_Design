package entities;

import java.util.List;

import strategy.SortedStrategy;
import strategy.strategy;

public class autoComplete {
    private trieNode trieNode;
    private strategy strategy;

    public autoComplete() {
        trieNode = new trieNode();
        strategy = new SortedStrategy();
    }

    public void addWord(String word) {
        trieNode.insertWord(word);
    }

    public List<String> getWords(String prefix) {
        List<String> ans = strategy.prefixStrategy(trieNode.getWords(prefix));

        if (ans.isEmpty()) {
            System.out.println("No words");
        } else {
            System.out.println("Words found for prefix '" + prefix + "':");
            for (String word : ans) {
                System.out.println(word);
            }
        }

        return ans;
    }

}