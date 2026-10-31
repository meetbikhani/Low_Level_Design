import entities.autoComplete;

public class autoCompleteDemo {
    public static void main(String[] args) {
        autoComplete ac = new autoComplete();
        ac.addWord("apple");
        ac.addWord("apricot");
        ac.addWord("apzricot");
        ac.addWord("banana");
        ac.addWord("bananaaaaa");
        ac.addWord("bandana");
        ac.getWords("ap");
        ac.getWords("banana");
        ac.getWords("ca");
    }
}