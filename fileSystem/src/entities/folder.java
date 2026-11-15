package entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class folder extends node{

    Map<String,node>children;
    
    public folder(String name, folder parent, Boolean isFile) {
        super(name, parent,isFile);
        this.children=new HashMap<>();
    }

    public void addChild(node node) {
        children.put(node.getName(), node);
    }

    public node getChild(String name) {
        return children.get(name);
    }

    public void removeChild(String name) {
        children.remove(name);
    }

    public Collection<node> getChildren() {
        return children.values();
    }

    public boolean contains(String name) {
        return children.containsKey(name);
    }

    public boolean isEmpty() {
        return children.isEmpty();
    }

}