import java.util.*;
public class lruService{
    int size;
    Node<Integer, Integer> head;
    Node<Integer, Integer> tail;
    Map<Integer, Node<Integer, Integer>> map;

    public lruService(int size){
        this.size = size;
        this.head = null;
        this.tail = null;
        this.map = new HashMap<Integer, Node<Integer, Integer>>();
    }

    public void put(Integer key, Integer value){
        if(this.head == null){
            this.head = new Node<Integer, Integer>(key, value);
            this.tail = this.head;
            this.map.put(key, this.head);
        }
        else{
            if(this.map.size() == this.size){
                Node<Integer, Integer> oldNode = this.head;
                this.head = this.head.next;
                this.head.prev = null;
                this.map.remove(oldNode.key);
            }
            Node<Integer, Integer> newNode = new Node<Integer, Integer>(key, value);
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
            this.map.put(key, newNode);
        }
    }

    public Integer get(Integer key){
        if(this.map.containsKey(key)){
            Node<Integer, Integer> node = this.map.get(key);
            if(node.prev == null){
                this.head = node.next;
                this.head.prev = null;
            }
            else{
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            this.tail.next = node;
            node.prev = this.tail;
            node.next = null;
            this.tail = node;
            return node.value;
        }
        else{
            return null;
        }
    }

    public void print(){
        Node<Integer, Integer> node = this.head;
        while(node != null){
            System.out.println(node.key + ": " + node.value);
            node = node.next;
        }
    }
}